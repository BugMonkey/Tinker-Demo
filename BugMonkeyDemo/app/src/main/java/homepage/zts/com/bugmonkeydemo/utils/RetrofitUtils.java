package homepage.zts.com.bugmonkeydemo.utils;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import homepage.zts.com.bugmonkeydemo.config.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by BugMonkey on 2018/2/3.
 */

public class RetrofitUtils {
    private static OkHttpClient httpClient;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(logging).build();
        }else {
            //不在debug时不输出log
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(logging).build();
        }

    }

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    /**
     * 创建retrofit   Service
     * @param mClass
     * @param <T>
     * @return
     */
    public static <T> T CreateRetrofitService(Class<T> mClass) {
        return RetrofitUtils.createRetrofit().create(mClass);
    }

}
