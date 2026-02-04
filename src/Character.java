public class Character {


    int HP = 100;
    int maxHealth = 100;
    int level = 1;
    int experiencePoints = 0;
    boolean canLevelUp = false;
    double gold = 0;
    boolean isAlive = true;
    boolean weaponEquipped = false;
    boolean[] armorEquipped = {false, false, false, false};
    String[] inventory = new String[10];
    String name;
    String currentClass;

    public Character(String characterName, String characterClass){

        name = characterName;
        currentClass = characterClass;
    }

    public void main(){}

    //Print character sheet / all stats
    public void printCharacterSheet(){

        System.out.println("======= Character Sheet =======");
        System.out.println("Name: "+ name);
        System.out.println("Class: " + currentClass);
        System.out.println("Level: "+ level);
        System.out.println("Health: "+ HP + "/" +maxHealth);
        System.out.println("XP: "+ experiencePoints + "/" + (level*10));
        System.out.println("Gold: "+ gold);
        System.out.println("Alive: "+ isAlive);

    }


    //Attack another character, using equipped weapon if one is present
    void attack(Character name){

    }

    //Reduce health, checking if dead
    void takeDamage(int amount){

        if (amount > HP){
            System.out.println("You have died");
            isAlive = false;
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

    //Returns true if health > 25%
    boolean isHealthCritical(){
        return (getHealthPercentage() < 25);
    }

    //Returns true if health > 0
    boolean isAlive() {
        return HP > 0;
    }

    //Returns health as percent of maxHealth
    double getHealthPercentage(){
        return (double) (HP*100)/maxHealth;
    }

    //Prints all items
    void printInventory() {
        int itemCount = 0;
        for (String s : inventory){
            if (!s.equals("Empty")){
                itemCount++;
            }
        }
        System.out.println("Inventory (" + itemCount + " / " + inventory.length + ")");
        for (String s : inventory) {
            if (!(s.equals("Empty"))){
                System.out.println("- " + s);
            }
        }
    }

    //Extra challenges for phase 3 of SP1

    //Returns true if item is present in inventory
    boolean hasItem(String item){
        boolean hasItem = false;
        for (String items : inventory){
            if (item.equals(items)){
                hasItem = true;
            }
        }
        return hasItem;
    }

    //Adds item to inventory
    void addItem(String item){

        boolean itemAdded = false;
        while (!itemAdded) {
            for (int i = 0; i < inventory.length && !itemAdded; i++) {
                if (inventory[i].equals("Empty")) {
                    inventory[i] = item;
                    itemAdded = true;
                }
            }
        }
        if (itemAdded){
            System.out.println(item + " has been added to the inventory");
        }
        else {
            System.out.println("Not enough space in inventory");
        }

    }

    //Remove item from inventory
    void removeItem(String item){

        boolean itemRemoved = false;
        for (int i = 0 ; i < inventory.length ; i++){
            if (inventory[i].equals(item)){
                inventory[i] = "Empty";
                itemRemoved = true;
            }
        }
        if (itemRemoved){
            System.out.println(item + " has been removed to the inventory");
        }
        else {
            System.out.println("You are not currently carrying " + item);
        }
    }

}
