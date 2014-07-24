package kr.co.browntime.www.browntimeadmin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;


public class BrownTimeAdminOrderActivity extends Activity {

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
    }
}
