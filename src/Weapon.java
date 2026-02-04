public class Weapon extends EquippableItem{

        String name;
        int damage;
        int durability;
        boolean isEquipped = false;

        public Weapon(String name, int damage, int durability){

            this.name = name;
            this.damage = damage;
            this.durability = durability;

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
