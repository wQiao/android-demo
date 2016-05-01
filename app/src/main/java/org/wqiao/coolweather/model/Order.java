package org.wqiao.coolweather.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import org.wqiao.coolweather.BR;

import java.io.Serializable;

/**
 * Created by wQiao on 16-4-24.
 */
public class Order extends BaseObservable implements Serializable {

    public Long id;

    public String content;

    public String details;

    //    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
//        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (null == this.content || !this.content.equals(content)) {
            this.content = content;
            notifyPropertyChanged(BR.content);
        }
    }

    @Bindable
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        notifyPropertyChanged(BR.details);
    }

}
