package com.sdey.api.vo.bingan;

import com.sdey.api.vo.hisoutpatient.InterfaceHeader;

import java.util.List;

/**
 * Created by liudo on 2017/4/10.
 */
public class BinganIndexInfo {
    private Integer code;
    private InterfaceHeader header;
    private List<BinganIndex> data;

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

    public List<BinganIndex> getData() {
        return data;
    }

    public void setData(List<BinganIndex> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BinganIndexInfo{" +
                "code=" + code +
                ", header=" + header +
                ", data=" + data +
                '}';
    }
}
