package org.wqiao.coolweather.rest;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by wQiao on 16-4-29.
 */
public class RestClient {

    public static final String API_BASE_URL  = "http://wechat.wqiao.me/";

    private static RestClient instance = new RestClient();

    private Retrofit retrofit;

    private RestClient() {
        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(MyType2.class, new MyTypeAdapter())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(createHttpClient())
                .build();
    }

    private static OkHttpClient createHttpClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(new Cache(context.getCacheDir(), 10 * 1024*1024));

        builder.addInterceptor(new Interceptor() { //Add Query Parameters to Every Request
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", "your-actual-api-key")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    public static <T> T create(Class<T> clazz) {
        return instance.retrofit.create(clazz);
    }
}
