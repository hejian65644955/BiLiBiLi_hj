package com.hejian.bilibili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 何健 on 2017/3/27.
 */

public class GoodsBean {


    /**
     * title : 【STN快报12】报告！仙女座发现大量如花！
     * cover : http://i0.hdslb.com/bfs/archive/868218d64405e65cad6dc89926aabc3a703b9fba.jpg
     * uri : bilibili://video/9364295
     * param : 9364295
     * goto : av
     * desc : 我很丑可是我很温柔
     * play : 387517
     * idx : 1490013271
     * tid : 17
     * tname : 单机联机
     * tag : {"tag_id":2975420,"tag_name":"STN快报"}
     * dislike_reasons : [{"reason_id":4,"reason_name":"UP主:史丹利Studio"},{"reason_id":2,"reason_name":"分区:单机联机"},{"reason_id":3,"reason_name":"标签:STN快报"},{"reason_id":1,"reason_name":"不感兴趣"}]
     * ctime : 1490358787
     * danmaku : 6797
     * duration : 1042
     * mid : 7349
     * name : 史丹利Studio
     * face : http://i0.hdslb.com/bfs/face/d8700b8127be51cec6b0e3270456edbcf40aad94.jpg
     */

    private String title;
    private String cover;
    private String uri;
    private String param;
    @SerializedName("goto")
    private String gotoX;
    private String desc;
    private int play;
    private int idx;
    private int tid;
    private String tname;
    private TagBean tag;
    private int ctime;
    private int danmaku;
    private int duration;
    private int mid;
    private String name;
    private String face;
    private List<DislikeReasonsBean> dislike_reasons;

    /**
     * 某个商品在购物车购买的数量
     */
    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getGotoX() {
        return gotoX;
    }

    public void setGotoX(String gotoX) {
        this.gotoX = gotoX;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public TagBean getTag() {
        return tag;
    }

    public void setTag(TagBean tag) {
        this.tag = tag;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public int getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(int danmaku) {
        this.danmaku = danmaku;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public List<DislikeReasonsBean> getDislike_reasons() {
        return dislike_reasons;
    }

    public void setDislike_reasons(List<DislikeReasonsBean> dislike_reasons) {
        this.dislike_reasons = dislike_reasons;
    }

    public static class TagBean {
        /**
         * tag_id : 2975420
         * tag_name : STN快报
         */

        private int tag_id;
        private String tag_name;

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }
    }

    public static class DislikeReasonsBean {
        /**
         * reason_id : 4
         * reason_name : UP主:史丹利Studio
         */

        private int reason_id;
        private String reason_name;

        public int getReason_id() {
            return reason_id;
        }

        public void setReason_id(int reason_id) {
            this.reason_id = reason_id;
        }

        public String getReason_name() {
            return reason_name;
        }

        public void setReason_name(String reason_name) {
            this.reason_name = reason_name;
        }
    }
}
