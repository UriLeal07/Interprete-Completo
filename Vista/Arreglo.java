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
    public final static int SPACING = 35;          // Espaciado entre elementos del arreglo
    public final static int DIGIT_FACTOR = 5;
    
    public Arreglo(String tipe, String[] values, String line, int index, Color colorIndex, int yInicial)
    {
        this.tipe = tipe;
        this.values = values;
        this.line = line;
        this.yInicial = yInicial;
        
        elementos = new ArrayList <>();
        font = new Font("Consolas", Font.BOLD, 14);
        
        fillArray(index, colorIndex);
    }
    
    private void fillArray(int index, Color colorIndex)
    {
        int i, x = 0, width;
        
        for(i = 0; i < values.length; i++)
        {
            width = calculateWidth(values[i].length());
            
            if(i == index)
                add(values[i], dinamicDim(values[i].length()), x, yInicial, width, SPACING, colorIndex);
            else
                add(values[i], dinamicDim(values[i].length()), x, yInicial, width, SPACING, Color.BLACK);
            
            x += width;
        }
    }
    
    private int calculateWidth(int nDigits)
    {
        return SPACING+((nDigits-1)*DIGIT_FACTOR);
    }
    
    private int dinamicDim(int nDigits)
    {
        return (nDigits-1)*DIGIT_FACTOR;
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
    
    public void add(String val, int length,  int x, int y, int width, int height, Color color)
    {
        Elemento c = new Elemento(val, length, x, y, width, height, color);
        
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
    
    public String getLine() { return line; }
    
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