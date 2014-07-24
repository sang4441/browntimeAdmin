package kr.co.browntime.www.browntimeadmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by kimsanghwan on 7/21/2014.
 */
public class BrownTest {
    @JsonProperty("id")
    private UUID mId;
    @JsonProperty("sellerId")
    private int mSellerId;
    @JsonProperty("typeId")
    private int mType;
    @JsonProperty("price")
    private int mPrice;
    @JsonProperty("timeRequested")
    private Date mTime;
    @JsonProperty("carts")
    private ArrayList<BrownTestCart> mCarts;

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public int getmSellerId() {
        return mSellerId;
    }

    public void setmSellerId(int mSellerId) {
        this.mSellerId = mSellerId;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public ArrayList<BrownTestCart> getmCarts() {
        return mCarts;
    }
    public void setmCarts(ArrayList<BrownTestCart> mCarts) {
        this.mCarts = mCarts;
    }
}
