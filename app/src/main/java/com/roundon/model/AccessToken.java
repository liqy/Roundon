package com.roundon.model;

/**
 * Created by liqy on 15/12/17.
 */
public class AccessToken {
    public String access_token;
    public String token_type;
    public long expires_in;
    public String refresh_token;
    public String scope;
    public String created_at;

    @Override
    public String toString() {
        return "AccessToken{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
