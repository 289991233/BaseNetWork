package basenetword.jack.com.network.utils;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * @Description：
 * @author: SDX
 * @time：2016-05-26 18:15
 */
public class RecycerScrollView extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;
    //滚动监听事件
    private OnScrollListener onScrollListener;
    /**
     * 滚动监听
     */
    private ScrollViewListener scrollViewListener = null;
    /**
     * 用户手指离开TopScrollView，TopScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public RecycerScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public RecycerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public RecycerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 用户手指离开TopScrollView的时候获取TopScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
            }
        }
    };

    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * TopScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * TopScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (onScrollListener != null) {
            onScrollListener.onScroll(lastScrollY = getScrollY());
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
                break;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回TopScrollView滑动的Y方向距离
         *
         * @param scrollY
         */
        void onScroll(int scrollY);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {

        void onScrollChanged(RecycerScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    /**
     * 示例
     */
//    binding.fmMain.setOnScrollListener(new TopScrollView.OnScrollListener() {
//        @Override
//        public void onScroll(int scrollY) {
//            if (scrollY >= screenHeight+100) { //大于显示否则就隐藏
//                Loger(scrollY);
//                binding.iv.setVisibility(View.VISIBLE);
//                //mEdit.setVisibility(View.GONE);
//            } else {
//                binding.iv.setVisibility(View.GONE);
//                //mEdit.setVisibility(View.VISIBLE);
//            }
//        }
//    });

//    binding.iv.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onCkxClick(View view) {
//            binding.fmMain.smoothScrollTo(0, 0); //点击就回到顶部
//            binding.iv.setVisibility(View.GONE);
//        }
//    });
}
