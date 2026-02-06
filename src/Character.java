import java.util.ArrayList;
import java.util.Arrays;
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
                Weapon starterDagger = new Weapon("Starting Dagger", 10, 100);
                equippedWeapon = starterDagger;
                Armor leatherArmor = new Armor("Leather Armor", 4, 100);
                equippedArmor = leatherArmor;
                break;
            case "Warrior":
                Weapon starterSword = new Weapon("Starting Sword", 10, 100);
                equippedWeapon = starterSword;
                Armor plateArmor = new Armor("Plate Armor", 6, 100);
                equippedArmor = plateArmor;
                break;
            case "Mage":
                Weapon starterStaff = new Weapon("Starting Staff", 10, 100);
                equippedWeapon = starterStaff;
                Armor clothArmor = new Armor("Cloth Armor", 2, 100);
                equippedArmor = clothArmor;
                break;
            case "Goblin":
                Weapon goblinSword = new Weapon("Goblin Sword", 5, 100);
                equippedWeapon = goblinSword;
                Armor goblinArmor = new Armor("Goblin Armor", 2, 100);
                equippedArmor = goblinArmor;
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
        String action = "";
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
            if (roundCounter % 2 == 1){
                System.out.println("What would you like to do?");
                System.out.println();
                System.out.println("attack | heal | check | flee");
                System.out.println();
                System.out.println();

                action = scanner.nextLine();

                switch (action){
                    case "attack":
                        attack(enemy);
                        if (killedTarget){
                            combatActive = false;
                            killedTarget = false;
                        }
                        break;
                    case "heal":
                        System.out.println("Healed for " + (level*15));
                        heal(level*15);
                        break;
                    case "check":
                        System.out.println("You check the enemies stats: ");
                        System.out.println();
                        enemy.printCharacterSheet();
                        break;
                    case "flee":
                        if (level >= enemy.level) {
                            System.out.println("Successfully fled the battle");
                            System.out.println();
                            combatActive = false;
                        }
                        else {
                            System.out.println("The enemy is higher level than you, fleeing is impossible");
                            System.out.println();
                        }
                        break;
                    default:
                        System.out.println("Invalid action");
                        System.out.println();
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
    void sellItem(Item item){
        if (inventory.contains(item)){
            inventory.remove(item);
            gold += item.value;
            System.out.println(item.name + " sold - gained "+ item.value);
        }
        else {
            System.out.println("You don't have this item in your inventory");
        }

    }

    //Prints current inventory, including equipped weapon / armor
    void printInventory(){
        System.out.println("Weapon: " + equippedWeapon.name +" | Damage: " + equippedWeapon.damage +" | Durability: " + equippedWeapon.durability);
        System.out.println("Armor: " + equippedArmor.name +" | Defense: " + equippedArmor.defense +" | Durability: " + equippedArmor.durability);
        System.out.println();

        System.out.println("Inventory:");
        for (Item item : inventory){
            System.out.println("- " + item.name + " | Value: " + item.value + "G" + " | Weight: " + item.weight);
        }
    }

    //Attack another character, using equipped weapon if one is present. Also handles durability of both attacker and target.
    void attack(Character target){
        int damage = equippedWeapon.damage;
        System.out.println(name + " attacks " + target.name + " with their " + equippedWeapon.name);
        System.out.println();

        if (target.HP < damage){
            System.out.println(target.name + " has been slain!");
            equippedWeapon.durability -= damage/2;
            kill(target);
            killedTarget = true;
        }
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

        addGold(goldDropped);
        addXP(xpDropped);

        System.out.println("======= Victory =======");
        System.out.println(target.name + " was defeated!");
        System.out.println();
        System.out.println("You gained:");
        System.out.println(goldDropped + " Gold");
        System.out.println(xpDropped + " XP");
        System.out.println();
        System.out.println("Total gold: " + gold);
        System.out.println("Total XP: " + experiencePoints + " / " + level*10);

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
        if ((amount + experiencePoints) >= level*10){
            System.out.println("Ready to level up!");
            experiencePoints += amount;
            canLevelUp = true;
        }
        else {
            experiencePoints += amount;
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
        }
        else {
            System.out.println("You do not have enough experience to level up");
        }
    }

    void addItem(Item item) {
        inventory.add(item);
    }

}
