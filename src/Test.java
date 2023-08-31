import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Test {
    // public void printtt(){
    //     System.out.println("in the class");
    // }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(5, 5, 10, 10);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);

    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = App.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
   public static void main(String[] args) {
        //Test t = new Test();
        // JLabel label = new JLabel();
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Test");
        // t.printtt();
        // System.out.println("Here");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Test t = new Test();
        

        panel.setOpaque(true);
        // contentPane.add(label);
        // contentPane.validate();


        // String path = String.format("./images/image1.jpeg");
        // System.out.println(path);
        // label.setIcon(new ImageIcon(path));
        // Dimension size = label.getPreferredSize(); //Gets the size of the image
        // label.setBounds(50, 30, size.width, size.height);
        // contentPane.add(label);

        String path = String.format("./images/image1.jpeg");
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(path));
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height);
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                System.out.println("Click at" + e.getPoint());
                //head
                if ((240 < e.getX() && e.getX() < 430) && (233 < e.getY() && e.getY() < 281) ){

                }
                else {
                    JLabel label1 = new JLabel();
                    String scarypath = String.format("./images/jumpscare.jpeg");
                    label1.setIcon(new ImageIcon(scarypath));
                    Dimension size = label1.getPreferredSize(); //Gets the size of the image
                    label1.setBounds(50, 30, size.width, size.height);
                    new Thread() {
                        int counter = 0;
                        public void run() {
                            while(counter >= 0) {
                                counter--;
                                JFrame scaryframe = new JFrame();
                                JPanel scarypanel = new JPanel();
                                scarypanel.setBackground(Color.BLACK);
                                scaryframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                label1.setIcon(new ImageIcon(scarypath));
                                Dimension size = label1.getPreferredSize(); //Gets the size of the image
                                label1.setBounds(50, 30, size.width, size.height);
                                scarypanel.add(label1);
                                // scarypanel.validate();   
                                scaryframe.add(scarypanel);
                                scaryframe.setSize(750, 550);
                                // scaryframe.setBackground(Color.DARK_GRAY);
                                scaryframe.setVisible(true);     
                                try{
                                    Thread.sleep(250);
                                } catch(Exception e) {}
                                scaryframe.setVisible(false);
                                scaryframe.dispose();
                            }
                        }
                    }.start();
                    }
                }
        });
        panel.add(label);
        //Image_1 region = new Image_1();
        
        // region.draw(null);
        //panel.add(region);
        frame.add(panel);
        int x1 = 1, x2 = 2, w = 3, h = 4, x = 5, y = 6, y1 = 1, y2 = 2, start = 3;
        Shape line = new Line2D.Float(x1, y1, x2, y2);
        Shape arc = new Arc2D.Float(x, y, w, h, start, 1, 1);
        Shape oval = new Ellipse2D.Float(x, y, w, h);
        Shape rectangle = new Rectangle2D.Float(x, y, w, h);
        Shape roundRectangle = new RoundRectangle2D.Float(x, y, w, h, 1, 2);
        // frame.add(oval);

        frame.setSize(1300, 850);
        frame.setVisible(true);
    } 
}

class Image_1 extends JPanel{
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.red);
        g.fillRect(10, 10, 100, 100);
    }
}


