package com.sdey.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "phoneblacklist")
public class Phoneblacklist {
    @Id
    private Integer uid;

    private String blackphone;

    private Date createtime;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return blackphone
     */
    public String getBlackphone() {
        return blackphone;
    }

    /**
     * @param blackphone
     */
    public void setBlackphone(String blackphone) {
        this.blackphone = blackphone;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}