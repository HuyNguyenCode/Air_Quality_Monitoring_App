package com.example.air_quality_monitoring_app.api;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("access_token")
    private String token;

    @SerializedName("expires_in")
    private float expires;
    @SerializedName("refresh_expires_in")
    private float rexpires;
    @SerializedName("refresh_token")
    private String reToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("not-before-policy")
    private float policy;
    @SerializedName("session_state")
    private String session;
    @SerializedName("scope")
    private String scope;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public float getExpires() {
        return expires;
    }

    public void setExpires(float expires) {
        this.expires = expires;
    }

    public float getRexpires() {
        return rexpires;
    }

    public void setRexpires(float rexpires) {
        this.rexpires = rexpires;
    }

    public String getReToken() {
        return reToken;
    }

    public void setReToken(String reToken) {
        this.reToken = reToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public float getPolicy() {
        return policy;
    }

    public void setPolicy(float policy) {
        this.policy = policy;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", expires=" + expires +
                ", rexpires=" + rexpires +
                ", reToken='" + reToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", policy=" + policy +
                ", session='" + session + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

    public Token(String token, float expires, float rexpires, String reToken, String tokenType, float policy, String session, String scope) {
        this.token = token;
        this.expires = expires;
        this.rexpires = rexpires;
        this.reToken = reToken;
        this.tokenType = tokenType;
        this.policy = policy;
        this.session = session;
        this.scope = scope;
    }
}
