/*
 * Devin Nicholson
 * CSC 422 Software Engineering
 * Week 2 / Assignement 2 Part 2 
 * Created: 10/23/2019
 * Revised: 
 * 
 * Pet class to build pets for array
 * 
 */

package CSC422SoftwareEngineering;

/**
 *
 * @author nicho
 */
public class Pet {
    
  
   private String name;
   private int age;

   /**
   * Instantiates a new pet.
   *
   * @param name the name
   * @param age the age
   */
   public Pet(String name, int age) {
       super();
       this.name = name;
       this.age = age;
   }

   // getters and setters
   public String getName() {
       return name;
   }

 
   public void setName(String name) {
       this.name = name;
   }

   public int getAge() {
       return age;
   }


   public void setAge(int age) {
       this.age = age;
   }

  
   @Override
   public String toString() {
       String s = "";
       s = String.format("%5s%10s%10s%5d%5s\n", "|", name, "|", age, "|");
       return s;
   }

}