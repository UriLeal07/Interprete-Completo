package Controlador;

import java.awt.Color;
import Vista.MainFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Control
{
    private final MainFrame mFrame;
    private boolean ban_archivo;
    
    public Control(MainFrame mFrame)
    {
        this.mFrame = mFrame;
        ban_archivo = false;
    }
    
    public void start(String path, String expresion)
    {

    }
    /*
    public void setError(String err) { mFrame.getTxtError().setText(err); }
    
    public void printOutput(String out) { mFrame.getTxtOutput().append(out+"\n"); }
    
    public void printOutError(String out)
    {
        mFrame.getTxtOutput().setForeground(Color.red);
        mFrame.getTxtOutput().append(out+"\n");
        
        mFrame.getTxtError().setText(out);
    }*/
}
