package kr.co.browntime.www.browntimeadmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kimsanghwan on 7/22/2014.
 */
public class BrownTestCart extends BrownMenu {
    @JsonProperty("priceTotal")
    private int mPriceTotal;
    @JsonProperty("quantity")
    private int mQuantity;
    @JsonProperty("instruction")
    private String mInstruction;

    public int getmPriceTotal() {
        return mPriceTotal;
    }

    public void setmPriceTotal(int mPriceTotal) {
        this.mPriceTotal = mPriceTotal;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmInstruction() {
        return mInstruction;
    }

    public void setmInstruction(String mInstruction) {
        this.mInstruction = mInstruction;
    }

    public BrownTestCart() {
        super();
    }

//    public BrownTestCart(int name, int price, int type) {
//        super(name, price, type);
//    }

//    public int getTotalPrice() {
//        return mQuantity * this.getmPrice();
//    }
}
