import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlaySlotsGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton startGameButton;
    private JTextField userNameInfo;
    private JButton playSlotsButton;
    private JButton quitButton;
    private JTextArea userInformationTextArea;
    private JPanel imagePanel;
    private JLabel imageOne;
    private JLabel imageTwo;
    private JLabel imageThree;
    private SlotMachine slots;
    private Player p;
    private boolean playerLoaded;
    private PlayerData pd;

    public PlaySlotsGUI() {
        super("Welcome to Das Slots!");
        pd = new PlayerData();
        slots = new SlotMachine();
        slots.pullLever();
        setContentPane(mainPanel);
        setSize(600, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userInformationTextArea.setEditable(false);
        setVisible(true);
        imageOne.setText("");
        imageTwo.setText("");
        imageThree.setText("");
        playSlotsButton.addActionListener(this);
        startGameButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    public ImageIcon getIcon(String filename) {
        try {
            BufferedImage bi = ImageIO.read(new File(filename));
            return new ImageIcon(bi, "");
        }
        catch (Exception io) {
            System.out.println("Unable to find image");
            return null;
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        if (source.getText().equals("Play Slots") && (p != null)) {

            playSlotsButton.setEnabled(false);
            updateIcons();
            String info = slots.determineScore();
            info += "\nCurrent jackpot: " + slots.getJackpot() + " Das Coins";
            info += "\nCurrently spent: " + p.getSpent() + " Das Coins";
            info += "\nLifetime winnings: " + p.getLifetimeWinnings() + " Das Coins";
            userInformationTextArea.setText(info);
            playSlotsButton.setEnabled(true);
        }

        if (source.getText().equals("Play Slots") && (p == null)) {
            String info = "Please enter a user name first before playing";
            userInformationTextArea.setText(info);
        }

        if (source.getText().equals("Start Game")) {
            if (userNameInfo.getText().equals("")) {
                String info = "Please enter a user name first before playing";
                userInformationTextArea.setText(info);
            }
            else {
                String userName = userNameInfo.getText();
                if (pd.playerExists(userName)) {
                    p = pd.getPlayer(userName);
                    String info = "Welcome back, " + userName + "!";
                    info += "\nCurrent jackpot: " + slots.getJackpot() + " Das Coins";
                    info += "\nCurrently spent: " + p.getSpent() + " Das Coins";
                    info += "\nLifetime winnings: " + p.getLifetimeWinnings() + " Das Coins";
                    userInformationTextArea.setText(info);
                }
                else {
                    p = new Player(userName, 0);
                    pd.addNewPlayer(p);
                    String info = "Welcome to DAS Slots, " + userName + "!";
                    userInformationTextArea.setText(info);
                }
            }
        }

        if (source.getText().equals("Quit")) {
            p.quit(slots.getJackpot());
            pd.saveData();
            this.dispose();
        }

    }

    public void updateIcons() {
        slots.pullLever();
        p.play();
        imageOne.setIcon(getIcon(slots.getCurrentSymbol(0)));
        imageOne.setText("");
        imageTwo.setIcon(getIcon(slots.getCurrentSymbol(1)));
        imageTwo.setText("");
        imageThree.setIcon(getIcon(slots.getCurrentSymbol(2)));
        imageThree.setText("");
    }
}
