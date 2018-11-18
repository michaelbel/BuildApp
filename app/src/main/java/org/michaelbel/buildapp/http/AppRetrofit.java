package org.michaelbel.buildapp.http;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class AppRetrofit {

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
            .baseUrl(ApiConfig.GITHUB_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}