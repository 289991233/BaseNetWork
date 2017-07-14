package basenetword.jack.com.network.test.homefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;


/**
 * 描    述：主界面Fragment控制器  使用recyclerview控制底部栏 灵活变换
 * 创 建 人：SDX
 * 创建日期：2017/7/14 14:24
 * 修订历史：
 * 修 改 人：
 */
public class TFragmentController {

    /**
     * 显示的Fragment
     */
    private int containerId;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;
    private TWelcomeEntity mWelcomeEntity;
    private static TFragmentController controller;
    private static boolean isReload;
    /**
     * 用于保存初始化显示按钮的位置
     */
    private int mHomePos;

    public static TFragmentController getInstance(FragmentActivity activity, int containerId, boolean isReload, TWelcomeEntity welcomeEntity) {
        TFragmentController.isReload = isReload;
        if (controller == null) {
            controller = new TFragmentController(activity, containerId, welcomeEntity);
        }
        return controller;
    }

    public static void onDestroy() {
        controller = null;
    }

    private TFragmentController(FragmentActivity activity, int containerId, TWelcomeEntity welcomeEntity) {
        this.containerId = containerId;
        this.mWelcomeEntity = welcomeEntity;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        if (isReload) {
            initFro();
//            fragments.add(new TMyFragment());
//            fragments.add(new TMyFragment());
//            fragments.add(new TMyFragment());
//            fragments.add(new TMyFragment());
            FragmentTransaction ft = fm.beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {
                ft.add(containerId, fragments.get(i), "" + i);
            }
            ft.commitAllowingStateLoss();
        } else {
            for (int i = 0; i < mWelcomeEntity.getList().getFooter_list().size(); i++) {
                fragments.add(fm.findFragmentByTag(i + ""));
            }
        }
    }

    /**
     * 添加对应的界面
     */
    private void initFro() {
        for (int i = 0; i < mWelcomeEntity.getList().getFooter_list().size(); i++) {
            if (mWelcomeEntity.getList().getFooter_list().get(i).getHref().equals("home")) {
                mHomePos = i;
                fragments.add(new TCircleFragment());
                mWelcomeEntity.getList().getFooter_list().get(i).setIcon_true(0);//设为选中状态
            } else if (mWelcomeEntity.getList().getFooter_list().get(i).getHref().equals("category_list")) {
                fragments.add(new TPlanetFragment());
            } else if (mWelcomeEntity.getList().getFooter_list().get(i).getHref().equals("mine")) {
                fragments.add(new TMyFragment());
            } else {
                fragments.add(new TMyFragment());
            }
        }
    }

    /**
     * 显示界面
     * 选择调用
     *
     * @param position 对应的位置
     */
    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.show(fragment);
        ft.commit();
    }

    /**
     * 隐藏界面
     */
    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 初始化调用
     */
    public void showFragment() {
        showFragment(mHomePos);
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}