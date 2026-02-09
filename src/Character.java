import java.util.ArrayList;
import java.util.Scanner;

public class Character {

    Scanner scanner = new Scanner(System.in);
    boolean combatActive = false;
    int HP = 100;
    int maxHealth = 100;
    int level = 1;
    int experiencePoints = 0;
    double gold = 0;
    boolean canLevelUp = false;
    boolean isAlive = true;
    boolean killedTarget = false;
    ArrayList<Item> inventory = new ArrayList<Item>();
    Weapon equippedWeapon;
    Armor equippedArmor;
    String name;
    String currentClass;


    //Constructor
    public Character(String characterName, String characterClass){

        name = characterName;
        currentClass = characterClass;

        //Assigning starting equipment depending on selected class
        switch (characterClass) {
            case "Rogue":
                equippedWeapon = new Weapon("Starting Dagger", 20, 100);
                equippedArmor = new Armor("Leather Armor", 3, 100);
                break;
            case "Warrior":
                equippedWeapon = new Weapon("Starting Sword", 20, 100);
                equippedArmor = new Armor("Plate Armor", 4, 100);
                break;
            case "Mage":
                equippedWeapon = new Weapon("Starting Staff", 20, 100);
                equippedArmor = new Armor("Cloth Armor", 2, 100);
                break;
            case "Goblin":
                equippedWeapon = new Weapon("Goblin Sword", 5, 100);
                equippedArmor = new Armor("Goblin Armor", 2, 100);
                break;
            default:
                System.out.println("Invalid class. Please pick on of the following options - Rogue | Warrior | Mage");
        }
    }

    //main
    public void main(){}

    //Handles combat, including turn order and user input for action
    void combat(Character enemy){

        combatActive = true;
        int action = 0;
        int roundCounter = 1;

        while (combatActive){

            //Prints combat status text
            System.out.println("=== Combat ===");
            System.out.println("Round: " + roundCounter);
            printCombatStats();
            System.out.println();
            enemy.printCombatStats();
            System.out.println();

            //If players turn
            if (roundCounter % 2 == 1) {
                boolean awaitingInput = true;
                System.out.println("What would you like to do?");
                System.out.println();
                System.out.println("1 - attack | 2 - heal | 3 - check | 4 - flee");
                System.out.println();
                System.out.println();

                action = scanner.nextInt();
                scanner.nextLine();

                while (awaitingInput) {

                    //handles action through player input
                    switch (action) {
                        case 1:
                            attack(enemy);
                            awaitingInput = false;
                            if (killedTarget) {
                                combatActive = false;
                                killedTarget = false;
                            }
                            break;
                        case 2:
                            System.out.println("Healed for " + (level * 15));
                            heal(level * 15);
                            awaitingInput = false;
                            break;
                        case 3:
                            System.out.println("You check the enemies stats: ");
                            System.out.println();
                            enemy.printCharacterSheet();
                            awaitingInput = false;
                            break;
                        case 4:
                            if (level >= enemy.level) {
                                System.out.println("Successfully fled the battle");
                                System.out.println();
                                combatActive = false;
                            } else {
                                System.out.println("The enemy is higher level than you, fleeing is impossible");
                                System.out.println();
                            }
                            break;
                        default:
                            System.out.println("Invalid action");
                            System.out.println();
                    }

                }
                roundCounter++;
            }
            //if enemies turn
            else {
                enemy.attack(this);
                roundCounter++;
            }
            System.out.println();
            System.out.println("=======================");
            System.out.println();
        }

    }

    //Print shorter versions of stats for combat, includes name, class, level, health/maxhealth, and gold
    void printCombatStats(){
        System.out.println("=== " + name + "(" + currentClass + ") ===");
        System.out.println("Level: " + level + " | Health: " + HP + "/" + maxHealth + " | Gold: " + gold);
    }


    //Print character sheet / all stats
    void printCharacterSheet(){

        System.out.println("======= Character Sheet =======");
        System.out.println("Name: "+ name);
        System.out.println("Class: " + currentClass);
        System.out.println("Level: "+ level);
        System.out.println("Health: "+ HP + "/" + maxHealth);
        System.out.println("XP: "+ experiencePoints + "/" + (level*10));
        System.out.println("Gold: "+ gold);
        System.out.println("Alive: "+ isAlive);

    }

    //Sells item currently in inventory for gold
    void sellItem(int index){
        try {
            Item item = inventory.get((index-1));

            addGold(item.value);
            System.out.println(item.name + " sold - gained "+ item.value);
            inventory.remove((index-1));
        }
        catch (Exception e){
            System.out.println("You don't have an item in this slot");
        }

    }

