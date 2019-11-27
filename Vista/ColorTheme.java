package Vista;

import java.awt.Color;


public class ColorTheme
{
    private int nColor;
    private final Color[] colors = {Color.GREEN.darker(), Color.MAGENTA.brighter(),Color.ORANGE, Color.CYAN.darker()};
    
    public ColorTheme()
    {
        nColor = 0;
    }
    
    public Color getColorTheme()
    {
        if(nColor >= colors.length)
            nColor = 0;
        
        return colors[nColor++];
    }
}