package mco364;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class App extends JFrame {
// see https://docs.oracle.com/javase/tutorial/collections/implementations/wrapper.html
    
    class WorkerThread implements Runnable {
        public void run() {
            heavyProcessing();

            SwingUtilities.invokeLater(
                    new Runnable() {
                @Override
                public void run() {
                    status.setText("Completed Processing REALLY!!");
                }
            }
            );
        }
    }
    JLabel status;

    public App() {
        setTitle("Multi threading funness");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout()); // defulat layout for JFRame is BorderLayout
        status = new JLabel("AAAA");

        JButton button = new JButton("Edon says press me");

        getContentPane().add(button);
        getContentPane().add(status);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                status.setText("Why did you press my button");
                Thread w = new Thread(new WorkerThread());
                w.start();
            }

        });

        setVisible(true);
    }

    private void heavyProcessing() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("REALLY COMPLETED NO ;;;;");
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new App();
    }
}
