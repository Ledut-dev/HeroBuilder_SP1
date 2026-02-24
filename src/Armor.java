public class Armor {

    private String name;
    private int defense;
    private int durability;

    //Constructor
    public Armor(String name, int defense, int durability){

        this.name = name;
        this.defense = defense;
        this.durability = durability;

    }

    public String getName(){
        return this.name;
    }

    public int getDefense(){
        return this.defense;
    }

    public int getDurability(){
        return this.durability;
    }

    public void setDurability(int amount){
        this.durability += amount;
    }


    public String toString(){
        return "Name: " + this.name + " | Defense: " + this.defense + " | Durability: " + this.durability;
    }

}
