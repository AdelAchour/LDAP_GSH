package com.prod.adelachour.ldap_gsh;

public class EmployeModel {

    String name;
    String tel;
    String title;
    //String departement;


    public EmployeModel(String name, String tel, String title) {
        this.name=name;
        this.tel=tel;
        this.title=title;


    }


    public String getName() {
        return name;
    }


    public String getTel() {
        return tel;
    }


    public String getTitle() {
        return title;
    }






}
