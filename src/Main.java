import java.util.*;
public class Main {
    public static void main(String[] args) {
        int budget;
        ArrayList<Item> ItemsChosen = new ArrayList<Item>();
        int totalSusScore;
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
        System.out.println("Welcome to the 0/1 knapsack algorithm tester." + "\n" +
                "Please enter your specified budget: ");
        budget = input.nextInt();
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
                    System.out.println("Invalid input. Please reply Y for yes or N for no");
            }
        }
        correctinput = false; //reset input checker for while-loop
        input.nextLine(); //clear buffer after inputted char, will skip next string entry if not done
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
                    System.out.println("Invalid input. Please reply Y for yes or N for no");
            }
        }
        correctinput = false;
        input.nextLine();
        if(min_score_sel){ //if statement triggers when user wishes to add a minimum sustainability score, skips otherwise
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
                        System.out.println("Invalid input. Please enter a number between 1-5");
                }
            }
            correctinput = false;
            input.nextLine();
        }
        if(cat_restricted){ //checks if user wishes to add a category restriction, similar to above if statement
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
                        System.out.println("Invalid category entered. Input \"" + CategsProcessed[i] + "\" ignored.");
                }
            }
        }

        if(min_score_sel){ //triggers if the user wants a minimum sustainability score, removes all restricted items from main list
            for(int i = 0; i < Items.size(); i++){
                if(Items.get(i).getSustainability() <= minimumSusScore){
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
        
    }
}