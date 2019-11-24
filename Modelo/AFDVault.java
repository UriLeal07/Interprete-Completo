package Modelo;

import Controlador.Control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AFDVault
{
    private Control c;
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<String> alfabeto = new ArrayList<>();
    private ArrayList<Transicion> transiciones= new ArrayList<>();
    private ArrayList<String> retrocesos= new  ArrayList<>();
    private ArrayList<String> numRetrocesos= new  ArrayList<>();
    private ArrayList<String> palabrasReservadas= new  ArrayList<>();
    private String inicial= "";
    private int IDENTIFICADOR=-1;
    private int maxl = 0;
    private Finales edosFinales = new Finales();
    private Boolean ready = false, bandRetroceso=false;
    LinkedList<Tokens> tokens = new LinkedList<>();
    private String error = "Error Lexico: ";

    public AFDVault(Control c, String path) throws IOException, ParserException 
    {
        this.c = c;
        File archivo = new File(path);
        BufferedReader bufferreader = new BufferedReader(new FileReader(archivo));
        String line = bufferreader.readLine();
        while (line != null) {
        	//System.out.println("linea sdf");
            if (line.startsWith("#")){
                switch (line.substring(1)){
                    case "ESTADOS":
                        line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//") && !line.isEmpty()){
                                estados.addAll(Arrays.asList(line.split("\\|")));
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(estados.toString());
                        if (estados.isEmpty())
                            throw new ParserException("Error no se recibieron estados");
                        break;
                    case "ALFABETO":
                        line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//") && !line.isEmpty()){
                                alfabeto.addAll(Arrays.asList(line.split("\\|")));
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(alfabeto.toString());
                        if (alfabeto.isEmpty())
                            throw new ParserException("Error no se recibieron caracteres para alfabeto");
                        break;
                    case "TRANSICIONES":
                        line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//") && !line.isEmpty()){
                                String[] values = line.split("\\|");
                                if (values.length != alfabeto.size()+1){
                                    throw new ParserException("Error en transiciones se esperaba "+alfabeto.size()+1+" valor(es)");
                                }
                                for(int i=0; i<alfabeto.size(); i++){
                                    transiciones.add(new Transicion(values[0],alfabeto.get(i),values[i+1]));
                                }
                                //transiciones.add(new Transicion(values[0],values[1],values[2]));
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(transiciones.toString());
                        break;
                    case "INICIAL":
                        line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//") && !line.isEmpty()){
                                if (!inicial.equals("")){
                                    throw new ParserException("Solo se puede asignar una vez el valor inicial");
                                }else{
                                    if (line.split("\\|").length > 1){
                                        throw new ParserException("Solo se puede asignar una vez el valor inicial");
                                    }
                                    inicial= line;
                                }
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(inicial);
                        if (inicial.isEmpty())
                            throw new ParserException("Error no se recibio valor inicial");
                        break;
                    case "FINAL":
                        line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//")){
                                if (!line.isEmpty()) {
                                    String[] values = line.split("\\|");
                                    if(values.length!=2){
                                        throw new ParserException("Error solo son dos valores para cada edo final");
                                    }else{
                                        try {
                                            edosFinales.addEdo(Integer.valueOf(values[0]),values[1]);
                                        }catch (NumberFormatException e){
                                            throw new ParserException("Se esperaba un numero entero");
                                        }
                                    }
                                }
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(edosFinales.toString());
                        if (edosFinales.isEmpty())
                            throw new ParserException("Error no se recibieron estado(s) final(es)");
                        break;
                    case "RETROCESOS":
                    	String[] retro;
                    	line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//")){
                                if (!line.isEmpty()) {
                                	//retrocesos.addAll(Arrays.asList(line.split("\\|")));
                                	retro=line.split("\\|");
                                	retrocesos.add(retro[0]);
                                	numRetrocesos.add(retro[1]);
                                }
                                    
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(retrocesos.toString());
                        break;
                    case "IDENTIFICADOR":
                        line = bufferreader.readLine();
                        while (line != null && !line.startsWith("#")){
                            if (!line.startsWith("//") && !line.isEmpty()){
                                if (IDENTIFICADOR != -1){
                                    throw new ParserException("Solo se puede asignar una vez el valor inicial");
                                }else{
                                    if (line.split("\\|").length > 1){
                                        throw new ParserException("Solo se puede asignar una vez el valor inicial");
                                    }
                                    try {
                                        IDENTIFICADOR = Integer.valueOf(line);
                                    }catch(NumberFormatException e){
                                        throw new ParserException("Solo se puede asignar numeros enteros");
                                    }
                                }
                            }
                            line = bufferreader.readLine();
                        }
                        //System.out.println(inicial);
                        if (IDENTIFICADOR == -1)
                            throw new ParserException("Error no se recibio valor para el identificador");
                        break;
                     default:
                    	 line = bufferreader.readLine();
                    	 //System.out.println("basura: "+line.toString());
                    	
                }
            }else{
                line = bufferreader.readLine();
            }
        }
        if(!transiciones.isEmpty()) ready = true;
        bufferreader.close();
    }
/*
    public void PrintAFD(){
        Interfa.textSalida.append("\n"+ estados.toString());
        Interaz.textSalida.append("\n"+ alfabeto.toString());
        Intefaz.textSalida.append("\n"+ transiciones.toString());
        Intrfaz.textSalida.append("\n"+ inicial);
        Inerfaz.textSalida.append("\n"+ edosFinales.toString());
    }
*/
    public void resetTokens(){
        tokens.clear();
    }

    public int getSizeofTokens(){
        return tokens.size();
    }

    public boolean isEmpty(){
        return tokens.isEmpty();
    }

    public Tokens getToken(){
        if (tokens.isEmpty())
            return new Tokens("FinFichero","FinFichero",maxl);
        return tokens.removeFirst();
    }

    public void setMaxLineas(int maxLineas){
        maxl = maxLineas;
    }

    public boolean allready(){
        return ready;
    }

    public void validaSt(String cadena,int linea) throws ParserException{
        //Preprocesamiento
    	cadena = cadena.concat("¬");
        cadena= cadena.trim();
        //cadena= cadena.replace(" ","");
        int apuntador = 0;
        char [] ncaracter= cadena.toCharArray();
        String EdoActual= inicial;
        int i=0;
        boolean flag= false;
        while (!flag && i < ncaracter.length){
            String aux= String.valueOf(ncaracter[i]);
            String res= ValidaTrans(EdoActual, aux);
            if(res.isEmpty())
            {
                flag=true;
                if(cadena.endsWith("¬"))
                {
                    c.printConsola(error+"El caracter \""+ aux+ "\" en la posicion "+ (i+1) + " linea "+linea+" no es valido.\n");
                    throw new ParserException(error+"El caracter \""+ aux+ "\" en la posicion "+ (i+1) + " linea "+linea+" no es valido.");
                }
                else
                {
                    c.printConsola(error+"El caracter \""+ aux+ "\" en la posicion "+ (i+1) + " linea "+linea+" no es valido.\n");
                    throw new ParserException(error+"El caracter \""+ aux+ "\" en la posicion "+ (i+1) + " linea "+linea+" no es valido.");
                }
            }
            EdoActual=res;
            try {
                if (edosFinales.containsedo(EdoActual)) {
                    //Almacenar token
                    int retroceso = 1;
                    int retrocesoI = 0;
                    boolean ban = false;

                    if (retrocesos.contains(EdoActual) || (i + 1) == (ncaracter.length - 1)) {
                        int auxIndice = retrocesos.indexOf(EdoActual);
                        retrocesoI = Integer.parseInt(numRetrocesos.get(auxIndice));
                        retroceso = 0;
                        ban = true;
                    }
                    if (Integer.parseInt(EdoActual) == IDENTIFICADOR) {
                        String isReservada = buscaReservadas(cadena.substring(apuntador, (i + retroceso)).trim().toLowerCase());
                        if (!isReservada.isEmpty()) {
                            //Interfaz.textSalida.append("\napun:"+apuntador+" retroc: "+ (i+retroceso)+"apun i:"+i);
                            c.printConsola("{" + "PR" + ", " + isReservada + ", " + "linea: " + linea + "}\n");
                            //retrocesoI=retrocesoI+(cadena.substring(apuntador,(i+retroceso)).length()-isReservada.length());
                            //apuntador=apuntador+isReservada.length();
                            tokens.add(new Tokens("PR",isReservada,linea));

                        } else {
                            //Interfaz.textSalida.append("\napun:"+apuntador+" retroc: "+ (i+retroceso)+"apun i:"+i);
                            c.printConsola("{" + edosFinales.valores.get(edosFinales.estados.indexOf(Integer.parseInt(EdoActual))) + ", " + cadena.substring(apuntador, (i + retroceso)) + ", " + "linea: " + linea + "}\n");
                            tokens.add(new Tokens(edosFinales.valores.get(edosFinales.estados.indexOf(Integer.parseInt(EdoActual))),cadena.substring(apuntador, (i + retroceso)),linea));
                            apuntador = (i + retroceso);
                        }


                    } else {
                        //Interfaz.textSalida.append("\napun:"+apuntador+" retroc: "+ (i+retroceso)+"apun i:"+i);
                        c.printConsola("{" + edosFinales.valores.get(edosFinales.estados.indexOf(Integer.parseInt(EdoActual))) + ", " + cadena.substring(apuntador, (i + retroceso)) + ", " + "linea: " + linea + "}\n");
                        tokens.add(new Tokens(edosFinales.valores.get(edosFinales.estados.indexOf(Integer.parseInt(EdoActual))),cadena.substring(apuntador, (i + retroceso)),linea));
                    }

                    apuntador = (i + retroceso);
                    if (ban)
                        i = i - retrocesoI;
                    //tokens.add(new Tokens(EdoActual,))
                    EdoActual = "0";
                }
            }catch(ParserException ex)
            {
                if(ex.getMessage().equals("Token no válido"))
                {
                    c.printConsola(error+" linea "+linea +" "+ex.getMessage()+"\n");
                    throw new ParserException(error+" linea "+linea +" "+ex.getMessage());
                }
                else
                {
                    c.printConsola(error+"En el caracter \""+ aux+ "\" en la posicion "+ (i+1) +" linea "+linea +" "+ex.getMessage()+"\n");
                    throw new ParserException(error+"En el caracter \""+ aux+ "\" en la posicion "+ (i+1) +" linea "+linea +" "+ex.getMessage());
                }
            }
            i++;
        }
    }

    private String buscaReservadas(String palabra)
    {
    	 Set<String> set = new HashSet<String>(palabrasReservadas);
    	 if(set.contains(palabra))
    		 return palabra;
    	 
    	String aux= new String(); 
    	/*for(int i=0;i<palabrasReservadas.size();i++) {
    		 if(palabra.startsWith(palabrasReservadas.get(i)) || palabra.endsWith(palabrasReservadas.get(i))){
    			 aux=palabrasReservadas.get(i);
    			 return aux;
    		 }
    		 	
    	 }*/
		return aux;
	}

	public String ValidaTrans(String EdoA, String val){
        //Interfaz.textSalida.append("\n"+EdoA+", "+val);
    	Character aux=val.charAt(0);
    	if(Character.isDigit(aux)) {
    		val="$";
    	}
    	if(Character.isAlphabetic(aux) || aux=='_') {
    		val="@";
    	}
    	if(aux==' ' || aux=='\t')
    	    val="¬";
        String res= "";
        for(Transicion Trans: transiciones){
            if(Trans.x.equals(EdoA)){
                if(Trans.y.equals(val)){
                    res=Trans.value;
                    break;
                }
            }
        }
        return res;
    }

	public boolean cargarReservadas(String path) throws IOException {
		File archivo = new File(path);
        BufferedReader bufferreader = new BufferedReader(new FileReader(archivo));
        String line = bufferreader.readLine();
        while (line != null) {
        	 if (!line.startsWith("//") && !line.isEmpty()){
        		 palabrasReservadas.add(line);
        		 //System.out.println(line);
        	 }
        	 line=bufferreader.readLine();
        }
        
        
        bufferreader.close();
        return true;
	}

   public Control getC(){ return c; }
}