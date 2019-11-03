/*
 * Devin Nicholson
 * CSC 422 Software Engineering
 * Week 2 / Assignement 2 Part 2 Pet Database / text file utilization
 * Created: 11/1/2019
 * Revised: 11/3/2019
 * 
 * Pet database that saves and loads from a text file.
 * only allows 5 pets in database
 * Age is limited 1 to 20
 * expect input errors and notify user 
 */
package CSC422SoftwareEngineering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author nicho
 */
public class PetDatabase {
    
// main method to run the program
   public static void main(String[] args) {
       // print welcome line
       System.out.println("Pet Database Program.");
       // create a scanner object to read user input
       Scanner s = new Scanner(System.in);
       // create an ArrayList to store pet data
       ArrayList<Pet> pets = new ArrayList<Pet>();
       // load data from a file
       loadData(pets,"petdata.txt");
       OUTER:
       while (true) {
           System.out.println();
           printMenu();
           int option = getUserInput(s);
           // check user option
           switch (option) {
               case 1:
                   // display all pets
                   displayPets(pets);
                   break;
               case 2:
                   // add new pet to database if database has space
                   addPet(pets,s);
                   break;
               case 3:
                   // delete a pet from the database
                   remove(pets,s);
                   break;
               default:
                   // break the while loop
                   break OUTER;
           }
       }
       // close the scanner object
       s.close();
       // save database
       saveData(pets,"petdata.txt");
       // print message and end program
       System.out.println();
       System.out.println("Goodbye!");
   }

   private static void saveData(ArrayList<Pet> pets, String filename) {
       // write pets data to given file
       try {
           // open a file to write
           FileWriter file = new FileWriter(filename);
           for(int i=0;i<pets.size();i++) {
               file.write(String.format("%s %d\n", pets.get(i).getName(), pets.get(i).getAge()));
           }
           // close the file
           file.close();
       } catch (IOException e) {
           // print error message
           System.err.println("Can not save Data!");
       }
      
   }

   private static void remove(ArrayList<Pet> pets, Scanner s) {
       System.out.println();
       // ask user for id to remove
       System.out.print("Enter the pet ID to remove: ");
       // check for valid input
       try {
           int ID = Integer.parseInt(s.nextLine());
           if(ID<0||ID>pets.size()-1) {
               // print error message
               System.out.print("Error: ID ");
               System.out.print(ID);
               System.out.println(" does not exist.");
           }
           else {
               // remove pet with given id
               // print message removal is success
               System.out.print(pets.get(ID).getName() + " " + pets.get(ID).getAge());
               System.out.println(" is removed.");  
               pets.remove(ID);
           }
       } catch (Exception e) {
           // print error message
           System.out.println("Invalid Input!");
       }
   }

   private static void addPet(ArrayList<Pet> pets, Scanner s) {
       System.out.println();
       // prompt user to add pets till database is full
       String input;
       
       do {
           System.out.print("Add pet (name, age): ");
           // get user input
           
           input = s.nextLine();
           String[] data = input.split(" ");
           // check input data
           
           try {
               if(data.length != 2) {
                   throw new IllegalArgumentException();
               }
               int age = Integer.parseInt(data[1]);
               // check for age
               if(age<1 || age>20) {
                   // print error message
                   System.out.print("Error: ");
                   System.out.print(age);
                   System.out.println(" is not a valid age");
               }
               else {
                   // create a pet object
                   Pet p = new Pet(data[0], age);
                   // check if database have space
                   if(pets.size()<5) {
                       // add pet to database
                       pets.add(p);
                   }
                   else {
                       // print error message
                       System.out.print("Error: ");
                       System.out.println("Database is full.");
                       break;
                   }
               }
           } catch (Exception e) {
               
               if(input.equalsIgnoreCase("done")){
                   break;
               }
               else {
               // print error message
               System.out.print("Error: ");
               System.out.print(input);
               System.out.println(" is not a valid input");
               }
           }
                            
           
       } while (!input.equalsIgnoreCase("done"));
       
               
   }

   private static void displayPets(ArrayList<Pet> pets) {
       // print header
       System.out.println("+-------------------------+");
       System.out.println(String.format("| %2s | %-12s | %3s |", "ID","NAME","AGE"));
       System.out.println("+-------------------------+");
       // print pets
       for(int i=0;i<pets.size();i++) {
           System.out.println(String.format("| %2d | %-12s | %3d |", i, pets.get(i).getName(), pets.get(i).getAge()));
       }
       System.out.println("+-------------------------+");
       System.out.print(pets.size());
       System.out.println(" rows in set.");
      
   }

   private static int getUserInput(Scanner s) {
       // get input from user and return an integer from 1 to 4
       int option = 0;
       // prompt user until a valid input is entered
       while(true) {
           System.out.print("Your choice: ");
           try {
               option = Integer.parseInt(s.nextLine());
               if(option>0||option<5) {
                   // return the correct option
                   break;
               }
           } catch (Exception e) {
               // print error message
               System.out.println("Invalid Input!");
           }
       }
       return option;
   }

   private static void printMenu() {
       // print menu
       System.out.println("What would you like to do?");
       System.out.println("1) View all pets");
       System.out.println("2) Add new pets");
       System.out.println("3) Remove a pet");
       System.out.println("4) Exit program");
   }

   private static void loadData(ArrayList<Pet> pets, String filename) {
       // read data from file if file exist
       // create a scanner object to read data
       Scanner file;
       try {
           file = new Scanner(new File(filename));
           while(file.hasNextLine()) {
               // get input data from file
               String input = file.nextLine();
               String[] data = input.split(" ");
               // create a pet object
               Pet p = new Pet(data[0], Integer.parseInt(data[1]));
               // add pet to database
               pets.add(p);
           }
           // close the scanner
           file.close();
       } catch (FileNotFoundException e) {
           // file does not exist
       }
   }

}
