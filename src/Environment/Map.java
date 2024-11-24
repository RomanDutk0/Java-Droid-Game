package Environment;
import Objects.*;
import java.util.Random;
import java.util.Scanner;

public class Map {
    private int Xscale;
    private int Yscale;
    public ElementOfMap[][] map;
    private static final int DroidPointer = 1;
    private static final int EmptyPointer = -1;
    private static final int SupportDroidPointer = 2;
    private static final int ArmoredDroidPointer = 3;
    private static final int WallPointer = 0;
    
    public Map(int Xscale, int Yscale) {
        this.Xscale = Xscale;
        this.Yscale = Yscale;
        this.map = new ElementOfMap [Xscale][Yscale];
    }
    
    public int droidStep(Droid droid) {
        Scanner scanner = new Scanner(System.in);
        String tombstoneEmoji = "\u274C"; // Пам'ятник (❌)
        this.map[droid.getXposition()][droid.getYposition()].setValue(-1);
        System.out.println("Введіть крок{0-2}:");
        int step = 0;
        do{
            step = Integer.parseInt(scanner.next());
        }while (step <0 || step>2);
        droid.droidMove(step);
        if (droid.getYposition() >= this.Yscale || droid.getXposition() >= this.Xscale || droid.getYposition() < 0 || droid.getXposition() < 0) {
            System.out.println("\n" + tombstoneEmoji + "\u001B[31m Ваш дроїд був знищений за втечу з поля бою \u001B[0m" + tombstoneEmoji);
            return -1;
        } else {
            if (this.map[droid.getXposition()][droid.getYposition()].getValue() == EmptyPointer) {
                setDroidOnMap(droid, droid.getXposition(), droid.getYposition());
            } else {
                if (this.map[droid.getXposition()][droid.getYposition()].getValue() > 0) {
                    System.out.println("\n" + tombstoneEmoji + "\u001B[31m Ваш дроїд був знищений за вхід в зону захисту іншого дроїда \u001B[0m" + tombstoneEmoji);
                } else {
                    System.out.println("\n" + tombstoneEmoji + "\u001B[31m Ваш дроїд розбився об перешкоду \u001B[0m" + tombstoneEmoji);
                }
            }
        }
        return 1;
    }
    
    public void generateMapTrops(int numberOfTraps) {
        Random random = new Random();
        for (int i = 0; i < numberOfTraps; i++) {
            int x = random.nextInt(8) + 1;
            int y = random.nextInt(8) + 1;
            this.map[x][y].setValue(0);
        }
    }
    
    public void clearMap() {
        for (int i = 0; i < Xscale; i++) {
            for (int j = 0; j < Yscale; j++) {
                map[i][j] = new ElementOfMap();
                map[i][j].setValue(EmptyPointer) ;
                map[i][j].setDroid(null);
            }
        }
    }
    
    public void setDroidOnMap(Droid droid, int Xpos, int Ypos) {
        droid.setXposition(Xpos);
        droid.setYposition(Ypos);
        if(droid.isAlive()) {
            if(this.map[Xpos][Ypos].getValue() == EmptyPointer) {
                if (droid instanceof ArmoredDroid) {
                    this.map[Xpos][Ypos].setValue(ArmoredDroidPointer);
                    this.map[Xpos][Ypos].setDroid(droid);
                } else if (droid instanceof SupportDroid) {
                    this.map[Xpos][Ypos].setValue(SupportDroidPointer);
                    this.map[Xpos][Ypos].setDroid(droid);
                } else {
                    this.map[Xpos][Ypos].setValue(DroidPointer);
                    this.map[Xpos][Ypos].setDroid(droid);
                }
            }
        }
    }
    
    public void toFire(Droid agressor, Droid target) {
        double impact = 0;
        Droid droid;
        droid = checkWayOfFire(agressor,target,this.map, impact);
        if (droid.isAlive()) {
            System.out.print("\n\t Дроїд " + agressor.getName() + " влучив в  дроїда " + droid.getName() );
            System.out.printf(" з  дистанції = %.2f%n",calculateDistance(agressor, droid));
            System.out.println();
        } else {
            System.out.print("\n\t Дроїд " + droid.getName() + " знищений дроїдом " + agressor.getName());
            System.out.printf(" з  дистанції = %.2f%n",calculateDistance(agressor, droid));
        }
    }
    public double calculateDistance(Droid agressor ,Droid target){
        double distance = Math.sqrt(Math.pow(target.getXposition() - agressor.getXposition(), 2) + Math.pow(target.getYposition() - agressor.getYposition(), 2));
        return  distance;
    }
    
