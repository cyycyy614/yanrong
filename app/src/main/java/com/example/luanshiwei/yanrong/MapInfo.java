package com.example.luanshiwei.yanrong;

import java.io.Serializable;

class MapInfo implements Serializable {
    public MapInfo(String mapid, String jingDu, String weiDu) {
        this.mapid = mapid;
        JingDu = jingDu;
        WeiDu = weiDu;
    }

    /**
     * mapid : 289
     * Name : 2018
     * Path : 258E3130-CEE2-4594-A739-CE0FF9285CF9.png
     * ParentId : 288
     * isMap : 1
     * LastModify : 2018-07-09 15:47:25
     * mapW : 544
     * mapH : 274
     * JingDu : 121.444862
     * WeiDu : 31.247145
     */

    private String mapid;
    private String Name;
    private String Path;
    private String ParentId;
    private String isMap;
    private String LastModify;
    private String mapW;
    private String mapH;
    private String JingDu;
    private String WeiDu;

    public String getMapid() {
        return mapid;
    }

    public void setMapid(String mapid) {
        this.mapid = mapid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public String getIsMap() {
        return isMap;
    }

    public void setIsMap(String isMap) {
        this.isMap = isMap;
    }

    public String getLastModify() {
        return LastModify;
    }

    public void setLastModify(String LastModify) {
        this.LastModify = LastModify;
    }

    public String getMapW() {
        return mapW;
    }

    public void setMapW(String mapW) {
        this.mapW = mapW;
    }

    public String getMapH() {
        return mapH;
    }

    public void setMapH(String mapH) {
        this.mapH = mapH;
    }

    public String getJingDu() {
        return JingDu;
    }

    public void setJingDu(String JingDu) {
        this.JingDu = JingDu;
    }

    public String getWeiDu() {
        return WeiDu;
    }

    public void setWeiDu(String WeiDu) {
        this.WeiDu = WeiDu;
    }
}
