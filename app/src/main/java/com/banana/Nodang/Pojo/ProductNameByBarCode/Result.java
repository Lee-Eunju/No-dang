package com.banana.Nodang.Pojo.ProductNameByBarCode;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("MSG")
    private String msg;
    @SerializedName("CODE")
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}