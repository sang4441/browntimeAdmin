package kr.co.browntime.www.browntimeadmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kimsanghwan on 7/24/2014.
 */
public class BrownBuyer {
    @JsonProperty("buyerId")
    private int mBuyerId;
    @JsonProperty("buyerName")
    private String mBuyerName;
    @JsonProperty("buyerCellNumber")
    private int mBuyerCellNumber;

    public int getmBuyerId() {
        return mBuyerId;
    }

    public void setmBuyerId(int mBuyerId) {
        this.mBuyerId = mBuyerId;
    }

    public String getmBuyerName() {
        return mBuyerName;
    }

    public void setmBuyerName(String mBuyerName) {
        this.mBuyerName = mBuyerName;
    }

    public int getmBuyerCellNumber() {
        return mBuyerCellNumber;
    }

    public void setmBuyerCellNumber(int mBuyerCellNumber) {
        this.mBuyerCellNumber = mBuyerCellNumber;
    }
}