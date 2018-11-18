package org.michaelbel.buildapp.json;

import com.squareup.moshi.Json;

public class User {
    @Json(name = "login") public String login;
    @Json(name = "id") public int id;
    @Json(name = "name") public String name;
    @Json(name = "avatar_url") public String avatarUrl;
    @Json(name = "html_url") public String htmlUrl;
}