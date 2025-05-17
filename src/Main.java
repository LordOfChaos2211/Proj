import java.util.*;
public class Main {
    public static void wait(int ms) //artificial delay function for suspense (better user experience)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void biKnapsack(int budget, ArrayList<Item> availableItems, ArrayList<Item> itemsTaken){ //knapsack algorithm
        int numOfItems = availableItems.size();
        int[][] knapsackMatrix = new int[numOfItems + 1][budget + 1]; //creates a matrix to hold and calculate the optimal selection of items
        //matrix bounds added by 1 on each dimention to simulate the lack of an item in the knapsack
        for(int itemIndex = 1; itemIndex < knapsackMatrix.length; itemIndex++){ //loops through all available items
            for(int currentBudget = 0; currentBudget < knapsackMatrix[0].length; currentBudget++){
                //loops to calculate remaining budget depending on items already selected based on the matrix position
                if(availableItems.get(itemIndex-1).getPrice() <= currentBudget){ //checks if an item fits in the current budget
                    //item fits in current budget
                    knapsackMatrix[itemIndex][currentBudget] = //sets the best current price as follows
                            Math.max(knapsackMatrix[itemIndex-1][currentBudget], //compares value if item currently selected not included
                                    knapsackMatrix[itemIndex-1] //considers previously selected items if any
                                            [currentBudget-availableItems.get(itemIndex-1).getPrice()]
                                            //value of the previous items. max value attainable by remaining budget subtracting the item currently considered
                                                + availableItems.get(itemIndex-1).getScore()); //adds selected item quality
                }
                else{
                    //item exceeds current budget
                    knapsackMatrix[itemIndex][currentBudget]=knapsackMatrix[itemIndex-1][currentBudget];
                    //selects max value possible for the current budget by checking previous index and sets at current index
                }
            }
        }
        int remainingBudget = budget;
        for (int itemIndex = numOfItems; itemIndex > 0 && remainingBudget > 0; itemIndex--){
            //for loop for finding items selected at best value as found in the knapsack matrix
            //iterates through all items
            if (knapsackMatrix[itemIndex][remainingBudget] != knapsackMatrix[itemIndex - 1][remainingBudget]){
            //checks if the current total value for the current budget differs than the above index
            //if it differs then the current item was included in the optimal value
                itemsTaken.add(availableItems.get(itemIndex-1)); //item added to Arraylist for storage
                remainingBudget-=availableItems.get(itemIndex-1).getPrice(); //budget updated to reflect item being added
            }
        }
    }


    public static void main(String[] args) {
        int budget;
        ArrayList<Item> ItemsChosen = new ArrayList<Item>();
        int totalSusScore = 0;
        int totalkWhSaved = 0;
        int totalCost = 0;
        boolean cat_restricted = false;
        boolean min_score_sel = false;
        //booleans added to check if a restriction has been made
        boolean correctinput=false;
        //boolean to check later inputs
        int minimumSusScore = 0; //sustainability restriction
        ArrayList<String> restrictedCategories = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        ArrayList<Item> Items = new ArrayList<Item>();
        //Arraylist that holds all items offered, Arraylist used instead of normal Array for ability to add and remove indices
        //lighting category
        Items.add(new Item(2400,160,5,"LED Panel Light","Lighting"));
        Items.add(new Item(1100,90,4,"Motion Sensor Light Switch","Lighting"));
        Items.add(new Item(3800,180,5,"Solar-Powered Outdoor Lamp","Lighting"));
        Items.add(new Item(3000,140,4,"Smart Dimming Control System","Lighting"));
        Items.add(new Item(850,110,3,"CFL Bulb Pack","Lighting"));
        //energy production category
        Items.add(new Item(32000,1600,5,"Rooftop Solar Panel Kit","Energy"));
        Items.add(new Item(45000,1800,4,"Small Wind Turbine","Energy"));
        Items.add(new Item(9000,500,4,"Micro-Inverter System","Energy"));
        Items.add(new Item(28000,700,5,"Solar Battery Storage","Energy"));
        Items.add(new Item(6500,220,3,"Energy Management Controller","Energy"));
        //heating category
        Items.add(new Item(6500,450,4,"High-Efficiency Electric Convector Heater","Heating"));
        Items.add(new Item(4200,300,5,"Smart Thermostat","Heating"));
        Items.add(new Item(15000,900,5,"Solar Water Heater","Heating"));
        Items.add(new Item(5800,370,4,"Infrared Wall Heater","Heating"));
        Items.add(new Item(18500,950,5,"Heat Pump Water Heater","Heating"));
        //cooling category
        Items.add(new Item(22000,620,4,"High-Efficiency Inverter Air Conditioner","Cooling"));
        Items.add(new Item(4800,150,3,"Smart Ceiling Fan","Cooling"));
        Items.add(new Item(9200,400,4,"Evaporative Air Cooler","Cooling"));
        Items.add(new Item(3500,200,4,"Thermal Window Film","Cooling"));
        Items.add(new Item(14000,380,5,"Zoned Cooling System","Cooling"));
        //printed lines to add design to terminal input
        System.out.println("*---------------------------------------------*");
        System.out.println("Welcome to the 0/1 knapsack algorithm tester.");
        wait(500);
        System.out.println("Please enter your specified budget: ");
        budget = input.nextInt();
        wait(500);
        System.out.println("*---------------------------------------------*");
        System.out.println("Do you wish to apply a restriction on Category? Y/N");
        //switch in while loop to handle faulty input and reprompt user to input data
        while(!correctinput) {
            switch (input.next().charAt(0)) {
                case 'y':
                    cat_restricted = true;
                    correctinput = true;
                    break;
                case 'Y':
                    cat_restricted = true;
                    correctinput = true;
                    break;
                case 'n':
                    correctinput = true;
                    break;
                case 'N':
                    correctinput = true;
                    break;
                default:
                    wait(500);
                    System.out.println("Invalid input. Please reply Y for yes or N for no");
                    wait(500);
            }
        }
        correctinput = false; //reset input checker for while-loop
        input.nextLine(); //clear buffer after inputted char, will skip next string entry if not done
        wait(1500);
        System.out.println("*---------------------------------------------*");
        System.out.println("Do you wish to set a minimum sustainability score? Y/N");
        while(!correctinput) {
            switch (input.next().charAt(0)) {
                case 'y':
                    min_score_sel = true;
                    correctinput = true;
                    break;
                case 'Y':
                    min_score_sel = true;
                    correctinput = true;
                    break;
                case 'n':
                    correctinput = true;
                    break;
                case 'N':
                    correctinput = true;
                    break;
                default:
                    wait(500);
                    System.out.println("Invalid input. Please reply Y for yes or N for no");
                    wait(500);
            }
        }
        correctinput = false;
        input.nextLine();
        if(min_score_sel){ //if statement triggers when user wishes to add a minimum sustainability score, skips otherwise
            wait(500);
            System.out.println("*---------------------------------------------*");
            System.out.println("Please enter your minimum sustainability score requirement");
            while(!correctinput) {
                switch (input.nextInt()){
                    case 1:
                        minimumSusScore = 1;
                        correctinput = true;
                        break;
                    case 2:
                        minimumSusScore = 2;
                        correctinput = true;
                        break;
                    case 3:
                        minimumSusScore = 3;
                        correctinput = true;
                        break;
                    case 4:
                        minimumSusScore = 4;
                        correctinput = true;
                        break;
                    case 5:
                        minimumSusScore = 5;
                        correctinput = true;
                        break;
                    default:
                        wait(500);
                        System.out.println("Invalid input. Please enter a number between 1-5");
                        wait(500);
                }
            }
            correctinput = false;
            input.nextLine();
        }
        if(cat_restricted){ //checks if user wishes to add a category restriction, similar to above if statement
            wait(500);
            System.out.println("*---------------------------------------------*");
            System.out.println("Please enter the categories you wish to restrict" + "\n" +
                    "Please input your categories separated by a comma, ex: categ1,categ2,etc.");
            String categs = input.nextLine();
            categs = categs.replaceAll(" ","");
            String[] CategsProcessed = categs.split(",");
            //user input text processed by removing all spaces, and separating all items by a comma
            //processed text is added to an Arraylist of unwanted categories, invalid input is disregarded and user is notified
            for (int i = 0; i < CategsProcessed.length; i++){
                switch (CategsProcessed[i].toLowerCase()){ //text forced to lowercase to simplify comparison
                    case "heating":
                        restrictedCategories.add("Heating");
                        break;
                    case "cooling":
                        restrictedCategories.add("Cooling");
                        break;
                    case "lighting":
                        restrictedCategories.add("Lighting");
                        break;
                    case "energyproduction":
                        restrictedCategories.add("Energy");
                        break;
                    default:
                        wait(500);
                        System.out.println("Invalid category entered. Input \"" + CategsProcessed[i] + "\" ignored.");
                        wait(500);
                }
            }
        }

        if(min_score_sel){ //triggers if the user wants a minimum sustainability score, removes all restricted items from main list
            for(int i = 0; i < Items.size(); i++){
                if(Items.get(i).getSustainability() < minimumSusScore){
                    Items.remove(i);
                    i--;
                }
            }
        }

        if(cat_restricted){ //triggers if the user doesn't want specific categories, removes all restricted items from main list
            for(int i = 0; i < Items.size(); i++){
                String temp = Items.get(i).getCategory();
                for(String restrictedCategory : restrictedCategories){
                    if(temp.equals(restrictedCategory)){
                        Items.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        //added design outputs to improve user experience
        System.out.println("*---------------------------------------------*");
        System.out.println("Please Hold for calculation.");
        wait(5000);
        System.out.println("Starting budget: " + budget + "TL.");
        wait(1500);
        if(cat_restricted||min_score_sel){ //detects if restrictions have been made and reacts accordingly
            System.out.println("Applying restrictions.");
            wait(2000);
            if(cat_restricted){
                System.out.print("Categories restricted: ");
                wait(1000);
                for(int i = 0; i < restrictedCategories.size(); i++){
                    if(i==restrictedCategories.size()-1) {
                        System.out.print(restrictedCategories.get(i) + ".");
                        wait(500);
                    }
                    else{
                        System.out.print(restrictedCategories.get(i)+", ");
                        wait(500);
                    }
                }
                System.out.println();
                wait(500);
            }
            wait(2000);
            if(min_score_sel){
                System.out.println("Applying minimum acceptable sustainability: " + minimumSusScore + ".");
                wait(1000);
            }
        }
        System.out.println("Compiling information.");
        wait(5000);
        //applies above 0-1 knapsack algorithm with the current budget, remaining items after restrictions,
        // and array for selected items
        biKnapsack(budget,Items,ItemsChosen);
        //displays items and all relevant data, waits added for extra suspence and improved user experience
        System.out.println("*---------------------------------------------*");
        System.out.println("Items chosen:");
        wait(500);
        for(int i = 0; i < ItemsChosen.size(); i++){
            totalSusScore += ItemsChosen.get(i).getScore();
            totalkWhSaved += ItemsChosen.get(i).getKwhSaving();
            totalCost += ItemsChosen.get(i).getPrice();
            System.out.println(ItemsChosen.get(i));
            wait(500);
        }
        System.out.println("Total price of all equipment: " + totalCost +"TL.");
        wait(500);
        System.out.println("Total energy saved (in Kilowatts): " + totalkWhSaved +"kWh.");
        wait(500);
        System.out.println("Total sustainable impact of equipment: " + totalSusScore +".");
        wait(500);
        System.out.println("Remaining budget: "+ (budget-totalCost) +"TL.");
        wait(500);
        System.out.println("Thank you for testing the algorithm.");
        System.out.println("*---------------------------------------------*");
    }
}