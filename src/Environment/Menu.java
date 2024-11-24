package Environment;
import java.io.*;
import java.util.ArrayList;
import Objects.*;
import GameLogic.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Menu {
    String tombstoneEmoji = "\u274C"; // Пам'ятник (❌)
    String earthEmoji = "\uD83C\uDF0D"; // Земля (🌍)
    String worldMapEmoji = "\uD83C\uDF0D"; // Карта світу (🗺️)
    String brickEmoji = "\uD83C\uDFE1"; // Цегла (🧱)
    static String gearEmoji = "\u2699"; // Шестерня (⚙️)
    String crossedSwordsEmoji = "\u2694"; // Схрещені мечі (⚔️)
    String daggerEmoji = "\uD83D\uDDE1"; // Кинджал (🗡️)
    String hammerAndWrenchEmoji = "\uD83D\uDEE0"; // Молоток та гайковерт (🛠️)
    String hammerAndPickEmoji = "\u2692"; // Молоток і кирка (⚒️)
    String gunEmoji = "\uD83D\uDD2B"; // Пістолет (🔫)
    String alienEmoji = "\uD83D\uDC7E"; // Інопланетянин (👾)
    String microbeEmoji = "\uD83E\uDD2C"; // Мікроб (🪬)
    String checkMarkEmoji = "\u2705"; // Галочка (✅)
    String checkMarkTickEmoji = "\u2714"; // Галочка зі стрілкою (✔️)
    String gameControllerEmoji = "\uD83C\uDFAE"; // Ігровий контролер (🎮)
    
    public Menu() {
    }
    
    public void showMenu(OneVsOne game1, FiveVsFive game2, Team f1, Team f2, ArrayList<Droid> usersDroids, ArrayList<Weapon> usersWeapon) {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        System.out.println("------------------------------------------------------------------------------\n"
                + "\t" + gameControllerEmoji + "Play[1]\n"
                + "\t" + gearEmoji + "Settings[2]\n"
                + "\t" + tombstoneEmoji + "Exit[3]\n" +
                "------------------------------------------------------------------------------");
        answer = scanner.next();
        switch (answer) {
            case "1":
                System.out.println("-------------------------------------------------------------------------------------\n"
                        + "\t" + daggerEmoji + "(1 vs 1)[1]\n"
                        + "\t" + crossedSwordsEmoji + "(5 vs 5)[2]\n" +
                        "-------------------------------------------------------------------------------------");
                switch (answer = scanner.next()) {
                    
                    case "1":
                                System.out.println("\u001B[34m Дроїди вибираються з команд, які сформовані на режим 5 на 5\u001B[0m");
                                System.out.println();
                                System.out.println("Команда №1");
                                outDroidsInfoArr(f1.getDroids());
                                System.out.println();
                                String inputDroid1;
                                int numberOfDroid1;
                                System.out.println("Виберіть дроїда з першої команди (№): ");
                                do {
                                    inputDroid1 = scanner.nextLine();
                                    try {
                                        numberOfDroid1 = Integer.parseInt(inputDroid1);
                                    } catch (NumberFormatException e) {
                                        numberOfDroid1 = -1; //
                                    }
                                } while (numberOfDroid1 <1 || numberOfDroid1 > 5);
                                
                                System.out.println("Команда №2");
                                outDroidsInfoArr(f2.getDroids());
                                System.out.println();
                                String inputDroid2;
                                int numberOfDroid2;
                                 System.out.println("Виберіть дроїда з другої команди (№): ");
                                do {
                                    inputDroid2 = scanner.nextLine();
                                    try {
                                        numberOfDroid2 = Integer.parseInt(inputDroid2);
                                    } catch (NumberFormatException e) {
                                        numberOfDroid2 = -1;
                                    }
                                } while (numberOfDroid2 < 1 || numberOfDroid2 > 5);
                        game1.startGame((setCorrectDroidClass(f1.getDroids()[numberOfDroid1-1])),(setCorrectDroidClass(f2.getDroids()[numberOfDroid2-1])));
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    case "2":
                        game2.startGame();
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    default:
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                }
                break;
            case "2":
                System.out.println("-------------------------------------------------------------------------------------\n"
                        + "\t" + alienEmoji + "(Droids)[1]\n"
                        + "\t" + hammerAndWrenchEmoji + "(Create new droid)[2]\n"
                        + "\t" + gunEmoji + "(Weapons)[3]\n"
                        + "\t" + hammerAndWrenchEmoji + "(Create new weapon)[4]\n"
                        + "\t" + checkMarkTickEmoji + "(Set weapon for droid)[5]\n"
                        + "\t" + worldMapEmoji + "(Create teams)[6]\n" +
                        "-------------------------------------------------------------------------------------");
                switch (answer = scanner.next()) {
                    case "1":
                        outDroidsInfo(usersDroids);
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    case "2":
                        createNewDroid(usersDroids);
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    case "3":
                        outWeaponsInfo(usersWeapon);
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    case "4":
                        createNewWeapon(usersWeapon);
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    case "5":
                        setWeaponForDroid(usersDroids, usersWeapon);
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    case "6":
                        outDroidsInfo(usersDroids);
                        System.out.println("Вкажіть 5 дроїдів, щоб створити команду 1:\n");
                        for (int i = 0; i < 5; i++) {
                            int numberOfUsersDroids = 0;
                            do {
                                 numberOfUsersDroids = getUserInput(scanner);
                            }while (numberOfUsersDroids<1 || numberOfUsersDroids> usersDroids.size());
                            f1.setDroid(setCorrectDroidClass(usersDroids.get(numberOfUsersDroids - 1)), i);
                        }
                        System.out.println("Вкажіть 5 дроїдів, щоб створити команду 2:\n");
                        for (int i = 0; i < 5; i++) {
                            int numberOfUsersDroids = 0;
                            do {
                                numberOfUsersDroids = getUserInput(scanner);
                            }while (numberOfUsersDroids<1 || numberOfUsersDroids> usersDroids.size());
                            f1.setDroid(setCorrectDroidClass(usersDroids.get(numberOfUsersDroids - 1)), i);
                            f2.setDroid(setCorrectDroidClass(usersDroids.get(numberOfUsersDroids - 1)), i);
                        }
                        System.out.println("\t\t"+checkMarkEmoji+"|\u001B[34m Команди створені|\u001B[0m"+checkMarkEmoji+"\n");
            
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                    default:
                        this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                        break;
                }
                break;
            case "3":
                break;
            default:
                this.showMenu(game1, game2, f1, f2, usersDroids, usersWeapon);
                break;
        }
    }
    private int getUserInput(Scanner scanner) {
        int numberOfUsersDroids;
        while (true) {
            try {
                System.out.print("Введіть номер дроїда: ");
                String input = scanner.next();
                numberOfUsersDroids = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть коректне число.");
            }
        }
        return numberOfUsersDroids;
    }
    public Droid setCorrectDroidClass(Droid droid){
        Droid droidC = null;
        if (droid instanceof ArmoredDroid) {
             droidC = new ArmoredDroid((ArmoredDroid)droid);
        } else if (droid instanceof SupportDroid) {
             droidC = new SupportDroid((SupportDroid) droid);
        } else if (droid instanceof Droid) {
             droidC = new Droid(droid);
        }
        return  droidC;
    }
    
    public static void createNewDroid(ArrayList<Droid> usersDroids) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------\n"
                + "\t(Standart Droid)[1]\n"
                + "\t(Support Droid)[2]\n"
                + "\t(Armored Droid)[3]\n" +
                "-------------------------------------------------------------------------------------");
        
        String answer = scanner.next();
        switch (answer) {
            case "1":
                createDroid(scanner,usersDroids);
                break;
            case "2":
                createSupportDroid(scanner, usersDroids);
                break;
            case "3":
                createArmoredDroid(scanner, usersDroids);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    private static void createDroid(Scanner scanner, ArrayList<Droid> usersDroids) {
        System.out.print("Droid creation:\n" + "Name:");
        String name = scanner.next();
        System.out.print("Health:");
        int health = getValidIntInput(scanner, "Enter a valid integer for health:");
        Droid droid = new Droid(name, 0, 0, health, null);
        usersDroids.add(droid);
    }
    
    // Function to create a Support Droid
    private static void createSupportDroid(Scanner scanner, ArrayList<Droid> usersDroids) {
        System.out.print("Droid creation:\n" + "Name:");
        String nameS = scanner.next();
        System.out.print("Health:");
        int healthS = getValidIntInput(scanner, "Enter a valid integer for health:");
        System.out.print("Healing:");
        int healing = getValidIntInput(scanner, "Enter a valid integer for healing:");
        Droid droidS = new SupportDroid(nameS, 0, 0, healthS, null, healing);
        usersDroids.add(droidS);
    }
    
    // Function to create an Armored Droid
    private static void createArmoredDroid(Scanner scanner, ArrayList<Droid> usersDroids) {
        System.out.print("Droid creation:\n" + "Name:");
        String nameA = scanner.next();
        System.out.print("Health:");
        int healthA = getValidIntInput(scanner, "Enter a valid integer for health:");
        System.out.print("Armor:");
        int armor = getValidIntInput(scanner, "Enter a valid integer for armor:");
        Droid droidA = new ArmoredDroid(nameA, 0, 0, healthA, null, armor);
        usersDroids.add(droidA);
    }
    
    // Function to get valid integer input
    private static int getValidIntInput(Scanner scanner, String errorMessage) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }
    
    public static void createNewWeapon(ArrayList<Weapon> userWeapons) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n"
                + "\t\t\t\t(Weapon create)[1]\n" +
                "\u001B[34m-------------------------------------------------------------------------------------\u001B[0m");
        
        System.out.print("Weapon creation:\n" + "Name:");
        String name = scanner.next();
        int minDamage = getValidIntInput(scanner, "Enter a valid integer for Minimum damage:");
        int maxDamage = getValidIntInput(scanner, "Enter a valid integer for Maximum damage:");
        int distance = getValidIntInput(scanner, "Enter a valid integer for Distance:");
        
        Weapon weapon = new Weapon(name, minDamage, maxDamage, distance);
        userWeapons.add(weapon);
    }
    
    public static void outWeaponsInfo(ArrayList<Weapon> userWeapons) {
        System.out.println("\n\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n" + "\t\t\t\tAll wepons" + "\n\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n");
        for (int i = 0; i < userWeapons.size(); i++) {
            System.out.println("--------------------------------- (№" + (i + 1) + ") -------------------------------------------------\n");
            System.out.println(userWeapons.get(i).toString());
            System.out.println("-------------------------------------------------------------------------------------\n");
        }
    }
    
    public static void outDroidsInfo(ArrayList<Droid> userDroids) {
        System.out.println("\n\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n" + "\t\t\t\tAll droids" + "\n\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n");
        for (int i = 0; i < userDroids.size(); i++) {
            System.out.println("--------------------------------- (№" + (i + 1) + ") -------------------------------------------------");
            System.out.println(userDroids.get(i).toString());
            System.out.println("-------------------------------------------------------------------------------------\n");
        }
    }
    
    public static void outDroidsInfoArr(Droid[] userDroids) {
        System.out.println("\n\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n" + "\t\t\t\tAll droids" + "\n\u001B[34m-------------------------------------------------------------------------------------\u001B[0m\n");
        for (int i = 0; i < userDroids.length; i++) {
            System.out.println("--------------------------------- (№" + (i + 1) + ") -------------------------------------------------\n");
            System.out.println(userDroids[i].toString());
            System.out.println("-------------------------------------------------------------------------------------\n");
        }
    }
    
    public static void setWeaponForDroid(ArrayList<Droid> usersDroids, ArrayList<Weapon> userWeapons) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n----------------------||||||||||||||||||||||||||||||||||--------------------------\n");
        outDroidsInfo(usersDroids);
        System.out.println("\n----------------------||||||||||||||||||||||||||||||||||--------------------------\n");
        outWeaponsInfo(userWeapons);
        System.out.println("Для екіпірування зброї вкажіть (№ дроїда)(№ зброї)\n");
        System.out.print("Дроїд №");
        int dPointer = getValidIntInput(scanner, "Enter a valid integer for Дроїд №:");
        System.out.print("Зброя №");
        int wPointer = getValidIntInput(scanner, "Enter a valid integer for Зброя №:");
        usersDroids.get(dPointer - 1).setWeapon(userWeapons.get(wPointer - 1));
        System.out.println("\n----------------------||||||||||||||||||||||||||||||||||--------------------------\n");
    }
    
    public static int playingMenu5vs5(int step, Team f1, Team f2, Map map) {
        if (step % 2 == 0) {
            System.out.println("\nХід команди " + f1.getName());
        } else {
            System.out.println("\nХід команди " + f2.getName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nХід №" + step);
        System.out.println("-------------------------------------------------------------------------------------\n"
                + "\t(Перемістити дроїда)[1]\n"
                + "\t(Атакувати)[2]\n"
                + "\t(Лікувати)[3]\n"
                + "\t(Ремонт броні)[4]\n"
                + "\t(Пропустити хід)[#]\n"
                + "\t(Дроїди)[5]\n" +
                "-------------------------------------------------------------------------------------");
        String answer = scanner.next();
        switch (answer) {
            case "1":
                System.out.println("Введіть номер дроїда:");
                do {
                    answer = (""+getValidIntInput(scanner, "Enter a valid integer for Minimum damage:"));
                }while (Integer.parseInt(answer) - 1<=0 && Integer.parseInt(answer) - 1 > 5);
                
                if (step % 2 == 0) {
                    runStep(f1 , f2 ,step ,answer ,map);
                } else {
                    runStep(f2 , f1 ,step ,answer ,map);
                }
                break;
            case "2":
                System.out.println("Введіть номер дроїда, який атакує:");
                int attackingDroidNumber = getValidIntInput(scanner, "Enter a valid integer for the attacking droid number:");
                System.out.println("Введіть номер ворожого дроїда, якого атакувати:");
                int targetDroidNumber = getValidIntInput(scanner, "Enter a valid integer for the target droid number:");
                if (step % 2 == 0) {
                    runFire(f1 ,f2 ,attackingDroidNumber ,targetDroidNumber ,map);
                } else {
                    runFire(f2 ,f1 ,attackingDroidNumber ,targetDroidNumber ,map);
                }
                break;
            case "3":
                int flag = 0 ;
                System.out.println("Введіть номер дроїда, який лікує:");
                int hillingDroidNumber = getValidIntInput(scanner, "Enter a valid integer for the attacking droid number:");
                System.out.println("Введіть номер ворожого дроїда, якого лікувати:");
                int hilledtargetDroidNumber = getValidIntInput(scanner, "Enter a valid integer for the target droid number:");
                if (step % 2 == 0) {
                  flag = runHpHelp(f1.getDroids()[hillingDroidNumber],f1.getDroids()[hilledtargetDroidNumber] );
                } else {
                  flag =  runHpHelp(f2.getDroids()[hillingDroidNumber],f2.getDroids()[hilledtargetDroidNumber] );
                }
                if(flag == -1) {
                    playingMenu5vs5(step, f1, f2, map);
                }
                break;
            case "4":
                int check = 0 ;
                System.out.println("Введіть номер дроїда, який лагодить броню:");
                int fixArmDroidNumber = getValidIntInput(scanner, "Enter a valid integer for the attacking droid number:");
                System.out.println("Введіть номер ворожого дроїда, якому полагодити:");
                int fixTargetDroidNumber = getValidIntInput(scanner, "Enter a valid integer for the target droid number:");
                if (step % 2 == 0) {
                   check= runArmorFixing(f1.getDroids()[fixArmDroidNumber],f1.getDroids()[fixTargetDroidNumber] );
                } else {
                   check = runArmorFixing(f2.getDroids()[fixArmDroidNumber],f2.getDroids()[fixTargetDroidNumber] );
                }
                if(check == -1) {
                    playingMenu5vs5(step, f1, f2, map);
                }
                break;
            case "5":
                System.out.println("\nКоманда №1");
                outDroidsInfoArr(f1.getDroids());
                System.out.println("\nКоманда №2");
                outDroidsInfoArr(f2.getDroids());
                playingMenu5vs5(step, f1,f2,map);
                break;
            default:
                break;
        }
           return 0;
    }
    public static  int  runHpHelp(Droid current,Droid target ){
        if (current instanceof  SupportDroid) {
            ((SupportDroid)current).toHeal(target);
        }else{
            System.out.println("Це не лікувальний дроїд!");
            return  -1;
        }
        return  0;
    }
    public static  int   runArmorFixing(Droid current, Droid target ){
        if (current instanceof  ArmoredDroid) {
            ((SupportDroid)current).toHeal(target);
            
        }else{
            System.out.println("Це не броньований дроїд!");
            return  -1;
        }
        return  0;
    }
    public static void runStep(Team f1 , Team f2 , int step , String answer , Map map){
        if (f1.getDroids()[Integer.parseInt(answer) - 1].isAlive()) {
            map.droidStep(f1.getDroids()[Integer.parseInt(answer) - 1]);
        } else {
            System.out.println("Цей знищений.Виберіть іншого.");
            playingMenu5vs5(step, f1, f2, map);
        }
    }
    public static void runFire(Team f1 , Team f2, int attackingDroidNumber , int targetDroidNumber , Map map){
        if (f1.getDroids()[(attackingDroidNumber)-1].isAlive()) {
            if (f2.getDroids()[(targetDroidNumber)-1].isAlive()) {
                map.toFire(f1.getDroids()[(attackingDroidNumber)-1], f2.getDroids()[(targetDroidNumber)-1]);
            }
            System.out.println("Дроїд " + f2.getDroids()[(attackingDroidNumber)-1].getName() + " знищений.Повторіть спробу атаки.");
        } else {
            System.out.println("Дроїд " + f1.getDroids()[(targetDroidNumber)-1].getName() + " знищений.Повторіть спробу атаки.");
        }
    }
    public static int playingMenu1vs1(int step, Droid d1, Droid d2, Map map){
        if (step % 2 == 0) {
            System.out.println("\nХід Дроїда  " + d1.getName());
        } else {
            System.out.println("\nХід Дроїдa  " + d2.getName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nХід №" + step);
        System.out.println("-------------------------------------------------------------------------------------\n"
                + "\t(Перемістити дроїда)[1]\n"
                + "\t(Атакувати)[2]\n"
                + "\t(Лікувати)[3]\n"
                + "\t(Ремонт броні)[4]\n"
                + "\t(Пропустити хід)[#]\n"
                + "\t(Дроїди)[5]\n" +
                "-------------------------------------------------------------------------------------");
        String answer = scanner.next();
        switch (answer) {
            case "1":
                if (step % 2 == 0) {
                        map.droidStep(d1);
                } else {
                        map.droidStep(d2);
                }
                break;
            case "2":
                    if (d1.isAlive()) {
                        if (d2.isAlive()) {
                            if (step % 2 == 0) {
                                map.toFire(d1, d2);
                            }else {
                                map.toFire(d2, d1);
                            }
                        }
                }
                break;
            case "3":
                int flag = 0;
                if (step % 2 == 0) {
                 flag =   runHpHelp(d1 ,d1);
                } else {
                   flag = runHpHelp(d2 ,d2);
                }
                if(flag == -1){
                    playingMenu1vs1(step,d1,d2,map);
                }
                break;
            case "4":
                int check = 0;
                if (step % 2 == 0) {
                 check=   runArmorFixing(d1 ,d1);
                } else {
                 check =   runArmorFixing(d2,d2);
                }
                if(check == -1){
                    playingMenu1vs1(step,d1,d2,map);
                }
                break;
            case "5":
                System.out.println("\n"+gearEmoji+"Дроїд №1");
                System.out.println(d1.toString());
                System.out.println("\n"+gearEmoji+"Дроїд №2");
                System.out.println(d2.toString());
                playingMenu1vs1(step, d1, d2, map);
                break;
            default:
                break;
        }
        return 0;
        
    }
    
    public static void readObjectsFromFile(ArrayList<Droid> usersDroids, ArrayList<Weapon> usersWeapons) {
        ArrayList<Droid> objects = new ArrayList<>();
        String filePath = "C:/StartInfo.txt";
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                switch (words[0]) {
                    case "#weapon":
                        usersWeapons.add(new Weapon(words[1], Integer.parseInt(words[2]),
                                Integer.parseInt(words[3]), Integer.parseInt(words[4])));
                        break;
                    case "#droid":
                        usersDroids.add(new Droid(words[1], Integer.parseInt(words[2]), usersWeapons.get(0)));
                        break;
                    case "#armored":
                        usersDroids.add(new ArmoredDroid(words[1], Integer.parseInt(words[2]),
                                usersWeapons.get(0), Integer.parseInt(words[3])));
                        break;
                    case "#support":
                        usersDroids.add(new SupportDroid(words[1], Integer.parseInt(words[2]),
                                usersWeapons.get(0), Integer.parseInt(words[3])));
                        break;
                    default:
                        System.out.println();
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    public static void logData(Team f1 ,Team f2 ) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        
        String fileName = "C:/Results.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            
            writer.write("Time: " + formattedTime + "\n");
            writer.write(f1.getName()+"\n");
            for(int i = 0 ; i< 5; i ++) {
                writer.write(f1.getDroids()[i].toString() +"\n");
            }
            writer.write(f2.getName()+"\n");
            for(int i = 0 ; i< 5; i ++) {
                writer.write(f2.getDroids()[i].toString() +"\n");
            }
            writer.write("------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    
    
}


