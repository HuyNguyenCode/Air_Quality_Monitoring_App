package com.example.air_quality_monitoring_app;

public class ActionItem {
    private String actionTitle;
    private String actionDes;
    private int actionImg;

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public String getActionDes() {
        return actionDes;
    }

    public void setActionDes(String actionDes) {
        this.actionDes = actionDes;
    }

    public int getActionImg() { return actionImg;}

    public void setActionImg(int actionImg) {this.actionImg = actionImg;}

    public ActionItem(String actionTitle, String actionDes, int actionImg) {
        this.actionTitle = actionTitle;
        this.actionDes = actionDes;
        this.actionImg = actionImg;
    }


}
