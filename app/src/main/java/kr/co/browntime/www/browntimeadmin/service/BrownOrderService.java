package kr.co.browntime.www.browntimeadmin.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import kr.co.browntime.www.browntimeadmin.BrownTimeAdminOrderActivity;
import kr.co.browntime.www.browntimeadmin.JSONRequest;
import kr.co.browntime.www.browntimeadmin.OrderLab;
import kr.co.browntime.www.browntimeadmin.R;
import kr.co.browntime.www.browntimeadmin.model.BrownOrder;

/**
 * Created by kimsanghwan on 8/3/2014.
 */
public class BrownOrderService extends IntentService {

    public static final String NOTIFICATION = "com.browntime.android.service.receiver";
    public static final String RESULT = "result";


    public BrownOrderService() {
        super("BrownOrderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        new HttpRequestGetOrder().execute();
    }

    private class HttpRequestGetOrder extends AsyncTask<Void, Void, List<BrownOrder>> {
        @Override
        protected List<BrownOrder> doInBackground(Void... params) {
            try {

//                final String url = "http://10.0.2.2:8080/BrownTime/json/getOrder";
                final String url = "http://browntime123.cafe24.com/json/getOrder";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownOrder[] orders = restTemplate.getForObject(url, BrownOrder[].class);

                return Arrays.asList(orders);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<BrownOrder> orders) {
            Intent intent = new Intent(NOTIFICATION);
//            intent.putExtra(RESULT, orders);

            int updatedOrderSize = 0;
            if (OrderLab.get(getApplicationContext()).getOrders().size() < orders.size()) {
                notification();

                intent.putExtra(BrownTimeAdminOrderActivity.SERVICE_KEY, updatedOrderSize);
                sendBroadcast(intent);
//                OrderLab.get(getApplicationContext()).clearOrders();
//                OrderLab.get(getApplicationContext()).setOrders(orders);
//                updatedOrderSize = orders.size() - OrderLab.get(getApplicationContext()).getOrders().size();
            }
        }
    }

    private void notification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_action_refresh)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(getApplicationContext(), BrownTimeAdminOrderActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(BrownTimeAdminOrderActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}
