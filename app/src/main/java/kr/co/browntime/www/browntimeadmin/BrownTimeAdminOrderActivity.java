package kr.co.browntime.www.browntimeadmin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.Calendar;

import kr.co.browntime.www.browntimeadmin.service.BrownOrderService;


public class BrownTimeAdminOrderActivity extends Activity {

    public static final String SERVICE_KEY = "brown_admin_order_service";

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int newOrderNum = bundle.getInt(SERVICE_KEY);
            if (newOrderNum > 0) {
                int i = 1;
                i = i+1;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brown_time_admin_order);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_orders);
        if (fragment == null) {
            fragment = new BrownTimeAdminOrderListFragment();
            fm.beginTransaction().add(R.id.fragment_orders, fragment).commit();
        }

        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(this, BrownOrderService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);

        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
// Start every 30 seconds
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pintent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(BrownOrderService.NOTIFICATION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

}
