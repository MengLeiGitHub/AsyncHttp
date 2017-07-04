package com.android.bean;

/**
 * Created by admin on 2016-11-07.
 */
public class UploadResultBean {


    /**
     * errcode : 0
     * status : 1
     * msg : success
     * data : {"id":null,"name":"http://oss.u-yin.cn/2016/11/5/user/headpic1479462137302.jpg","list":null,"pageInfo":null}
     */

    private int errcode;
    private int status;
    private String msg;
    /**
     * id : null
     * name : http://oss.u-yin.cn/2016/11/5/user/headpic1479462137302.jpg
     * list : null
     * pageInfo : null
     */

    private DataBean data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private Object id;
        private String name;
        private Object list;
        private Object pageInfo;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }

        public Object getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(Object pageInfo) {
            this.pageInfo = pageInfo;
        }
    }
}
