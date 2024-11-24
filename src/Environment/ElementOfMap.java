package Environment;

import Objects.ArmoredDroid;
import Objects.Droid;
import Objects.SupportDroid;

public class ElementOfMap {
    private int  value ;
    private Droid  droid ;
    ElementOfMap(){}
    ElementOfMap(int value){
        this.value = value;
        this.droid = null;
    }
    ElementOfMap(int value , Droid droid ){
        this.value = value;
        if(droid instanceof ArmoredDroid) {
            this.droid = new ArmoredDroid((ArmoredDroid) droid);
        }else if(droid instanceof SupportDroid){
            this.droid = new SupportDroid((SupportDroid) droid);
        }else if(droid instanceof Droid){
            this.droid = new Droid( droid);
        }
    }
    ElementOfMap(ElementOfMap another){
        this.value = another.value;
        this.droid = another.droid;
    }
    public int getValue(){
        return this.value;
    }
    public Droid getDroid(){
        return  this.droid;
    }
    public void setValue(int newValue){
        this.value = newValue;
    }
    public void  setDroid(Droid newDroid){
        this.droid = newDroid;
    }
}
