package homepage.zts.com.bugmonkeydemo.config.api;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by BugMonkey on 2018/2/3.
 */

public interface DownLoadFileApi {


    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@Url String url);

}
