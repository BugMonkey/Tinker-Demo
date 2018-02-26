package homepage.zts.com.bugmonkeydemo.model.DateSource;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;

import homepage.zts.com.bugmonkeydemo.config.api.DownLoadFileApi;
import homepage.zts.com.bugmonkeydemo.moudle.main.DownLoadContact;
import homepage.zts.com.bugmonkeydemo.utils.CommomUtils;
import homepage.zts.com.bugmonkeydemo.utils.DeviceTool;
import homepage.zts.com.bugmonkeydemo.utils.RetrofitUtils;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by BugMonkey on 2018/2/3.
 */

public class DownLoadDataSource {
    DownLoadFileApi downLoadFileApi;
    Context context;
    private DownLoadContact.onFileDownLoadCallBack downloadListener;

    public DownLoadDataSource(Context context, DownLoadContact.onFileDownLoadCallBack downloadListener) {
        this.downloadListener = downloadListener;
        downLoadFileApi = RetrofitUtils.CreateRetrofitService(DownLoadFileApi.class);
        this.context = context;
    }


  /*  public void DownLoadFile2(Map<String, String> stringStringMap) {
        Call call = downLoadFileApi.downLoadFile2(stringStringMap);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }*/


    public void DownLoadFile(String url, final String path, Subscriber subscriber) {
        downLoadFileApi.downLoadFile(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {

                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        ProgressResponseBody progressResponseBody=new ProgressResponseBody(responseBody,downloadListener);
                        return progressResponseBody.byteStream();
                    }
                }).observeOn(Schedulers.computation())
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {
                        if (DeviceTool.checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            String status = Environment.getExternalStorageState();
                            String destFileDir;
                            if (status.equals(Environment.MEDIA_MOUNTED)) {
                                destFileDir = path;
                                CommomUtils.writeFile(inputStream, destFileDir);

                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }



    public class ProgressResponseBody extends ResponseBody {
        private ResponseBody responseBody;
        private DownLoadContact.onFileDownLoadCallBack downloadListener;

        private BufferedSource bufferedSource;
        public ProgressResponseBody(ResponseBody responseBody, DownLoadContact.onFileDownLoadCallBack downloadListener) {
            this.responseBody = responseBody;
            this.downloadListener = downloadListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(final Source source) {
            return new ForwardingSource(source) {
                long bytesReaded = 0;
                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                    //实时发送当前已读取的字节和总字节
                    Logger.e("-------当前进度："+bytesReaded+"/"+responseBody.contentLength());
                    downloadListener.onProgress(bytesReaded*100/responseBody.contentLength());
                    return bytesRead;
                }
            };
        }
    }




}


