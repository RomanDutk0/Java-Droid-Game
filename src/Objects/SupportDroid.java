package Objects;
import java.util.Random;
import java.util.Scanner;

public class SupportDroid extends  Droid{
    private double healing;
    public SupportDroid(){}
    public SupportDroid(String name, double health, Weapon weapon, double healing) {
        super(name,  health, weapon );
        this.healing = healing;
    }
    public SupportDroid(String name, int Xposition, int Yposition, double health, Weapon weapon, double healing) {
        super(name, Xposition , Yposition,  health, weapon );
        this.healing = healing;
    }
    public SupportDroid(SupportDroid another){
        this.name = another.name;
        this.helth = another.helth;
        this.weapon = another.weapon;
        this.healing = another.healing;
        this.Xposition = another.Xposition;
        this.Yposition = another.Yposition;
    }
    
    @Override
    public String toString()
    {
        return ("\uD83D\uDC7EDroid Name: " + this.name + "  \u2764\uFE0FHealth: " + this.helth +"  \uD83D\uDC8AHealing: " + this.healing + this.weaponChek());
    }
    
    public double getHealing()
    {
        return this.healing;
    }
    
    public void setHealing(double newHealing)
    {
        this.healing = newHealing;
    }
    
    public void toHeal(Droid target)
    {
        if(healing > 0) {
            Random random = new Random();
            double hp = target.getHelth();
            hp += random.nextDouble(this.healing + 1);
            target.setHelth(hp);
        }
    }
}

