package com.example.sudip.player;


public class Property {

    //property basics
    private long  taskID;
    private String TaskName;
    private String GoalID;
    private String People;
    private String description;
    private String image;
    private Double price;
    private int points;
    private int timeleft;
    private int completion;
    private Boolean featured;

    //constructor
    public Property(
            long taskID, String TaskName, String GoalID, String People,
            String description, String image, int points, int timeleft, int completion, Boolean featured){

        this.taskID = taskID;
        this.TaskName =TaskName;
        this.GoalID = GoalID;
        this.People = People;
        this.description = description;
        //this.price = price;
        this.image = image;
        this.points = points;
        this.timeleft = timeleft;
        this.completion = completion;
        this.featured = featured;
    }

    //getters
    public long getTaskID() { return taskID; }
    public String getTaskName() {return TaskName; }
    public String getGoalID() {return GoalID; }
    public String getPeople() {return People; }
    public String getDescription() {return description; }
    //public Double getPrice() {return price; }
    public String getImage() { return image; }
    public int getPoints(){ return points; }
    public int getTimeleft(){ return timeleft; }
    public int getCompletion(){ return completion; }
    public Boolean getFeatured(){return featured; }
}
