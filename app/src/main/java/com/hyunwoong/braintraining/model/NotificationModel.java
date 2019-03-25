package com.hyunwoong.braintraining.model;

public class NotificationModel {

    private String to;
    private Notification notification;
    private Data data;

    public Notification getNotification() {
        notification = new Notification();
        return notification;
    }

    public Data getData() {
        data = new Data();
        return data;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static class Notification {

        public String title;
        public String text;
    }

    public static class Data {
        public String title;
        public String text;
    }
}
