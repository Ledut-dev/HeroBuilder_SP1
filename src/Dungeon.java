import java.util.Scanner;

public class Dungeon{

    int actionCounter = 0;
    boolean dungeonActive = true;

    public Dungeon(){

}

    void main(){}

    //Starts the dungeon, while dungeonActive runs through dungeon actions
    void startDungeon(Character hero){
        System.out.println("=== Welcome to the dungeon ===");
        System.out.println("Good luck!");

        while (dungeonActive){
            dungeonNextAction(hero);
        }
    }

    //Handles all player actions, as well as initiating combat
    void dungeonNextAction(Character hero){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to do?");
        System.out.println("1 - Check inventory | 2 - Check Character | 3 - Heal | 4 - Add item | 5 - Sell item | 6 - Search for treasure | 7 - Level up | 8 - Call out to an enemy | 9 - End dungeon");

        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action){
            //Check inventory
            case 1:
                hero.printInventory();
                actionCounter++;
                break;
            //Check Character
            case 2:
                hero.printCharacterSheet();
                actionCounter++;
                break;
            //Heal
            case 3:
                int baseHeal = hero.maxHealth/4;
                hero.heal(baseHeal);
                System.out.println("Healed for: " + baseHeal + " HP");
                actionCounter++;
                break;
            //Add Item
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
            //Sell item
            case 5:
                hero.printInventory();
                System.out.println();
                System.out.println("What item would you like to sell? (specify inventory slot)");
                int inventorySlot = scanner.nextInt();
                hero.sellItem(inventorySlot);
                hero.printInventory();
                actionCounter++;
                break;
            //Search for treasure
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
            //Level up
            case 7:
                hero.levelUp();
                actionCounter++;
                break;
            //Call out to an enemy
            case 8:
                System.out.println("A monster appears!");
                Character goblin = new Character("Dark Goblin", "Goblin");
                hero.combat(goblin);
                actionCounter++;
                break;
            //Leave the dungeon
            case 9:
                System.out.println("You left the dungeon");
                System.out.println("Current stats:");
                hero.printCharacterSheet();
                dungeonActive = false;
                break;
            default:
                System.out.println("Invalid action");
                System.out.println();
                dungeonNextAction(hero);
        }
    }
    }


