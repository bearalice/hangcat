package hangman;

import java.util.List;

/**
 * This interface represent an interface of the model
 * of the hangman game. A Model gets its puzzle randomly
 * from {@link Puzzles}.
 * The user can guess up to 8 times.
 */
public interface InterfaceModel {

    /**
     * Check if a guess of one letter is correct or wrong.
     * @param currGuess the letter that the user has entered to guess.
     * @return true if the letter is in the word and false if not.
     * @throws IllegalStateException if game is over.
     */
    boolean guess(String currGuess) throws IllegalStateException;

    /**
     * Check if a guess of one word is correct or wrong.
     * @param currGuess the word that the user has entered to guess.
     * @return true if the word is the the same as the puzzle and false if not.
     * @throws IllegalStateException if game is over.
     */
    boolean guessWord(String currGuess) throws IllegalStateException;

    /**
     * Return a string that represents the current word that the user
     * has guessed, with correct letters and underscores.
     * @return s string to show the user's progress.
     */
    String printCurrString();

    /**
     * Get the number of remaining guesses.
     * @return number of remaining guesses.
     */
    int getRemainingGuesses();

    /**
     * Check if the game is over.
     * @return true if the game is over and false otherwise.
     */
    boolean isGameOver();

    /**
     * Return the puzzle of the current round of game.
     * @return puzzle of the current round of game.
     */
    String getPuzzle();

    /**
     * Reset the model of the game so the user can play again.
     */
    void reset();

    /**
     * Return a list of used letters to show to the user.
     * @return
     */
    List<String> getUsedLetters();

    boolean isValidInput(String currGuess);
}
