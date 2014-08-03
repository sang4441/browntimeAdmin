package kr.co.browntime.www.browntimeadmin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import kr.co.browntime.www.browntimeadmin.model.BrownCart;
import kr.co.browntime.www.browntimeadmin.model.BrownOrder;

/**
 * Created by kimsanghwan on 7/25/2014.
 */
public class BrownTimeAdminOrderFragment extends Fragment {
    public static final String EXTRA_ORDER_ID = "browntime.order_id";

    private BrownOrder mOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOrder = new BrownOrder();
        int orderId = (int)getArguments().getInt(EXTRA_ORDER_ID);
        mOrder = OrderLab.get(getActivity()).getOrder(orderId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, parent, false);

        TextView orderIdView = (TextView)v.findViewById(R.id.order_id);
        orderIdView.setText(String.valueOf(mOrder.getmId()));

        TextView orderTotalPriceView = (TextView)v.findViewById(R.id.order_total_price);
        orderTotalPriceView.setText(String.valueOf(mOrder.getmPrice()));

        ListView listView = (ListView)v.findViewById(R.id.fragment_carts);
        BrownCartAdapter adapter = new BrownCartAdapter(mOrder.getmCarts());
        listView.setAdapter(adapter);

        View.OnClickListener durationListener = new View.OnClickListener() {
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.order_duration_5:
                        orderStatusToConfirmed(5);
                        break;
                    case R.id.order_duration_10:
                        orderStatusToConfirmed(10);
                        break;
                    case R.id.order_duration_15:
                        orderStatusToConfirmed(15);
                        break;
                    case R.id.order_duration_20:
                        orderStatusToConfirmed(20);
                        break;
                    case R.id.order_duration_25:
                        orderStatusToConfirmed(25);
                        break;
                    case R.id.order_duration_30:
                        orderStatusToConfirmed(30);
                        break;
                }

            }
        };

        v.findViewById(R.id.order_duration_5).setOnClickListener(durationListener);
        v.findViewById(R.id.order_duration_10).setOnClickListener(durationListener);
        v.findViewById(R.id.order_duration_15).setOnClickListener(durationListener);
        v.findViewById(R.id.order_duration_20).setOnClickListener(durationListener);
        v.findViewById(R.id.order_duration_25).setOnClickListener(durationListener);
        v.findViewById(R.id.order_duration_30).setOnClickListener(durationListener);

        return v;
    }

    private class BrownCartAdapter extends ArrayAdapter<BrownCart> {

        public BrownCartAdapter(List<BrownCart> carts) {
            super(getActivity(), 0, carts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_cart, parent, false);
            }

            BrownCart cart = getItem(position);

            String packageName = getActivity().getPackageName();

            TextView cartQuantity = (TextView) convertView.findViewById(R.id.order_cart_quantity);
            cartQuantity.setText(String.valueOf(cart.getmQuantity()));


            TextView cartName = (TextView) convertView.findViewById(R.id.order_cart_name);
            String cartMenuName = getResources().getString(getResources().getIdentifier(cart.getmName(),"string", packageName));
            cartName.setText(cartMenuName);


            TextView cartPrice = (TextView) convertView.findViewById(R.id.order_cart_price);
            cartPrice.setText(String.valueOf(cart.getmPrice()));

            return convertView;
        }
    }


    public static BrownTimeAdminOrderFragment newInstance(int orderId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ORDER_ID, orderId);

        BrownTimeAdminOrderFragment fragment = new BrownTimeAdminOrderFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void orderStatusToConfirmed(int min) {
        new HttpRequestUpdateDuration().execute(min);
    }

    private class HttpRequestUpdateDuration extends AsyncTask<Integer, Void, List<BrownOrder>> {
        @Override
        protected List<BrownOrder> doInBackground(Integer... mins) {
            try {

                int duration = mins[0];
                final String url = "http://10.0.2.2:8080/BrownTime/json/updateOrderDuration/"+mOrder.getmId()+"/"+duration;
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

        }
    }
}
