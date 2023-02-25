package com.example.mylifeinorder1.model;

import com.example.mylifeinorder1.activity.ActivityType;

public class LioItem {

    private int mIconResource;
    private String mText;

    private ActivityType activityType;

    public LioItem(int mIconResource, String mText, ActivityType activityType) {
        this.mIconResource = mIconResource;
        this.mText = mText;
        this.activityType = activityType;
    }

    public int getmIconResource() {
        return mIconResource;
    }

    public String getmText() {
        return mText;
    }

    public ActivityType getActivityType() {
        return activityType;
    }
}
