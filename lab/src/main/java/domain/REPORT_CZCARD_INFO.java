package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class REPORT_CZCARD_INFO {

    @Id
    private String CARD_CODE;
    private String PROVINCE;
    private String CITY;
    private String REGION;
    private String ACC_SUBJECT;
    private String STATUS;
    private String SOURCE;
    private String SITECODE;
    private String SITENAME;
    private String SITE_RECEIVE_FLAG;
    private String EPMS_CODE;
    private String CONTRACTCODE;
    private String OVALUE;
    private String MONTH_SALES;
    private String SUM_SALES;
    private String PAYABLES;
    private String SALES_SDATE;
    private String SALES_EDATE;
    private String SALES_MONTH;
    private String AMORTIZED_MONTH;
    private String RES_SALES_MONTH;
    private String CREATEDATE;
    private String CARRYOVERDATE;
    private String PRE_TRAN_ACC;
    private String OVERALL_BALANCE;

    public String getOVERALL_BALANCE() {
        return OVERALL_BALANCE;
    }
    public void setOVERALL_BALANCE(String OVERALL_BALANCE) {
        this.OVERALL_BALANCE = OVERALL_BALANCE;
    }
    
    public String getPRE_TRAN_ACC() {
        return PRE_TRAN_ACC;
    }
    public void setPRE_TRAN_ACC(String PRE_TRAN_ACC) {
        this.PRE_TRAN_ACC = PRE_TRAN_ACC;
    }
    
    public String getCARRYOVERDATE() {
        return CARRYOVERDATE;
    }
    public void setCARRYOVERDATE(String CARRYOVERDATE) {
        this.CARRYOVERDATE = CARRYOVERDATE;
    }
    
    public String getCREATEDATE() {
        return CREATEDATE;
    }
    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }
    
    public String getRES_SALES_MONTH() {
        return RES_SALES_MONTH;
    }
    public void setRES_SALES_MONTH(String RES_SALES_MONTH) {
        this.RES_SALES_MONTH = RES_SALES_MONTH;
    }
    
    public String getAMORTIZED_MONTH() {
        return AMORTIZED_MONTH;
    }
    public void setAMORTIZED_MONTH(String AMORTIZED_MONTH) {
        this.AMORTIZED_MONTH = AMORTIZED_MONTH;
    }
    
    public String getSALES_MONTH() {
        return SALES_MONTH;
    }
    public void setSALES_MONTH(String SALES_MONTH) {
        this.SALES_MONTH = SALES_MONTH;
    }
    
    public String getSALES_EDATE() {
        return SALES_EDATE;
    }
    public void setSALES_EDATE(String SALES_EDATE) {
        this.SALES_EDATE = SALES_EDATE;
    }
    
    public String getSALES_SDATE() {
        return SALES_SDATE;
    }
    public void setSALES_SDATE(String SALES_SDATE) {
        this.SALES_SDATE = SALES_SDATE;
    }
    
    public String getPAYABLES() {
        return PAYABLES;
    }
    public void setPAYABLES(String PAYABLES) {
        this.PAYABLES = PAYABLES;
    }
    
    public String getSUM_SALES() {
        return SUM_SALES;
    }
    public void setSUM_SALES(String SUM_SALES) {
        this.SUM_SALES = SUM_SALES;
    }
    
    public String getMONTH_SALES() {
        return MONTH_SALES;
    }
    public void setMONTH_SALES(String MONTH_SALES) {
        this.MONTH_SALES = MONTH_SALES;
    }
    
    public String getOVALUE() {
        return OVALUE;
    }
    public void setOVALUE(String OVALUE) {
        this.OVALUE = OVALUE;
    }
    
    public String getCONTRACTCODE() {
        return CONTRACTCODE;
    }
    public void setCONTRACTCODE(String CONTRACTCODE) {
        this.CONTRACTCODE = CONTRACTCODE;
    }
    
    public String getEPMS_CODE() {
        return EPMS_CODE;
    }
    public void setEPMS_CODE(String EPMS_CODE) {
        this.EPMS_CODE = EPMS_CODE;
    }
    
    public String getSITE_RECEIVE_FLAG() {
        return SITE_RECEIVE_FLAG;
    }
    public void setSITE_RECEIVE_FLAG(String SITE_RECEIVE_FLAG) {
        this.SITE_RECEIVE_FLAG = SITE_RECEIVE_FLAG;
    }
    
    public String getSITENAME() {
        return SITENAME;
    }
    public void setSITENAME(String SITENAME) {
        this.SITENAME = SITENAME;
    }
    
    public String getSITECODE() {
        return SITECODE;
    }
    public void setSITECODE(String SITECODE) {
        this.SITECODE = SITECODE;
    }
    
    public String getSOURCE() {
        return SOURCE;
    }
    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }
    
    public String getSTATUS() {
        return STATUS;
    }
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    
    public String getACC_SUBJECT() {
        return ACC_SUBJECT;
    }
    public void setACC_SUBJECT(String ACC_SUBJECT) {
        this.ACC_SUBJECT = ACC_SUBJECT;
    }
    
    public String getREGION() {
        return REGION;
    }
    public void setREGION(String REGION) {
        this.REGION = REGION;
    }
    
    public String getCITY() {
        return CITY;
    }
    public void setCITY(String CITY) {
        this.CITY = CITY;
    }
    
    public String getPROVINCE() {
        return PROVINCE;
    }
    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }
    public String getCARD_CODE() {
        return CARD_CODE;
    }
    public void setCARD_CODE(String CARD_CODE) {
        this.CARD_CODE = CARD_CODE;
    }



}
