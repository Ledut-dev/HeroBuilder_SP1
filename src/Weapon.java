public class Weapon{

        private String name;
        private int damage;
        private int durability;

        //Constructor
        public Weapon(String name, int damage, int durability){

            this.name = name;
            this.damage = damage;
            this.durability = durability;
        }

    public String getName(){
        return this.name;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getDurability(){
        return this.durability;
    }

    public void setDurability(int amount){
        this.durability += amount;
    }


    public String toString(){
        return "Name: " + this.name + " | Damage: " + this.damage + " | Durability: " + this.durability;
    }

}

