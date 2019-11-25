package com.missouristate.guadagnano.countingthreadapp;

public class Timer {
    // TIME ELEMENTS
    private long mStartTime;
    private long mTimeUpdate;
    private long mStoredTime;

    public Timer() {
        mStartTime = 0L;
    }

    public void setStartTime(long startTime){
        mStartTime = startTime;
    }

    public long getStartTime(){
        return mStartTime;
    }
    public void setTimeUpdate(long timeUpdate){
        mTimeUpdate = timeUpdate;
    }
    public long getTimeUpdate(){
        return mTimeUpdate;
    }
    public void addStoredTime(long timeInMilliseconds){
        mStoredTime += timeInMilliseconds;
    }
    public long getStoredTime(){
        return mStoredTime;
    }
}
