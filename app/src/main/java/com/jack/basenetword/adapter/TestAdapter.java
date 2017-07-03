package com.jack.basenetword.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.jack.basenetword.R;
import com.jack.basenetword.entity.NewEntity;

import java.util.List;

import basenetword.jack.com.network.utils.ImageLoad;
import basenetword.jack.com.network.utils.recycleviewutils.BaseRecyclerAdapter;
import basenetword.jack.com.network.utils.recycleviewutils.RecyclerViewHolder;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/6/30 14:11
 * 修订历史：
 * 修 改 人：
 */
public class TestAdapter extends BaseRecyclerAdapter<NewEntity.ListBean.NewslistBean> {

    private Context mContext;
    public TestAdapter(Context ctx, List<NewEntity.ListBean.NewslistBean> list) {
        super(ctx, list);
        this.mContext = ctx;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.rv_new_item;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, NewEntity.ListBean.NewslistBean item) {
        holder.setText(R.id.tvTitle, item.getTitle()).setText(R.id.tvTitleaSsistant, item.getShort_title());
        if (item.getImage().size() > 0) {
            ImageLoad.glideLoader(mContext, (ImageView) holder.getView(R.id.ivImage),item.getImage().get(0).toString());
        }
    }
}
