public class Item {

    private String name;
    private double weight;
    private double value;

    //Constructor
    public Item(String name, double weight, double value){

        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getValue(){
        return this.value;
    }

    public String toString(){
        return "Name: " + this.name + " | Weight: " + this.weight + " | Value: " + this.value;
    }




}
