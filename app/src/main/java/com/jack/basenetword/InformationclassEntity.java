package com.jack.basenetword;

import java.util.List;

/**
 * @Description：资讯分类
 * @author: SDX
 * @time： 2016/6/89:39
 */
public class InformationclassEntity {


    /**
     * code : 40000
     * hint :
     * list : [{"cid":"","cname":"头条","flags":"","type":"click"},{"cid":"","cname":"最新","flags":"","type":"addtime"},{"cid":"","cname":"推广","flags":"c","type":""},{"cid":"7","cname":"分类3","flags":"","type":""},{"cid":"6","cname":"分类2","flags":"","type":""},{"cid":"5","cname":"分类1","flags":"","type":""}]
     */

    private String code;
    private String hint;
    /**
     * cid :
     * cname : 头条
     * flags :
     * type : click
     */

    private List<ListBean> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String cid;
        private String cname;
        private String flags;
        private String type;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getFlags() {
            return flags;
        }

        public void setFlags(String flags) {
            this.flags = flags;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
