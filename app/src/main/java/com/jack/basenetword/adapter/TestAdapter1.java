package com.jack.basenetword.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.jack.basenetword.R;
import com.jack.basenetword.entity.NewEntity;

import java.util.List;

import basenetword.jack.com.network.utils.ImageLoad;
import basenetword.jack.com.network.utils.baserecycleviewadapter.BaseQuickAdapter;
import basenetword.jack.com.network.utils.baserecycleviewadapter.BaseViewHolder;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/6/30 14:11
 * 修订历史：
 * 修 改 人：
 */
public class TestAdapter1 extends BaseQuickAdapter<NewEntity.ListBean.NewslistBean> {

    private Context mContext;
    List<NewEntity.ListBean.NewslistBean> list;

    public TestAdapter1(Context context, List<NewEntity.ListBean.NewslistBean> data) {
        super(context, R.layout.rv_new_item, data);
        this.mContext = context;
        this.list = data;
    }


    @Override
    protected void convert(BaseViewHolder holder, NewEntity.ListBean.NewslistBean item, int position) {
        holder.setText(R.id.tvTitle, item.getTitle()).setText(R.id.tvTitleaSsistant, item.getShort_title());
        ImageView imageView = holder.getView(R.id.ivImage);
        if (item.getImage().size() > 0) {
            imageView.setVisibility(View.VISIBLE);
            ImageLoad.load(mContext, imageView, item.getImage().get(0).toString());
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
