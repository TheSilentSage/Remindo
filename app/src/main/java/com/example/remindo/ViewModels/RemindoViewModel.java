package com.example.remindo.ViewModels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RemindoViewModel {
    int priority;
    String taskName;
    String taskDescription;
//    String taskDuration = "Wed 03 May,2023 11:22 AM";
    String taskDuration;
    Boolean isDone;
    Date endDate;
    SimpleDateFormat parseFormat;

    public RemindoViewModel(){
    };

    public RemindoViewModel(int priority, String taskName, String taskDescription, String taskDuration, Boolean isDone) {
        this.priority = priority;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDuration = taskDuration;
        this.isDone = isDone;
        parseFormat = new SimpleDateFormat("EEE dd MMM,yyyy hh:mm aa");
        try {
            endDate = parseFormat.parse(taskDuration);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public String getTaskDuration() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEE, dd MMM");
        return parseFormat.format(endDate) ;
    }

    public String getTaskTime() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm aa");
        return parseFormat.format(endDate) ;
    }

    public void setTaskDuration(String taskDuration) {
        this.taskDuration = taskDuration;
    }
    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getRemainingDuration(){
        Date cal_date = Calendar.getInstance().getTime();

        long different = endDate.getTime() - cal_date.getTime();


        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        if(elapsedDays==0){
            return elapsedHours + " hrs " + elapsedMinutes + " mins";
        } else if (elapsedHours==0) {
            return elapsedMinutes + " mins";
        }

        return elapsedDays + " days " + elapsedHours + " hrs " + elapsedMinutes + " mins";

    }
}
