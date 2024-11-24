package Objects;

public class ArmoredDroid extends Droid{
    private double armor ;
    public ArmoredDroid() {}
    public ArmoredDroid(String name, double health, Weapon weapon, double armor) {
        super(name, health, weapon );
        this.armor = armor;
    }
    public ArmoredDroid(String name, int Xposition, int Yposition, double health, Weapon weapon, double armor) {
        super(name, Xposition , Yposition,  health, weapon );
        this.armor = armor;
    }
    public ArmoredDroid(ArmoredDroid another){
        this.name = another.name;
        this.helth = another.helth;
        this.armor = another.armor;
        this.weapon = another.weapon;
        this.Xposition = another.Xposition;
        this.Yposition = another.Yposition;
    }
    
    @Override
    public String toString() {
        return ("\uD83D\uDDFFDroid Name: " + this.name + "  \u2764\uFE0FHealth: " + this.helth + "  \uD83E\uDE96Armor: " + this.armor + this.weaponChek());
    }
    
    public double getArmor()
    {
        return this.armor;
    }
    
    public void setArmor(double armor)
    {
        this.armor = armor;
    }
    
    @Override
    public double HPdecrease(Weapon weapon, double distance)
    {
        double impact = weapon.toShoot(distance);
        if(this.armor <= 0)
        {
            this.helth-= impact;
        }
        else
        {
            this.armor -= impact/2;
            if(this.armor <0){
                this.helth+= this.armor;
                this.armor = 0;
            }
            return impact;
        }
        return impact;
    }
    
    public void toFixArmor(){
        this.armor +=  this.armor/2;
    }
}
