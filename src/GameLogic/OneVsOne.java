package GameLogic;
import Environment.*;
import Objects.*;

import java.util.Scanner;

public class OneVsOne {
    
    Map map;
    Droid d1;
    Droid d2;
    OneVsOne(){}
    public OneVsOne(Map map){
        this.map = map;
    }
    public void startGame(Droid d1, Droid d2){
        this.d1 = d1;
        this.d2 = d2;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть кількість перешкод: ");
        this.map.generateMapTrops(Integer.parseInt(scanner.next()));
        this.map.visualMap();
        setDroidsOfTeams();
        int step = 2;
        while(aliveDroidsCount(step) == 0){
            this.map.visualMap();
            Menu.playingMenu1vs1(step,d1,d2,this.map);
            step++;
        }
    }
 
    public int aliveDroidsCount(int step){
        int teamOne = 0 , teamTwo = 0 ;
            if(this.d1.isAlive()){
                teamOne++;
            }
            if(this.d2.isAlive()){
                teamTwo++;
            }
        if(teamOne == 0){
            System.out.println("\n\n\t\t\t\u001B[35mGAME OVER\u001B[0m\n\t\t\uD83D\uDC51\u001B[35mПереможець\u001B[0m\uD83D\uDC51 "+ d2.getName());
            return 2;
        }else if (teamTwo == 0){
            System.out.println("\n\n\t\t\t\u001B[35mGAME OVER\u001B[0m\n\t\t\uD83D\uDC51\u001B[35mПереможець\u001B[0m\uD83D\uDC51 "+ d1.getName());
            return  1;
        }
        return  0;
    }
  
    public int setDroidsOfTeams() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n\tГравець 1 розставляє свої дроїди на позиції\n");
            setCord(d1);
        System.out.println("\n\tГравець 1 успішно зайняв свої позиції\n");
        
        System.out.println("\n\tГравець 2 розставляє свої дроїди на позиції\n");
            setCord(d2);
        System.out.println("\n\tГравець 2 успішно зайняв свої позиції\n");
        
        return 0;
    }
    public void setCord(Droid d){
        Scanner scanner = new Scanner(System.in);
        int x = 0, y = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Введіть координати місця для дроїда " + d.getName() + " [x][y]:");
            String inputX = scanner.next();
            String inputY = scanner.next();
            
            try {
                x = Integer.parseInt(inputX);
                y = Integer.parseInt(inputY);
                if( x>=0 && x<=map.getXscale() && y>=0 && y<=map.getYscale()){
                    validInput = true;
                }else{
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некоректні координати. Введіть числа [x][y] ще раз.");
            }
        }
        map.setDroidOnMap(d , x, y);
    }
}
