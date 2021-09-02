
package com.aliyayman.NotlarAppRetroift;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Notlar implements Serializable {

    @SerializedName("not_id")
    @Expose
    private String notId;
    @SerializedName("ders_adi")
    @Expose
    private String dersAdi;
    @SerializedName("not1")
    @Expose
    private String not1;
    @SerializedName("not2")
    @Expose
    private String not2;

    public String getNotId() {
        return notId;
    }

    public void setNotId(String notId) {
        this.notId = notId;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getNot1() {
        return not1;
    }

    public void setNot1(String not1) {
        this.not1 = not1;
    }

    public String getNot2() {
        return not2;
    }

    public void setNot2(String not2) {
        this.not2 = not2;
    }

}
