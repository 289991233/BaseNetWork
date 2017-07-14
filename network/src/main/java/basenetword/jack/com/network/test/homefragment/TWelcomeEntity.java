package basenetword.jack.com.network.test.homefragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */

public class TWelcomeEntity {


    /**
     * code : 40000
     * hint :
     * list : {"alipay_private_key":"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANjoFNM9Yo96VglpBSKi5Vb70Xn8WoiUJvsNRb4ehrnphdCECv8W6zltohFFsexbix37eb+6e/truaFBCmw0lsdVe0KNrN2XmGdfFBZlDyJ7X44RYw9kl1WE7v9J4eVl3Wif6jzRWq33PHZ6TFUzUDvWE3houC3hYGYAnbmsWsibAgMBAAECgYEAmP5KAh2gMXemzAhpeN7RSSNhw9s9uGxXemkIMmuxt9yBaGxanUb7L4ym7evs7bw8Si+g1p2g6dw4GQeZZG1Lk40dJRYZaJxBiaQ7LE/lFV1kmWNmtrllXLV54OfwphXbw/1EDcLaw4yiMZMO0CnphrYzimYeVWNvaGejdQ4lnBECQQD7HWysSOfs5W9qFqaUHvX1hwhCTJnPXPaOsgHeUJq5GF4Qs0xNRn/ZCSARRCL/7XDmoMeEQrQURsDwi+292ktNAkEA3SBLxxq1wpEtHLFocdfEN6Ni/zwPyzCcxcFYodet/aAdWYdmrlHh5WpLq7JIowArHCNqpnOHdY0JHiMG7LnfhwJBANEW4greNccKD2gNbix9Tx1ejyDtOVDxPhb43xdmlD40rPZI5OqfHgrwTzQxQNdKtKxECXz2MY2EkFh6mr1vSy0CQECmhaFD3Opy+aaO9AN82yCNQ49uJwv4PY3P9rLy1Sr3Gj2nycyjohEqH8+mQ3hsvy3t6OubkXo77vOBuC+UNJcCQQCeOtmGyUDkVyzFuMfbbjjdP9CatTvaGb3MvfRMZWtLE8At2A6Jk+Nxc9oA7hHijVgsbD2rS8anGRsqnZ5SiMc4","amap_key":"1003706dc64f177cd125d9c35bb9a27b","android_code":"2.0.30","companphone":"400-680-6820","distribution_flag":"1","download_android":"","download_ios":"","footer_list":[{"check_login":"0","href":"home","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_home_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_home_selected.png","title":"首页","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"0","href":"company_list","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_company_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_company_selected.png","title":"企业","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"0","href":"news_list","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_news_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_news_selected.png","title":"资讯","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"1","href":"cart","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_cart_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_cart_selected.png","title":"购物车","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"1","href":"mine","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_mine_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_mine_selected.png","title":"我的","title_color_normal":"#151618","title_color_selected":"#FF5000"}],"index_template":"1","push_key":"0357da5e55251191b657fd96","rongcloud_key":"ik1qhw091izdp","sms_flag":"0","start_flag":"1","start_img":"http://china.m.huisou.com/Uploads/Admin/image/20160823/20160823171722_15670.png","start_time":"5","start_url":"http://china.m.huisou.com?g=app&m=apps&a=company_home&id=12"}
     */

