package org.wqiao.coolweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.adapter.UserAdapter;
import org.wqiao.coolweather.core.AppApi;
import org.wqiao.coolweather.core.UserRestService;
import org.wqiao.coolweather.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wQiao on 16-4-15.
 */
public class UserListActivity extends BaseActivity {

    private UserAdapter adapter;

    private User selected;

    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        adapter = new UserAdapter(UserListActivity.this);

        ListView listView = (ListView) findViewById(R.id.listview_user);

        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1) {
                    ((UserAdapter)view.getAdapter()).loadData();
//                    loadData(view.getLastVisiblePosition());
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                selected = (User) listView.getItemAtPosition(position);
                Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
                intent.putExtra("user", selected);
                startActivityForResult(intent, 1);

            }
        });

        mSwipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_list_user);
        mSwipeLayout.setDistanceToTriggerSync(200);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.loadData();
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult Code:", requestCode + "");
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    User user = (User) data.getSerializableExtra("user");
                    Log.i("onActivityResult", "begin");
                    Log.i("User:name", user.getName());
                    Log.i("User: email", user.getEmail());
                    selected.setName(user.getName());
                    selected.setEmail(user.getEmail());
                }
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selected = null;
        adapter = null;
        mSwipeLayout = null;
    }

    // ************** 自定义方法*****************

    /**
     *   远程请求数据（测试）
     * @param offset
     */
    private void loadData(int offset) {
        UserRestService service = AppApi.create(UserRestService.class);

        Map<String,Object> queryParams = new HashMap<>();

        Call<List<User>> call = service.query(0,20,queryParams);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    adapter.addItems(response.body());
                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
