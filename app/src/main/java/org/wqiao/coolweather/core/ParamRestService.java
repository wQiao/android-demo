package org.wqiao.coolweather.core;

import org.wqiao.coolweather.model.Param;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by wQiao on 16-4-30.
 */
public interface ParamRestService {

    @GET("params/3/{type}")
    Call<List<Param>> query( @Path("type") int type);
}
