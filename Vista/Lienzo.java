package Vista;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Lienzo extends JPanel
{
    private final LinkedList <Historial> hist;
    protected int step;
    
    public Lienzo(LinkedList <Historial> hist)
    {
	//setLayout(null); // Quitamos gestor de distribucion
	this.hist = hist;
        step = -1;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	setBackground(Color.white);
        
        if(step >= 0)
        {
            for(Historial h : hist)
                h.drawOne(g2, step);
        }
    }
    
    public void paintStep(int step)
    {
	this.step = step;
    }
    
    public void clear()
    {
        step = -1;
	repaint();
    }
}