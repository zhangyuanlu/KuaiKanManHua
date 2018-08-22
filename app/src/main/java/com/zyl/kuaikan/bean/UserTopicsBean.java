package com.zyl.kuaikan.bean;

import java.util.List;

public class UserTopicsBean {

    /**
     * code : 200
     * data : {"topics":[{"comic_type":0,"comics_count":133,"cover_image_url":"https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg","created_at":1459132969,"description":"少男少女同住一个屋檐下，一同历经青春的懵懂和烦恼。画风温馨的超人气少女漫，感受青春悸动的萌芽，这一部就够！【独家/暑假期间 每周二&周六更新，责编：00】","discover_image_url":"https://i1s.kkmh.com/image/160401/qtglp1ihq.webp-w750.jpg","exclusive_flag":1,"gender_bias":0,"id":766,"is_favourite":true,"label_id":41,"latest_comic_title":"第51话  撒娇","male_cover_image":null,"male_vertical_cover_image":null,"order":799,"quality_certified":0,"title":"怦然心动","update_day":"每周二、周六更新","update_status":1,"updated_at":1459132969,"user":{"avatar_url":"https://i1s.kkmh.com/image/150421/uelm8ugiz.jpg-w180","birthday":852048000000,"created_at":1418546567000,"email":null,"flag":0,"gender":null,"grade":1,"id":47124,"intro":"木有简介，可能在世界角落游荡","nickname":"\u203bkid岁","phone":"18825169114","privilege":171,"role":"AUTHOR","status":"active"},"user_id":47124,"vertical_image_url":"https://i1s.kkmh.com/image/180409/mr07xq2sd.webp-w320.w.jpg"},{"comic_type":0,"comics_count":32,"cover_image_url":"https://i1s.kkmh.com/image/180118/qbj1zqcit.webp-w750.jpg","created_at":1516277513,"description":"我和动物同学们的爆笑日常！【 独家/每周二更新 责编:珉xi 】","discover_image_url":null,"exclusive_flag":1,"gender_bias":2,"id":1886,"is_favourite":true,"label_id":5,"latest_comic_title":"第32话 悲惨的手气王","male_cover_image":null,"male_vertical_cover_image":null,"order":0,"quality_certified":0,"title":"我是学校唯一的人类","update_day":"每周二更新","update_status":1,"updated_at":1516277513,"user":{"avatar_url":"https://i1s.kkmh.com/FvrCvFgr3gZLbT1Lr5yH-s89azl5-w180","birthday":699120000000,"created_at":1516276174000,"email":null,"flag":0,"gender":null,"grade":1,"id":96574084,"intro":"画漫画，吃西瓜","nickname":"柴犬君","phone":"13688022972","privilege":0,"role":"AUTHOR","status":"active"},"user_id":96574084,"vertical_image_url":"https://i1s.kkmh.com/image/180118/mwr38zq5d.webp-w320.w.jpg"},{"comic_type":0,"comics_count":120,"cover_image_url":"https://i1s.kkmh.com/image/170103/fs5s4s2kl.webp-w750.jpg","created_at":1417673566,"description":"高冷的喵星人原则：看心情。\r\n家里的狗和主人，都是喵主子的玩物！\r\n\r\n【授权/每周二更新  责编：楚宁】","discover_image_url":null,"exclusive_flag":0,"gender_bias":2,"id":68,"is_favourite":true,"label_id":5,"latest_comic_title":"第120话 教育之道","male_cover_image":null,"male_vertical_cover_image":null,"order":770,"quality_certified":0,"title":"就喜欢你看不惯我又干不掉我的样子","update_day":"每周二更新","update_status":1,"updated_at":1417673566,"user":{"avatar_url":"https://i1s.kkmh.com/image/150421/z5yh40lvr.jpg-w180","birthday":852048000000,"created_at":1417673573000,"email":null,"flag":0,"gender":null,"grade":1,"id":196,"intro":"知名漫画家，代表作《就喜欢你看不惯我又干不掉我的样子》","nickname":"吾皇的白茶","phone":null,"privilege":3,"role":"AUTHOR","status":"active"},"user_id":196,"vertical_image_url":"https://i1s.kkmh.com/image/170104/f0uqibb4l.webp-w320.w.jpg"},{"comic_type":0,"comics_count":2,"cover_image_url":"https://i1s.kkmh.com/image/180820/B7NLRo8WV.webp-w750.jpg","created_at":1534477794,"description":"医学系第一男神在游戏中吊打全服通缉的第一女贼！意外发现，这个众人唾弃女贼竟是电竞圈曾经的第一女赛手！她为何改名换姓沦为一介女贼？线下不断陌路擦肩、误会连连的两人，又会在电竞场闯出怎样的天地？\u2026\u2026【独家/每周三更新   责编：33】\n","discover_image_url":null,"exclusive_flag":1,"gender_bias":0,"id":2319,"is_favourite":true,"label_id":41,"latest_comic_title":"序章2 朝花惜时作者新作！","male_cover_image":null,"male_vertical_cover_image":null,"order":null,"quality_certified":0,"title":"成也萧河","update_day":"每周三更新","update_status":1,"updated_at":1534477794,"user":{"avatar_url":"https://i1s.kkmh.com/default_avatar_image.jpg-w180","birthday":852048000000,"created_at":1534489118000,"email":null,"flag":0,"gender":null,"grade":1,"id":118009019,"intro":"","nickname":"壳小杀（主笔）＋左小翎（编剧）","phone":null,"privilege":0,"role":"AUTHOR","status":"active"},"user_id":118009019,"vertical_image_url":"https://i1s.kkmh.com/image/180817/t8n3xu72a.webp-w320.w.jpg"}]}
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
        private List<TopicsBean> topics;

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public static class TopicsBean {
            /**
             * comic_type : 0
             * comics_count : 133
             * cover_image_url : https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg
             * created_at : 1459132969
             * description : 少男少女同住一个屋檐下，一同历经青春的懵懂和烦恼。画风温馨的超人气少女漫，感受青春悸动的萌芽，这一部就够！【独家/暑假期间 每周二&周六更新，责编：00】
             * discover_image_url : https://i1s.kkmh.com/image/160401/qtglp1ihq.webp-w750.jpg
             * exclusive_flag : 1
             * gender_bias : 0
             * id : 766
             * is_favourite : true
             * label_id : 41
             * latest_comic_title : 第51话  撒娇
             * male_cover_image : null
             * male_vertical_cover_image : null
             * order : 799
             * quality_certified : 0
             * title : 怦然心动
             * update_day : 每周二、周六更新
             * update_status : 1
             * updated_at : 1459132969
             * user : {"avatar_url":"https://i1s.kkmh.com/image/150421/uelm8ugiz.jpg-w180","birthday":852048000000,"created_at":1418546567000,"email":null,"flag":0,"gender":null,"grade":1,"id":47124,"intro":"木有简介，可能在世界角落游荡","nickname":"\u203bkid岁","phone":"18825169114","privilege":171,"role":"AUTHOR","status":"active"}
             * user_id : 47124
             * vertical_image_url : https://i1s.kkmh.com/image/180409/mr07xq2sd.webp-w320.w.jpg
             */

