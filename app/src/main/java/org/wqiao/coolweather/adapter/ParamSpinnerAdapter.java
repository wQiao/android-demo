package org.wqiao.coolweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import org.wqiao.coolweather.core.AppApi;
import org.wqiao.coolweather.core.ParamRestService;
import org.wqiao.coolweather.databinding.ItemSpinParamBinding;
import org.wqiao.coolweather.model.Param;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wQiao on 16-4-30.
 */
public class ParamSpinnerAdapter extends BaseAdapter  {

    private List<Param> items= new ArrayList<>();
    private  Context context;
    public ParamSpinnerAdapter(Context context) {
        this.context =context;
            loadData();
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSpinParamBinding  binding;
        if( null == convertView){
//            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_spin_param,parent,false); // 使用简便方法
            binding = ItemSpinParamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        }else{
            binding = (ItemSpinParamBinding) convertView.getTag();
        }
        binding.setParam(items.get(position));
        return convertView;
    }

    /**
     *   远程请求数据（测试）
     */
    private void loadData() {
        ParamRestService service = AppApi.create(ParamRestService.class);

        Call<List<Param>> call = service.query(111);

        call.enqueue(new Callback<List<Param>>() {
            @Override
            public void onResponse(Call<List<Param>> call, Response<List<Param>> response) {
                if (response.isSuccessful()) {
                    List<Param> result =response.body();
                    if(null != result && !result.isEmpty()){
                        items.addAll(result);
                        notifyDataSetChanged();
                    }
                } else {
                    showMessage(response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Param>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
