/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;


public class MessageManager {
    private static POEPart2[] sentMessages;
    private static int totalMessages = 0;
    
public static void showMainMenu(){
        
               JOptionPane.showMessageDialog(null,"Welcome to QuickChat");
        
       int messageLimit = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));
        sentMessages = new POEPart2[messageLimit];
       int messagesSent = 0;
   
        
    while(true){
         String menu = "Choose an option:\n"
                        + "1) Send Message\n"
                        + "2) Show Recently Sent Messages\n"
                        + "3) Quit";
        int option = Integer.parseInt(JOptionPane.showInputDialog(menu));

     switch(option){
           case 1:
             if(messagesSent >= messageLimit){           
             JOptionPane.showMessageDialog(null,"Message limit reached");
         break;
            }
             POEPart2 message = new POEPart2();
             message.create();
                    
            String action = message.sendMessageAction();
                  
            boolean validAction = false;
                    
    while (!validAction) {
     switch(action.toLowerCase()){
           case "send":
             sentMessages[messagesSent] = message;
             totalMessages++;
             messagesSent++;
             JOptionPane.showMessageDialog(null, message.getMessageDetails());
             validAction = true;
         break;
                            
           case "store":
             storeMessageToFile(message);
             JOptionPane.showMessageDialog(null, "Message successfully stored.");
             validAction = true;
         break;
                            
           case "discard":
             JOptionPane.showMessageDialog(null, "Message discarded.");
             validAction = true;
          break;
                            
          default:
             JOptionPane.showMessageDialog(null, "Unknown action.");            
         }                  
    }
         break;
                                      
           case 2:
           JOptionPane.showMessageDialog(null,"Coming soon...");
           /*  if (messagesSent == 0) {
             JOptionPane.showMessageDialog(null, "No messages sent yet.");
   } else {
             StringBuilder builder = new StringBuilder("Sent Messages:\n");
             for (int i = 0; i < messagesSent; i++) {
             builder.append(sentMessages[i].getMessageDetails()).append("\n---\n");
         }
             JOptionPane.showMessageDialog(null, builder.toString());
         }*/
         break;
                    
           case 3:
             JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages + "\nGoodbye!");
         return;

          default:
             JOptionPane.showMessageDialog(null, "Invalid option.");
                   
            }
        }
    }
    
public static void storeMessageToFile(POEPart2 message) {
    final int MAX_MESSAGES = 100; // you can change this limit
    String[] messages = new String[MAX_MESSAGES];
    int count = 0;

    File file = new File("storedMessages.json");

    // Step 1: Read existing messages into the array
    if (file.exists()) {
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder jsonBuilder = new StringBuilder();
    while (scanner.hasNextLine()) {
         jsonBuilder.append(scanner.nextLine());
            }

         String json = jsonBuilder.toString().trim();
   if (json.startsWith("[") && json.endsWith("]")) {
         json = json.substring(1, json.length() - 1).trim(); // remove [ and ]
   if (!json.isEmpty()) {
         String[] entries = json.split("},\\s*\\{");
   for (int i = 0; i < entries.length && i < MAX_MESSAGES; i++) {
         String entry = entries[i];
   if (!entry.startsWith("{")) entry = "{" + entry;
   if (!entry.endsWith("}")) entry = entry + "}";
       messages[count++] = entry;
        }
    }
}
        } catch (IOException e) {
            System.out.println("Error reading from file.");
    }
}

    // Step 2: Add new message as JSON string
    String newMessageJson = "{\n" +
        "  \"Message ID\": \"" + message.getMessageID() + "\",\n" +
        "  \"Message Hash\": \"" + message.getMessageHash() + "\",\n" +
        "  \"Recipient\": \"" + message.getRecipient() + "\",\n" +
        "  \"Message Body\": \"" + message.getMessageBody().replace("\"", "\\\"") + "\"\n" +
        "}";
    
    if (count < MAX_MESSAGES) {
        messages[count++] = newMessageJson;
    } else {
        System.out.println("Message storage limit reached.");
    }

    // Step 3: Write all messages back to file as a JSON array
    try (FileWriter writer = new FileWriter(file)) {
        writer.write("[\n");
   for (int i = 0; i < count; i++) {
        writer.write(messages[i]);
   if (i < count - 1) writer.write(",\n");
        }
        writer.write("\n]");
    } catch (IOException e) {
        System.out.println("Error writing to JSON file.");
        }
    }
}

