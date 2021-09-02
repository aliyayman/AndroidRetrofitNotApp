
package com.aliyayman.NotlarAppRetroift;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NotlarCevap {

    @SerializedName("notlar")
    @Expose
    private List<Notlar> notlar = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Notlar> getNotlar() {
        return notlar;
    }

    public void setNotlar(List<Notlar> notlar) {
        this.notlar = notlar;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
