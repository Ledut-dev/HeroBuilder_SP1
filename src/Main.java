public class Main {

    public void main(){

        Character hero = new Character("Ledut", "M");
        Item rock = new Item("rock", 20, 20);

        hero.inventory.add(rock);

        for (Item i : hero.inventory){
            System.out.println(i.name);
        }

        System.out.println(hero.equippedWeapon.name);
    }
}
