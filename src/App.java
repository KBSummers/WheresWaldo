/* 
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
*/

/* 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JButton startButton;
    private JButton scoreButton;

    public GameMenu() {
        setTitle("Game Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code here to start the game
                JOptionPane.showMessageDialog(null, "Starting the game!");
            }
        });

        scoreButton = new JButton("Score");
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code here to show the score
                JOptionPane.showMessageDialog(null, "Score: 100");
            }
        });

        panel.add(startButton);
        panel.add(scoreButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameMenu();
            }
        });
    }
}
*/

//Where the GUI is created:
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
/* MenuLookDemo.java requires images/middle.gif. */
 
/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class App {
    JTextArea output;
    JScrollPane scrollPane;
 
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu; //, submenu;
        JMenuItem menuItem;
        //JRadioButtonMenuItem rbMenuItem;
        //JCheckBoxMenuItem cbMenuItem;
 
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("Start");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
 
        //a group of JMenuItems
        menuItem = new JMenuItem("Play Game...",
                                 KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);
 
        return menuBar;

    }

    // GET 10 RANDOM NUMBERS FOR IMAGES
    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }
    // 10 random numbers created above

    public Container playGame(JPanel panel) throws IOException, InterruptedException{
        panel.removeAll();
        panel.updateUI();
        int [] a = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        shuffleArray(a);
        for (int i : a) {
            int delay = 3000;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    panel.removeAll();
                    panel.updateUI();
                    String path = String.format("./images/image%d.jpeg", i);
                    System.out.println(path);
                    //BufferedImage gameImage = ImageIO.read(new File(path));
                    JLabel label = new JLabel();
                    label.setIcon(new ImageIcon(path));
                    Dimension size = label.getPreferredSize(); //Gets the size of the image
                    label.setBounds(50, 30, size.width, size.height);
                    panel.add(label);
                }
            };
            new Timer(delay, taskPerformer).start();

            // int delay = 4000;
            // Timer timer = new Timer(3000, new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent e) {
                    // panel.removeAll();
                    // panel.updateUI();
                    // String path = String.format("./images/image%d.jpeg", i);
                    // System.out.println(path);
                    // //BufferedImage gameImage = ImageIO.read(new File(path));
                    // JLabel label = new JLabel();
                    // label.setIcon(new ImageIcon(path));
                    // Dimension size = label.getPreferredSize(); //Gets the size of the image
                    // label.setBounds(50, 30, size.width, size.height);
                    // panel.add(label);
            //     }
            // });
            // timer.start();
                // panel.removeAll();
                // panel.updateUI();
                // String path = String.format("./images/image%d.jpeg", i);
                // System.out.println(path);
                // //BufferedImage gameImage = ImageIO.read(new File(path));
                // JLabel label = new JLabel();
                // label.setIcon(new ImageIcon(path));
                // Dimension size = label.getPreferredSize(); //Gets the size of the image
                // label.setBounds(50, 30, size.width, size.height);
                // panel.add(label);
            
            // ImageIcon gameImage = new ImageIcon(this.getClass().getResource(path));
            // JLabel gameImagee = new JLabel(gameImage);

            // panel.add(gameImagee);
            //Thread.sleep(30000);
            // new Timer(delay, ).start();
            System.out.println(i);
        }
        
        return panel;
    }
    public Container instructionPane(JPanel panel){
        panel.removeAll();
        panel.updateUI();
        String tinstructions = """
            <html>
            Once the exercise has started, you will be shown 10 images at random. <br><br>

            Each image shows something camouflaged in nature. You will have 30 seconds for each image to click the 'correct' area of the image. <br><br>
            
            If you do not click the correct area of the image within 30 seconds, you will be shown a "scary" image as punishment.<br><br>
            
            Click the button below to begin.
            </html>
                """;
        JLabel instructions = new JLabel(tinstructions);
        instructions.setFont(new Font("Verdana",1,20));
        panel.add(instructions, BorderLayout.CENTER);
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(40, 40));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                try {
                    playGame(panel);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        panel.add(start, BorderLayout.SOUTH);
        panel.updateUI();
        return panel;
    }
 
    public Container createContentPane() {
        JButton inst;
        //Create the content-pane-to-be.
        //JPanel contentPane = new JPanel(new BorderLayout());
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        // output = new JTextArea(5, 30);
        // output.setEditable(false);
        // scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        inst = new JButton("Instructions");
        inst.setPreferredSize(new Dimension(40, 40));
        //contentPane.add(scrollPane, BorderLayout.CENTER);
        //contentPane.add(start);
        JLabel welcome = new JLabel("Welcome, click below to see instructions.");
        welcome.setFont(new Font("Verdana",1,20));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(welcome, BorderLayout.CENTER);
        contentPane.add(inst, BorderLayout.SOUTH);
        inst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionPane(contentPane);
                //your actions
                // contentPane.removeAll();
                // contentPane.updateUI();
                // String tinstructions = """
                //     <html>
                //     Once the exercise has started, you will be shown 10 images at random. <br><br>

                //     Each image shows something camouflaged in nature. You will have 30 seconds for each image to click the 'correct' area of the image. <br><br>
                    
                //     If you do not click the correct area of the image within 30 seconds, you will be shown a "scary" image as punishment.<br><br>
                    
                //     Click the button below to begin.
                //     </html>
                //         """;
                // JLabel instructions = new JLabel(tinstructions);
                // instructions.setFont(new Font("Verdana",1,20));
                // contentPane.add(instructions, BorderLayout.CENTER);
                // contentPane.updateUI();

            }
        });
        
 
        return contentPane;
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = App.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("WheresWaldo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        App demo = new App();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
 
        //Display the window.
        frame.setSize(1000, 850);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
