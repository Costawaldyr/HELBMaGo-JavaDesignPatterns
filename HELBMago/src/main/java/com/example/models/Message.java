package com.example.models;

import com.example.utils.MyGlobals;


/**
 * Represents one message coming from the social network
 * A message is responsible for analysing it's own content
*/
public class Message {

    private static final String ALL_LETTERS = "abcdefghijklmnopqrstuvwxyzàâäéèêëîïôöùûüÿç";
    private static final String VOWEL = "aeiouyàâäéèêëîïôöùûüÿ";
    private static final String HTTP_PREFIX = "http://";
    private static final int INITIAL_COUNTER = 0;
    private static final int NOT_FOUND = -1;

    private String text;
    
    public Message(String text) {
        this.text = text;
    }

    // Getter
    public String getText() { return text; }


    /**
     * Checks whether the message is valid.
     * A valid message:
     * - contains at least one letter
     * - does not contain "http://"
     * @return true if valid
    */
    public boolean isValid() {
        if (containsHttpLink()) {
            return false;
        }
        return countLetters() > INITIAL_COUNTER;
    }


    /**
     * Checks whether the message contains a http link.
     * @return true if the message contains "http://"
    */
    public boolean containsHttpLink() { 
        return text.contains(HTTP_PREFIX); 
    }

    /**
     * Counts the number of vowels.
     * @return number of vowels
    */
    public int countVowels() {
        int cpt = INITIAL_COUNTER;
        String lower = text.toLowerCase();

        for(int i = MyGlobals.INITIAL_INDEX; i < lower.length(); i++){
            char character = lower.charAt(i);
            if (isVowel(character)) {
                cpt++;
            }
        }
        return cpt; 
    }


    /**
     * Counts the number of consonants.
     * @return number of consonants
    */
    public int countConsonants() {
        int cpt = INITIAL_COUNTER;
        String lower = text.toLowerCase();

        for (int i = MyGlobals.INITIAL_INDEX; i < lower.length(); i++) {
            char charact = lower.charAt(i);
            if (isLetter(charact)) {
                if (!isVowel(charact)) {
                    cpt++;
                }
            }
        }
        return cpt;
    }

    /**
     * Counts the number of positive punctuation characters ('!' and '?')
     * @return number of positive punctuation characters
    */
    public int countPositivePunctuation() {
        int cpt =INITIAL_COUNTER;

        for (int i = MyGlobals.INITIAL_INDEX; i < text.length(); i++) {
            char charact = text.charAt(i);
            if (charact == '!' || charact == '?') {
                cpt++;
            }
        }

        return cpt;
    }

    /**
     * Counts all alphabetic letters.
     * @return number of letters
    */
    public int  countLetters() {
        int cpt = INITIAL_COUNTER;

        for(int i = MyGlobals.INITIAL_INDEX; i < text.length(); i++){
            char charact = Character.toLowerCase(text.charAt(i));

            if (isLetter(charact)) {
                cpt++;
            }
        }
        return cpt;
    }

    /**
     * Checks whether a character is a vowel.
     * @param character character to check
     * @return true if the character is a vowel
     */
    private boolean isVowel(char character) {
        return VOWEL.indexOf(character) != NOT_FOUND;
    }


    /**
     * Checks whether a character is an alphabetic letter.
     * @param character character to check
     * @return true if the character is a letter
     */
    private boolean isLetter(char character) {
        return ALL_LETTERS.indexOf(character) != NOT_FOUND;
    }
}