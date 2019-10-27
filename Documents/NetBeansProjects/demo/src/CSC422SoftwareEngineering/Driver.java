/*
 * Devin Nicholson
 * CSC 422 Software Engineering
 * Week 1 / Assignement 1 Part 2 Version Control
 * Created: 10/23/2019
 * Revised: 
 * 
 * driver class
 * 
 */
package CSC422SoftwareEngineering;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicho
 */
public class Driver {
    
     /** The pets. */
   static ArrayList<Pet> pets;

   /**
   * The main method.
   *
   * @param args the arguments
   */
   public static void main(String[] args) {

       Scanner scan = new Scanner(System.in);
       pets = new ArrayList<>();
       int option;
       do {
           menu();
           System.out.print("Your chioce: ");
           option = scan.nextInt();
           scan.nextLine();
           switch (option) {
           case 1:
               viewAllPets();
               break;
           case 2:
               addMorePets(scan);
               break;
           case 3:
               System.out.println("Thank you for using the Pet Database System :)");
               break;

           default:
               System.out.println("Invalid choice!");
               break;
           }

       } while (option != 7);

       scan.close();
   }

   //view all pets that were added
   private static void viewAllPets() {

       System.out.println("+---------------------------------------+");
       System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
       System.out.println("+---------------------------------------+");
       int i = 0;
       for (Pet pet : pets) {

           System.out.printf("|%5d%5s", i, pet.toString());

           i++;
       }
       System.out.println("+---------------------------------------+");
       System.out.println((i) + " rows in set.");
   }

   //code for adding pets
   private static void addMorePets(Scanner scan) {
       int count = 0;
       String petString = "";
       do {

           System.out.print("add pet (name, age): ");
           petString = scan.nextLine();
           if (petString.equalsIgnoreCase("done")) {

               break;
           }
           String name = petString.split("\\s+")[0];
           int age = Integer.parseInt(petString.split("\\s+")[1]);

           pets.add(new Pet(name, age));
           count++;

       } while (!petString.equalsIgnoreCase("done"));
       System.out.println(count + " pets added.");
   }
    
   //build the menu
   public static void menu() {

       System.out.println("What would like to do?\n" + 
                          "\t1) View all pets\n" + 
                          "\t2) Add more pets\n" + 
                          "\t3) Exit program");
   }

}
