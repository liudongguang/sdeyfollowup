package com.sdey.api.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "returnsms")
public class MessageRecerveXml implements Serializable {
   private MessageRecerveXml_callbox callbox;

    public MessageRecerveXml_callbox getCallbox() {
        return callbox;
    }

    public void setCallbox(MessageRecerveXml_callbox callbox) {
        this.callbox = callbox;
    }

    @Override
    public String toString() {
        return "MessageRecerveXml{" +
                "callbox=" + callbox +
                '}';
    }
}
