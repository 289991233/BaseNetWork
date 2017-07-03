package com.jack.basenetword.entity;

import java.util.List;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/3 15:52
 * 修订历史：
 * 修 改 人：
 */
public class NewEntity {

    /**
     * code : 40000
     * hint :
     * list : {"carousel":[{"data_id":"241","href_model":"news_detail","href_type":"2","name":"薇姿Vichy泉之净清润爽肤水","thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20170505/20170505113534_32816.jpg","url":"https://hssc.m.huisou.com/?g=app&m=apps&a=news_detail&id=241"},{"data_id":"542","href_model":"product_detail","href_type":"2","name":"薇姿净颜无瑕面部护肤两步曲套装","thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20170505/20170505113543_61654.jpg","url":"https://hssc.m.huisou.com/?g=app&m=apps&a=product_detail&id=542"},{"data_id":"370","href_model":"product_detail","href_type":"2","name":"胖妹妹加肥加大码女装胖mm冬装2014韩版新款加绒加厚保暖连衣裙2","thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20170511/20170511122825_30148.jpg","url":"https://hssc.m.huisou.com/?g=app&m=apps&a=product_detail&id=370"},{"data_id":"544","href_model":"product_detail","href_type":"2","name":"专柜正品丹姿水密码深层卸妆油120ML","thumbnail":"https://hssc.m.huisou.com/Uploads/Admin/image/20161202/20161202144839_68335.jpg","url":"https://hssc.m.huisou.com/?g=app&m=apps&a=product_detail&id=544"}],"newslist":[{"addtime":"06-16","collect_code":"40005","collect_count":"0","comment_count":"0","id":"288","image":[],"short_title":"化妆品加盟连锁化妆品批发","title":"化妆品加盟连锁化妆品批发"},{"addtime":"06-16","collect_code":"40005","collect_count":"0","comment_count":"0","id":"287","image":[],"short_title":"未来化妆品OEM/ODM的发展趋势：全面创新","title":"未来化妆品OEM/ODM的发展趋势：全面创新"},{"addtime":"06-16","collect_code":"40005","collect_count":"0","comment_count":"0","id":"286","image":[],"short_title":"经常熬夜吗？化妆品OEM厂家传授护肤妙招","title":"经常熬夜吗？化妆品OEM厂家传授护肤妙招"},{"addtime":"06-16","collect_code":"40005","collect_count":"0","comment_count":"0","id":"285","image":["http://hssc.m.huisou.com/Uploads/Admin/image/20170616/20170616101359_47063.png"],"short_title":"怎样选择化妆品？化妆品OEM厂家分享诀窍","title":"怎样选择化妆品？化妆品OEM厂家分享诀窍"},{"addtime":"06-16","collect_code":"40005","collect_count":"0","comment_count":"0","id":"284","image":["http://hssc.m.huisou.com/Uploads/Admin/image/20170616/20170616101305_79658.png"],"short_title":"化妆品生产线管理需要注意哪些小细节？","title":"化妆品生产线管理需要注意哪些小细节？"},{"addtime":"06-13","collect_code":"40005","collect_count":"0","comment_count":"0","id":"283","image":[],"short_title":"2017年墨西哥国际汽车零配件展览会","title":"2017年墨西哥国际汽车零配件展览会"},{"addtime":"06-13","collect_code":"40005","collect_count":"0","comment_count":"0","id":"282","image":[],"short_title":"2017年英国伯明翰国际汽车零部件展览会","title":"2017年英国伯明翰国际汽车零部件展览会"},{"addtime":"06-13","collect_code":"40005","collect_count":"0","comment_count":"0","id":"281","image":[],"short_title":"2016年连接器行业市场规模分析","title":"2016年连接器行业市场规模分析"},{"addtime":"06-13","collect_code":"40005","collect_count":"0","comment_count":"0","id":"280","image":["http://hssc.m.huisou.com/Uploads/Admin/image/20170613/20170613174614_73803.png"],"short_title":"冬季车辆养护的六个小窍门","title":"冬季车辆养护的六个小窍门"},{"addtime":"06-13","collect_code":"40005","collect_count":"0","comment_count":"0","id":"279","image":["http://hssc.m.huisou.com/Uploads/Admin/image/20170613/20170613174126_39125.png"],"short_title":"2017广州汽车用品与汽车零配件展览会","title":"2017广州汽车用品与汽车零配件展览会"}]}
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
        private List<CarouselBean> carousel;
        private List<NewslistBean> newslist;

        public List<CarouselBean> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<CarouselBean> carousel) {
            this.carousel = carousel;
        }

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class CarouselBean {
            /**
             * data_id : 241
             * href_model : news_detail
             * href_type : 2
             * name : 薇姿Vichy泉之净清润爽肤水
             * thumbnail : https://hssc.m.huisou.com/Uploads/Admin/image/20170505/20170505113534_32816.jpg
             * url : https://hssc.m.huisou.com/?g=app&m=apps&a=news_detail&id=241
             */

            private String data_id;
            private String href_model;
            private String href_type;
            private String name;
            private String thumbnail;
            private String url;

            public String getData_id() {
                return data_id;
            }

            public void setData_id(String data_id) {
                this.data_id = data_id;
            }

            public String getHref_model() {
                return href_model;
            }

            public void setHref_model(String href_model) {
                this.href_model = href_model;
            }

            public String getHref_type() {
                return href_type;
            }

            public void setHref_type(String href_type) {
                this.href_type = href_type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class NewslistBean {
            /**
             * addtime : 06-16
             * collect_code : 40005
             * collect_count : 0
             * comment_count : 0
             * id : 288
             * image : []
             * short_title : 化妆品加盟连锁化妆品批发
             * title : 化妆品加盟连锁化妆品批发
             */

            private String addtime;
            private String collect_code;
            private String collect_count;
            private String comment_count;
            private String id;
            private String short_title;
            private String title;
            private List<String> image;

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getCollect_code() {
                return collect_code;
            }

            public void setCollect_code(String collect_code) {
                this.collect_code = collect_code;
            }

            public String getCollect_count() {
                return collect_count;
            }

            public void setCollect_count(String collect_count) {
                this.collect_count = collect_count;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getShort_title() {
                return short_title;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getImage() {
                return image;
            }

            public void setImage(List<String> image) {
                this.image = image;
            }
        }
    }
}
