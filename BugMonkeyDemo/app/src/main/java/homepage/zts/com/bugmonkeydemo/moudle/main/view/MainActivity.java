package homepage.zts.com.bugmonkeydemo.moudle.main.view;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;

import homepage.zts.com.bugmonkeydemo.R;
import homepage.zts.com.bugmonkeydemo.config.NetConfig;
import homepage.zts.com.bugmonkeydemo.moudle.main.DownLoadContact;
import homepage.zts.com.bugmonkeydemo.moudle.main.DownLoadPresenter;
import homepage.zts.com.bugmonkeydemo.utils.PermissionsUtils;

public class MainActivity extends AppCompatActivity implements DownLoadContact.IDownLoadView {
    private DownLoadPresenter downLoadPresenter;
    private NotificationCompat.Builder builder;
    private ProgressBar progressBar;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        manager = (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        downLoadPresenter = new DownLoadPresenter(this);
        progressBar = findViewById(R.id.pb_activity_main);
        findViewById(R.id.btn_activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadPresenter.downLoadfile(NetConfig.DOWNLOAD_FILE, Environment.getExternalStorageDirectory().getPath() + File.separator + "test.Apk");
            }
        });

    }

    private void initPermission() {
        PermissionsUtils.with(this).addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).initPermission();
    }


    @Override
    public void onDownLoadStart() {
        showNotification();
    }

    @Override
    public void onDownLoadSucess(String path) {

        builder.setContentText("下载完成，点击安装");
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;


        PendingIntent pi = PendingIntent.getActivity(this, 0, installApkIntent(path), flags);
        builder.setContentIntent(pi);
        builder.setAutoCancel(true);
        manager.notify(1, builder.build());
    }

    @Override
    public void onDownLoadFailed(String error) {
        builder.setContentText("下载失败");
        manager.notify(1, builder.build());

    }


    @Override
    public void onProgress(long progress) {
        if (builder != null) {
            builder.setProgress(100, (int) progress, false);
            progressBar.setProgress((int) (progress));
            manager.notify(1, builder.build());

        }
    }

    /**
     * 显示下载进度条
     */
    private void showNotification() {
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("BugMonkey")
                .setContentText("正在下载");

        manager.notify(1, builder.build());


    }


    private Intent installApkIntent(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(),
                    "com.zts.antifake.file_provider",
                    new File(path)), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(path)),
                    "application/vnd.android.package-archive");
        }
        return intent;
    }

}
