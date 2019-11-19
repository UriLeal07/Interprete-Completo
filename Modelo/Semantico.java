package Modelo;

import Controlador.Control;
import java.util.ArrayList;
import java.util.LinkedList;

public class Semantico {
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
/*
    public void printtabla(){
        for(Variables lista: tabla) {
            if(lista.isArreglo){
                System.out.println("Es Arreglo");
                System.out.println("Nombre: "+lista.nombre +" Tipo: "+lista.tipo);
                System.out.println("Values: "+lista.printvalues());
            }else{
                if(lista.isConstante){
                    System.out.println("Es constante");
                    System.out.println("Nombre: "+lista.nombre +" Tipo: "+lista.tipo +" Valor:" + lista.value);
                }else{
                    System.out.println("Es variable");
                    System.out.println("Nombre: "+lista.nombre +" Tipo: "+lista.tipo +" Valor:" + lista.value);
                }
            }
        }
    }
    */
    
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
    	for(Variables lista: tabla) {
    		if(lista.isArreglo){
    			String[] row= {lista.nombre, lista.tipo, lista.printvalues()};
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
    
    
    public void constantesInterfaz() {
    	/*ArrayList<String[]> aux;
    	aux = this.getConstantes();
    	String[][] valores= new String[aux.size()][3];
    	
    	for(int i=0;i<aux.size();i++) {
    		valores[i][0]=aux.get(i)[0];
    		valores[i][1]=aux.get(i)[1];
    		valores[i][2]=aux.get(i)[2];
    	}*/
        
        c.printConst(this.getConstantes());
    }
    
    public void arreglosInterfaz(String msg) {
    	/*ArrayList<String[]> aux;
    	aux = this.getArreglos();
    	String[][] valores = new String[aux.size()][3];
    	
    	for(int i=0;i<aux.size();i++)
        {
    		valores[i][0]=aux.get(i)[0];
    		valores[i][1]=aux.get(i)[1];
    		valores[i][2]=aux.get(i)[2];
    	}*/
        
        c.printArreglos(this.getArreglos());
   }
    

    public void variablesInterfaz(String msg) {
    	/*ArrayList<String[]> aux;
    	aux= this.getVariables();
    	String[][] valores= new String[aux.size()][3];
    	
    	for(int i=0;i<aux.size();i++) {
    		valores[i][0]=aux.get(i)[0];
    		valores[i][1]=aux.get(i)[1];
    		valores[i][2]=aux.get(i)[2];
    	}*/
        
        c.printVar(this.getVariables());
   }
}
