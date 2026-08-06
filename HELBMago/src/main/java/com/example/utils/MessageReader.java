package com.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.models.Message;;

/**
 * Reads messages from a text file and stores only valid messages.
 * Messages can then be retrieved one by one.
 */
public class MessageReader {

    private final List<Message> messageList =  new ArrayList<>();
    private int maxIndex = 0;
    private int currentIndex = 0;

    /**
     * Creates a MessageReader and loads all valid messages from the given file.
     * @param filename name of the file to read
     */
    public MessageReader(String filename){
        readMessages(filename);
        this.maxIndex = messageList.size();
    }

     /**
     * Returns the total number of valid messages.
     */
    public int getNumberOfMessages() { 
        return messageList.size();
    }

    public List<Message> getMessagesList() { 
        return messageList; 
    }
    
    public void removeMessage(Message message){ 
        messageList.remove(message); 
    }

    /**
     * Checks whether another message is available.
     * @return true if another message can be read
     */
    public boolean hasNextMessage() {
        return (currentIndex < maxIndex);
    }

    /**
     * Returns the next available message.
     * @return the next message
     * @throws IndexOutOfBoundsException if no message is available
     */
    public Message getNextMessage() {
        if(hasNextMessage()){
            return messageList.get(currentIndex++);
        }else{
            throw new IndexOutOfBoundsException("No more messages available");
        }
    }
    
    /**
     * Creates a Message object from one line of text.
     * @param line line read from the file
     * @return the created Message
     */
    private Message createMessageFromLine(String line) { return new Message(line); }

    /**
     * Reads the input file and stores all valid messages.
     *
     * <p>Each line is converted into a {@link Message}. Invalid
     * messages are ignored. If the file cannot be opened, an
     * error message is printed to the standard error stream.</p>
     *
     * @param filename the path to the input file
     */
    private void readMessages(String filename) {
        
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Message message = createMessageFromLine(line);
                if (message.isValid()) {
                    messageList.add(message);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
