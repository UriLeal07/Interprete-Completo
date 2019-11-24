package Vista;

import java.awt.*;

public class Elemento
{
    private String val;
    private final Font font;
    private final Color colorLine;
    private final Color colorFont;
    private final int rX;
    private final int rY;
    private final int rWidth;
    private final int rHeight;
    private final int centerX;
    private final int centerY;
    private final int X_MARGIN = 5;
    
    public Elemento(String val, int length, int rX, int rY, int width, int height)
    {
        this.val = val;
        this.rX = rX+X_MARGIN;
        this.rY = rY;
        
        rWidth = width;
        rHeight = height;
        centerX = this.rX+(width/2)-length;
        centerY = rY+(height/2);
        
        colorLine = Color.BLACK;
        colorFont = Color.BLACK;
        font = new Font("Consolas", Font.BOLD, 14);
    }
    
    public void draw(Graphics2D g2)
    {
        // Dibuja cuadrado (casilla/cuadrado)
        g2.setPaint(colorLine);
        g2.drawRect(rX, rY, rWidth, rHeight);
        
        // Dibuja valor (dentro de la casilla/cuadrado)
        g2.setPaint(colorFont);
        g2.setFont(font);
        g2.drawString(val, centerX, centerY);
    }
    
    public int getRy() { return rY; }
    
    public String getVal() { return val; }
    
    public void setVal(String val) { this.val = val; }
}