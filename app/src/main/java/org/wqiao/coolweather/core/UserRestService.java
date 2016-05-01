package org.wqiao.coolweather.core;

import org.wqiao.coolweather.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * User Rest 访问服务
 */
public interface UserRestService {
    String KEY_OFFSET = "offset";
    String KEY_LIMIT = "limit";

    /*@Headers({
                "Accept: application/vnd.yourapi.v1.full+json",
                "User-Agent: Your-App-Name"
        })*/
    @GET("api/users")
    Call<List<User>> query(@Query(KEY_LIMIT) int limit, @Query(KEY_OFFSET) int offset, @QueryMap Map<String, Object> options);//options 动态参数

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") Long id);

    @POST("api/users")
    Call<User> create(@Body User user);

    @PUT("api/users/{id}")
    Call<User> update(@Path("id") Long id, @Body User user);

    @DELETE("api/users/{id}/")
    Call<Void> delete(@Path("id") Long id);

}
