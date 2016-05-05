package org.wqiao.coolweather.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.adapter.ParamSpinnerAdapter;
import org.wqiao.coolweather.databinding.ActivityFirstBinding;

public class FirstActivity extends BaseActivity {

//    private TextView userName;

//    private Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first);
       final ActivityFirstBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

       // getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent data = getIntent();
        Log.i("userName --->",data.getStringExtra("userName"));

        binding.button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,UserListActivity.class);
                startActivity(intent);
            }
        });

        binding.spinParams.setAdapter(new ParamSpinnerAdapter(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode ==RESULT_OK){

                }
            default:
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}
