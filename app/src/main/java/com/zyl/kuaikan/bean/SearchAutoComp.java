package com.zyl.kuaikan.bean;

import java.util.List;

public class SearchAutoComp {

    /**
     * http://www.kuaikanmanhua.com/web/topic/search?keyword=青庭
     * code : 200
     * data : {"author":[{"id":92301830,"nickname":"我叫Abbily","params":{"keyword":"我叫Abbily","type":1},"score":{"score":28.970621,"strict_match":true}},{"id":16621175,"nickname":"我是肥志","params":{"keyword":"我是肥志","type":1},"score":{"score":28.970621,"strict_match":true}},{"id":96094073,"nickname":"我是YT","params":{"keyword":"我是YT","type":1},"score":{"score":28.970621,"strict_match":true}}],"topic":[{"id":1678,"score":{"score":3.0265415,"strict_match":true},"title":"我是我妻"},{"id":538,"score":{"score":2.269906,"strict_match":true},"title":"我和我的幼稚鬼"},{"id":1014,"score":{"score":2.269906,"strict_match":true},"title":"我的男团我的神"},{"id":655,"score":{"score":2.269906,"strict_match":true},"title":"我和我的损友们"},{"id":124,"score":{"score":2.140088,"strict_match":true},"title":"我恋爱了"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<AuthorBean> author;
        private List<TopicBean> topic;

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public static class AuthorBean {
            /**
             * id : 92301830
             * nickname : 我叫Abbily
             * params : {"keyword":"我叫Abbily","type":1}
             * score : {"score":28.970621,"strict_match":true}
             */

            private int id;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        public static class TopicBean {
            /**
             * id : 1678
             * score : {"score":3.0265415,"strict_match":true}
             * title : 我是我妻
             */

            private int id;

            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }


            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
