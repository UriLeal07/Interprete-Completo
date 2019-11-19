package Vista;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Arreglo
{
    private ArrayList <Elemento> Elementos;
    private String name;
    private int width;
    private int height;
    
    public Arreglo(String name)
    {
        this.name = name;
        width = 0;
        height = 0;
        Elementos = new ArrayList <>();
    }
    
    public int add(int x, int y, int valor, String name)
    {
        Elemento c = new Elemento(name, valor, x, y);
        
        Elementos.add(c);        
        
        return 0;
    }
    
    public int delete(String name)
    {
        for(Elemento c : Elementos)
            if(c.getNombre().equals(name))
            {
                Elementos.remove(c);
                return 0;
            }
        
        return -4;
    }
    
    public String printAll()
    {
        String cad = " ";
        
        for(Elemento v: Elementos)
            cad += v.getNombre()+" , ";
        
        return cad;
    }

    public void draw(Graphics2D g2)
    {
        // Pintamos los caras
        for(Elemento c : Elementos)
	    c.dibujar(g2);
    }
    
    public void clear(Lienzo l)
    {
        Elementos.clear();
        l.repaint();
    }
    
    public boolean testColision(Rectangle2D a, Rectangle2D b)
    {
        if((b.getMaxY() < a.getMinY()) || (b.getMinY() > a.getMaxY()))
            return false;
        
        if((b.getMaxX() < a.getMinX()) || (b.getMinX() > a.getMaxX()))
            return false;
        
        return true;
    }
    
    private boolean testColision(Shape shapeA, Shape shapeB)
    {
        Area areaA = new Area(shapeA);
        Area areaB = new Area(shapeB);
        
        return (areaA.intersects(areaB.getBounds2D()));
    }
    
    public void reset()
    {
        Elementos.clear();
    }
    
    public ArrayList <Elemento> getElementos() { return Elementos; }
    
    public void setBounds(Rectangle r){ height = r.height; width = r.width; }
    public void setHeight(int height){ this.height = height; }
    public void setWidth(int width){ this.width = width; }
}