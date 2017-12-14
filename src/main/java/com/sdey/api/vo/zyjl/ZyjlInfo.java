package com.sdey.api.vo.zyjl;

import com.sdey.api.vo.hisoutpatient.InterfaceHeader;

import java.util.List;

/**
 * Created by liudo on 2017/4/10.
 */
public class ZyjlInfo {
    private Integer code;
    private InterfaceHeader header;
    private List<ZyjData> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public InterfaceHeader getHeader() {
        return header;
    }

    public void setHeader(InterfaceHeader header) {
        this.header = header;
    }

    public List<ZyjData> getData() {
        return data;
    }

    public void setData(List<ZyjData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ZyjlInfo{" +
                "code=" + code +
                ", header=" + header +
                ", data=" + data +
                '}';
    }
}
