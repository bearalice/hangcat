package hangman;

/**
 * This interface represents the interface of the
 * controller of the Hangman game.
 * It handles user moves by executing them using the model
 * and conveys guess outcomes to the user via the view.
 */
public interface InterfaceController {

    /**
     * Execute a single guess. When the guess outcome has been
     * shown, get the next guess and execute it until the game
     * ends.
     */
    void play();
}
