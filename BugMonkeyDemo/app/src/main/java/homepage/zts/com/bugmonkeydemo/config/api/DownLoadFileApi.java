package homepage.zts.com.bugmonkeydemo.config.api;


import java.util.Map;

import homepage.zts.com.bugmonkeydemo.config.NetConfig;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by BugMonkey on 2018/2/3.
 */

public interface DownLoadFileApi {

    @POST(NetConfig.DOWNLOAD_FILE)
    Observable<Response> downLoadFile(@QueryMap Map<String, String> map, Subscriber subscriber);


    @POST(NetConfig.DOWNLOAD_FILE)
    Call downLoadFile2(@QueryMap Map<String, String> map);

}
