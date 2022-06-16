package com.banana.Nodang.Pojo.ProductNameByBarCode;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class C005 {
    @SerializedName("total_count")
    private String totalCount;
    @SerializedName("row")
    private List<Row> row = null;
    @SerializedName("RESULT")
    private Result result;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
