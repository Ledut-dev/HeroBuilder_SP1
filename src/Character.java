import java.util.ArrayList;
import java.util.Arrays;

public class Character {

    int HP = 100;
    int maxHealth = 100;
    int level = 1;
    int experiencePoints = 0;
    double gold = 0;
    boolean canLevelUp = false;
    boolean isAlive = true;
    ArrayList<Item> inventory = new ArrayList<Item>();
    Weapon equippedWeapon;
    Armor equippedArmor;
    String name;
    String currentClass;

    //Constructor
    public Character(String characterName, String characterClass){

        name = characterName;
        currentClass = characterClass;
        switch (characterClass) {
            case "R":
                Weapon starterDagger = new Weapon("Starting Dagger", 10, 100, 5, 50);
                equippedWeapon = starterDagger;
                break;
            case "W":
                Weapon starterSword = new Weapon("Starting Sword", 10, 100, 10, 50);
                equippedWeapon = starterSword;
                break;
            case "M":
                Weapon starterStaff = new Weapon("Starting Staff", 10, 100, 8, 50);
                equippedWeapon = starterStaff;
                break;
            default:
                System.out.println("Invalid class. Please pick on of the following options - R (rogue) / W (warrior) / M ( mage)");
        }
    }

    //main
    public void main(){}

    //Print character sheet / all stats
    public void printCharacterSheet(){

        System.out.println("======= Character Sheet =======");
        System.out.println("Name: "+ name);
        System.out.println("Class: " + currentClass);
        System.out.println("Level: "+ level);
        System.out.println("Health: "+ HP + "/" + maxHealth);
        System.out.println("XP: "+ experiencePoints + "/" + (level*10));
        System.out.println("Gold: "+ gold);
        System.out.println("Alive: "+ isAlive);

    }

    void sellItem(Item item){

        gold += item.value;
    }

    //Attack another character, using equipped weapon if one is present
    void attack(Character target){

    }

    //Reduce health, checking if dead
    void takeDamage(int amount){

        if (amount > HP){
            System.out.println("You have died");
        }
        else {
            HP -= amount;
        }

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


}
