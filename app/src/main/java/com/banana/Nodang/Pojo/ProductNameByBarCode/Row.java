package com.banana.Nodang.Pojo.ProductNameByBarCode;

import com.google.gson.annotations.SerializedName;

public class Row {
    @SerializedName("CLSBIZ_DT")
    private String clsbizDt;
    @SerializedName("SITE_ADDR")
    private String siteAddr;
    @SerializedName("PRDLST_REPORT_NO")
    private String prdlstReportNo;
    @SerializedName("PRMS_DT")
    private String prmsDt;
    @SerializedName("PRDLST_NM")
    private String prdlstNm;
    @SerializedName("BAR_CD")
    private String barCd;
    @SerializedName("POG_DAYCNT")
    private String pogDaycnt;
    @SerializedName("PRDLST_DCNM")
    private String prdlstDcnm;
    @SerializedName("BSSH_NM")
    private String bsshNm;
    @SerializedName("END_DT")
    private String endDt;
    @SerializedName("INDUTY_NM")
    private String indutyNm;

    public String getClsbizDt() {
        return clsbizDt;
    }

    public void setClsbizDt(String clsbizDt) {
        this.clsbizDt = clsbizDt;
    }

    public String getSiteAddr() {
        return siteAddr;
    }

    public void setSiteAddr(String siteAddr) {
        this.siteAddr = siteAddr;
    }

    public String getPrdlstReportNo() {
        return prdlstReportNo;
    }

    public void setPrdlstReportNo(String prdlstReportNo) {
        this.prdlstReportNo = prdlstReportNo;
    }

    public String getPrmsDt() {
        return prmsDt;
    }

    public void setPrmsDt(String prmsDt) {
        this.prmsDt = prmsDt;
    }

    public String getPrdlstNm() {
        return prdlstNm;
    }

    public void setPrdlstNm(String prdlstNm) {
        this.prdlstNm = prdlstNm;
    }

    public String getBarCd() {
        return barCd;
    }

    public void setBarCd(String barCd) {
        this.barCd = barCd;
    }

    public String getPogDaycnt() {
        return pogDaycnt;
    }

    public void setPogDaycnt(String pogDaycnt) {
        this.pogDaycnt = pogDaycnt;
    }

    public String getPrdlstDcnm() {
        return prdlstDcnm;
    }

    public void setPrdlstDcnm(String prdlstDcnm) {
        this.prdlstDcnm = prdlstDcnm;
    }

    public String getBsshNm() {
        return bsshNm;
    }

    public void setBsshNm(String bsshNm) {
        this.bsshNm = bsshNm;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getIndutyNm() {
        return indutyNm;
    }

    public void setIndutyNm(String indutyNm) {
        this.indutyNm = indutyNm;
    }

}