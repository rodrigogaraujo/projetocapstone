package party.com.br.party.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import party.com.br.party.InitActivity;
import party.com.br.party.R;
import party.com.br.party.helper.Constants;

/**
 * Created by Isabelly on 08/05/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class EventJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SEND_EVENT_NOTIFICATION, Constants.SEND_EVENT_NOTIFICATION_OK);
        Intent intent = new Intent(this, InitActivity.class);
        intent.putExtras(bundle);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "")
                .setContentTitle(getString(R.string.help_us_title))
                .setContentText(getString(R.string.help_us))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_logo)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        manager.notify(0, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
