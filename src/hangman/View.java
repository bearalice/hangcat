package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * This class represents the view of the Hangman game.
 * It has a button to allow the user to play again,
 * a pic panel and its components to show the corresponding images,
 * JLables to show the guessing progess and a JTextField to get
 * the user's input.
 */
public class View extends JFrame implements InterfaceView{
    private javax.swing.JButton button;
    private javax.swing.JPanel textPanel;
    private javax.swing.JPanel picPanel;
    private javax.swing.JLabel currString;
    private javax.swing.JLabel remainingGuesses;
    private JLabel usedLettersLabel;
    private javax.swing.JLabel message;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField guessInput;
    private BufferedImage[] images;
    private Image pic;
    private JLabel picLabel;


    /**
     * Constructor of the View class.
     */
    public View() {
        initComponents();
    }

    /**
     * Helper method to initiliaze the components of the View's frame.
     */
    private void initComponents(){
        setSize(800,700);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hang-cat by alice");
        setAlwaysOnTop(true);
        setResizable(false);

        panel = new JPanel();
        textPanel = new JPanel();
        picPanel = new JPanel();


        buttonInit();
        this.button.setVisible(false);

        currString = new JLabel();
        currString.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        remainingGuesses = new JLabel();
        remainingGuesses.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        guessInput = new JTextField(12);
        guessInput.setToolTipText("Emter your guess here:");
        guessInput.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        message = new JLabel();
        message.setFont(new Font("Sans Serif", Font.BOLD, 20));
        message.setText("");

        usedLettersLabel = new JLabel();
        usedLettersLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        GridLayout stackL = new GridLayout(10,1,50,0);
        textPanel.setLayout(stackL);
        textPanel.add(button);
        textPanel.add(currString);
        textPanel.add(remainingGuesses);
        textPanel.add(guessInput);
        textPanel.add(message);
        textPanel.add(usedLettersLabel);


        imagesInit();
        pic = images[8];
        picLabel = new JLabel(new ImageIcon(pic));
        picPanel.add(picLabel);

        GridLayout l = new GridLayout(1,2,50,50);
        panel.setLayout(l);
        panel.add(textPanel);
        panel.add(picPanel);
        add(panel);
    }

    /**
     * Helper method to initialize the "play again" button.
     */
    private void buttonInit(){
        button = new JButton("PLAY AGAIN");
        button.setFont(new Font("Sans Serif", Font.PLAIN, 20));
    }

    /**
     * Helper method to initialize the images.
     */
    private void imagesInit(){
        images = new BufferedImage[11];
        images[10] = new Resource().getResourceImage("res/badinput.jpeg");
        images[9] = new Resource().getResourceImage("res/win.jpeg");
        images[8] = new Resource().getResourceImage("res/hangcat.jpeg");
        images[7] = new Resource().getResourceImage("res/hangcat7.jpeg");
        images[6] = new Resource().getResourceImage("res/hangcat6.jpeg");
        images[5] = new Resource().getResourceImage("res/hangcat5.jpeg");
        images[4] = new Resource().getResourceImage("res/hangcat4.jpeg");
        images[3] = new Resource().getResourceImage("res/hangcat3.jpeg");
        images[2] = new Resource().getResourceImage("res/hangcat2.jpeg");
        images[1] = new Resource().getResourceImage("res/hangcat1.jpeg");
        images[0] = new Resource().getResourceImage("res/catlost.jpeg");
    }

    @Override
    public void win() {
        remainingGuesses.setText("You won!");
        remainingGuesses.setFont(new Font("Sans Serif", Font.BOLD, 20));
        remainingGuesses.setForeground(new Color(34, 139, 34));
        showPic(9);
        message.setText("");
        usedLettersLabel.setText("");
        button.setVisible(true);
        currString.setVisible(false);
        guessInput.setVisible(false);
        repaint();
    }

    @Override
    public void lose(String puzzle) {
        remainingGuesses.setText("You lost! It's: "+puzzle);
        remainingGuesses.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        this.button.setVisible(true);
        message.setText("");
        usedLettersLabel.setText("");
        currString.setVisible(false);
        guessInput.setVisible(false);
        repaint();
    }

    @Override
    public void showMessage(boolean b) {
        if (b==true){
            message.setText("Bingo!");
            message.setForeground(new Color(34, 139, 34));
        } else{
            message.setText("Wrong!");
            message.setForeground(Color.RED);
        }

    }

    @Override
    public JTextField getGuessInput() {
        return this.guessInput;
    }

    @Override
    public void playAgain(MouseListener l) {
        button.addMouseListener(l);
    }

    @Override
    public void reset(String newCurrString) {
        button.setVisible(false);
        currString.setVisible(true);
        guessInput.setVisible(true);
        guessInput.setText("");
        guessInput.setToolTipText("Enter your guess here");
        showRemainingGuesses(8);
        message.setText("");
        usedLettersLabel.setText("");
        showState(newCurrString);
        showPic(8);
        repaint();
    }

    @Override
    public void showUsedLetters(List<String> usedLetters) {
        usedLettersLabel.setText("You have guessed: "+String.join(" ", usedLetters));
    }

    @Override
    public void showState(String s) {
        String newS = "";
        for (int i=0; i<s.length()-1; i++){
            newS += String.valueOf(s.charAt(i)) +" ";
        }
        newS += String.valueOf(s.charAt(s.length()-1));
        currString.setText("Length is: " + s.length() + " " + newS);
        repaint();
    }

    @Override
    public void showRemainingGuesses(int n) {
        remainingGuesses.setText("Number of remaining guesses: "+n);
        repaint();
    }

    @Override
    public void showPic(int n) {
        picPanel.remove(picLabel);
        this.pic = images[n];
        picLabel = new JLabel(new ImageIcon(pic));
        picPanel.add(picLabel);
        panel.add(picPanel);
        repaint();
    }
}
