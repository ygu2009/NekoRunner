import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Yingying on 9/4/2016.
 */
public class SimulationPanel extends JPanel {

    int xpos;
    int ypos=50;
    Image currentImage;

    Image nekopics[] = new Image[9];
    String nekosrc[] = { "right1.gif", "right2.gif", "stop.gif",
            "yawn.gif", "scratch1.gif", "scratch2.gif",
            "sleep1.gif", "sleep2.gif", "awake.gif" };

    public Simulator simulator;

    public void setSimulator(Simulator simulator){
        this.simulator=simulator;

    }

    public void requestPause(){
        if(simulator!=null){
            simulator.pauseToggle();
        }

    }

    public SimulationPanel(){
        setBackground(Color.white);
        setLayout(new GridBagLayout());

        // initialization
        for (int i=0; i < nekopics.length; i++) {
            try {
                nekopics[i] = getScaledImage(nekosrc[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        if (simulator == null){
            return;
        }
        drawNeko(canvas);
    }


    private Image getScaledImage(String imageName) throws IOException {
        // read the pictures from resources folder
        BufferedImage img=ImageIO.read(getClass().getResource(imageName));
        return img.getScaledInstance((int)(img.getWidth() * 1.0), (int)(img.getHeight() * 1.0), BufferedImage.TYPE_INT_ARGB);
    }

    private void drawNeko(Graphics2D canvas){
        xpos=simulator.getNekoState();
        currentImage=nekopics[simulator.getCurrentImage_state()];
        canvas.drawImage(currentImage,xpos,ypos,this);
    }



}
