package Modelo;

import java.util.LinkedList;

public class ParasControl {
    public int indice;
    public LinkedList<Tokens> instrucciones;

    public ParasControl(){
        indice = 0;
        instrucciones = new LinkedList<>();
    }

    public void AddInstruccion(Tokens token){
       instrucciones.add(token);
    }

    public void printInstrucciones(){
        String res= "";
        for(Tokens inst : instrucciones){
            res=res.concat(inst.secuencia+"|");
        }
    }
}
