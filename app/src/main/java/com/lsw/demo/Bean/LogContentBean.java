package com.lsw.demo.Bean;

import java.util.List;

public class LogContentBean {


    /**
     * code : A00000
     * data : {"id":"b21648f9-81ea-4b9d-a4f3-cddfef866946","bizType":"reader_android","fields":[]}
     * msg : 发送成功
     */

    private String code;
    /**
     * id : b21648f9-81ea-4b9d-a4f3-cddfef866946
     * bizType : reader_android
     * fields : []
     */

    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String id;
        private String bizType;
        private List<?> fields;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }

        public List<?> getFields() {
            return fields;
        }

        public void setFields(List<?> fields) {
            this.fields = fields;
        }
    }
}
