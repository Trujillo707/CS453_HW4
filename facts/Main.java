package facts;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FactsProject factsProject = new FactsProject();
                factsProject.setMinimumSize(new Dimension(1300,400)); // reasonable
                factsProject.setVisible(true);
            }
        });
    }
}
