public class HeroBuilder_SP1 {

   public void main(){

        String name = "Ledut";
        int healthPoints = 85;
        int maxHealth = 100;
        int level = 1;
        int experiencePoints = 150;
        double gold = 250.50;
        boolean isAlive = true;
        String specialization = "R";

        String[] inventory = {"Dagger", "Thieves Kit", "Smokebomb"};


        printCharacterSheet();



        for (int i = 0; i < inventory.length; i++){

            System.out.println("- "+inventory[i]);

        }



}

    //Print character sheet / all stats
    public void printCharacterSheet(){

        System.out.println("======= Character Sheet =======");
        System.out.println("Name: "+name);
        System.out.println("Class: "+specialization);
        System.out.println("Level: "+level);
        System.out.println("Health: "+healthPoints+"/"+maxHealth);
        System.out.println("XP: "+experiencePoints);
        System.out.println("Gold: "+gold);
        System.out.println("Alive: "+isAlive);
        System.out.println();
        System.out.println("Inventory ("+inventory.length+" items):");

    }

    //Reduce health, checking if dead
    void takeDamage(int amount){

        if (amount > healthPoints){
            System.out.println("You've have died");
            isAlive = false;
        }

    }
    //Increases health (max to maxHealth)
    void heal(){

    }

    //Removes gold, returns true if success, false if not enough gold
    boolean removeGold(){

    }

    //Adds XP, checks for level up
    void addXP(){

    }

    //Increases level, reset XP, increases maxHealth
    void levelUp(){

    }

    //Returns true if health > 25%
    boolean isHealthCritical(){

    }

    //Returns true if health > 0
    boolean isAlive(){

    }

    //Returns health as percent og maxHealth
    double getHealthPercentage(){

    }

    //Prints all items
    void printInventory(){

    }



}
