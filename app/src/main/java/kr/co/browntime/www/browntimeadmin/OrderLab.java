package kr.co.browntime.www.browntimeadmin;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kr.co.browntime.www.browntimeadmin.model.BrownOrder;

public class OrderLab {
    private List<BrownOrder> mOrders;
    private static OrderLab sBrownLab;
    private Context mAppContext;

    private OrderLab(Context appContext) {
        mAppContext = appContext;
        mOrders = new ArrayList<BrownOrder>();
    }

    public static OrderLab get(Context c) {
        if (sBrownLab == null) {
            sBrownLab = new OrderLab(c.getApplicationContext());
        }
        return sBrownLab;
    }

    public void setOrders(List<BrownOrder> orders) {
        mOrders = orders;
    }

    public List<BrownOrder> getOrders() { return mOrders; }

    public BrownOrder getOrder(int orderId) {
        for (BrownOrder c: mOrders) {
            if (c.getmId()==orderId)
                return c;
        }
        return null;
    }

    public void addOrder(BrownOrder order) {
        mOrders.add(order);
    }

    public BrownOrder getLastOrder() {
        return mOrders.get(mOrders.size()-1);
    }

}
