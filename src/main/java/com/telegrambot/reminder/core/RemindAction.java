package com.telegrambot.reminder.core;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RemindAction extends TimerTask {
    private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;


    private final static int TWO_AM = 2;
    private final static int ZERO_MINUTES = 0;


    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        long stopTime = currentTime + 1000*60*60*2;//provide the 2hrs time it should execute 1000*60*60*2
        while (stopTime != System.currentTimeMillis()) {
            // Do your Job Here
            System.out.println("Start Job" + stopTime);
            System.out.println("End Job" + System.currentTimeMillis());
        }
    }

    private static Date getTomorrowMorning2AM() {

        Date date2am = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date2am);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return date2am;
    }


    //call this method from your servlet init method
    public static void startTask() {
        RemindAction task = new RemindAction();
        Timer timer = new Timer();
        timer.schedule(task, getTomorrowMorning2AM(), 1000 * 60 * 60 * 24);
    }
}
