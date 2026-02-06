import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public void main(){

        Character hero = new Character("Ledut", "Rogue");
        Character goblin = new Character("Cave Goblin", "Goblin");

        hero.printInventory();
        goblin.printInventory();
        System.out.println();

        hero.printCharacterSheet();
        System.out.println();
        hero.addXP(25);
        hero.levelUp();
        System.out.println();
        hero.printCharacterSheet();

    }



}
