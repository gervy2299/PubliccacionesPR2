
package proyectprogra2;

import Login.LOGIN;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.*;

public class ProyectProgra2 {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new LOGIN().setVisible(true);
    }
    
}
