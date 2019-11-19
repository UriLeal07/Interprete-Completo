package Vista;

import java.awt.*;
import java.awt.geom.*;

public class Elemento
{
    private String nombre;
    private int valor;
    private final Font font;
    private Point2D origen;
    private Color color;
    private BasicStroke stroke;
    
    public Elemento(String nombre, int valor, int x, int y)
    {
        this.nombre = nombre;
        this.valor = valor;
        origen = new Point2D.Float(x, y);
        
        color = Color.YELLOW;
        stroke = new BasicStroke(3);
        font = new Font("Consolas", Font.BOLD, 11);
    }
    
    public void dibujar(Graphics2D g2)
    {
        g2.setPaint(color);
        // Dibuja cuadrado (casilla)
        
        g2.setPaint(Color.BLACK);
        g2.setFont(font);
        g2.drawString(nombre, ((float)origen.getX()), ((float)origen.getY()));
    }
    
    public String getNombre() { return nombre; }
    public Point2D getOrigen() { return origen; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
}