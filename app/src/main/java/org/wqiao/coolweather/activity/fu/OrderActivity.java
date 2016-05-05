package org.wqiao.coolweather.activity.fu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.activity.BaseActivity;
import org.wqiao.coolweather.model.Order;

public class OrderActivity extends BaseActivity implements OrderListFragment.OnListFragmentInteractionListener, OrderDetailFragment.OnFragmentInteractionListener {

    private final String tabListTag = "tab1", tabDetailTag = "tab2";

    private Order selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initToolbar();

        FragmentManager fm = getSupportFragmentManager();
        // 使用R.id.fragment_order_container，向FragmentManager请求获取fragment。如要获取的fragment在队列中已经存在，FragmentManager随即会将之返还。
        Fragment fragment = fm.findFragmentById(R.id.fragment_order_container);

        if (savedInstanceState == null) {
            // 如指定容器视图资源ID的fragment不存在，则fragment变量为空值。
            // 这时应创建一个新的OrderListFragment，并创建一个新的fragment transaction用来把新建的fragment添加到队列中。
            if (fragment == null) {
                fragment = new OrderListFragment(); //OrderListFragment.newInstance(1);
                fm.beginTransaction()
                        .add(R.id.fragment_order_container, fragment, tabListTag)
                        .commit();
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.m_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onListFragmentInteraction(Order item) {
        selected = item;
        Log.d("onListFrInteraction -->", item.getId().toString());
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentByTag(tabDetailTag);

        FragmentTransaction fTransaction = fm.beginTransaction();

        fTransaction
                .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                .hide(fm.findFragmentByTag(tabListTag));

        if (fragment == null) {

            fragment = new OrderDetailFragment();

            Bundle bundle = new Bundle();

            bundle.putSerializable("order", item);

            fragment.setArguments(bundle);

            fTransaction.add(R.id.fragment_order_container, fragment, tabDetailTag);
        } else {
            fragment.getArguments().putSerializable("order", item);

            fTransaction.show(fragment);
        }

//        fTransaction.replace(R.id.fragment_order_container,fragment);

        //fTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

//        fTransaction.addToBackStack(null);

        fTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Order item) {
        backToListFragment();
        selected.setContent(item.getContent());
//        fm.popBackStack();
    }

    private void backToListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
                .hide(fm.findFragmentByTag(tabDetailTag))
                .show(fm.findFragmentByTag(tabListTag))
                .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tabDetailTag);
        if (fragment != null && fragment.isVisible()) {
            backToListFragment();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        selected = null;
    }
}
