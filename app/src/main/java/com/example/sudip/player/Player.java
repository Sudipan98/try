package com.example.sudip.player;

public class Player {

        private String  playerID;
        private String Name;
        private String Email;
        private String Gender;
        private String College;
        private String username;
        private String taskIDs;
        private String DoBs;
             //constructor
        public Player(
                String  playerID, String Name, String Email,String Gender, String College,String username,String taskIDs,String DoBs){

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
        public String getPlayerID() { return playerID; }
        public String getName() {return Name; }
        public String getEmail() {return Email; }
        public String getGender(){ return Gender; }
        public String getCollege(){ return College; }
        public String getUsername() {return username; }
        public String getTaskIDs() {return taskIDs; }
        public String getDoBs() {return DoBs; }
}


