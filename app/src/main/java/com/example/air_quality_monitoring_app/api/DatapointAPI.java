package com.example.air_quality_monitoring_app.api;

import com.google.gson.annotations.SerializedName;

public class DatapointAPI {

    @SerializedName("realm")
    private String realm;

    @SerializedName("realmId")
    private String realmId;

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;
    @SerializedName("enable")
    private boolean enable;
    @SerializedName("createdOn")
    private float createdOn;
    @SerializedName("serviceAccount")
    private boolean serviceAccount;
    @SerializedName("username")
    private String username;

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public float getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(float createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(boolean serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
