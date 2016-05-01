package org.wqiao.coolweather.model;

import java.io.Serializable;

/**
 * Created by wQiao on 16-4-30.
 */
public class Param implements Serializable {

    private Long id;

    private String title;

    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //////////////////////////////////////////////////////
    //  data binging
    //////////////////////////////////////////////////////
//    @BindingAdapter({"bind:selection"})
//    public static void selection(Spinner view, Param item) {
//        view.getse
//    }
}
