package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Historial
{
    private String name; // A quien pertenece el historial
    private ArrayList<Arreglo> arrs;
    private Rectangle bounds;
    private final Font font;
    private final int posInicial;
    private final Color color;
    
    public Historial(String name, Rectangle bounds, int posInicial, Color color)
    {
        this.name = name;
        this.bounds = bounds;
        this.posInicial = posInicial;
        this.color = color;
        arrs = new ArrayList<>();
        font = new Font("Consolas", Font.BOLD, 14);
    }
    
    public void drawOne(Graphics2D g2, int nArray, Color color)
    {
        g2.setFont(font);
        g2.setPaint(Color.BLACK);
        g2.drawString(name, 10, posInicial);
        
        if(nArray < arrs.size())
            arrs.get(nArray).draw(g2, color);
        
        else
            arrs.get(arrs.size()-1).draw(g2, color);
        
    }
    
    public Color getColor() { return color; }
    public int getPosInicial() { return posInicial; }
    
    public String getName() { return name; }
    
    public ArrayList<Arreglo> getArrs() { return arrs; }
}