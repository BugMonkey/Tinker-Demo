package homepage.zts.com.bugmonkeydemo.moudle.main;

import android.content.Context;

import homepage.zts.com.bugmonkeydemo.moudle.main.view.DownLoadFileBiz;

/**
 * Created by BugMonkey on 2018/2/26.
 */

public class DownLoadPresenter implements DownLoadContact.IDownLoadPresenter {
    private DownLoadContact.IDownLoadFileBiz iDownLoadFileBiz;

    public DownLoadPresenter( DownLoadContact.IDownLoadView downLoadView) {
        final DownLoadContact.IDownLoadView iDownLoadView=downLoadView;
        DownLoadContact.onFileDownLoadCallBack fileDownLoadCallBack=  new DownLoadContact.onFileDownLoadCallBack() {
            @Override
            public void onStart() {
                iDownLoadView.onDownLoadStart();
            }

            @Override
            public void onError(String error) {
                iDownLoadView.onDownLoadFailed(error);
            }

            @Override
            public void onProgress(long progress) {
                iDownLoadView.onProgress(progress);
            }

            @Override
            public void onSucess(String path) {
                iDownLoadView.onDownLoadSucess(path);

            }
        };
        this.iDownLoadFileBiz = new DownLoadFileBiz((Context) iDownLoadView, fileDownLoadCallBack);
    }

    @Override
    public void downLoadfile(String url, String path) {
        iDownLoadFileBiz.downLoadfile(url, path);
    }


}
