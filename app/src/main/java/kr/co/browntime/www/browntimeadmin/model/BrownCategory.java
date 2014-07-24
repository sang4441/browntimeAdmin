package kr.co.browntime.www.browntimeadmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kimsanghwan on 7/24/2014.
 */
public class BrownCategory {

    @JsonProperty("id")
    private int mId;
    @JsonProperty("name")
    private String mName;

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
}

