public class Dessert {
    public int flavor;
    public int price;

    public static int numDesserts;

    public Dessert(int startflavor, int startprice){
        flavor = startflavor;
        price = startprice;
        numDesserts ++;
    }

    public void printDessert(){
        System.out.print(flavor+" "+price+" "+numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
