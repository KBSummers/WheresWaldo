import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
/* MenuLookDemo.java requires images/middle.gif. */
 
/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
class ImageStats{
    int imageID = -1;
    int numClicks = 0;
    boolean levelPaseed = false;
    double timeSpent = 0.0;

}

public class App {
    private boolean stopUpdating;
    JTextArea output;
    // JScrollPane scrollPane;
    static ImageStats[] gameStats = new ImageStats[11];
    // for (int i = 0; i < gameStats.length; i++){
    //     gameStats[i] = new ImageStats();
    // }
    static int level = 1;
 
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

    public static String extractNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group());
        }

        return result.toString();
    }
    // public void jumpscare(){
    //     JLabel label1 = new JLabel();
    //     String scarypath = String.format("../images/jumpscare.jpeg");
    //     label1.setIcon(new ImageIcon(scarypath));
    //     Dimension size = label1.getPreferredSize(); //Gets the size of the image
    //     label1.setBounds(50, 30, size.width, size.height);
    //     // new Thread(new Runnable() {
    //     // // The wrapper thread is unnecessary, unless it blocks on the
    //     // // Clip finishing; see comments.
    //     //     public void run() {
    //     //     try {
    //     //         Clip clip = AudioSystem.getClip();
    //     //         AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("./audio/crash.wav"));
    //     //         clip.open(inputStream);
    //     //         clip.start(); 
    //     //     } catch (Exception e) {
    //     //         System.err.println(e.getMessage());
    //     //     }
    //     //     }
    //     // }).start();
    //     new Thread() {
    //         int counter = 0;
    //         public void run() {
    //             try {
    //                 File musicpath = new File("../audio/crash.wav");
    //                 if (musicpath.exists()){
    //                     AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
    //                     Clip clip = AudioSystem.getClip();
    //                     clip.open(audioInput);
    //                     clip.start();
    //                 }
    //                 else{
    //                     System.out.println("Cant find file");
    //                 }

    //                 // Clip clip = AudioSystem.getClip();
    //                 // AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("../audio/crash.wav"));
    //                 // clip.open(inputStream);
    //                 // clip.start(); 
    //             } catch (Exception e) {
    //                 System.err.println(e.getMessage());
    //             }
    //             while(counter >= 0) {
    //                 counter--;
    //                 JFrame scaryframe = new JFrame();
    //                 JPanel scarypanel = new JPanel();
    //                 scarypanel.setBackground(Color.BLACK);
    //                 scaryframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //                 label1.setIcon(new ImageIcon(scarypath));
    //                 Dimension size = label1.getPreferredSize(); //Gets the size of the image
    //                 label1.setBounds(50, 30, size.width, size.height);
    //                 scarypanel.add(label1);
    //                 // scarypanel.validate();   
    //                 scaryframe.add(scarypanel);
    //                 scaryframe.setSize(750, 550);
    //                 // scaryframe.setBackground(Color.DARK_GRAY);
    //                 scaryframe.setVisible(true);     
    //                 try{
    //                     Thread.sleep(500);
    //                 } catch(Exception e) {}
    //                 scaryframe.setVisible(false);
    //                 scaryframe.dispose();
    //             }
    //         }
    //     }.start();
    // }

    
    public boolean inBounds(int x, int y, int imageNumber){
        boolean bounded = false;
        if(App.level < 11){
            // App.gameStats[this.level-1] = new ImageStats();
            App.gameStats[App.level-1].numClicks++;
        }
        switch(imageNumber){
            case 1:
            // x 224 296 y 260 532
                if((224 < x && x < 296)&&(260 < y && y < 532)){
                    bounded = true;
                }
                break;
            case 2:
            //x 253 439 y 341 388
                if((250 < x && x < 445)&&(310 < y && y < 388)){
                    bounded = true;
                }
                break;
            case 3:
                // neck down
                if((650 < x && x < 685)&&(208 < y && y < 589)){
                    bounded = true;
                }
                // body
                if((638 < x && x < 707)&&(370 < y && y < 592)){
                    bounded = true;
                }
                break;
            case 4:
            // x 311 360 y 380 417
                if((309 < x && x < 365)&&(376 < y && y < 421)){
                    bounded = true;
                }
                break;
            case 5:
                if((582 < x && x < 625)&&(320 < y && y < 433)){
                    bounded = true;
                }
                break;
            case 6:
                if((602 < x && x < 668) && (215 < y && y < 282)){
                    bounded = true;
                }
                // if((623 < x && x < 667) && (218 < y && y < 240)){
                //     bounded = true;
                
                // }
                break;
            case 7:
                if((270 < x && x < 341)&&(460 < y && y < 515)){
                    bounded = true;
                }
                if((291 < x && x < 333)&&(420 < y && y < 470)){
                    bounded = true;
                }
                break;
            case 8:
            
                if((788 < x && x <912)&&(302 < y && y < 389)){
                    bounded = true;
                }
                if((845 < x && x <976)&&(38 < y && y < 275)){
                    bounded = true;
                }
                if((920 < x && x < 1150)&&(255 < y && y < 750)){
                    bounded = true;
                }
                break;
            case 9:
                //body
                if((375 <x && x < 545)&&(471 < y && y < 582)){
                    bounded = true;
                }
                //head
                if((362 < x && x < 410)&&(420 < y && y < 500)){
                    bounded = true;
                }
                break;
            case 10:
            // x 423 590 y 316 561
                if((423 < x && x < 590)&&(316 < y && y < 561)){
                    bounded = true;
                }
                break;
            default:
                break;
            

        }
        // if(!bounded && this.level < 11) {
        //     App.gameStats[this.level-1].levelPaseed = true;  
        // } 
        if (level < 11 && bounded) {
            App.gameStats[App.level-1].levelPaseed = true;
            App.level++;
        }
        return bounded;
    }

    public void jumpscare(JPanel panel, JLabel label){
        // panel.removeAll();
        // panel.updateUI();

        JLabel label1 = new JLabel();
        String scarypath = String.format("../images/jumpscare1.jpg");
        label1.setIcon(new ImageIcon(scarypath));
        Dimension size = label1.getPreferredSize(); //Gets the size of the image
        label1.setBounds(50, 30, size.width, size.height);
        // panel.add(label1);
        // panel.validate();
        



        // Timer t = new Timer(2000, new ActionListener() {
        //     // ImageStats[] z = App.gameStats;
        //     @Override
        //     public void actionPerformed(ActionEvent e) {


        //         panel.add(label1);
        //         panel.validate();
        //     }
        // });
        // t.setRepeats(false);
        // t.setInitialDelay(0);
        // t.start();

        new Thread() {
            int counter = 0;
            public void run() {
                try {
                    File musicpath = new File("../audio/crash.wav");
                    if (musicpath.exists()){
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        clip.start();
                    }
                    else{
                        System.out.println("Cant find file");
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                while(counter >= 0) {
                    counter--;
                    JFrame scaryframe = new JFrame();
                    JPanel scarypanel = new JPanel();
                    scarypanel.setBackground(Color.BLACK);
                    scaryframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    label1.setIcon(new ImageIcon(scarypath));
                    Dimension size = label1.getPreferredSize(); //Gets the size of the image
                    label1.setBounds(50, 30, size.width, size.height);
                    // panel.add(label1);
                    scarypanel.add(label1);
                    scaryframe.add(scarypanel);
                    scaryframe.setSize(1300, 850);
                    scaryframe.setVisible(true);     
                    try{
                        Thread.sleep(1800);
                    } catch(Exception e) {}
                    scaryframe.setVisible(false);
                    scaryframe.dispose();
                    // panel.removeAll();

                }
            }
        }.start();
    }
    public void updateLabel(JPanel panel, int i, JLabel label, boolean delayChanged) {
        panel.removeAll();
        panel.validate();
        // App.gameStats[this.level-1] = new ImageStats();
        if(App.level < 11){
            JLabel lblNewLabel = new JLabel("30");
            panel.add(lblNewLabel);
            new Thread() {
                double counter = 30.0;
                // if (delayChanged) {counter = 32.0;}
                public void run() {
                    if (!delayChanged){
                        while(counter >= 0) {
                            lblNewLabel.setFont(new Font("Verdana", 1, 30));
                            lblNewLabel.setText("Time Remaining: " + String.format("%.1f",counter = counter -0.1));
                            try{
                                Thread.sleep(100);
                            } catch(Exception e) {}
                        }
                    }
                    else{
                        counter = 32.0;
                        while(counter >= 0) {
                            if(counter > 30.0) {
                                lblNewLabel.setFont(new Font("Verdana", 1, 30));
                                lblNewLabel.setText("Time Remaining: 30.0");
                                counter = counter -0.1;
                            }
                            else{
                                lblNewLabel.setFont(new Font("Verdana", 1, 30));
                                lblNewLabel.setText("Time Remaining: " + String.format("%.1f",counter = counter -0.1));
                            }
                            try{
                                Thread.sleep(100);
                            } catch(Exception e) {}
                        }
                    }
                }
            }.start();
            panel.add(lblNewLabel, BorderLayout.SOUTH);
        }
        // Update label text here
        // String path = String.format("./images/image%d.jpeg", i);
        String relpath = new File("").getAbsolutePath();
        System.out.println(relpath);
        String path = String.format("../images/image%d.jpeg", i);
        //System.out.println(path);
        //JLabel label = new JLabel();

        label.setIcon(new ImageIcon(path));
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height);
        panel.add(label);
        panel.validate();
    }
    public Container playGame(JPanel panel) throws IOException, InterruptedException{
        // panel.removeAll();
        // panel.updateUI();
        for (int i = 0; i < gameStats.length; i++){
            gameStats[i] = new ImageStats();
        }
        final int [] g = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JLabel label = new JLabel();
        shuffleArray(g);
        int [] a = Arrays.copyOf(g, g.length + 1);
        a[a.length - 1] = 11;
        for(int i = 0; i < gameStats.length; i++){
            gameStats[i].imageID = a[i];
        }
        panel.removeAll();
        panel.updateUI();
        
        int i = 0;
        //ImageStats[] stats = App.gameStats;
        final Timer t = new Timer(32000, new ActionListener() { //maybe final
            int ii = i;
            static boolean delayChanged = false;
            // ImageStats[] z = App.gameStats;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ii == 10){
                    JTable table = new JTable(11, 3);
                    JFrame statFrame = new JFrame("Game Statistics");
                    Object columns[] = {"ImageID", "Number of Clicks", "Level Passed"};
                    
                    table.setName("Game Statistics");
                    DefaultTableModel model = new DefaultTableModel(columns, 0);
                    for(int i = 0; i < 10; i++){
                        Object rows[]  = {App.gameStats[i].imageID, App.gameStats[i].numClicks, App.gameStats[i].levelPaseed};
                        model.addRow(rows);
                    }
                    table.setModel(model);
                    table.getTableHeader().setReorderingAllowed(false);
                    table.setEnabled(false);
                    statFrame.add(new JScrollPane(table), BorderLayout.CENTER);
                    statFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    statFrame.setSize(1300, 850);

                    statFrame.setVisible(true);
                }
                if(!stopUpdating && ii > 0 && ii < 11) {
                    App.level++;
                    ((Timer)e.getSource()).setDelay(34000);
                    delayChanged = true;
                    // ((Timer)e.getSource()).stop();
                    jumpscare(panel, label);
                    // ((Timer)e.getSource()).start();
                }
                stopUpdating = false;
                if (!stopUpdating && ii < 11) {
                    // jumpscare();
                    if(((Timer)e.getSource()).getDelay() == 32000){
                        delayChanged = false;
                    }
                    updateLabel(panel, a[ii], label, delayChanged);
                }
                ii++;
            }
            
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getPoint());
                int x = e.getX();
                int y = e.getY();
                //int level = 1;
                //Icon i = label.getIcon();
                int imageNo = Integer.valueOf(extractNumbers(label.getIcon().toString()));
                if(inBounds(x, y, imageNo)){
                    //System.out.println(extractNumbers(label.getIcon().toString()));
                    stopUpdating = true;
                    t.setDelay(32000);
                // t.setDelay(0);
                t.restart();
                }

            }
        });
        t.setInitialDelay(0);
        t.start();
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
        JLabel instructions = new JLabel(tinstructions, SwingConstants.CENTER);
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
        contentPane.setBackground(Color.LIGHT_GRAY);
 
        //Create a scrolled text area.
        // output = new JTextArea(5, 30);
        // output.setEditable(false);
        // scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        inst = new JButton("Instructions");
        inst.setPreferredSize(new Dimension(40, 40));
        //contentPane.add(scrollPane, BorderLayout.CENTER);
        //contentPane.add(start);
        JLabel welcome = new JLabel("Welcome, click below to see instructions.", SwingConstants.CENTER);
        welcome.setFont(new Font("Verdana",1,40));
        // welcome.setBackground(Color.CYAN);
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(welcome, BorderLayout.CENTER);
        contentPane.add(inst, BorderLayout.SOUTH);
        inst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionPane(contentPane);
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
        //frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
 
        //Display the window.
        frame.setSize(1300, 850);
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
