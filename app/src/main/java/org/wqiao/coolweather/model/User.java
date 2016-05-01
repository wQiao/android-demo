package org.wqiao.coolweather.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.wqiao.coolweather.BR;
import org.wqiao.coolweather.R;

import java.io.Serializable;

/**
 * Created by wQiao on 16-4-12.
 */
public class User extends BaseObservable implements Serializable {

    public  Long id;

    private String name;

    private String email;

    private String  logoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (null == this.name || !this.name.equals(name)) {
            this.name = name;
            notifyPropertyChanged(BR.name);
        }
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(null == this.email || !this.email.equals(email)) {
            this.email = email;
            notifyPropertyChanged(BR.email);
        }
    }

//    @Bindable
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext()).load(url).placeholder(R.drawable.avatar_default).error(error).into(view);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).placeholder(R.drawable.avatar_default).into(view);
    }
}
