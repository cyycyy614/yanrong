package com.example.luanshiwei.yanrong;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

public class AlermBean extends BasePageBean<AlermBean.ResultBean.ItemsBean>{
    /**
     * success : 1
     * result : {"items":[{"ID":"9","mapName":"null","FQName":"null","EventType":"null","EventTime":"null","FQType":"null","ConfirmType":"null","ConfirmTime":"null","ConfirmMessage":"null","OpUser":"null","videoSum":"null","Alarmid":"null","FQID":"null"}]}
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
             * ID : 9
             * mapName : null
             * FQName : null
             * EventType : null
             * EventTime : null
             * FQType : null
             * ConfirmType : null
             * ConfirmTime : null
             * ConfirmMessage : null
             * OpUser : null
             * videoSum : null
             * Alarmid : null
             * FQID : null
             */

            private String ID;
            private String mapName;
            private String FQName;
            private String EventType;
            private String EventTime;
            private String FQType;
            private String ConfirmType;
            private String ConfirmTime;
            private String ConfirmMessage;
            private String OpUser;
            private String videoSum;
            private String Alarmid;
            private String FQID;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getMapName() {
                return mapName;
            }

            public void setMapName(String mapName) {
                this.mapName = mapName;
            }

            public String getFQName() {
                return FQName;
            }

            public void setFQName(String FQName) {
                this.FQName = FQName;
            }

            public String getEventType() {
                return EventType;
            }

            public void setEventType(String EventType) {
                this.EventType = EventType;
            }

            public String getEventTime() {
                return EventTime;
            }

            public void setEventTime(String EventTime) {
                this.EventTime = EventTime;
            }

            public String getFQType() {
                return FQType;
            }

            public void setFQType(String FQType) {
                this.FQType = FQType;
            }

            public String getConfirmType() {
                return ConfirmType;
            }

            public void setConfirmType(String ConfirmType) {
                this.ConfirmType = ConfirmType;
            }

            public String getConfirmTime() {
                return ConfirmTime;
            }

            public void setConfirmTime(String ConfirmTime) {
                this.ConfirmTime = ConfirmTime;
            }

            public String getConfirmMessage() {
                return ConfirmMessage;
            }

            public void setConfirmMessage(String ConfirmMessage) {
                this.ConfirmMessage = ConfirmMessage;
            }

            public String getOpUser() {
                return OpUser;
            }

            public void setOpUser(String OpUser) {
                this.OpUser = OpUser;
            }

            public String getVideoSum() {
                return videoSum;
            }

            public void setVideoSum(String videoSum) {
                this.videoSum = videoSum;
            }

            public String getAlarmid() {
                return Alarmid;
            }

            public void setAlarmid(String Alarmid) {
                this.Alarmid = Alarmid;
            }

            public String getFQID() {
                return FQID;
            }

            public void setFQID(String FQID) {
                this.FQID = FQID;
            }
        }
    }
}
