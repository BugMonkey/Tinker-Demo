package homepage.zts.com.bugmonkeydemo.moudle.main;

/**
 * Created by BugMonkey on 2018/2/26.
 */

public class DownLoadContact {
    public interface IDownLoadView{
        void onDownLoadStart();
        void onDownLoadSucess(String path);
        void onDownLoadFailed(String error);
        void onProgress(long progress);
    }

    public interface IDownLoadPresenter{
        void downLoadfile(String url,String path);
    }

    public interface  IDownLoadFileBiz{
        void downLoadfile(String url,String path);
    }

    public interface  onFileDownLoadCallBack{
        void onStart();
        void onError(String error);
        void onProgress(long progress);
        void onSucess(String path);

    }
}
