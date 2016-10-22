/**
 * Created by Yingying on 9/6/2016.
 */
public class Simulator {

    private int xpos;
    private int currentImage_state=0;

    SimulationPanel simulationPanel = new SimulationPanel();

    public Simulator(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    transient boolean paused = false;

    public boolean isPaused() {
        return paused;
    }

    public void pauseToggle() {
        paused = !paused;
    }

    private boolean checkInterruption() {
        while (isPaused()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public void simulateProcess() {

        simulationPanel.setSimulator(this);

        // run from one side of the screen to the middle
        nekorun(xpos, simulationPanel.getWidth()/2);


        // stop and pause
        currentImage_state = 2;
        checkInterruption();
        simulationPanel.repaint();
        pause(500);

        // yawn
        currentImage_state = 3;
        checkInterruption();
        simulationPanel.repaint();
        pause(200);

        // scratch four times
        nekoscratch(5);

        // sleep for 5 seconds
        nekosleep(5);

        // wake up and run off
        currentImage_state = 8;
        checkInterruption();
        simulationPanel.repaint();
        pause(200);
        nekorun(xpos, simulationPanel.getWidth());

    }


    public int getNekoState() { return xpos; }
    public int getCurrentImage_state() {return currentImage_state;}

    void nekorun(int start, int end){
        for (int i = start; i < end; i+=10) {

            xpos = i;
            // swap images
            if (currentImage_state == 0)
                currentImage_state = 1;
            else if (currentImage_state == 1)
                currentImage_state = 0;
            else currentImage_state = 0;
            checkInterruption();
            simulationPanel.repaint();
            pause(200);
        }
    }

    void nekoscratch(int numtimes) {
        for (int i = numtimes; i > 0; i--) {
            currentImage_state = 4;
            checkInterruption();
            simulationPanel.repaint();
            pause(100);
            currentImage_state = 5;
            checkInterruption();
            simulationPanel.repaint();
            pause(100);
        }
    }

    void nekosleep(int numtimes) {
        for (int i = numtimes; i > 0; i--) {
            currentImage_state = 6;
            checkInterruption();
            simulationPanel.repaint();
            pause(100);
            currentImage_state = 7;
            checkInterruption();
            simulationPanel.repaint();
            pause(100);
        }
    }

    void pause(int time) {
        try { Thread.sleep(time); }
        catch (InterruptedException e) { }
    }


}
