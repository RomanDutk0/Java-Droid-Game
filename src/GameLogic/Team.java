package GameLogic;

import Objects.Droid;

public class Team {
    private String name;
    private Droid[] droids;
    public Team(){}
    public Team(String name, Droid[] droids){
        this.name= name;
        this.droids = droids;
    }
    public Team(Team another){
        this.name= another.name;
        this.droids = another.droids;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public String getName(){
        return this.name;
    }
    public Droid[] getDroids(){
        return this.droids;
    }
    public void setDroids(Droid[] newDroids){
        this.droids = newDroids;
    }
    public void setDroid(Droid newDroids,int i){
        this.droids[i] = newDroids;
    }
}
