public class Armor {

    String name;
    int defense;
    int durability;
    int armorSlot;
    boolean isEquipped = false;

    public Armor(String name, int defense, int durability, int armorSlot){

        this.name = name;
        this.defense = defense;
        this.durability = durability;
        this.armorSlot = armorSlot;

    }

    void main(){}

    void equip(){
        if (!isEquipped){
            isEquipped = true;
            System.out.println(name + " has been equipped");
        }
        else {
            isEquipped = false;
            System.out.println(name + " has been unequipped");
        }
    }

}
