package com.tech.entite;

import java.sql.Timestamp;

public class Post {

    private int pid;
    private String title;
    private String pic;
    private String content;
    private Timestamp postdate;
    private int cid;
    private String code;
    private int userid;

    public Post(int pid, String title, String pic, String content, Timestamp postdate, int cid, String code, int userid) {
        this.pid = pid;
        this.title = title;
        this.pic = pic;
        this.content = content;
        this.postdate = postdate;
        this.cid = cid;
        this.code = code;
        this.userid = userid;
    }

    public Post(int pid, String title, String pic, String content, Timestamp postdate, String code, int userid) {
        this.pid = pid;
        this.title = title;
        this.pic = pic;
        this.content = content;
        this.postdate = postdate;
        this.code = code;
        this.userid = userid;
    }
    
    

    public Post(String title, String pic, String content, Timestamp postdate, int cid, String code, int userid) {
        this.title = title;
        this.pic = pic;
        this.content = content;
        this.postdate = postdate;
        this.cid = cid;
        this.code = code;
        this.userid = userid;
    }

    public Post() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostdate() {
        return postdate;
    }

    public void setPostdate(Timestamp postdate) {
        this.postdate = postdate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }
}
