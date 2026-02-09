import java.util.Scanner;

public class Main {


    void main(){

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
                System.out.println("1 - Check inventory | 2 - Check Character | 3 - Heal | 4 - Add item | 5 - Sell item | 6 - Search for treasure | 7 - Level up | 8 - Call out to an enemy | 9 - End dungeon");

                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action){
                    case 1:
                        hero.printInventory();
                        actionCounter++;
                        break;
                    case 2:
                        hero.printCharacterSheet();
                        actionCounter++;
                        break;
                    case 3:
                        int baseHeal = hero.maxHealth/4;
                        hero.heal(baseHeal);
                        System.out.println("Healed for: " + baseHeal + " HP");
                        actionCounter++;
                        break;
                    case 4:
                        String name = "";
                        double weight = 0;
                        double value = 0;

                        System.out.println("What item would you like to add?");
                        System.out.println("(Name), (Weight), (Value)");
                        while (name.isEmpty()){
                            System.out.println("Enter name: ");
                            name = scanner.nextLine();
                        }
                        while (weight == 0) {
                            System.out.println("Enter weight: ");
                            weight = scanner.nextDouble();
                            scanner.nextLine();
                    }
                        while (value == 0) {
                            System.out.println("Enter value: ");
                            value = scanner.nextDouble();
                            scanner.nextLine();
                        }
                        hero.addItem(name, weight, value);
                        actionCounter++;
                        break;
                    case 5:
                        System.out.println("What item would you like to sell? (specify inventory slot)");
                        int inventorySlot = scanner.nextInt();
                        hero.sellItem(inventorySlot);
                        hero.printInventory();
                        actionCounter++;
                        break;
                    case 6:
                        if (actionCounter%2 == 0){
                            System.out.println("You found treasure!");
                            System.out.println("Found: 25 G");
                            hero.addGold(25);
                        }
                        else{
                            System.out.println("A monster appears!");
                            Character goblin = new Character("Dark Goblin", "Goblin");
                            hero.combat(goblin);
                        }
                        actionCounter++;
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
                    case 9:
                        System.out.println("You left the dungeon");
                        System.out.println("Current stats:");
                        hero.printCharacterSheet();
                        awaitingAction = false;
                        dungeonActive = false;
                        break;
                    default:
                        System.out.println("Invalid action");
                        System.out.println();
                }
            }

        }
    }

}
