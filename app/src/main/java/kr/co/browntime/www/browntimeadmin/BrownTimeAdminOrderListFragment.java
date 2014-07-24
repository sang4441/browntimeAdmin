package kr.co.browntime.www.browntimeadmin;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.browntime.www.browntimeadmin.model.BrownOrder;

/**
 * Created by kimsanghwan on 7/24/2014.
 */
public class BrownTimeAdminOrderListFragment extends ListFragment {

    private ArrayList<BrownOrder> orders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HttpRequestTask().execute();
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

            TextView orderBuyerNameView = (TextView)convertView.findViewById(R.id.order_buyer_name);
            TextView orderTypeView = (TextView)convertView.findViewById(R.id.order_type);
            orderTypeView.setText("here");
            TextView orderStatusView = (TextView)convertView.findViewById(R.id.order_status);
            TextView orderDate = (TextView)convertView.findViewById(R.id.order_date);
            TextView orderBuyerNumber = (TextView)convertView.findViewById(R.id.order_buyer_number);

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

            BrownOrderAdapter adapter = new BrownOrderAdapter(orders);

            setListAdapter(adapter);
        }
    }
}