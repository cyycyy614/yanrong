package com.example.yanrong.yanrong;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

public class FqItemBean extends BasePageBean<FqItemBean.ResultBean.ItemsBean>{


    /**
     * success : 1
     * result : {"items":[{"id":1,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"单线触摸报警","fqtype":"张力","circularwidth":0,"lastmodify":39},{"id":2,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"拉紧报警","fqtype":"脉冲","circularwidth":0,"lastmodify":39},{"id":3,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"攀爬报警","fqtype":"张力","circularwidth":0,"lastmodify":39},{"id":4,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"松弛报警","fqtype":"张力","circularwidth":0,"lastmodify":39},{"id":5,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"单线触摸报警","fqtype":"脉冲","circularwidth":0,"lastmodify":39},{"id":6,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"单线触摸报警","fqtype":"脉冲","circularwidth":0,"lastmodify":39},{"id":7,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"拉紧报警","fqtype":"脉冲","circularwidth":0,"lastmodify":39},{"id":8,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"攀爬报警","fqtype":"张力","circularwidth":0,"lastmodify":39},{"id":9,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"松弛报警","fqtype":"脉冲","circularwidth":0,"lastmodify":39},{"id":10,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"拉紧报警","fqtype":"脉冲","circularwidth":0,"lastmodify":39},{"id":11,"fqid":"hrc-01","mapid":"书库东侧","fqname":"异常撤防嗖嗖嗖","Status":"短路报警","fqtype":"张力","circularwidth":0,"lastmodify":39}]}
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
    public List<ResultBean.ItemsBean> getItemDatas() {
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
             * id : 1
             * fqid : hrc-01
             * mapid : 书库东侧
             * fqname : 异常撤防嗖嗖嗖
             * Status : 单线触摸报警
             * fqtype : 张力
             * circularwidth : 0
             * lastmodify : 39
             */

            private int id;
            private String fqid;
            private String mapid;
            private String fqname;
            private String Status;
            private String fqtype;
            private int circularwidth;
            private int lastmodify;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFqid() {
                return fqid;
            }

            public void setFqid(String fqid) {
                this.fqid = fqid;
            }

            public String getMapid() {
                return mapid;
            }

            public void setMapid(String mapid) {
                this.mapid = mapid;
            }

            public String getFqname() {
                return fqname;
            }

            public void setFqname(String fqname) {
                this.fqname = fqname;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getFqtype() {
                return fqtype;
            }

            public void setFqtype(String fqtype) {
                this.fqtype = fqtype;
            }

            public int getCircularwidth() {
                return circularwidth;
            }

            public void setCircularwidth(int circularwidth) {
                this.circularwidth = circularwidth;
            }

            public int getLastmodify() {
                return lastmodify;
            }

            public void setLastmodify(int lastmodify) {
                this.lastmodify = lastmodify;
            }
        }
    }
}
