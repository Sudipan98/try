package com.example.sudip.player;

public class Completed {
    private long  taskID;

    private String GoalID;

    private int points;
    private int man_hours;
    private Boolean featured;

    //constructor
    public Completed(
            long taskID,  String GoalID, int points, int man_hours,  Boolean featured){

        this.taskID = taskID;
        this.GoalID = GoalID;
        this.points = points;
        this.man_hours = man_hours;
        this.featured = featured;
    }

    //getters
    public long getTaskID() { return taskID; }

    public String getGoalID() {return GoalID; }

    public int getPoints(){ return points; }
    public int getTimeleft(){ return man_hours; }

    public Boolean getFeatured(){return featured; }
}