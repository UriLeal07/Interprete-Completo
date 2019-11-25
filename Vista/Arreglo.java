package Vista;

import java.awt.*;
import java.util.*;

public class Arreglo
{
    private final ArrayList <Elemento> elementos;
    private final Font font;
    private final String tipe;
    private final String line;
    private final String[] values;
    private final int yInicial;
    private final int SPACING = 35; // Espaciado entre elementos del arreglo
    private final int DIGIT_FACTOR = 5;
    
    public Arreglo(String tipe, String[] values, String line, int yInicial)
    {
        this.tipe = tipe;
        this.values = values;
        this.line = line;
        this.yInicial = yInicial;
        
        elementos = new ArrayList <>();
        font = new Font("Consolas", Font.BOLD, 14);
        
        fillArray();
    }
    
    private void fillArray()
    {
        int x = 0, width;
        
        for(String value : values)
        {
            width = SPACING+dinamicDim(value.length());
            add(value, dinamicDim(value.length()), x, yInicial, width, SPACING);
            x += width;
        }
    }
    
    private int totalWidth()
    {
        int width = 0;
        
        for(String value : values)
        {
            width += SPACING+dinamicDim(value.length());
        }
        
        return width;
    }
    
    private int dinamicDim(int nDigits)
    {
        return (nDigits-1)*DIGIT_FACTOR;
    }
    
    public void add(String val, int length,  int x, int y, int width, int height)
    {
        Elemento c = new Elemento(val, length, x, y, width, height);
        
        elementos.add(c);
    }
    
    public int delete(String val)
    {
        for(Elemento c : elementos)
            if(c.getVal().equals(val))
            {
                elementos.remove(c);
                return 0;
            }
        
        return -1;
    }
    
    public String printAll()
    {
        String cad = "";
        
        for(Elemento v: elementos)
            cad += v.getVal()+" ";
        
        return cad;
    }

    public void draw(Graphics2D g2, Color color)
    {
        g2.setFont(font);
        g2.setPaint(Color.BLACK);
        g2.drawString("Linea:", totalWidth()+10, yInicial);
        g2.setPaint(Color.RED);
        g2.drawString(line, totalWidth()+65, yInicial);
        
        for(Elemento c : elementos)
	    c.draw(g2, color);
    }
    
    public void clear(Lienzo l)
    {
        elementos.clear();
        l.repaint();
    }
    
    public void reset()
    {
        elementos.clear();
    }
    
    public ArrayList <Elemento> getElementos() { return elementos; }
}