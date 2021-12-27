package hangman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Model class.
 */
public class ModelTest {
    private Model wordWin;
    private Model wordLose;
    private Model generalWin;
    private Model generalLose;

    /**
     * Setup of the tests.
     */
    @Before
    public void setUp() {
        wordWin = new Model("hello");
        wordLose = new Model("novel");
        generalWin = new Model("alice");
        generalLose = new Model("serendipity");
    }

    /**
     * Test for guessWord method when the user wins.
     */
    @Test
    public void testGuessWord() {
        assertTrue(wordWin.guessWord("hello"));
        assertEquals(8, wordWin.getRemainingGuesses());
    }

    /**
     * Test for guessWord method when the guess is wrong.
     */
    @Test
    public void testGuessWordWrong() {
        assertFalse(wordLose.guessWord("shovel"));
        assertEquals(7, wordLose.getRemainingGuesses());
    }

    /**
     * Test for guess method when the user wins.
     */
    @Test
    public void testGuessWin() {
        assertTrue(generalWin.guess("a"));
        assertTrue(generalWin.guess("l"));
        assertTrue(generalWin.guess("i"));
        assertTrue(generalWin.guess( "c"));
        assertTrue(generalWin.guess("e"));
        assertTrue(generalWin.printCurrString().equals(generalWin.getPuzzle()));
    }

    /**
     * Test for guess method when the user loses after 8 wrong attempts.
     * Test for isGameOver method.
     */
    @Test
    public void testGuessLose() {
        //"serendipity"
        assertTrue(generalLose.guess("s"));
        assertFalse(generalLose.guess("w"));
        assertTrue(generalLose.guess("p"));
        assertFalse(generalLose.guess("k"));
        assertFalse(generalLose.guess("l"));
        assertTrue(generalLose.guess("n"));
        assertFalse(generalLose.guess("m"));
        assertTrue(generalLose.guess("d"));
        assertFalse(generalLose.guess("x"));
        assertFalse(generalLose.guess("o"));
        assertFalse(generalLose.guess("z"));
        assertFalse(generalLose.guess("b"));
        assertFalse(generalLose.printCurrString().equals(generalLose.getPuzzle()));
        assertTrue(generalLose.isGameOver());
    }

    /**
     * Test the reset method.
     */
    @Test
    public void testReset() {
        Model round1 = new Model("game");
        round1.guess("x");
        assertEquals(7, round1.getRemainingGuesses());
        round1.reset();
        //enter round2
        assertEquals(8, round1.getRemainingGuesses());
    }

    /**
     * Test when there are capital letters in the input.
     */
    @Test
    public void testCapitalInput() {
        Model testCapital = new Model("cafe");
        assertTrue(testCapital.guess("C"));
        assertTrue(testCapital.guessWord("CAFE"));
    }

    /**
     * Test the getUsedLetters method.
     */
    @Test
    public void testGetUsedLetters() {
        generalLose.guess("s");
        generalLose.guess("e");
        assertEquals("se", String.join("",generalLose.getUsedLetters()));
    }

    /**
     * Test for updateAlphabet method.
     */
    @Test
    public void testUpdateAlphabet(){
        Model updateAlphabet = new Model("pencil");
        updateAlphabet.guess("e");
        updateAlphabet.guess("i");
        assertEquals(2, updateAlphabet.getUsedLetters().size());
        assertEquals("ei", String.join("",updateAlphabet.getUsedLetters()));
    }

    /**
     * Test for updateWord method.
     */
    @Test
    public void testUpdateWord(){
        Model updateWord = new Model("food");
        updateWord.guess("o");
        assertEquals("_oo_", updateWord.printCurrString());
    }

    /**
     * Test for printCurrString method.
     */
    @Test
    public void testPrintCurrString(){
        Model printCurrString = new Model("danger");
        printCurrString.guess("e");
        printCurrString.guess("a");
        assertEquals("_a__e_", printCurrString.printCurrString());
    }

    /**
     * Test for getRemainingGuesses method.
     */
    @Test
    public void testRemainingGuesses(){
        Model remainingGuesses = new Model("glass");
        remainingGuesses.guess("a");
        remainingGuesses.guess("e");
        assertEquals(7, remainingGuesses.getRemainingGuesses());
    }

    /**
     * Test the isValidInput method when there is non-letter input.
     * For example, when the input is a letter.
     */
    @Test
    public void testIsValidInput() {
        Model validInput = new Model("cookie");
        assertTrue(validInput.guess("o"));
        assertFalse(validInput.guess("0"));
    }

    /**
     * Test the isValidInput method when there is non-letter input.
     * For example, when the input is a sign.
     */
    @Test
    public void testIsValidInputSigns() {
        Model validInput = new Model("laptop");
        assertTrue(validInput.guess("a"));
        assertFalse(validInput.guess("@"));
    }

    /**
     * Test when there is attempt to enter input after game is over by winning.
     * This also tests isSame method.
     */
    @Test
    public void testInputAfterGameOver(){
        Model inputAfterGameOver = new Model("cat");
        assertTrue(inputAfterGameOver.guessWord("cat"));
        assertEquals(8,inputAfterGameOver.getRemainingGuesses());
        try{
            inputAfterGameOver.guessWord("cat");
            fail("Exception should have been thrown!");
        } catch (IllegalStateException e){
        }
    }

    /**
     * Test when there is attempt to enter input after game is over by losing.
     */
    @Test
    public void testInputAfterGameOverLose(){
        Model inputAfterGameOverLose = new Model("equivalent");

        assertTrue(inputAfterGameOverLose.guess("e"));
        assertFalse(inputAfterGameOverLose.guess("b"));
        assertFalse(inputAfterGameOverLose.guess("c"));
        assertFalse(inputAfterGameOverLose.guess("d"));
        assertFalse(inputAfterGameOverLose.guess("f"));
        assertFalse(inputAfterGameOverLose.guess("g"));
        assertFalse(inputAfterGameOverLose.guess("h"));
        assertFalse(inputAfterGameOverLose.guess("x"));
        assertFalse(inputAfterGameOverLose.guess("y"));
        assertEquals(0,inputAfterGameOverLose.getRemainingGuesses());
        try{
            inputAfterGameOverLose.guess("v");
            fail("Exception should have been thrown!");
        } catch (IllegalStateException e){
        }
    }

    /**
     * Test for getPuzzle method.
     */
    @Test
    public void testGetPuzzle(){
        assertEquals("alice", generalWin.getPuzzle());
    }
}