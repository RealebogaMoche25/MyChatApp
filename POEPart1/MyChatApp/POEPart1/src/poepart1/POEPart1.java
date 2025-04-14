/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
public class POEPart1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Scanner get = new Scanner(System.in);
        ClassLogin use = new ClassLogin();
        
        System.out.println("*****USER REGISTRATION*****");
        System.out.print("Enter your name: ");
        String name = get.nextLine();
        use.setName(name);
        
        System.out.print("Enter your surname: ");
        String surname = get.nextLine();
        use.setSurname(surname);
        
        System.out.print("Enter your phone number: ");
        String phone_number = get.nextLine();
        use.setPhone_number(phone_number);
        if(use.checkPhoneNumber(phone_number)){
            System.out.println("Phone number successfully captured");
        }while(!use.checkPhoneNumber(phone_number)){
             System.out.println("Phone number does not contain international code or is incorrectly formatted  ");
             System.out.print("Enter your phone number: ");
         phone_number = input.nextLine();
        use.setPhone_number(phone_number);
        }
        if(use.checkPhoneNumber(phone_number)){
            System.out.println("Phone number successfully captured");
        }
        
         System.out.print("Enter your username: ");
        String username = get.nextLine();
        use.setUserName(username);
        if(use.checkUserName(username)){
            System.out.println("Username successfully captured.");
        }while(!use.checkUserName(username)){
           System.out.println("Username is incorrectly formatted, please ensure that your username contains an underscore and is no more than five characters long in length.");  
           System.out.print("Enter your username: ");
          username = get.nextLine();
           use.setUserName(username);
        }
        if(use.checkUserName(username)){
            System.out.println("Username successfully captured.");
        }
            
        System.out.print("Enter your password: ");
        String password = get.nextLine();
        use.setPassword(password);
        if(use.checkPassword(password)){
            System.out.println("Password successfully captured.");
        }while(!use.checkPassword(password)){
           System.out.println("Password is incorrectly formatted; please ensure that password contains at least eight characters, a capital letter, a number, and a special character.");  
           System.out.print("Enter your password: ");
          password = get.nextLine();
        use.setPassword(password);
        }
         if(use.checkPassword(password)){
            System.out.println("Password successfully captured.");
        }
    
        System.out.println(use.RegisterUser(password,username));
        
        System.out.println("*****USER LOGIN*****");
        System.out.print("Username: ");
        String LoginUsername = input.nextLine();
        
        System.out.print("Password: ");
        String LoginPassword = input.nextLine();
        
        if(use.loginUser(LoginPassword,LoginUsername)){
            use.returnLoginStatus(username,password);
        }else{
            System.out.print("Username or Password incorrect, please try again");
        }
        while (!use.loginUser(LoginPassword, LoginUsername)){
            System.out.print("Username: ");
            LoginUsername = input.nextLine();
            
            System.out.print("Password: ");
            LoginPassword = input.nextLine();
            if(use.loginUser(LoginPassword,LoginUsername)){
                use.returnLoginStatus(username, password);
            }
        }
       /// System.out.println("");
        }
    }

                                                                                                                                                                                                                      