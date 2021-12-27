package hangman;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class that holds the puzzles of the Hangman game.
 */
public class Puzzles {
    private List<String> puzzleList;

    /**
     * Set the list of available puzzles.
     */
    public Puzzles(){
        this.puzzleList = Arrays.asList("apple", "cat", "dog", "phone", "work",
                "alice", "assassin", "creek", "stream", "normal",
                "dictionary", "superficial", "sentence", "hug", "beautiful",
                "derogatory", "crescent", "composition", "evangelion",
                "sanguine", "capricious", "corroborate", "alchemist",
                "perhaps", "maybe", "animation", "violet", "sapphire",
                "labyrinth", "euphoria", "bungalow", "aurora", "serendipity",
                "solitude", "love", "renaissance", "peace", "lullaby",
                "pristine", "paradox", "benchmark", "bank", "central",
                "silhouette", "gloria", "graffiti", "ammunition", "salad",
                "amnesty", "faith", "american", "canada", "maple", "homeland",
                "china", "fate", "stay", "night", "sinner", "naivety"
                );
    }

    /**
     * Get a puzzle from the list randomly.
     * @return a puzzle generated randomly.
     */
    public String getNewPuzzle(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(puzzleList.size());
        return puzzleList.get(randomIndex);
    }
}
