package com.jack.basenetword.adapter;

import android.content.Context;
import android.view.View;
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
    private List<NewEntity.ListBean.NewslistBean> list;

    //    public TestAdapter(Context context, List<NewEntity.ListBean.NewslistBean> data) {
//        super(context, R.layout.rv_new_item, data);
//        this.mContext = context;
//    }
    public TestAdapter(Context ctx, List<NewEntity.ListBean.NewslistBean> list) {
        super(ctx, list);
        this.mContext = ctx;
        this.list = list;
    }

//    @Override
//    protected void convert(BaseViewHolder holder, NewEntity.ListBean.NewslistBean item, int position) {
//        holder.setText(R.id.tvTitle, item.getTitle()).setText(R.id.tvTitleaSsistant, item.getShort_title());
//        if (item.getImage().size() > 0) {
//            ImageLoad.glideLoader(mContext, (ImageView) holder.getView(R.id.ivImage), item.getImage().get(0).toString());
//        }
//    }

    //    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.rv_new_item;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, NewEntity.ListBean.NewslistBean item) {
        holder.setText(R.id.tvTitle, item.getTitle()).setText(R.id.tvTitleaSsistant, item.getShort_title());
        ImageView imageView = (ImageView) holder.getView(R.id.ivImage);
        if (item.getImage().size() > 0) {
            imageView.setVisibility(View.VISIBLE);
            ImageLoad.load(mContext, imageView, item.getImage().get(0).toString());
        } else {
            imageView.setVisibility(View.GONE);
        }
//
//        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
//        List<NewEntity.ListBean.NewslistBean> imageDetails = list;
//        if (imageDetails != null) {
//            for (NewEntity.ListBean.NewslistBean imageDetail : imageDetails) {
//                ImageInfo info = new ImageInfo();
//                info.setThumbnailUrl(imageDetail.getImage().toString());
//                info.setBigImageUrl(imageDetail.getImage().toString());
//                imageInfo.add(info);
//            }
//        }
//        NineGridView nineGridView = (NineGridView) holder.getView(R.id.iv_grid);
//        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext,imageInfo));
//        holder.getView(R.id.iv_grid).setAdapter(new ClickNineGridViewAdapter(context, imageInfo));
    }
}
