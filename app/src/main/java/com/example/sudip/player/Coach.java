package com.example.sudip.player;

public class Coach {


        private long  coachID;
        private String Name;
        private String Email;
        private Character Gender;
        private String Profession;
        private String username;
        private String goalIDs;
        private String DoBs;
        //constructor
        public Coach(
                long  coachID, String Name, String Email,Character Gender, String Profession,String username,String goalIDs,String DoBs){

            this.coachID = coachID;
            this.Name = Name;
            this.Email = Email;
            this.Gender = Gender;
            this.Profession = Profession;
            this.username=username;
            this.goalIDs=goalIDs;
            this.DoBs=DoBs;
        }

        //getters
        public long getCoachID() { return coachID; }
        public String getName() {return Name; }
        public String getEmail() {return Email; }
        public Character getGender(){ return Gender; }
        public String getProfession(){ return Profession; }
        public String getUsername() {return username; }
        public String getGoalIDs() {return goalIDs; }
        public String getDoBs() {return DoBs; }
    }




