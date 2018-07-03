package com.bdqn.demo.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 *
 * 员工表
 */
public class Employee {
    private Integer id;
    private Integer did;
    private String ename;
    private String eaddress;
    private String etelephone;
    private String dname;//部门名称


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }


    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }


    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }


    public String getEtelephone() {
        return etelephone;
    }

    public void setEtelephone(String etelephone) {
        this.etelephone = etelephone;
    }
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

}
