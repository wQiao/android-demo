package org.wqiao.coolweather.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.databinding.UserDetailBinding;
import org.wqiao.coolweather.model.User;

/**
 * Created by wQiao on 16-4-15.
 */
public class UserDetailActivity  extends  BaseActivity{

    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserDetailBinding binding =DataBindingUtil.setContentView(this, R.layout.activity_user_detail); //自定义bind class

        final Intent intent = getIntent();

        user =  (User) intent.getSerializableExtra("user");

        binding.setUser(user);

        Button saveBtn = (Button) findViewById(R.id.button_save);

        saveBtn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                user.setName("Cike");
//                user.setEmail("Cike456@163.com");
                Log.i("User ---> name",user.getName());
                Log.i("User --> email",user.getEmail());
                Intent intent1 = new Intent();
                intent1.putExtra("user" ,user);
                setResult(RESULT_OK,intent1);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("UserDetailActivity","onBackPressed");
    }
}
