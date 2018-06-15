package com.example.sudip.player;

public class Player {

        private long  playerID;
        private String Name;
        private String Email;
        private Character Gender;
        private String College;
        private String username;
        private String taskIDs;
        private String DoBs;
             //constructor
        public Player(
                long  playerID, String Name, String Email,Character Gender, String College,String username,String taskIDs,String DoBs){

            this.playerID = playerID;
            this.Name = Name;
            this.Email = Email;
            this.Gender = Gender;
            this.College = College;
            this.username=username;
            this.taskIDs=taskIDs;
            this.DoBs=DoBs;
        }

        //getters
        public long getPlayerID() { return playerID; }
        public String getName() {return Name; }
        public String getEmail() {return Email; }
        public Character getGender(){ return Gender; }
        public String getCollege(){ return College; }
        public String getUsername() {return username; }
        public String getTaskIDs() {return taskIDs; }
        public String getDoBs() {return DoBs; }
}


