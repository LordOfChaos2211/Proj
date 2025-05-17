public class Item {
    private int price;
    private int kwhSaving;
    private int susScore;
    private int totScore;
    private String name;
    private String category;
    //all properties of items declared
    public Item(int p, int k, int s, String n, String c){
        this.price = p;
        this.kwhSaving = k;
        this.susScore = s;
        this.totScore = k*s; //knapsack comparison property automatically calculated, user input not needed
        this.name = n;
        this.category = c;
    }
    //getters
    public int getPrice(){
        return price;
    }
    public int getKwhSaving() {
        return kwhSaving;
    }
    public int getSustainability() {
        return susScore;
    }
    public int getScore() {
        return totScore;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() { //toString overridden for simplification's sake, easier print
        return (this.getName() + " Price:" + this.getPrice() +" Category:" + getCategory() +
                " SusScore:" +getSustainability() + " kWh Savings:"+getKwhSaving());
    }
}
