package cn.yunt.www.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    private NotificationManager notifyMgr;
    private Intent service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn1.setText("COMMON NOTIFICATION");
        btn2.setText("SERVICE NOTIFICATION");
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        notifyMgr = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        service = new Intent(this,DownLoadService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                simpleNotify();
                break;
            case R.id.btn2:
                startService(service);
                break;
        }
    }

    /**
     * 普通通知
     */
    public void simpleNotify() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示提示
        builder.setTicker("common notification");
        builder.setContentTitle("title");
        builder.setContentText("notification content");
        builder.setSubText("third line content");
        builder.setAutoCancel(true);
        builder.setNumber(2);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.push));

        Intent intent = new Intent(this, SettingActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        notifyMgr.notify(1, notification);

    }
}
