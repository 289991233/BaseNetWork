package basenetword.jack.com.network.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * SwipeRefreshLayout与横向滚动控件事件冲突问题
 */
public class KvSwipeRefreshLayout extends SwipeRefreshLayout {

    private boolean mIsViewDragger;
    private final int mTouchSlop;
    private float startY;
    private float startX;

    public KvSwipeRefreshLayout(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public KvSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                //记录手指按下的坐标
                startX = ev.getX();
                startY = ev.getY();
                //初始化标记
                mIsViewDragger = false;
                break;

            case MotionEvent.ACTION_MOVE:
                // 如果横向滚动控件正在拖拽中，那么不拦截它的事件，直接return false；
                if (mIsViewDragger) {
                    return false;
                }
                //获取当前手指的位置
                float endX = ev.getX();
                float endY = ev.getY();

                float distanceX = Math.abs(startX - endX);
                float distanceY = Math.abs(startY - endY);

                // 如果X轴位移大于Y轴位移，那么将事件交给横向滚动控件处理。
                if (distanceX > mTouchSlop && distanceX > distanceY) {
                    mIsViewDragger = true;
                    return false;
                }
                break;

            case MotionEvent.ACTION_UP:

                break;

            case MotionEvent.ACTION_CANCEL:
                mIsViewDragger = false;
                break;

        }

        return super.onInterceptTouchEvent(ev);
    }
}

//作者：墨源
//        链接：http://www.jianshu.com/p/75db2d2e8d1f
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
