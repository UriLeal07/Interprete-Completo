package Controlador;

import Modelo.AFDVault;
import Modelo.ParserException;
import Modelo.Sintactico;
import Vista.MainFrame;
import java.io.IOException;
import java.util.ArrayList;

public class Control
{
    private final MainFrame mFrame;
    private AFDVault afd;
    
    public Control(MainFrame mFrame)
    {
        this.mFrame = mFrame;
        afd = null;
    }
    
    public void cargarAuto(String path) throws IOException, ParserException
    {
        afd = new AFDVault(this, path);
    }
    
    public void cargarReserv(String path) throws IOException, ParserException
    {
        if(afd != null)
        {
            afd.cargarReservadas(path);
        }
        else
            throw new ParserException("No se ha cargado automata");
    }
    
    public void start(String path, String codigo) throws ParserException
    {
        afd.resetTokens();
        
        String[] cod = codigo.split("\n");
        afd.setMaxLineas(cod.length);
        
        for(int i=0;i<cod.length;i++) 
            afd.validaSt(cod[i],(i+1));
        
        printConsola("\n-------------------------------------------------");
        Sintactico analsin = new Sintactico(afd);
        analsin.Iniciar();
        printConsola("\nAnalisis finalizado");
    }
    
    //public void setError(String err) { mFrame.getTxtError().setText(err); }
    
    public void printOutput(String out) { mFrame.getTxtOutput().append(out+"\n"); }
    
    public void printConsola(String out) { mFrame.getTxtConsola().append(out+"\n"); }
    
    public void printArreglos(ArrayList<String[]> valores) { mFrame.updateArr(valores); };
    
    public void printConst(ArrayList<String[]> valores) { mFrame.updateConst(valores); };
    
    public void printVar(ArrayList<String[]> valores) { mFrame.updateVar(valores); };
    
    public void setTitulo(String title) { System.out.println("Pon titulo"+title); }
    
    public String requestInput(String message) { return mFrame.inputDial(message); }
    
    public void setMsgError(String msg, String title, int tipo) { mFrame.msgDial(msg, title, tipo); }
    /*
    public void printOutError(String out)
    {
        mFrame.getTxtOutput().setForeground(Color.red);
        mFrame.getTxtOutput().append(out+"\n");
        
        mFrame.getTxtError().setText(out);
    }*/
}
