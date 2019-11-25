package Vista;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Lienzo extends JPanel
{
    private final LinkedList <Historial> hist;
    private int step;
    protected Color color;
    
    public Lienzo(LinkedList <Historial> hist)
    {
	//setLayout(null); // Quitamos gestor de distribucion
	this.hist = hist;
        step = -1;
        color = Color.BLACK;
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
                h.drawOne(g2, step, color);
        }
    }
    
    public void paintStep(int step, Color color)
    {
	this.step = step;
        this.color = color;
    }
    
    public void clear()
    {
        step = -1;
        color = Color.BLACK;
	repaint();
    }
}