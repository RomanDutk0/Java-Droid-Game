import Environment.*;
import GameLogic.*;
import Objects.*;
import java.util.ArrayList;

import static Environment.Menu.logData;
import static Environment.Menu.readObjectsFromFile;

public class Main
{
    public static void main(String[] args)
    {
        Map map = new Map(10,10);
        map.clearMap();
        Droid[] Team1Droids = new Droid[5];
        Droid[] Team2Droids = new Droid[5];
        Team f1 = new Team("One",Team1Droids);
        Team f2 = new Team("Two",Team2Droids);
        ArrayList<Droid> usersDroids = new ArrayList<>();
        ArrayList<Weapon> usersWeapons = new ArrayList<>();
        FiveVsFive game2 = new FiveVsFive(map,f1,f2);
        OneVsOne game1 = new OneVsOne(map);
        readObjectsFromFile(usersDroids,usersWeapons);
        Menu mainMenu = new Menu();
        mainMenu.showMenu(game1,game2,f1,f2,usersDroids,usersWeapons);
        logData(f1 ,f2);
    }
}