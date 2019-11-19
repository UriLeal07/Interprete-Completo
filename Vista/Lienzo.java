package Vista;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Lienzo extends JPanel
{
    private ArrayList<Arreglo> arrs;
    
    public Lienzo(ArrayList<Arreglo> g1)
    {
	//setLayout(null); // Quitamos gestor de distribucion
	arrs = g1;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	setBackground(Color.white);
        
        for(Arreglo a : arrs)
            a.draw(g2);
    }
    
    public void limpiar(ArrayList<Arreglo> arrs)
    {
	this.arrs = arrs;
	repaint();
    }
}