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
    private static POEPart2[] sentMessages = new POEPart2[100];
    private static POEPart2[] disregardedMessages = new POEPart2[100];
    private static String[] messageHashes =  new String[100];
    private static String[] messageIDs = new String[100];
    private static int sentCount = 0;
    private static int disregardedCount =0;
    private static int totalMessages = 0;
    private static String hash;
    
public static void showMainMenu(){
        
               JOptionPane.showMessageDialog(null,"Welcome to QuickChat");
        
       int messageLimit = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));
        sentMessages = new POEPart2[messageLimit];
       int messagesSent = 0;
   
        
    while(true){
         String menu = "Choose an option:\n"
                        + "1) Send Message\n"
                        + "2) Show Recently Sent Messages\n"
                        + "3) Load Stored Messages from JSON\n"
                        + "4) Display Sent Report\n"
                        + "5) Display Longest Message\n"
                        + "6) Search Using Message ID\n"
                        + "7) Search Using Recipient\n"
                        + "8) Delete Using Message Hash\n"
                        + "9) Quit";
                        
        int option = Integer.parseInt(JOptionPane.showInputDialog(menu));

     switch(option){
           case 1:
               handleNewMessage();
             /*if(messagesSent >= messageLimit){           
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
    }*/
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
               loadStoredMessagesFromJson();
            // JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages + "\nGoodbye!");
         break;
         
           case 4:
                displayReport();
                    break;
                    
           case 5:
                    displayLongestMessage();
                    break;
                    
           case 6:
                    searchByID();
                    break;
                    
           case 7:
                    searchByRecipient();
                    break;
                    
                case 8:
                    deleteByHash(hash);
                    break;
                    
                case 9:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    return;    
          
          default:
             JOptionPane.showMessageDialog(null, "Invalid option.");
                   
            }
        }
    }

private static void handleNewMessage() {
    // Handles user input for creating a new message
// Based on the user's action ("Send", "Store", or "Discard"), it updates relevant arrays or files

POEPart2 message = new POEPart2();
        message.create();

        String action = message.sendMessageAction();

        switch (action.toLowerCase()) {
            case "send":
                sentMessages[sentCount] = message;
                messageHashes[sentCount] = message.getMessageHash();
                messageIDs[sentCount] = message.getMessageID();
                sentCount++;
                JOptionPane.showMessageDialog(null, message.getMessageDetails());
                break;
            case "store":
                storeMessageToFile(message);
                JOptionPane.showMessageDialog(null, "Message successfully stored.");
                break;
            case "discard":
                disregardedMessages[disregardedCount++] = message;
                JOptionPane.showMessageDialog(null, "Message successfully discarded.");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid action.");
        }
    }

    public static void storeMessageToFile(POEPart2 message) {
        // Saves a message to a JSON-like file (storedMessages.json) by appending to the file
// This simulates storing to a persistent data file for later retrieval

        String json = "{\n" +
                "  \"Message ID\": \"" + message.getMessageID() + "\",\n" +
                "  \"Message Hash\": \"" + message.getMessageHash() + "\",\n" +
                "  \"Recipient\": \"" + message.getRecipient() + "\",\n" +
                "  \"Message Body\": \"" + message.getMessageBody().replace("\"", "\\\"") + "\"\n" +
                "}";

        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            file.write(json + ",\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing JSON: " + e.getMessage());
        }
    }

    private static void loadStoredMessagesFromJson() {
        // Loads stored messages from the JSON file and displays them as raw JSON text
// This allows the user to retrieve and view messages saved from previous sessions

        File file = new File("storedMessages.json");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "No stored JSON message was found.");
            return;
        }

        try {
            StringBuilder content = new StringBuilder();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                content.append(sc.nextLine());
            }
            sc.close();

            String json = "[" + content.toString().replaceAll(",\\s*$", "") + "]";
            JOptionPane.showMessageDialog(null, "Stored messages loaded:\n" + json);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading to JSON.");
        }
    }

    private static void displayReport() {
        // Outputs a full report of all messages stored in sentMessages
// This can be printed or reviewed for documentation or review

        StringBuilder builder = new StringBuilder("Message Report:\n");
        for (int i = 0; i < sentCount; i++) {
            builder.append(sentMessages[i].getMessageDetails()).append("\n---\n");
        }
        JOptionPane.showMessageDialog(null, builder.toString());
    }

    private static void displayLongestMessage() {
        // Finds and displays the longest message from the sentMessages array
// Useful to show the message with the most content

        int maxLen = 0;
        String longest = "";
        for (int i = 0; i < sentCount; i++) {
            String msg = sentMessages[i].getMessageBody();
            if (msg.length() > maxLen) {
                maxLen = msg.length();
                longest = msg;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest message:\n" + longest);
    }

    private static void searchByID() {
        // Searches for a message in the sentMessages array by message ID
// Displays the message details if a match is found

        String id = JOptionPane.showInputDialog("Enter Message ID to search:");
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getMessageID().equals(id)) {
                JOptionPane.showMessageDialog(null, "Found:\n" + sentMessages[i].getMessageDetails());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID you entered was not found.");
    }

    static void searchByRecipient() {
        // Searches for all messages sent to a particular recipient (phone number)
// Displays matching messages to the user

        String rec = JOptionPane.showInputDialog("Enter recipient mobile number:");
        StringBuilder matches = new StringBuilder("Messages sent to " + rec + ":\n");
        boolean found = false;
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getRecipient().equals(rec)) {
                matches.append(sentMessages[i].getMessageBody()).append("\n");
                found = true;
            }
        }
        JOptionPane.showMessageDialog(null, found ? matches.toString() : "No messages found for the recipient you entered.");
    }

    static boolean deleteByHash(String hash) {
        // Deletes a message from the sentMessages array based on its unique hash
// Also shifts array elements to keep the array contiguous

        hash = JOptionPane.showInputDialog("Enter hash to delete:");
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getMessageHash().equals(hash)) {
                String deletedMsg = sentMessages[i].getMessageBody();
                // Shift elements left
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                sentMessages[--sentCount] = null;
                JOptionPane.showMessageDialog(null, "Deleted message:\n" + deletedMsg);
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Message hash you entered was not found.");
    return false;
    }
    
    // For unit testing support (must match what tests expect)
public static void clearMessages() {
    sentCount = 0;
    for (int i = 0; i < sentMessages.length; i++) {
        sentMessages[i] = null;
    }
}

public static void sendMessage(String recipient, String body, String status) {
    POEPart2 msg = new POEPart2();
    if (status.equalsIgnoreCase("Sent")) {
        sentMessages[sentCount++] = msg;
    }
}

public static POEPart2[] getSentMessages() {
    POEPart2[] result = new POEPart2[sentCount];
    System.arraycopy(sentMessages, 0, result, 0, sentCount);
    return result;
}

public static int getSentCount() {
    return sentCount;
}

public static String getLongestMessage() {
    int maxLen = 0;
    String longest = "";
    for (int i = 0; i < sentCount; i++) {
        String msg = sentMessages[i].getMessageBody();
        if (msg.length() > maxLen) {
            maxLen = msg.length();
            longest = msg;
        }
    }
    return longest;
}
public static POEPart2 searchMessageByID(String id) {
    for (int i = 0; i < sentCount; i++) {
        if (sentMessages[i].getMessageID().equals(id)) {
            return sentMessages[i];
        }
    }
    return null;
}

public static String getReport() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < sentCount; i++) {
        builder.append(sentMessages[i].getMessageDetails()).append("\n---\n");
    }
    return builder.toString();
}


}
    
/*public static void storeMessageToFile(POEPart2 message) {
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
}*/



