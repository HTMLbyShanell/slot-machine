
package slotmachine;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 @author Shanell Spann
 * Course: IT-DEV 140  Programming with JAVA
 * Assignment 4
 * Date:  10/16/2020
 * Description: Create a program that simulates a slot machine game. 
 * Purpose: Practice using methods & logic with if-else statements.
 */
public class SlotMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LineUnavailableException {
        
        String userInput;
        double userAmountEntered;
        Random random = new Random();
        sound s = new sound();
        int wordIndex;
        String word = "";
        String outputString = "";
        String word1 = "";
        String word2 = "";
        String word3 = "";
        char playAgain = 'y';
        double totalUserAmountEntered = 0;
        double totalAmountWonSoFar = 0;
        
        while(playAgain == 'y'){
            outputString = "";
            s.playIntro();
            userInput = JOptionPane.showInputDialog(null, "Insert Gambling Money: ");
            userAmountEntered = Double.parseDouble(userInput);
            totalUserAmountEntered += userAmountEntered; // shorthand for totalUserAmountEntered = totalUserAmountEntered + userAmountEntered
            
            for(int wordCount = 1; wordCount <= 3; wordCount++){
                wordIndex = random.nextInt(6);
                
                if(wordIndex == 0){
                    word = "Cherries";
                } else if (wordIndex == 1){
                    word = "Oranges";
                } else if (wordIndex == 2){
                    word = "Plums";
                } else if (wordIndex == 3){
                    word = "Bells";
                } else if (wordIndex == 4){
                    word = "Melons";
                } else if (wordIndex == 5){
                    word = "Bars";
                }
                
                if(wordCount == 1){
                    word1 = word;
                } else if(wordCount == 2){
                    word2 = word;
                } else if (wordCount == 3){
                    word3 = word;
                }    
            } // end of for loop
            
            outputString = outputString + "[ " + word1 + " ]\t\t\t\t\t\t[ " + word2 + " ]\t\t\t\t\t\t[ " + word3 + " ]"; //controls display & alignment
            
            if((word1 != word2) && (word1 != word3) && (word2 != word3)){
                outputString  = outputString + "\n\nYou won $0.00";
                totalAmountWonSoFar = totalAmountWonSoFar + 0;
            } else if (((word1 == word2) && (word1 != word3)) || ((word1 == word3) && (word1 != word2)) || ((word2 == word3) && (word2 != word1))){
                outputString = outputString + String.format("\t\t\t x2\n\nYou won $%,.2f", (userAmountEntered * 2));
                totalAmountWonSoFar = totalAmountWonSoFar + (userAmountEntered * 2);
            } else{
                outputString = outputString + String.format("\t\t\t x3\n\nYou won $%,.2f", (userAmountEntered * 3));
                totalAmountWonSoFar = totalAmountWonSoFar + (userAmountEntered * 3);
            } // end of if-else statement
            
            outputString = outputString + String.format("\n\nAmount you've inserted so far: $%,.2f\nAmount won so far: $%,.2f", totalUserAmountEntered, totalAmountWonSoFar);
            playAgain = JOptionPane.showInputDialog(outputString + "\n\nDo you want to play again?\nEnter Y or N:").charAt(0); // will match 0 with y since we initialized in variables above. 
        }
        
        if(totalUserAmountEntered > totalAmountWonSoFar){
            s.playLose();
            JOptionPane.showMessageDialog(null, String.format("You inserted $%,.2f and won $%,.2f making a loss of $%,.2f. Better luck next time!", totalUserAmountEntered, totalAmountWonSoFar, totalUserAmountEntered - totalAmountWonSoFar));
        } else if (totalAmountWonSoFar > totalUserAmountEntered){
            s.playWin();
            JOptionPane.showMessageDialog(null, String.format("You inserted $%,.2f and won $%,.2f making a profit of $%,.2f. CONGRATULATIONS!", totalUserAmountEntered, totalAmountWonSoFar, totalAmountWonSoFar - totalUserAmountEntered));
        } else{
            s.playWin();
            JOptionPane.showMessageDialog(null, String.format("You inserted $%,.2f and won $%,.2f. You get to leave with what you came with. Thanks for playing!", totalUserAmountEntered, totalAmountWonSoFar));
        }
        
        System.exit(0);
    }
}
