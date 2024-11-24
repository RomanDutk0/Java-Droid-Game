package Objects;
import Environment.Map;

import java.util.Scanner;

public class Droid {
    protected String name = "Droid#";
    protected int Xposition = 0;
    protected int Yposition = 0;
    protected  double helth = 1;
    protected Weapon weapon;
    public Droid(){};
    
    public Droid(String name, double helth, Weapon weapon)
    {
        this.name = name;
        this.helth = helth;
        this.weapon = weapon;
    }
    public Droid(String name, int Xposition, int Yposition, double helth, Weapon weapon)
    {
        this.name = name;
        this.helth = helth;
        this.weapon = weapon;
        this.Xposition = Xposition;
        this.Yposition = Yposition;
    }
    public Droid (Droid another){
        this.name = another.name;
        this.helth = another.helth;
        this.weapon = another.weapon;
        this.Xposition = another.Xposition;
        this.Yposition = another.Yposition;
    }
    @Override
    public String toString()
    {
        return  ("\uD83E\uDD16Droid Name: " + this.name +" \u2764\uFE0FHealth: " + this.helth + this.weaponChek());
    }
    
    public String weaponChek(){
        if (this.weapon != null) {
            return (" \uD83D\uDD2BWeapon: " +this.weapon.getName());
        } else {
            return "\uD83D\uDD2BWeapon: None";
        }
    }
    public String getName()
    {
        return this.name;
    }
    
    public int getXposition ()
    {
        return this.Xposition ;
    }
    
    public int getYposition()
    {
        return this.Yposition;
    }
    
    public void setXposition(int newX)
    {
        this.Xposition = newX;
    }
    
    public  void  setYposition(int newY)
    {
        this.Yposition = newY;
    }
    
    public void setName(String newName)
    {
        this.name = newName;
    }
    
    public double getHelth()
    {
        return this.helth;
    }
    
    public void setHelth(double newHelth)
    {
        this.helth = newHelth;
    }
    
    public void setWeapon(Weapon newWeapon)
    {
        this.weapon = newWeapon;
    }
    
    public Weapon getWeapon()
    {
        return this.weapon;
    }
    
    public double  HPdecrease(Weapon weapon, double distance)
    {
        double impact = weapon.toShoot(distance);
        this.helth -= impact;
        return impact ;
    }
    
    public double toHit(Droid another, double distance)
    {
        if(this.isAlive() && another.isAlive()) {
            return another.HPdecrease(this.weapon, distance);
        }else{
            return -1;
        }
    }
    
    public boolean isAlive()
    {
        return (this.helth > 0) ? true : false;
    }
    
    public void droidMove(int Step)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\t(w)\u2B06\uFE0F\n" +
                "(a)\u2B05\uFE0F\t\u27A1\uFE0F(d)\n\ts)\u2B07\uFE0F");
        char answer ;
        switch (answer= scan.next().charAt(0))
        {
            case('w'):
                this.Xposition-=Step;
                break;
            case('s'):
                this.Xposition+=Step;
                break;
            case('a'):
                this.Yposition-=Step;
                break;
            case('d'):
                this.Yposition+=Step;
                break;
            default:
                break;
        }
    }
}
