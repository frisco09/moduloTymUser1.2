package com.tymtorneos.modulotymuser1.retrofit;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiResponseInterceptor implements Interceptor {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final Gson GSON = new Gson();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        final ResponseBody body = response.body();
        ApiResponse ApiResponse = GSON.fromJson(body.string(), ApiResponse.class);
        body.close();

        // TODO any logic regarding ApiResponse#status or #code you need to do

        final Response.Builder newResponse = response.newBuilder()
                .body(ResponseBody.create(JSON, ApiResponse.data.toString()));
        return newResponse.build();
    }
}