    public static int[] positionCheckY(Droid agressor, Droid target, int x, ElementOfMap map[][]) {
        int result1 = map[x][(int) Math.floor(y(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), x))].getValue();
        int result2 = map[x][(int) Math.ceil(y(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), x))].getValue();
        int[] results = {result1, result2};
        return results;
    }
    public static int[] positionCheckX(Droid agressor, Droid target, int y, ElementOfMap map[][]) {
        int result1 = map[(int) Math.floor(x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y))][y].getValue();
        //System.out.println("\n\nx = "+(x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y))+"  y = "+y);
        //System.out.println("x = "+(int) Math.floor(x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y))+"  y = "+y + "Check = "+ result1);
        int result2= map[(int) Math.ceil(x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y))][y].getValue();
        //System.out.println("\n\nx = "+(x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y))+"  y = "+y);
        //System.out.println("x = "+(int) Math.ceil(x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y))+"  y = "+y+ "Check = "+ result2);
        int[] results = {result1, result2};
        return results;
    }
    
    public static double y(double x1, double x2, double y1, double y2, double x) {
            double y = ((-x2 * y1) - (x * y2) + (x1 * y2) + (x * y1)) / (-x2 + x1);
            return y;
    }
    
    public static double x(double x1, double x2, double y1, double y2, double y) {
        double x = ((x2 * y) - (x2 * y1) - (x1 * y) + (x1 * y2)) / (y2 - y1);
        return x;
    }
    
    public Droid checkWayOfFire(Droid agressor, Droid target,  ElementOfMap map[][], double impact) {
        impact = 0;
        int X1 = agressor.getXposition();
        int X2 = target.getXposition();
        int minX, maxX, k1 = 0;
        if (agressor.getXposition() > target.getXposition()) {
            minX = X2;
            maxX = X1;
            k1 = -1;
        } else {
            minX = X1;
            maxX = X2;
            k1 = 1;
        }
        int Y1 = agressor.getYposition();
        int Y2 = target.getYposition();
        int minY,maxY,k2 = 0;
        if (agressor.getYposition() > target.getYposition()) {
            minY = Y2;
            maxY = Y1;
            k2 = -1;
        } else {
            minY = Y1;
            maxY = Y2;
            k2 = 1;
        }
        
        boolean flag = false;
        
        if (Y1 == Y2) {
            flag = handleHorizontalCollision(agressor, target,agressor.getXposition(), minX, maxX, Y1, k1, map , impact);
        } else if (X1 == X2) {
            flag = handleVerticalCollision(agressor, target,agressor.getYposition(), minY, maxY, X1, k2, map, impact);
        } else {
            flag = handleDiagonalCollision(agressor, target,agressor.getXposition(), agressor.getYposition(),  minX, maxX,minY,maxY, k1, k2 ,map, impact);
        }
        
        if (flag == false) {
            impact += agressor.toHit(target, calculateDistance(agressor, target));
        }
        System.out.printf("\tШкоди нанесено:  %.2f hp",impact);
        System.out.println();
        return target;
    }
  
    
    private int calculateDirection(int pos1, int pos2) {
        return pos1 > pos2 ? -1 : 1;
    }
    
    private boolean handleHorizontalCollision(Droid agressor, Droid target,int firstval, int minX, int maxX, int yPos, int step, ElementOfMap map[][], double impact) {
        boolean flag = false;
        for (int x = firstval+ step; minX < x && x < maxX; x = x + step) {
            int check = map[x][agressor.getYposition()].getValue();
            flag = handleCollision(agressor, target,  x, yPos, check,check, map,  impact);
            if (flag) {
                break;
            }
        }
        return flag;
    }
    
    private boolean handleVerticalCollision(Droid agressor, Droid target,int firstval, int minY, int maxY, int xPos, int step, ElementOfMap map[][], double impact) {
        boolean flag = false;
        for (int y = firstval + step; minY < y && y < maxY; y = y + step) {
            int check = map[agressor.getXposition()][y].getValue();
            //System.out.println(" check = "+ check);
            flag = handleCollision(agressor, target,  xPos, y, check,check, map,  impact);
            if (flag) {
                break;
            }
        }
        return flag;
    }
    
    private boolean handleDiagonalCollision(Droid agressor, Droid target,int firstvalX,int firstvalY,  int minX, int maxX,int minY, int maxY, int stepX, int stepY, ElementOfMap map[][], double impact) {
        boolean flag = false;
        if(Math.abs(target.getXposition() - agressor.getXposition())>Math.abs(target.getYposition() - agressor.getYposition())) {
            for (int x = firstvalX + stepX; minX < x && x < maxX; x = x + stepX) {
                int check[] = positionCheckY(agressor, target, x, this.map);
                flag = handleCollision(agressor, target, x, (int)y(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), x), check[0],check[1], map, impact);
                if (flag) {
                    break;
                }
            }
        } else {
            for (int y = firstvalY + stepY; minY < y&& y < maxY; y = y + stepY) {
                int check[] = positionCheckX(agressor, target, y, this.map);
                flag = handleCollision(agressor, target, (int)x(target.getXposition(), agressor.getXposition(), target.getYposition(), agressor.getYposition(), y), y, check[0],check[1], map, impact);
                if (flag) {
                    break;
                }
            }
        }
        return flag;
    }
    
    private boolean handleCollision(Droid agressor, Droid target,  int x, int y, int check,int check1, ElementOfMap map[][], double impact) {
        boolean flag = false;
        if(check == ArmoredDroidPointer || check1 == ArmoredDroidPointer) {
            toDamageAnother(flag ,agressor,map , x ,y ,impact);
        }else if (check == SupportDroidPointer || check1 == SupportDroidPointer) {
            toDamageAnother(flag ,agressor,map , x ,y ,impact);
        }else  if (check == DroidPointer || check1 == DroidPointer) {
            toDamageAnother(flag ,agressor,map , x ,y ,impact);
        }else if(check == WallPointer || check1 == WallPointer) {
            System.out.println("\t\u001B[31mПопали в стіну\u001B[0m");
            flag = true;
        }
        return flag;
    }
    public  void toDamageAnother(boolean flag , Droid agressor ,ElementOfMap map[][] , int x , int y, double impact){
        flag = true;
        impact = agressor.toHit(map[x][y].getDroid(), calculateDistance(agressor, map[x][y].getDroid()));
        //System.out.println("\t\u001BПопали в \u001B[0m"+map[x][y].getDroid().getName());
    }
    
    public int getXscale(){
        return  this.Xscale;
    }
    public int getYscale(){
        return  this.Yscale;
    }
    
    
    
    public void visualMap() {
        String robotEmoji = "\uD83E\uDD16";
        String brickEmoji = "\uD83E\uDDF1";
        String hillerEmoji = "\uD83D\uDC7E";
        String ArmoredEmoji = "\uD83D\uDDFF";
        System.out.print("#\uFE0F\u20E3");
        for (int j = 0; j < Yscale; j++) {
            System.out.print( j + "\uFE0F\u20E3");
        }
        System.out.println(" (Y)");
        for (int i = 0; i < Xscale; i++) {
            System.out.print(i + "\uFE0F\u20E3");
            for (int j = 0; j < Yscale; j++) {
                if (map[i][j].getValue()== EmptyPointer) {
                    System.out.print("\uD83D\uDFE9");
                } else if (map[i][j].getValue() == DroidPointer) {
                    System.out.print(robotEmoji);
                } else if (map[i][j].getValue() == SupportDroidPointer) {
                    System.out.print(hillerEmoji);
                } else if (map[i][j].getValue() == ArmoredDroidPointer) {
                    System.out.print(ArmoredEmoji);
                } else if (map[i][j].getValue() == WallPointer) {
                    System.out.print(brickEmoji);
                }
            }
            System.out.println();
        }
        System.out.println("(X)");
    }
}
