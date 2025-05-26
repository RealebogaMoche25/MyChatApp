/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author RC_Student_lab
 */
public class POEPart2 {
    private static int messageCounter = 0;
    private String messageID;
    private String recipient;
    private String messageBody;
    private String messageHash;
    
public void create(){
        this.messageID = String.valueOf((long)(Math.random()*1_000_000_0000L));
        messageCounter++;
        
        while (true) {
            recipient = JOptionPane.showInputDialog("Enter recipient phone number (e.g. +27...)");
        if (checkRecipientCell(recipient)) {
            JOptionPane.showMessageDialog(null, "Phone number captured.");
        break;
    } else {
             JOptionPane.showMessageDialog(null, "Invalid phone number. Must start with '+27' and be 10–13 digits long.");
    }
}
        
        while (true) {
            messageBody = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
        if (messageBody.length() <= 250) {
            JOptionPane.showMessageDialog(null, "Message accepted.");
        break;
    } else {
            JOptionPane.showMessageDialog(null, "Message too long by " + (messageBody.length() - 250) + " characters.");
    }
}
         
        this.messageHash = createMessageHash();
    }
    
    public boolean checkRecipientCell(String number){
        return number.startsWith("+27")&& number.length()== 12;
    }
    
    public String createMessageHash(){
        String[] words = messageBody.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        return messageID.substring(0, 2) + ":" + messageCounter + ":" + first + last;
    }
    
    public String sendMessageAction(){
       String action = JOptionPane.showInputDialog("Choose action: \nsend \nstore \ndiscard");
    return action != null ? action:"discard";
    }
    
    public String getMessageDetails() {
        return "Message ID:" + messageID + "\nMessage Hash:" + messageHash + "\nRecipient:" + recipient + "\nMessage:" + messageBody;
    }
    
     public String getMessageID(){
         return messageID;
     }
     
     public String getMessageHash(){
         return messageHash;
     }
     
     public String getRecipient(){
         return recipient;
     }
     
     public String getMessageBody(){
         return messageBody;

    }
    
}
