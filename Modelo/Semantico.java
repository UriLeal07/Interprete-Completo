package Modelo;

import Controlador.Control;
import java.util.ArrayList;
import java.util.LinkedList;

public class Semantico
{
    public String nombrePrograma;
    public LinkedList<Variables> tabla;
    private Control c;

    public Semantico(Control c) {
        tabla = new LinkedList<>();
        this.c = c;
    }

    public boolean add(Variables aux){
        if(tabla.isEmpty()){
            tabla.add(aux);
            return true;
        }else{
            for(Variables lista: tabla){
                if(lista.nombre.equals(aux.nombre)){
                    return false;
                }
            }
            tabla.add(aux);
        }
        return true;
    }

    public Variables getByName(String name){
        for(Variables lista: tabla){
            if(lista.nombre.equals(name)){
                return lista;
            }
        }
        return null;
    }

    public String intentaagregar(Variables aux){
        if(tabla.isEmpty()){
            tabla.add(aux);
            return "";
        }else{
            Variables match = getByName(aux.nombre);
            if(match != null){
                if(match.isConstante){
                    return "No se puede modificar una constante";
                }
                if(match.isArreglo){
                    return "No se puede modificar un arreglo";
                }
                //aux.indexsel = tabla.indexOf(match);
                tabla.set(tabla.indexOf(match),aux);
            }else{
                tabla.add(aux);
                return "";
            }
        }
        return "";
    }

    public String saltaArregloAdd(Variables aux){
        if(tabla.isEmpty()){
            tabla.add(aux);
            return "";
        }else{
            Variables match = getByName(aux.nombre);
            if(match != null){
                if(match.isConstante){
                    return "No se puede modificar una constante";
                }
                tabla.set(tabla.indexOf(match),aux);
            }else{
                tabla.add(aux);
                return "";
            }
        }
        return "";
    }
    
    public ArrayList<String[]> getConstantes() {
    	ArrayList<String[]> aux = new ArrayList<>();
    	for(Variables lista: tabla) {
    		if(lista.isConstante){
    			String[] row= {lista.nombre, lista.tipo, lista.value};
    			aux.add(row);
    		}
    	}
    	return aux;
    }
    
    
    public ArrayList<String[]> getArreglos()
    {
    	ArrayList<String[]> aux = new ArrayList<>();
        
    	for(Variables lista: tabla)
        {
    		if(lista.isArreglo){
    			String[] row = {lista.nombre, lista.tipo, lista.printvalues(),
                                        String.valueOf(lista.pos), String.valueOf(lista.indexsel)};
    			aux.add(row);
    		}
    	}
        
    	return aux;
    }
    
    public ArrayList<String[]> getVariables() {
    	ArrayList<String[]> aux = new ArrayList<>();
    	for(Variables lista: tabla) {
    		if(lista.isArreglo==false && lista.isConstante==false){
    			String[] row= {lista.nombre, lista.tipo, lista.value};
    			aux.add(row);
    		}
    	}
    	return aux;
    }
    
    public void constantesInterfaz()
    {
        c.printConst(this.getConstantes());
    }
    
    public void arreglosInterfaz(String msg)
    {
        c.printArreglos(this.getArreglos());
    }
    
    public void variablesInterfaz(String msg)
    {
        c.printVar(this.getVariables());
    }
}
