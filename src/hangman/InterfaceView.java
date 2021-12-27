package hangman;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * This interface represents the view of the hangman game.
 * It used Java AWT and Swing to present a GUI.
 */
public interface InterfaceView {

    /**
     * Show a message indicating success of the
     * current round of game.
     */
    void win();

    /**
     *  Show a message indicating failure of the
     *  current round of game.
     * @param puzzle the puzzle of current round of game
     */
    void lose(String puzzle);

    /**
     * For each guess, show whether the guess if correct
     * or wrong.
     * @param b true if the guess is correct and false otherwise.
     */
    void showMessage(boolean b);

    /**
     * Return the JTextField for guess input.
     * @return JTextField for guess input.
     */
    JTextField getGuessInput();

    /**
     * Method that allows the user to play again.
     * @param l MouseListener to get the user's action.
     */
    void playAgain(MouseListener l);

    /**
     * To reset the view's components so that the user
     * can play again.
     * @param newCurrString the new current string.
     */
    void reset(String newCurrString);

    /**
     * To show the user the letters that have been guessed.
     * @param usedLetters list of letters that have been guessed.
     */
    void showUsedLetters(List<String> usedLetters);

    /**
     * To show the user's progress.
     * @param s current string of what the user has guessed.
     */
    void showState(String s);

    /**
     * To show the user's number of remaining guesses.
     * @param n number of remaining guesses.
     */
    void showRemainingGuesses(int n);

    /**
     * To show the corresponding image of the number of
     * remaining guesses.
     * @param n number of remaining guesses.
     */
    void showPic(int n);
}
