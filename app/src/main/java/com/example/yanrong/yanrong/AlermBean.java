package com.example.yanrong.yanrong;

import java.util.List;

public class AlermBean {

    /**
     * ret : succ
     * desc : id,fqid,fqname,fqtype,mapid,DotX,DotY,LineX,LineY,LineX2,LineY2,Linewidth,DotType,Status,circularwidth,lastmodify
     * result : ["http://140.207.114.102:1900/www_html/1/2018-08-27/1090033024.htm"]
     * handle : 1090027314
     */

    private String ret;
    private String desc;
    private int handle;
    private List<String> result;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getHandle() {
        return handle;
    }

    public void setHandle(int handle) {
        this.handle = handle;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
