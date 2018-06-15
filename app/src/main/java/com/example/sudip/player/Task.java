package com.example.sudip.player;

public class Task {


        private long  taskID;
        private long goalID;
        private String GITRepLink;
        private double Time;
        private int points;
        private int priorityLevel;
        private String userIDs;
        //constructor
        public Task(
                long  taskID, long goalID, String GITRepLink,double time, int points,int priorityLevel,String userIDs){

            this.taskID = taskID;
            this.goalID = goalID;
            this.GITRepLink = GITRepLink;
            this.Time = time;
            this.points = points;
            this.priorityLevel=priorityLevel;
            this.userIDs=userIDs;

        }

        //getters
        public long getTaskID() { return taskID; }
        public long getgoalID() {return goalID; }
        public String getGITRepLink() {return GITRepLink; }
        public double gettime(){ return Time; }
        public int getPoints(){ return points; }
        public int getPriorityLevel() {return priorityLevel; }
        public String getUserIDs() {return userIDs; }

    }


