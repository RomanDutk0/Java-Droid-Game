package GameLogic;

import Environment.*;
import Environment.Menu;
import Objects.Droid;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class FiveVsFive {
    private Map map;
    private Team f1;
    private Team f2;
    FiveVsFive(){}
    public FiveVsFive(Map map , Team f1 ,Team f2){
        this.f1 = f1;
        this.f2 = f2 ;
        this.map = map ;
    }
    public FiveVsFive(FiveVsFive another){
        this.f1 = another.f1;
        this.f2 = another.f2 ;
        this.map = another.map;
    }
    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть кількість перешкод: ");
        this.map.generateMapTrops(Integer.parseInt(scanner.next()));
        this.map.visualMap();
        setDroidsOfTeams();
        int step = 2;
        while( aliveDroidsCount() == 0){
            this.map.visualMap();
            Menu.playingMenu5vs5(step,f1,f2,this.map);
            step++;
        }
    }
    public int setDroidsOfTeams() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n\tГравець " + f1.getName() + " розставляє свої дроїди на позиції\n");
        for (int i = 0; i < 5; i++) {
            setCord(f1,i);
        }
            System.out.println("\n\tГравець " + f1.getName() + " успішно зайняв свої позиції\n");
        
            System.out.println("\n\tГравець " + f2.getName() + " розставляє свої дроїди на позиції\n");
        for (int i = 0; i < 5; i++) {
            setCord(f2, i);
        }
        System.out.println("\n\tГравець " + f2.getName() + " успішно зайняв свої позиції\n");
        
        return 0;
    }
    public int  aliveDroidsCount(){
        int teamOne = 0 , teamTwo = 0 ;
        for(int i = 0 ; i<f1.getDroids().length; i++){
            if(f1.getDroids()[i].isAlive()){
                teamOne++;
            }
            if(f2.getDroids()[i].isAlive()){
                teamTwo++;
            }
        }
        if(teamOne == 0){
            System.out.println("\n\n\t\t\t\u001B[35mGAME OVER\u001B[0m\n\t\t\uD83D\uDC51\u001B[35mПереможець\u001B[0m\uD83D\uDC51 "+ f1.getName());
            return 2;
        }else if (teamTwo == 0){
            System.out.println("\n\n\t\t\t\u001B[35mGAME OVER\u001B[0m\n\t\t\uD83D\uDC51\u001B[35mПереможець\u001B[0m\uD83D\uDC51 "+ f2.getName());
            return 1;
        }
        return  0;
    }
    public void setCord(Team f, int i){
        Scanner scanner = new Scanner(System.in);
        int x = 0, y = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Введіть координати місця для дроїда " + f.getDroids()[i].getName() + " [x][y]:");
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
        map.setDroidOnMap(f.getDroids()[i], x, y);
    }
    
    public void renameTeams(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Введіть назву Гравця №1");
        String player1Name = scanner.next();
        
        while (player1Name.isEmpty()) {
            System.out.println("Назва не може бути пустою. Введіть назву Гравця №1 ще раз:");
            player1Name = scanner.next();
        }
        
        f1.setName(player1Name);
        
        System.out.println("Введіть назву Гравця №2");
        String player2Name = scanner.next();
        
        while (player2Name.isEmpty()) {
            System.out.println("Назва не може бути пустою. Введіть назву Гравця №2 ще раз:");
            player2Name = scanner.next();
        }
        
        f2.setName(player2Name);
    }
    
    public Map getMap(){
        return this.map;
    }
    public Team getFirstTeam(){
        return this.f1;
    }
    public Team getSecondTeam(){
        return this.f2;
    }
    public void setMap(Map newMap){
        this.map = newMap;
    }
    public void setFirstTeam(Team newFirstTeam){
        this.f1 = newFirstTeam;
    }
    public void setSecondTeam(Team newSecondTeam){
        this.f2 = newSecondTeam;
    }
}
