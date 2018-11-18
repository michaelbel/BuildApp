package org.michaelbel.buildapp.service;

import org.michaelbel.buildapp.json.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersService {

    @GET("users/{username}")
    Call<User> getUser(
       @Path("username") String username
    );
}