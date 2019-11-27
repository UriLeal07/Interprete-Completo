package Modelo;

import java.util.LinkedList;

public class Variables {
    public String nombre;
    public String tipo;
    public String value;
    public LinkedList<String> values;
    public int pos;
    public boolean isConstante;
    public boolean isArreglo;
    public int tam;
    public int indexsel;
    public int valorsel;
    public String charsel;

    public Variables()
    {
        values = new LinkedList<>();
        nombre= "";
        tipo= "";
        value= "";
        isConstante=false;
        isArreglo = false;
        tam= 0;
        indexsel= 0;
        valorsel= -1;
        charsel = "";
        pos= 0;
    }

    public String addNewValortoArreglo(String valor){
        if (valor.startsWith("'")){
            if(tipo.equals("char")){
                values.add(valor);
                return "";
            }else{
                return "Es un arreglo de enteros no se puede agregar caracter";
            }
        }else{
            if(tipo.equals("int")){
                values.add(valor);
                return "";
            }else{
                return "Es un arreglo de caracteres no se puede agregar entero";
            }
        }
    }

    public int length(){
        return values.size();
    }

    public String getValuebyIndex(int index){
        return values.get(index);
    }

    public String updatevalue(int index,String nuevo) {
        if (nuevo.startsWith("'")){
            if(tipo.equals("char")){
                values.set(index,nuevo);
                return "";
            }else{
                return "Es un arreglo de enteros no se puede modificar caracter";
            }
        }else{
            if(tipo.equals("int")){
                values.set(index,nuevo);
                return "";
            }else{
                return "Es un arreglo de caracteres no se puede modificar entero";
            }
        }
    }

    public String printvalues(){
        String valores= "";
        for(String val: values){
            valores = valores.concat(val+"|");
        }
        return valores;
    }


}