            private int comic_type;
            private int comics_count;
            private String cover_image_url;
            private int created_at;
            private String description;
            private String discover_image_url;
            private int exclusive_flag;
            private int gender_bias;
            private int id;
            private boolean is_favourite;
            private int label_id;
            private String latest_comic_title;
            private Object male_cover_image;
            private Object male_vertical_cover_image;
            private int order;
            private int quality_certified;
            private String title;
            private String update_day;
            private int update_status;
            private int updated_at;
            private AuthorBean user;
            private int user_id;
            private String vertical_image_url;

            public int getComic_type() {
                return comic_type;
            }

            public void setComic_type(int comic_type) {
                this.comic_type = comic_type;
            }

            public int getComics_count() {
                return comics_count;
            }

            public void setComics_count(int comics_count) {
                this.comics_count = comics_count;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDiscover_image_url() {
                return discover_image_url;
            }

            public void setDiscover_image_url(String discover_image_url) {
                this.discover_image_url = discover_image_url;
            }

            public int getExclusive_flag() {
                return exclusive_flag;
            }

            public void setExclusive_flag(int exclusive_flag) {
                this.exclusive_flag = exclusive_flag;
            }

            public int getGender_bias() {
                return gender_bias;
            }

            public void setGender_bias(int gender_bias) {
                this.gender_bias = gender_bias;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIs_favourite() {
                return is_favourite;
            }

            public void setIs_favourite(boolean is_favourite) {
                this.is_favourite = is_favourite;
            }

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public String getLatest_comic_title() {
                return latest_comic_title;
            }

            public void setLatest_comic_title(String latest_comic_title) {
                this.latest_comic_title = latest_comic_title;
            }

            public Object getMale_cover_image() {
                return male_cover_image;
            }

            public void setMale_cover_image(Object male_cover_image) {
                this.male_cover_image = male_cover_image;
            }

            public Object getMale_vertical_cover_image() {
                return male_vertical_cover_image;
            }

            public void setMale_vertical_cover_image(Object male_vertical_cover_image) {
                this.male_vertical_cover_image = male_vertical_cover_image;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getQuality_certified() {
                return quality_certified;
            }

            public void setQuality_certified(int quality_certified) {
                this.quality_certified = quality_certified;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUpdate_day() {
                return update_day;
            }

            public void setUpdate_day(String update_day) {
                this.update_day = update_day;
            }

            public int getUpdate_status() {
                return update_status;
            }

            public void setUpdate_status(int update_status) {
                this.update_status = update_status;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public AuthorBean getUser() {
                return user;
            }

            public void setUser(AuthorBean user) {
                this.user = user;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getVertical_image_url() {
                return vertical_image_url;
            }

            public void setVertical_image_url(String vertical_image_url) {
                this.vertical_image_url = vertical_image_url;
            }

            public static class AuthorBean {
                /**
                 * avatar_url : https://i1s.kkmh.com/image/150421/uelm8ugiz.jpg-w180
                 * birthday : 852048000000
                 * created_at : 1418546567000
                 * email : null
                 * flag : 0
                 * gender : null
                 * grade : 1
                 * id : 47124
                 * intro : 木有简介，可能在世界角落游荡
                 * nickname : ※kid岁
                 * phone : 18825169114
                 * privilege : 171
                 * role : AUTHOR
                 * status : active
                 */

                private String avatar_url;
                private long birthday;
                private long created_at;
                private Object email;
                private int flag;
                private Object gender;
                private int grade;
                private int id;
                private String intro;
                private String nickname;
                private String phone;
                private int privilege;
                private String role;
                private String status;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public long getBirthday() {
                    return birthday;
                }

                public void setBirthday(long birthday) {
                    this.birthday = birthday;
                }

                public long getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(long created_at) {
                    this.created_at = created_at;
                }

                public Object getEmail() {
                    return email;
                }

                public void setEmail(Object email) {
                    this.email = email;
                }

                public int getFlag() {
                    return flag;
                }

                public void setFlag(int flag) {
                    this.flag = flag;
                }

                public Object getGender() {
                    return gender;
                }

                public void setGender(Object gender) {
                    this.gender = gender;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public int getPrivilege() {
                    return privilege;
                }

                public void setPrivilege(int privilege) {
                    this.privilege = privilege;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
