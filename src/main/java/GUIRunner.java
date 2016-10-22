import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yingying on 9/4/2016.
 */
public class GUIRunner {

    public void start(){

        // create main window frame
        MainWindowFrame mainWindowFrame = new MainWindowFrame();

        // add panel
        final JPanel topPanel=new JPanel(new FlowLayout());
        mainWindowFrame.add(topPanel,BorderLayout.PAGE_START);

        // simulation panel
        final SimulationPanel simulationPanel=new SimulationPanel();
        mainWindowFrame.add(simulationPanel,BorderLayout.CENTER);

        // run button
        JButton runButton=new JButton("Run");
        runButton.addActionListener(new StartActionListener(simulationPanel));
        topPanel.add(runButton);

        // switch between pause and resume button
        JButton pauseButton=new JButton("Pause/Resume");
        pauseButton.addActionListener(new PauseActionListener(simulationPanel));
        topPanel.add(pauseButton);

        mainWindowFrame.setVisible(true);

    }


    public static void main(String[] args){
        (new GUIRunner()).start();
    }

}

class StartActionListener implements ActionListener {
    private final SimulationPanel panel;

    public StartActionListener(SimulationPanel panel) {
        this.panel = panel;
    }

    public void actionPerformed(final ActionEvent e) {

        final Thread simulationThread = new Thread(new Runnable() {
            public void run() {
                Simulator newSimulator = new Simulator(panel);
                newSimulator.simulateProcess();
                panel.repaint();
            }
        });

        simulationThread.start();
    }
}


class PauseActionListener implements ActionListener {
    private final SimulationPanel panel;

    public PauseActionListener(SimulationPanel panel) {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        panel.requestPause();
        panel.repaint();
    }
}


class MainWindowFrame extends JFrame{
    public MainWindowFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setPreferredSize(new Dimension(500, 200));
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }
}
