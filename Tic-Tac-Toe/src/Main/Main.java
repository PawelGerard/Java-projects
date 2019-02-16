
package Main;

import Main.Frame;
import java.awt.EventQueue;

public class Main {
    public static Frame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                Main.frame = new Frame();
            }
        });
    }

}

