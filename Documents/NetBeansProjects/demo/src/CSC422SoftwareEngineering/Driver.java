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
               updateExistingPet(scan);
               break;
           case 4:
               removeExistingPet(scan);
               break;
           case 5:
               searchByPetName(scan);
               break;
           case 6:
               searchByPetAge(scan);
               break;
           case 7:
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
   
   //updating an existing pet
   private static void updateExistingPet(Scanner scan) {

       viewAllPets();
       System.out.print("Enter the pet ID you want to update: ");
       int id = scan.nextInt();
       scan.nextLine();
       System.out.print("Enter new name and new age: ");
       String petString = scan.nextLine();
       String name = petString.split("\\s+")[0];
       int age = Integer.parseInt(petString.split("\\s+")[1]);
       String oldName = pets.get(id).getName();
       int oldAge = pets.get(id).getAge();
       pets.get(id).setName(name);
       pets.get(id).setAge(age);

       System.out.println(oldName + " " + oldAge + " changed to " + name + " " + age);
   }

   //removing of a pet
   private static void removeExistingPet(Scanner scan) {

       viewAllPets();
       System.out.print("Enter the pet ID to remove: ");
       int id = scan.nextInt();
       scan.nextLine();
       String name = pets.get(id).getName();
       int age = pets.get(id).getAge();
       pets.remove(id);
       System.out.println(name + " " + age + " is removed.");
   }
   
   //search for pet by name
   private static void searchByPetName(Scanner scan) {

       System.out.print("Enter name to search: ");
       String name = scan.nextLine();

       System.out.println("+---------------------------------------+");
       System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
       System.out.println("+---------------------------------------+");
       int i = 0;
       for (Pet pet : pets) {

           if (pet.getName().equalsIgnoreCase(name)) {

               System.out.printf("|%5d%5s", i, pet.toString());
               i++;
           }
       }
       System.out.println("+---------------------------------------+");
       System.out.println((i) + "rows in set.");

   }

   //search for pet by age
   private static void searchByPetAge(Scanner scan) {

       System.out.print("Enter age to search: ");
       int age = scan.nextInt();
       scan.nextLine();
       System.out.println("+---------------------------------------+");
       System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
       System.out.println("+---------------------------------------+");
       int i = 0;
       for (Pet pet : pets) {

           if (pet.getAge() == age) {

               System.out.printf("|%5d%5s", i, pet.toString());
               i++;
           }
       }
       System.out.println("+---------------------------------------+");
       System.out.println((i) + "rows in set.");

   }
    
   //build the menu
     public static void menu() {

       System.out.println("What would like to do?\n" + 
                          "\t1) View all pets\n" + 
                          "\t2) Add more pets\n" + 
                          "\t3) Update an existing pet\n" + 
                          "\t4) Remove an existing pet\n" + 
                          "\t5) Search pets by name\n" + 
                          "\t6) Search pets by age\n" + 
                          "\t7) Exit program");
   }

}
