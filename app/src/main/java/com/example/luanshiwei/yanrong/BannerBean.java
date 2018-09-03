package com.example.luanshiwei.yanrong;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

public class BannerBean extends BasePageBean<BannerBean.ResultBean.ItemsBean> {

    /**
     * code : 1
     * message : success
     * notice : {"newsCount":21}
     * result : {"items":[{"detail":"","href":"https://www.oschina.net/news/98910/show-your-changes-for-decade","id":98910,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_3c58241a-6b6f-41bc-b05d-283d5fb0f005.png","name":"#秀出你的十年变化照# 十年前立下的flag，如今都达成了吗？","pubDate":"2018-08-23 10:58:49","type":6,"weight":13},{"detail":"","href":"https://www.oschina.net/news/99139/oschina-app-4-2-2-released","id":99139,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_2dee320f-3e60-46da-92fc-cba8e8bab46f.jpg","name":"4.2.2版本IOS同步更新丨全面适应刘海屏~~","pubDate":"2018-08-21 11:38:47","type":6,"weight":13},{"detail":"","href":"https://www.oschina.net/news/99271/redis-database-license-change","id":99271,"img":"https://static.oschina.net/uploads/cooperation/event/guangzhou/event/2201438_cef3436f-c261-4b58-979d-ee5dd0fa1dd0.jpg","name":"Redis 模块开源许可证变更，多个项目不再开源遭质疑","pubDate":"2018-08-24 11:12:02","type":6,"weight":9},{"detail":"","href":"https://www.oschina.net/news/99266/most-popular-vscode-extensions-in-2018","id":99266,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_758800bd-e5fc-41d4-9cba-727f3276af95.jpg","name":"2018 年最受欢迎的 Visual Studio Code 扩展插件合集","pubDate":"2018-08-24 11:35:18","type":6,"weight":8},{"detail":"","href":"https://www.oschina.net/question/2720166_2285446","id":2285446,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_5cbe6608-af0a-4f40-b8f2-ec7004d44ac7.jpg","name":"深度学习如此火热，你了解得怎么样了？","pubDate":"2018-08-21 18:20:59","type":2,"weight":3}],"nextPageToken":"61AF0C190D6BD629","prevPageToken":"3EA621243546C8A5","requestCount":5,"responseCount":5,"totalResults":5}
     * time : 2018-08-26 20:48:49
     */

    private int code;
    private String message;
    private NoticeBean notice;
    private ResultBean result;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public List<ResultBean.ItemsBean> getItemDatas() {
        return result.items;
    }

    public static class NoticeBean {
        /**
         * newsCount : 21
         */

        private int newsCount;

        public int getNewsCount() {
            return newsCount;
        }

        public void setNewsCount(int newsCount) {
            this.newsCount = newsCount;
        }
    }

    public static class ResultBean {
        /**
         * items : [{"detail":"","href":"https://www.oschina.net/news/98910/show-your-changes-for-decade","id":98910,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_3c58241a-6b6f-41bc-b05d-283d5fb0f005.png","name":"#秀出你的十年变化照# 十年前立下的flag，如今都达成了吗？","pubDate":"2018-08-23 10:58:49","type":6,"weight":13},{"detail":"","href":"https://www.oschina.net/news/99139/oschina-app-4-2-2-released","id":99139,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_2dee320f-3e60-46da-92fc-cba8e8bab46f.jpg","name":"4.2.2版本IOS同步更新丨全面适应刘海屏~~","pubDate":"2018-08-21 11:38:47","type":6,"weight":13},{"detail":"","href":"https://www.oschina.net/news/99271/redis-database-license-change","id":99271,"img":"https://static.oschina.net/uploads/cooperation/event/guangzhou/event/2201438_cef3436f-c261-4b58-979d-ee5dd0fa1dd0.jpg","name":"Redis 模块开源许可证变更，多个项目不再开源遭质疑","pubDate":"2018-08-24 11:12:02","type":6,"weight":9},{"detail":"","href":"https://www.oschina.net/news/99266/most-popular-vscode-extensions-in-2018","id":99266,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_758800bd-e5fc-41d4-9cba-727f3276af95.jpg","name":"2018 年最受欢迎的 Visual Studio Code 扩展插件合集","pubDate":"2018-08-24 11:35:18","type":6,"weight":8},{"detail":"","href":"https://www.oschina.net/question/2720166_2285446","id":2285446,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_5cbe6608-af0a-4f40-b8f2-ec7004d44ac7.jpg","name":"深度学习如此火热，你了解得怎么样了？","pubDate":"2018-08-21 18:20:59","type":2,"weight":3}]
         * nextPageToken : 61AF0C190D6BD629
         * prevPageToken : 3EA621243546C8A5
         * requestCount : 5
         * responseCount : 5
         * totalResults : 5
         */

        private String nextPageToken;
        private String prevPageToken;
        private int requestCount;
        private int responseCount;
        private int totalResults;
        private List<ItemsBean> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }

        public int getResponseCount() {
            return responseCount;
        }

        public void setResponseCount(int responseCount) {
            this.responseCount = responseCount;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * detail :
             * href : https://www.oschina.net/news/98910/show-your-changes-for-decade
             * id : 98910
             * img : https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_3c58241a-6b6f-41bc-b05d-283d5fb0f005.png
             * name : #秀出你的十年变化照# 十年前立下的flag，如今都达成了吗？
             * pubDate : 2018-08-23 10:58:49
             * type : 6
             * weight : 13
             */

            private String detail;
            private String href;
            private int id;
            private String img;
            private String name;
            private String pubDate;
            private int type;
            private int weight;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }
        }
    }
}
