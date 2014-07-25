package kr.co.browntime.www.browntimeadmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ListView listView = (ListView)v.findViewById(R.id.fragment_carts);
        BrownCartAdapter adapter = new BrownCartAdapter(mOrder.getmCarts());
        listView.setAdapter(adapter);

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
}
