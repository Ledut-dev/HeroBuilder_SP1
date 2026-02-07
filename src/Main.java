import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public void main(){

        Character hero = new Character("Ledut", "Rogue");

        startDungeon(hero);

    }


    void startDungeon(Character hero){
        boolean dungeonActive = true;
        boolean awaitingAction = true;
        int actionCounter = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== Welcome to the dungeon ====");
        System.out.println("Good luck!");
         while (dungeonActive) {

            while (awaitingAction) {
                System.out.println("What would you like to do?");
                System.out.println("1 - Check inventory | 2 - Check Character | 3 - Heal | 4 - Add item | 5 - Sell item | 6 - Search for treasure | 7 - Level up | 8 - Call out to an enemy");

                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action){
                    case 1:
                        hero.printInventory();
                        actionCounter++;
                        break;
                    case 6:
                        if (actionCounter%2 == 0){
                            System.out.println("You found treasure!");
                            System.out.println("Found: 25 G");
                            hero.addGold(25);
                            actionCounter++;
                        }
                        else{
                            System.out.println("A monster appears!");
                            Character goblin = new Character("Dark Goblin", "Goblin");
                            hero.combat(goblin);
                            actionCounter++;
                        }
                        break;
                    case 7:
                        hero.levelUp();
                        actionCounter++;
                        break;
                    case 8:
                        System.out.println("A monster appears!");
                        Character goblin = new Character("Dark Goblin", "Goblin");
                        hero.combat(goblin);
                        actionCounter++;
                        break;
                    default:
                        System.out.println("Invalid action");
                        System.out.println();
                        actionCounter++;
                }
            }

        }
    }

}
