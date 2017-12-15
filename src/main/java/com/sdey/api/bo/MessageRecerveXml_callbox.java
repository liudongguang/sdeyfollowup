package com.sdey.api.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class MessageRecerveXml_callbox implements Serializable {
   private String mobile;
    private String taskid;
    private String content;
    private String receivetime;
    private String extno;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime;
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }

    @Override
    public String toString() {
        return "MessageRecerveXml_callbox{" +
                "mobile='" + mobile + '\'' +
                ", taskid='" + taskid + '\'' +
                ", content='" + content + '\'' +
                ", receivetime='" + receivetime + '\'' +
                ", extno='" + extno + '\'' +
                '}';
    }
}
