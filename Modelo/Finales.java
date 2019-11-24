package Modelo;

import java.util.ArrayList;

public class Finales {
    public ArrayList<Integer> estados;
    public ArrayList<String> valores;

    public Finales(){
        estados = new ArrayList<>();
        valores = new ArrayList<>();
    }

    public void addEdo(int estado, String valor){
        estados.add(estado);
        valores.add(valor);
    }

    public boolean containsedo(int estado){
        return estados.contains(estado);
    }

    public boolean containsedo(String edo) throws ParserException{
        try{
            int value = Integer.valueOf(edo);
            return estados.contains(value);
        }catch (NumberFormatException e){
            throw new ParserException("Token no válido");
        }
    }

    public boolean isEmpty(){
        return estados.isEmpty();
    }

    public String getToken(String nestado) throws  ParserException {
        try {
            int value=Integer.valueOf(nestado);
            return valores.get(estados.indexOf(value));
        } catch (Exception e) {
            throw new ParserException("Se esperaba un numero entero");
        }
    }

    public String getEstado (String ntoken) throws ParserException {
        try {
            return String.valueOf(estados.get(valores.indexOf(ntoken)));
        }catch(Exception e){
            throw new ParserException("Se esperaba un token");
        }
    }

    public int getEstadoInt (String ntoken) throws ParserException{
        try{
            return estados.get(valores.indexOf(ntoken));
        }catch (Exception e){
            throw new ParserException("Se esperaba un token");
        }
    }
    
    @Override
    public String toString(){
        return ("[{"+estados+"},{"+valores+"}]");
    }
    
}
