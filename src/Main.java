public class Main {

    public void main(){

        Character hero = new Character("Ledut", "R");
        Weapon newWeapon = new Weapon("Sword", 5, 20);

        hero.printCharacterSheet();

        System.out.println(hero.weaponEquipped);
        newWeapon.equip();

    }
}
