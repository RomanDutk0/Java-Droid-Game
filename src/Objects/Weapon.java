package Objects;
import java.util.Random;
public class Weapon {
    private String name;
    private double mindamage;
    private double maxdamage;
    private double  distanse;
    
    Weapon(){}
    public Weapon(String name, double mindamage, double maxdamage, double distanse)
    {
        this.name = name;
        this.maxdamage = maxdamage;
        this.mindamage = mindamage;
        this.distanse = distanse;
    }
    public Weapon(Weapon another){
        this.name = another.name;
        this.distanse = another.distanse;
        this.maxdamage = another.maxdamage;
        this.mindamage = another.mindamage;
    }
    @Override
    public String toString(){
        String output = (
                 "\n\t\uD83D\uDD2BName:" + this.name
                +"\n\t\uD83D\uDD36Max damage:" + this.maxdamage
                +"\n\t\uD83D\uDD38Min damage:" + this.mindamage
                +"\n\t\uD83C\uDFAFDistanse:" + this.distanse);
        return output;
    }
    
    public String getName ()
    {
        return this.name;
    }
    
    public void setName(String newName)
    {
        this.name = newName;
    }
    
    public double  getMaxdamage ()
    {
        return this.maxdamage;
    }
    
    public void setMaxdamage(double maxdamage)
    {
        this.maxdamage = maxdamage;
    }
    
    public double getMindamage()
    {
        return this.mindamage;
    }
    
    public void setMindamage(double mindamage)
    {
        this.mindamage = mindamage;
    }
    
    public double getDistanse()
    {
        return this.distanse;
    }
    
    public void setDistanse(double distanse)
    {
        this.distanse = distanse;
    }
    
    public double toShoot(double distanse)
    {
        Random random = new Random();
        double impact;
        if(this.distanse < distanse)
        
        {
            impact = random.nextDouble(this.maxdamage - this.mindamage + 1) + this.mindamage;
            //System.out.println("Не максимальний урон ="+impact);
        }
        else
        {
            impact  = this.maxdamage;
            //System.out.println("Максимальний урон ="+impact);
        }
        return impact;
    }
    
    
}
