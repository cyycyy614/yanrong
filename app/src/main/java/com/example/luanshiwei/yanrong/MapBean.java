package com.example.luanshiwei.yanrong;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

public class MapBean extends BasePageBean<MapBean.ResultBean.ItemsBean> {

    /**
     * success : 1
     * result : {"items":[{"mapid":"289","Name":"2018","Path":"258E3130-CEE2-4594-A739-CE0FF9285CF9.png","ParentId":"288","isMap":"1","LastModify":"2018-07-09 15:47:25","mapW":"544","mapH":"274","JingDu":"121.444862","WeiDu":"31.247145","":null},{"mapid":"162","Name":"lw100","Path":"389029F3-D1B9-442a-B3EA-B48044910CDD.png","ParentId":"160","isMap":"1","LastModify":"2018-06-16 17:49:41","mapW":"544","mapH":"274","JingDu":"121.500130","WeiDu":"30.909273","":null},{"mapid":"225","Name":"hrc","Path":"97B00CFF-7A99-40f0-82D3-20F6FABD7B49.png","ParentId":"224","isMap":"1","LastModify":"2018-06-16 18:28:54","mapW":"800","mapH":"518","JingDu":"121.520897","WeiDu":"31.198093","":null},{"mapid":"321","Name":"lw200","Path":"97B00CFF-7A99-40f0-82D3-20F6FABD7B49.png","ParentId":"320","isMap":"1","LastModify":"2018-06-16 17:49:41","mapW":"544","mapH":"274","JingDu":"120.500130","WeiDu":"29.000000","":null}]}
     * msg : 获取数据成功！
     */

    private int success;
    private ResultBean result;
    private String msg;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public List<MapBean.ResultBean.ItemsBean> getItemDatas() {
        return result.items;
    }

    public static class ResultBean {
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
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
             *  : null
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
    }
}