    private String code;
    private String hint;
    private ListBean list;

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

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * alipay_private_key : MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANjoFNM9Yo96VglpBSKi5Vb70Xn8WoiUJvsNRb4ehrnphdCECv8W6zltohFFsexbix37eb+6e/truaFBCmw0lsdVe0KNrN2XmGdfFBZlDyJ7X44RYw9kl1WE7v9J4eVl3Wif6jzRWq33PHZ6TFUzUDvWE3houC3hYGYAnbmsWsibAgMBAAECgYEAmP5KAh2gMXemzAhpeN7RSSNhw9s9uGxXemkIMmuxt9yBaGxanUb7L4ym7evs7bw8Si+g1p2g6dw4GQeZZG1Lk40dJRYZaJxBiaQ7LE/lFV1kmWNmtrllXLV54OfwphXbw/1EDcLaw4yiMZMO0CnphrYzimYeVWNvaGejdQ4lnBECQQD7HWysSOfs5W9qFqaUHvX1hwhCTJnPXPaOsgHeUJq5GF4Qs0xNRn/ZCSARRCL/7XDmoMeEQrQURsDwi+292ktNAkEA3SBLxxq1wpEtHLFocdfEN6Ni/zwPyzCcxcFYodet/aAdWYdmrlHh5WpLq7JIowArHCNqpnOHdY0JHiMG7LnfhwJBANEW4greNccKD2gNbix9Tx1ejyDtOVDxPhb43xdmlD40rPZI5OqfHgrwTzQxQNdKtKxECXz2MY2EkFh6mr1vSy0CQECmhaFD3Opy+aaO9AN82yCNQ49uJwv4PY3P9rLy1Sr3Gj2nycyjohEqH8+mQ3hsvy3t6OubkXo77vOBuC+UNJcCQQCeOtmGyUDkVyzFuMfbbjjdP9CatTvaGb3MvfRMZWtLE8At2A6Jk+Nxc9oA7hHijVgsbD2rS8anGRsqnZ5SiMc4
         * amap_key : 1003706dc64f177cd125d9c35bb9a27b
         * android_code : 2.0.30
         * companphone : 400-680-6820
         * distribution_flag : 1
         * download_android :
         * download_ios :
         * footer_list : [{"check_login":"0","href":"home","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_home_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_home_selected.png","title":"首页","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"0","href":"company_list","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_company_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_company_selected.png","title":"企业","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"0","href":"news_list","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_news_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_news_selected.png","title":"资讯","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"1","href":"cart","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_cart_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_cart_selected.png","title":"购物车","title_color_normal":"#151618","title_color_selected":"#FF5000"},{"check_login":"1","href":"mine","icon_normal":"http://china.m.huisou.com/Public/Apps/images/footer_mine_normal.png","icon_selected":"http://china.m.huisou.com/Public/Apps/images/footer_mine_selected.png","title":"我的","title_color_normal":"#151618","title_color_selected":"#FF5000"}]
         * index_template : 1
         * push_key : 0357da5e55251191b657fd96
         * rongcloud_key : ik1qhw091izdp
         * sms_flag : 0
         * start_flag : 1
         * start_img : http://china.m.huisou.com/Uploads/Admin/image/20160823/20160823171722_15670.png
         * start_time : 5
         * start_url : http://china.m.huisou.com?g=app&m=apps&a=company_home&id=12
         */

        private String alipay_private_key;
        private String amap_key;
        private String android_code;
        private String companphone;
        private String distribution_flag;
        private String download_android;
        private String download_ios;
        private String index_template;
        private String push_key;
        private String rongcloud_key;
        private String sms_flag;
        private String start_flag;
        private String start_img;
        private String start_time;
        private String start_url;
        private String enquiry_rid;

        public String getEnquiry_rid() {
            return enquiry_rid;
        }

        public void setEnquiry_rid(String enquiry_rid) {
            this.enquiry_rid = enquiry_rid;
        }

        public String getEnquiry_rname() {
            return enquiry_rname;
        }

        public void setEnquiry_rname(String enquiry_rname) {
            this.enquiry_rname = enquiry_rname;
        }

        private String enquiry_rname;
        private List<FooterListBean> footer_list;
        private List<SearchListBean> search_list;

        public String getAlipay_private_key() {
            return alipay_private_key;
        }

        public void setAlipay_private_key(String alipay_private_key) {
            this.alipay_private_key = alipay_private_key;
        }

        public String getAmap_key() {
            return amap_key;
        }

        public void setAmap_key(String amap_key) {
            this.amap_key = amap_key;
        }

        public String getAndroid_code() {
            return android_code;
        }

        public void setAndroid_code(String android_code) {
            this.android_code = android_code;
        }

        public String getCompanphone() {
            return companphone;
        }

        public void setCompanphone(String companphone) {
            this.companphone = companphone;
        }

        public String getDistribution_flag() {
            return distribution_flag;
        }

        public void setDistribution_flag(String distribution_flag) {
            this.distribution_flag = distribution_flag;
        }

        public String getDownload_android() {
            return download_android;
        }

        public void setDownload_android(String download_android) {
            this.download_android = download_android;
        }

        public String getDownload_ios() {
            return download_ios;
        }

        public void setDownload_ios(String download_ios) {
            this.download_ios = download_ios;
        }

        public String getIndex_template() {
            return index_template;
        }

        public void setIndex_template(String index_template) {
            this.index_template = index_template;
        }

        public String getPush_key() {
            return push_key;
        }

        public void setPush_key(String push_key) {
            this.push_key = push_key;
        }

        public String getRongcloud_key() {
            return rongcloud_key;
        }

        public void setRongcloud_key(String rongcloud_key) {
            this.rongcloud_key = rongcloud_key;
        }

        public String getSms_flag() {
            return sms_flag;
        }

        public void setSms_flag(String sms_flag) {
            this.sms_flag = sms_flag;
        }

        public String getStart_flag() {
            return start_flag;
        }

        public void setStart_flag(String start_flag) {
            this.start_flag = start_flag;
        }

        public String getStart_img() {
            return start_img;
        }

        public void setStart_img(String start_img) {
            this.start_img = start_img;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getStart_url() {
            return start_url;
        }

        public void setStart_url(String start_url) {
            this.start_url = start_url;
        }

        public List<FooterListBean> getFooter_list() {
            return footer_list;
        }

        public void setFooter_list(List<FooterListBean> footer_list) {
            this.footer_list = footer_list;
        }

        public List<SearchListBean> getSearch_list() {
            return search_list;
        }

        public void setSearch_list(List<SearchListBean> search_list) {
            this.search_list = search_list;
        }

        public static class SearchListBean {
            private String title;
            private String href;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class FooterListBean {
            /**
             * check_login : 0
             * href : home
             * icon_normal : http://china.m.huisou.com/Public/Apps/images/footer_home_normal.png
             * icon_selected : http://china.m.huisou.com/Public/Apps/images/footer_home_selected.png
             * title : 首页
             * title_color_normal : #151618
             * title_color_selected : #FF5000
             */

            private String check_login;
            private String href;
            private String icon_normal;
            private String icon_selected;
            private String title;
            private String title_color_normal;
            private String title_color_selected;

            public int getIcon_true() {
                return icon_true;
            }

            public void setIcon_true(int icon_true) {
                this.icon_true = icon_true;
            }

            private int icon_true = 1;

            public String getCheck_login() {
                return check_login;
            }

            public void setCheck_login(String check_login) {
                this.check_login = check_login;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getIcon_normal() {
                return icon_normal;
            }

            public void setIcon_normal(String icon_normal) {
                this.icon_normal = icon_normal;
            }

            public String getIcon_selected() {
                return icon_selected;
            }

            public void setIcon_selected(String icon_selected) {
                this.icon_selected = icon_selected;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle_color_normal() {
                return title_color_normal;
            }

            public void setTitle_color_normal(String title_color_normal) {
                this.title_color_normal = title_color_normal;
            }

            public String getTitle_color_selected() {
                return title_color_selected;
            }

            public void setTitle_color_selected(String title_color_selected) {
                this.title_color_selected = title_color_selected;
            }
        }
    }
}
