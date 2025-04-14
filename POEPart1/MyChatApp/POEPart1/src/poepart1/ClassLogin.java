/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

/**
 *
 * @author RC_Student_lab
 */
public class ClassLogin {
private String Name;
private String Surname;
private String Phone_number;
private String Username;
private String Password;

public void setName(String name){
    this.Name = name;
}
public String getName(){
    return Name;
}  


public void setSurname(String surname){
    this.Surname = surname;
}
public String getSurname(){
    return Surname;
}


public void setUserName(String username){
    this.Username = username;
}
public String getUserName(){
    return Username;
}


public void setPhone_number(String phone_number){
    this.Phone_number = phone_number;
}
public String getPhone_number(){
    return Phone_number;
}


public void setPassword(String password){
    this.Password = password;
}   
public String getPassword(){
    return Password;
}


  public boolean checkUserName(String username){
        return this.Username.contains("_") && this.Username.length()<=5;
 } 
  
  
 public boolean checkPassword(String password){
     return this.Password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*?])[A-Za-z\\d!@#$%^&*?]{8,20}");
            
 }
 
 
 public boolean checkPhoneNumber(String phone_number){
   return this.Phone_number.startsWith("+27")&& this.Phone_number.length()==12;
    }
 
 
 public String RegisterUser(String password, String username){
      if (checkUserName(username) && checkPassword(password)){
         System.out.print("Registration Successful.");
      } else if (!checkUserName(username)){
         System.out.print("Username does not meet requirements");
 } else if(!checkPassword(password)){
         System.out.print("Password does not meet requirements");
     
 }
return"";
 }
 
 
 public boolean loginUser(String password, String username){
     return this.Password.equals(password) && this.Username.equals(username);
 }
 
 
 public String returnLoginStatus(String Username, String Password){ 
     if (checkPassword(Password) && checkUserName(Username)){
         System.out.println("Login Successful.");
         System.out.println("Welcome " + getName() + " " + getSurname());
     }else if (!checkPassword(Password)){
         System.out.println("Login Unsuccessful");
         System.out.println("Username or Password incorrect.Please try again");
         }else if (!checkUserName(Username)){
         System.out.println("Login Unsuccessful");
         System.out.println("Username or Password incorrect.Please try again");
     }
      return"";   
     }
 }
 

   

