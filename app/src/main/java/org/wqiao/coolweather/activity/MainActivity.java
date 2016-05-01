package org.wqiao.coolweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.activity.fu.OrderActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        Log.d("MainActivity",this.toString());
        FloatingActionButton fabtn = (FloatingActionButton) findViewById(R.id.fab);
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this,FirstActivity.class);

                intent.putExtra("userName","wQiao");

//                User user = new User();
//                user.setName("wQiao");
//                user.setEmail("Cike@163.com");
//                intent.putExtra("user",user);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left); //动画效果
            }
        });
    }

    @NotNull
    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //隐藏标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.abc_ic);
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.d("onOptionsItemSelected",String.valueOf(item.getItemId()));
         switch(item.getItemId()){
             case R.id.action_settings:
                 Toast.makeText(this,"系统设置",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.action_orders:
                 gotoOrders();
                 break;
             case android.R.id.home:
                 Log.d("onOptionsItemSelected", "home ");
                 break;
             default:

         }
        return super.onOptionsItemSelected(item);
    }

    private  void gotoOrders(){
        Intent intent = new Intent(MainActivity.this,OrderActivity.class);

        startActivity(intent);
    }
}
