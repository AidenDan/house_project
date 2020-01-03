package com.condition;

/**
 * @author Aiden
 * @version 1.0
 * @description 封装了动态查询的参数
 * @date 2019-12-24 18:05:14
 */
public class UsersCondition {
    private String cname;
    private String ctelephone;
    /*封装房屋动态查询的查询条件*/
    private Integer ctypeId;
    private Integer cdistrictId;
    private Integer cstreetId;
    private Integer cispass;
    private String cprice1;
    private String cprice;
    private String ctitle;

    private Integer cfloorage1;
    private Integer cfloorage;

    public Integer getCfloorage1() {
        return cfloorage1;
    }

    public void setCfloorage1(Integer cfloorage1) {
        this.cfloorage1 = cfloorage1;
    }

    public Integer getCfloorage() {
        return cfloorage;
    }

    public void setCfloorage(Integer cfloorage) {
        this.cfloorage = cfloorage;
    }

    public Integer getCtypeId() {
        return ctypeId;
    }

    public void setCtypeId(Integer ctypeId) {
        this.ctypeId = ctypeId;
    }

    public Integer getCdistrictId() {
        return cdistrictId;
    }

    public void setCdistrictId(Integer cdistrictId) {
        this.cdistrictId = cdistrictId;
    }

    public Integer getCstreetId() {
        return cstreetId;
    }

    public void setCstreetId(Integer cstreetId) {
        this.cstreetId = cstreetId;
    }

    public Integer getCispass() {
        return cispass;
    }

    public void setCispass(Integer cispass) {
        this.cispass = cispass;
    }

    public String getCprice1() {
        return cprice1;
    }

    public void setCprice1(String cprice1) {
        this.cprice1 = cprice1;
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public UsersCondition() {
    }

    public UsersCondition(String cname, String ctelephone) {
        this.cname = cname;
        this.ctelephone = ctelephone;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtelephone() {
        return ctelephone;
    }

    public void setCtelephone(String ctelephone) {
        this.ctelephone = ctelephone;
    }
}
