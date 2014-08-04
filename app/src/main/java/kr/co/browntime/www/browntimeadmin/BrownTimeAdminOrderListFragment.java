package kr.co.browntime.www.browntimeadmin;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.browntime.www.browntimeadmin.model.BrownOrder;

/**
 * Created by kimsanghwan on 7/24/2014.
 */
public class BrownTimeAdminOrderListFragment extends Fragment {

    private ArrayList<BrownOrder> orders;
    View v;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_view_order, parent, false);
        listView = (ListView) v.findViewById(R.id.content_frame);

        new HttpRequestTask().execute();

        return v;
    }
    private class BrownOrderAdapter extends ArrayAdapter<BrownOrder> {

        public BrownOrderAdapter(List<BrownOrder> orders) {
            super(getActivity(), 0, orders);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_order, parent, false);
            }

            BrownOrder order = getItem(position);

            String packageName = getActivity().getPackageName();

            TextView orderBuyerNameView = (TextView)convertView.findViewById(R.id.order_buyer_name);
            orderBuyerNameView.setText(order.getmBuyerName());

            TextView orderTypeView = (TextView)convertView.findViewById(R.id.order_type);
            int id = getResources().getIdentifier(order.getmTypeName(),"string", packageName);
            String typeName = getResources().getString(id);
            orderTypeView.setText(typeName);

            TextView orderStatusView = (TextView)convertView.findViewById(R.id.order_status);
            String orderName = getResources().getString(getResources().getIdentifier(order.getmStatusName(),"string", packageName));
            orderStatusView.setText(orderName);

            TextView orderDate = (TextView)convertView.findViewById(R.id.order_date);
            orderDate.setText(order.getmTime().toString());
            TextView orderBuyerNumber = (TextView)convertView.findViewById(R.id.order_buyer_number);
            orderBuyerNumber.setText(String.valueOf(order.getmBuyerCellNumber()));

            if (order.getmStatusId() == 1) {
                orderStatusView.setTextColor(Color.parseColor("#FF8000"));
            } else if (order.getmStatusId() == 2) {
                orderStatusView.setTextColor(Color.parseColor("#31B404"));
            } else {
                orderStatusView.setTextColor(Color.parseColor("#6E6E6E"));
            }

            return convertView;
        }
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<BrownOrder>> {
        @Override
        protected List<BrownOrder> doInBackground(Void... params) {
            try {

                final String url = "http://10.0.2.2:8080/BrownTime/json/getOrder";
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

            OrderLab.get(getActivity()).setOrders(orders);
            BrownOrderAdapter adapter = new BrownOrderAdapter(orders);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    BrownOrder order = (BrownOrder) (parent.getAdapter().getItem(position));
                    Intent i = new Intent(getActivity(), BrownTimeAdminOrderPagerActivity.class);
                    i.putExtra(BrownTimeAdminOrderFragment.EXTRA_ORDER_ID, order.getmId());
                    startActivity(i);
                }
            });
        }
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        BrownOrder order = (BrownOrder)(getListAdapter()).getItem(position);
//        Intent i = new Intent(getActivity(), BrownTimeAdminOrderPagerActivity.class);
//        i.putExtra(BrownTimeAdminOrderFragment.EXTRA_ORDER_ID, order.getmId());
//        startActivity(i);
//    }
}
