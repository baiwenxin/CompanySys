package com.bdqn.demo.util;

import java.util.List;

public class Page<T> {
    private Integer pageNo;//当前页面
    private Integer pageSize;//每页显示记录数
    private Integer infoCount;//总数据
    private Integer pageCount;//总页数
    private List<T> infolist;//所有信息

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(Integer infoCount) {
        this.infoCount = infoCount;
        this.pageCount = this.infoCount % this.pageSize == 0 ? this.infoCount / this.pageSize : this.infoCount / this.pageSize + 1;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getInfolist() {
        return infolist;
    }

    public void setInfolist(List<T> infolist) {
        this.infolist = infolist;
    }

}
