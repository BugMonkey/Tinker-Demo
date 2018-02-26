package homepage.zts.com.bugmonkeydemo.moudle.main.view;

import android.content.Context;

import com.orhanobut.logger.Logger;

import homepage.zts.com.bugmonkeydemo.model.DateSource.DownLoadDataSource;
import homepage.zts.com.bugmonkeydemo.moudle.main.DownLoadContact;
import rx.Subscriber;

/**
 * Created by BugMonkey on 2018/2/26.
 */

public class DownLoadFileBiz implements DownLoadContact.IDownLoadFileBiz {
    private DownLoadDataSource downLoadDataSource;
    private DownLoadContact.onFileDownLoadCallBack  downloadListener;

    public DownLoadFileBiz(Context context, DownLoadContact.onFileDownLoadCallBack  downloadListener) {
        downLoadDataSource=new DownLoadDataSource(context,downloadListener);
        this.downloadListener=downloadListener;
    }

    @Override
    public void downLoadfile(String url, final String path) {
        downloadListener.onStart();
        Logger.e("-------downloadListener.onStart()");
        downLoadDataSource.DownLoadFile(url,path, new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                downloadListener.onError(e.getMessage());

            }

            @Override
            public void onNext(Object o) {
                downloadListener.onSucess(path);
            }
        });
    }
}
