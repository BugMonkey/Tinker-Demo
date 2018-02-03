package homepage.zts.com.bugmonkeydemo.model.DateSource;

import java.util.Map;
import java.util.Observable;

import homepage.zts.com.bugmonkeydemo.config.api.DownLoadFileApi;
import homepage.zts.com.bugmonkeydemo.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by BugMonkey on 2018/2/3.
 */

public class DownLoadDataSource {
    DownLoadFileApi downLoadFileApi;

    public DownLoadDataSource() {
        downLoadFileApi = RetrofitUtils.CreateRetrofitService(DownLoadFileApi.class);
    }


    public void DownLoadFile2(Map<String, String> stringStringMap) {
        Call call = downLoadFileApi.downLoadFile2(stringStringMap);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }


    public Observable  DownLoadFile(Map<String, String> stringStringMap , Subscriber<Response> subscriber){
        return     downLoadFileApi.downLoadFile(stringStringMap,subscriber);
    }
}
