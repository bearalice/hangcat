package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class represents the controller of the Hangman game.
 */
public class Controller implements InterfaceController {
    private Model model;
    private View view;


    /**
     * Constructor of the controller.
     * It initializes the model and the view.
     */
    public Controller() {
        model = new Model(new Puzzles().getNewPuzzle());
        view = new View();
        //System.out.println(model.getPuzzle());
    }

    @Override
    public void play() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });

        try {
            //start of the game
            view.showRemainingGuesses(model.getRemainingGuesses());
            view.showState(model.printCurrString());

            view.getGuessInput().addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    typed(e);
                }

                private void typed(ActionEvent e) {
                    String currGuess = view.getGuessInput().getText();
                    view.showState(model.printCurrString());
                    view.showRemainingGuesses(model.getRemainingGuesses());

                    if (model.isValidInput(currGuess)){
                        if (currGuess.length() > 1) {
                            if (model.guessWord(currGuess)) {
                                view.showState(model.printCurrString());
                                view.win();
                            } else {
                                view.showState(model.printCurrString());
                                view.showRemainingGuesses(model.getRemainingGuesses());
                            }
                        } else {
                            boolean result = model.guess(currGuess);
                            view.showMessage(result);
                            view.showPic(model.getRemainingGuesses());
                            view.showState(model.printCurrString());
                            view.showRemainingGuesses(model.getRemainingGuesses());
                        }
                        view.showUsedLetters(model.getUsedLetters());
                        view.getGuessInput().setText("");
                    }

                    if (model.isGameOver()){
                        if (model.printCurrString().equals(model.getPuzzle())) {
                            view.win();
                        } else{
                            view.lose(model.getPuzzle());
                        }
                    }
                }
            });

            //to play again
            view.playAgain(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    model.reset();
                    view.reset(model.printCurrString());
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

        } catch (IllegalArgumentException e) {
            //System.out.println("not a word or char");
            view.showPic(10);
        }

    }
}