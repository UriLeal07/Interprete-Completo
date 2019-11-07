package Controlador;

import Vista.MainFrame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Launcher
{
    public static void main(String []args)
    {
        try
        {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            int idx;
            
            for(idx = 0; idx < installedLookAndFeels.length; idx++)
                if("Nimbus".equals(installedLookAndFeels[idx].getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al cargar Look and Feel", "Error de Tema", JOptionPane.ERROR_MESSAGE);
        }
        
        MainFrame app = new MainFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        app.setVisible(true);
    }
}