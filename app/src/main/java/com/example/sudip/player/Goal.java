package com.example.sudip.player;

public class Goal {
    private String taskID;
    private long goalID;
    private double Time;
    private String userIDs;
    //constructor
    public Goal(
            String  taskID, long goalID,double time,String userIDs){

        this.taskID = taskID;
        this.goalID = goalID;
        this.Time = time;
        this.userIDs=userIDs;

    }
    //getters
    public String getTaskID() { return taskID; }
    public long getgoalID() {return goalID; }
    public double gettime(){ return Time; }
    public String getUserIDs() {return userIDs; }
}
