package com.android.bean;

/**
 * Created by admin on 2016-11-08.
 */
public class User {

    /**
     * errcode : 0
     * status : 1
     * msg : success
     * data : {"id":18,"isDelete":false,"gmtCreate":1478246254000,"gmtModified":1478612381000,"creator":"","modifier":"","source":1,"username":"15093201628","nickname":null,"realname":"孟垒","avatar":"/2016/11/18/user/headpic1478587468723.jpg","sex":0,"company":"优印科技","companyAddress":null,"position":"程序员","info":"很好的一个人","password":"0d42a4ada0687008395d1dd82453903a","errorTimes":0,"token":"435E846103E8477BB063CC478D3DB0FF","hxpwd":"e565f08d058ebb4a1c99907a9860a93b","factoryList":null}
     */

    private int errcode;
    private int status;
    private String msg;
    /**
     * id : 18
     * isDelete : false
     * gmtCreate : 1478246254000
     * gmtModified : 1478612381000
     * creator :
     * modifier :
     * source : 1
     * username : 15093201628
     * nickname : null
     * realname : 孟垒
     * avatar : /2016/11/18/user/headpic1478587468723.jpg
     * sex : 0
     * company : 优印科技
     * companyAddress : null
     * position : 程序员
     * info : 很好的一个人
     * password : 0d42a4ada0687008395d1dd82453903a
     * errorTimes : 0
     * token : 435E846103E8477BB063CC478D3DB0FF
     * hxpwd : e565f08d058ebb4a1c99907a9860a93b
     * factoryList : null
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
        private int id;
        private boolean isDelete;
        private long gmtCreate;
        private long gmtModified;
        private String creator;
        private String modifier;
        private int source;
        private String username;
        private Object nickname;
        private String realname;
        private String avatar;
        private int sex;
        private String company;
        private Object companyAddress;
        private String position;
        private String info;
        private String password;
        private int errorTimes;
        private String token;
        private String hxpwd;
        private Object factoryList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public long getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(long gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public Object getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(Object companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getErrorTimes() {
            return errorTimes;
        }

        public void setErrorTimes(int errorTimes) {
            this.errorTimes = errorTimes;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHxpwd() {
            return hxpwd;
        }

        public void setHxpwd(String hxpwd) {
            this.hxpwd = hxpwd;
        }

        public Object getFactoryList() {
            return factoryList;
        }

        public void setFactoryList(Object factoryList) {
            this.factoryList = factoryList;
        }
    }
}
