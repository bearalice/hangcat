package hangman;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the model of the hangman game.
 * It has a puzzle - the word to be guessed, a currString stored
 * in a list to represent what the user has guessed, a list to store
 * the letter that have been guessed and a number of remaining guesses.
 */
public class Model implements InterfaceModel {
    private String puzzle;
    private List<String> currString;
    private int remainingChances;
    private List<String> alphabet;


    /**
     * Constructor of the model. It initializes the model
     * with the given puzzle.
     * @param Puzzle word to guess.
     */
    public Model(@NotNull String Puzzle) {
        this.puzzle = Puzzle;
        this.remainingChances = 8;
        this.currString = new ArrayList<>(Puzzle.length());
        for (int i=0; i<Puzzle.length(); i++){
            this.currString.add("_");
        }
        this.alphabet = new ArrayList<>();
    }

    @Override
    public boolean guessWord(String currGuess) throws IllegalStateException {
        if (isGameOver()){
            throw new IllegalStateException("Game Over");
        }
        currGuess = currGuess.toLowerCase();
        if (currGuess.equals(this.puzzle)){
            //System.out.println("You win!");
            this.currString = Arrays.asList(currGuess.toLowerCase().split(""));
            return true;
        } else{
            this.remainingChances -= 1;
            //System.out.println("Wrong! You have " +  this.remainingChances + " guesses left.");
            return false;
        }
    }

    @Override
    public boolean guess(String currGuess)  throws IllegalStateException {
        if (isGameOver()) {
            throw new IllegalStateException("Game Over");
        }
        currGuess = currGuess.toLowerCase();
        boolean res; //result
        if (this.puzzle.contains(currGuess)){
            //System.out.println("Bingo! You have " + this.remainingChances + " guesses left.");
            res = true;
        } else {
            this.remainingChances -= 1;
            //System.out.println("Wrong! You have " +  this.remainingChances + " guesses left.");
            res = false;
        }

        updateAlphabet(currGuess);
        updateWord(this.currString, currGuess);
        //System.out.println("Now the word is: ");
        printCurrString();
        if (this.currString.equals(this.puzzle)){
            //System.out.println("You win!");
        }
        return res;
    }

    /**
     * Helper method to update the letters that have been guessed.
     * @param s the current letter that is being guessed.
     */
    private void updateAlphabet(String s) {
        if (!alphabet.contains(s)){
            this.alphabet.add(s);
        }
    }

    /**
     * Update the current word to document the user's progress.
     * @param currString the current state of the word.
     * @param currGuess the current letter to be added.
     */
    private void updateWord(List<String> currString, String currGuess) {
        for (int i=0; i<currString.size(); i++){
            if ( currString.get(i) == "_" &&
                    String.valueOf(this.puzzle.charAt(i)).equals(currGuess) ){
                this.currString.set(i, currGuess);
            }
        }
        return;
    }

    @Override
    public String printCurrString() {
        return String.join("", currString);
//        this.currString.forEach(System.out::print);
    }

    @Override
    public int getRemainingGuesses() {
        return this.remainingChances;
    }

    /**
     * Check if the current word of the user's guess and
     * the puzzle are the same.
     * @return true if they are the same and false if not.
     */
    private boolean isSame() {
        String tmp = "";
        for (int i=0; i<this.currString.size(); i++){
            tmp += this.currString.get(i);
        }
        return tmp.equals(puzzle);
    }

    @Override
    public boolean isGameOver() {
        return this.remainingChances<=0 || isSame();
    }

    @Override
    public String getPuzzle() {
        return this.puzzle;
    }

    @Override
    public void reset() {
        puzzle = new Puzzles().getNewPuzzle();
        this.remainingChances = 8;
        this.currString = new ArrayList<>(puzzle.length());
        for (int i=0; i<puzzle.length(); i++){
            this.currString.add("_");
        }
        this.alphabet = new ArrayList<>();
    }

    @Override
    public List<String> getUsedLetters() {
        return this.alphabet;
    }

    @Override
    public boolean isValidInput(String currGuess) {
        currGuess = currGuess.toLowerCase();
        if (currGuess.matches("^[ a-z]+$") && !alphabet.contains(currGuess)){
            return true;
        }
        return false;
    }
}
