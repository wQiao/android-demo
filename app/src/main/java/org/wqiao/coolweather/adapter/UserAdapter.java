package org.wqiao.coolweather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import org.wqiao.coolweather.R;
import org.wqiao.coolweather.databinding.ViewUserItemBinding;
import org.wqiao.coolweather.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wQiao on 16-4-15.
 */
public class UserAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<User> projects  =new ArrayList<>();
//    private UserListActivity context;
    private int index =0;

    public UserAdapter(Context context) {
        this.inflater=LayoutInflater.from(context);
//        this.context=context;
        initData();
//        resourceId = textViewResourceId;
    }
    private void initData(){
        for (int i=0;i<15;i++){
            index=index+1;
            User u = new User();
            u.setId((long) index);
            u.setName("wQiao"+index);
            u.setEmail("wQiao"+index+"@163.com");
            StringBuilder logoUrl = new StringBuilder("http://image.wQiao.me/static/avatar");
            if(index%2==0) {
                logoUrl.append("1");
            }else{
                logoUrl.append("2");
            }
            logoUrl.append(".jpg");
            u.setLogoUrl(logoUrl.toString());
            projects.add(u);
        }

    }


    public void addItems(@Nullable List<User> items){
        if(!items.isEmpty()) {
            projects.addAll(items);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return projects.size();
    }

    @Override
    public Object getItem(int position) {
        return projects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewUserItemBinding binding ;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.view_user_item, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ViewUserItemBinding) convertView.getTag();
        }
        User user  = projects.get(position);
        binding.setUser(user);
//        View view = inflater.inflate(R.layout.view_user_item,null);
//        UserItemBinding binding = UserItemBinding.inflate(inflater, null, false);
//        TextView userName = (TextView) view.findViewById(R.id.user_name);
//        TextView userEmail = (TextView) view.findViewById(R.id.user_email);
//        userName.setText(user.getName());
//        userEmail.setText(user.getEmail());
        Log.d("UserAdapter", "getView: userName:"+user.getName()+" email:"+user.getEmail());
        return convertView;
    }
//    public void  updateData(User user){
//
//    }
}
