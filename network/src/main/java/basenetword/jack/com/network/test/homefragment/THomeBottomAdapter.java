package basenetword.jack.com.network.test.homefragment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.utils.ImageLoad;
import basenetword.jack.com.network.utils.baserecycleviewadapter.BaseQuickAdapter;
import basenetword.jack.com.network.utils.baserecycleviewadapter.BaseViewHolder;


/**
 * 描    述：
 * 创 建 人：
 * 创建日期：2016/12/21 15:19
 * 修订历史：
 * 修 改 人：
 */
public class THomeBottomAdapter extends BaseQuickAdapter<TWelcomeEntity.ListBean.FooterListBean> {
    private Context mContext;
    public TextView textView;

    public THomeBottomAdapter(Context context, List<TWelcomeEntity.ListBean.FooterListBean> data) {
        super(context, R.layout.rv_home_bottom, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, TWelcomeEntity.ListBean.FooterListBean item, int position) {
        helper.setIsRecyclable(false);
        textView = helper.getView(R.id.TvNum);
        /**
         * 右上角的小圆点
         */
        if (item.getHref().equals("mine")) {
            textView.setVisibility(View.GONE);
        }
        //底部栏标题
        TextView textViews = helper.getView(R.id.tv_name);
        textViews.setText(item.getTitle().toString());
        if (item.getIcon_true() == 0) {//
            ImageLoad.load(mContext, (ImageView) helper.getView(R.id.iv_image), item.getIcon_selected());
            //用来加载图片 初始化就将选中和未选中的图片加载出来 缓存起来
            ImageLoad.load(mContext, (ImageView) helper.getView(R.id.iv_images), item.getIcon_normal());
            textViews.setTextColor(ContextCompat.getColor(mContext, R.color.color_orange));
        } else {
            //用来加载图片
            ImageLoad.load(mContext, (ImageView) helper.getView(R.id.iv_images), item.getIcon_selected());
            ImageLoad.load(mContext, (ImageView) helper.getView(R.id.iv_image), item.getIcon_normal());
            textViews.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
        }
    }
}