    //Prints current inventory, including equipped weapon / armor
    void printInventory(){
        //Print equipped weapon/armor
        System.out.println("Weapon: " + equippedWeapon.name +" | Damage: " + equippedWeapon.damage +" | Durability: " + equippedWeapon.durability);
        System.out.println("Armor: " + equippedArmor.name +" | Defense: " + equippedArmor.defense +" | Durability: " + equippedArmor.durability);
        System.out.println();

        //Print inventory
        int inventorySlot = 1;
        System.out.println("Inventory:");
        for (Item item : inventory){
            System.out.println("Slot " + inventorySlot + " - | Name: " + item.name + " | Value: " + item.value + "G" + " | Weight: " + item.weight);
            inventorySlot++;
        }
    }

    //Attack another character, using equipped weapon if one is present. Also handles durability of both attacker and target.
    //Aware that durability currently does nothing, but too lazy at this moment to implement functionality
    void attack(Character target){
        int damage = equippedWeapon.damage;
        System.out.println(name + " attacks " + target.name + " with their " + equippedWeapon.name);
        System.out.println();

        //If target will die from hit
        if (target.HP < damage){
            System.out.println(target.name + " has been slain!");
            equippedWeapon.durability -= damage/2;
            kill(target);
            killedTarget = true;
        }
        //If target armor defense is higher than attackers weapon attack
        else if (damage < target.equippedArmor.defense){
            System.out.println("Couldn't break through!");
            System.out.println(target.name + "'s " + target.equippedArmor.name + " overpowers your attack!");
            target.equippedArmor.durability -= damage/4;
            equippedWeapon.durability -= damage/2;
        }
        //Resolve damage normally
        else {
            target.HP -= damage-target.equippedArmor.defense;
            //Not having a double is fine even if dividing by 4, I prefer taking less durability damage rounded down.
            target.equippedArmor.durability -= damage/4;
            equippedWeapon.durability -= damage/2;
            System.out.println(target.name + " took " + damage + " damage!");
        }
    }

    //Handles on-kill/defeating enemies, adding XP, items and gold, displaying victory text.
    void kill(Character target){

        double goldDropped = target.gold/4;
        int xpDropped = target.level*5;

        //Print victory screen and earned gold/XP
        System.out.println("======= Victory =======");
        System.out.println(target.name + " was defeated!");
        System.out.println();
        System.out.println("You gained:");
        System.out.println(goldDropped + " Gold");
        System.out.println(xpDropped + " XP");
        System.out.println();
        System.out.println("Total gold: " + gold);
        System.out.println("Total XP: " + (experiencePoints+xpDropped) + " / " + (level*10));

        addGold(goldDropped);
        addXP(xpDropped);

        //TODO - ASK TESS HOW BEST TO "DELETE" AN OBJECT. GARBAGE COLLECTOR? SOME COMMAND?
        //TODO - SEEMINGLY ONLY GC ABLE TO REMOVE, AFTER NO MORE REFERENCES TO OBJECT IS MADE.
        target = null;
    }

    //Increases health (max to maxHealth)
    void heal(int amount){

        if ((amount + HP) > maxHealth){
            HP = maxHealth;
        }
        else {
            HP += amount;
        }
    }

    //Adds gold
    void addGold(double amount) {
        gold += amount;
    }

    //Removes gold, returns true if success, false if not enough gold
    boolean removeGold(double amount){
        if ((amount - gold) < 0){
            return false;
        }
        else {
            gold -= amount;
            return true;
        }
    }

    //Adds XP, checks for level up
    void addXP(int amount){
        experiencePoints += amount;
        if ((experiencePoints) >= (level*10)){
            System.out.println("Ready to level up!");
            canLevelUp = true;
            System.out.println();
        }
    }

    //Increases level, reset XP, increases maxHealth
    void levelUp(){
        if (canLevelUp){
            canLevelUp = false;
            experiencePoints = experiencePoints - (level*10);
            level++;
            maxHealth += level*5;
            System.out.println("Leveled up! You are now level: " + level);
            System.out.println("Total XP: " + experiencePoints + "/" + (level*10));
        }
        else {
            System.out.println("You do not have enough experience to level up");
        }
    }

    //Creates item on method call with parameters, then adds to inventory
    void addItem(String name, double weight, double value){
        inventory.add(new Item(name, weight, value));
        System.out.println(name + " has been added to your inventory");
    }

}
