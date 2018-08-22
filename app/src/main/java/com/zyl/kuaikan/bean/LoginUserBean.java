package com.zyl.kuaikan.bean;

public class LoginUserBean {

    /**
     * code : 200
     * data : {"user":{"grade":0,"nickname":"憋说话吻我_---","id":102507856,"pub_feed":0,"avatar_url":"http://f2.kkmh.com/default_avatar_image.jpg-w180","reg_type":"phone"}}
     * message : ok
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
        /**
         * user : {"grade":0,"nickname":"憋说话吻我_---","id":102507856,"pub_feed":0,"avatar_url":"http://f2.kkmh.com/default_avatar_image.jpg-w180","reg_type":"phone"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * grade : 0
             * nickname : 憋说话吻我_---
             * id : 102507856
             * pub_feed : 0
             * avatar_url : http://f2.kkmh.com/default_avatar_image.jpg-w180
             * reg_type : phone
             */

            private int grade;
            private String nickname;
            private int id;
            private int pub_feed;
            private String avatar_url;
            private String reg_type;

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPub_feed() {
                return pub_feed;
            }

            public void setPub_feed(int pub_feed) {
                this.pub_feed = pub_feed;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getReg_type() {
                return reg_type;
            }

            public void setReg_type(String reg_type) {
                this.reg_type = reg_type;
            }
        }
    }
}
