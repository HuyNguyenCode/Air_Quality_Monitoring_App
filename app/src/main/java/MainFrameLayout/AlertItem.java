package MainFrameLayout;

public class AlertItem {
    private String alertTitle;
    private String alertTime;

    public String getAlertTitle() {
        return alertTitle;
    }

    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    public String getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(String alertTime) {
        this.alertTime = alertTime;
    }

    public AlertItem(String alertTitle, String alertTime) {
        this.alertTitle = alertTitle;
        this.alertTime = alertTime;
    }
}
