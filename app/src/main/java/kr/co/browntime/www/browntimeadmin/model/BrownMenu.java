package kr.co.browntime.www.browntimeadmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrownMenu {

    @JsonProperty("id")
	private int mId;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("price")
    private int mPrice;
    @JsonProperty("category")
    private int mCategory;
    @JsonProperty("description")
    private String mDescription;



    public BrownMenu() {

    }

//    public BrownMenu(int name, int price, int type) {
//        mId = UUID.randomUUID();
//        mName = name;
//        mPrice = price;
//        mCategory = type;
//    }

    public BrownMenu(int id, String name, int price, int category, String description) {
        mId = id;
        mName = name;
        mPrice = price;
        mCategory = category;
        mDescription = description;
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getmCategory() {
        return mCategory;
    }

    public void setmCategory(int mCategory) {
        this.mCategory = mCategory;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

}
