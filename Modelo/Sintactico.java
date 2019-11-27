package Modelo;

import java.util.LinkedList;

public class Sintactico
{
    public static Semantico semantico;
    
    private AFDVault lexico;
    private Tokens token;
    private Tokens ultimoToken;
    private LinkedList<Tokens> estadoActual;
    private LinkedList<Tokens> estadoPrev;
    private LinkedList<ParasControl> controlPara;
    private int indiceactual;
    private boolean ready;
    
    public Sintactico(AFDVault lexico)
    {
        this.lexico = lexico;
        semantico = new Semantico(lexico.getC());
        estadoActual= new LinkedList<>();
        controlPara = new LinkedList<>();
        estadoPrev = null;
        indiceactual = 0;
        ready= false;
    }

    public void Iniciar() throws ParserException
    {
        token = lexico.getToken();
        ultimoToken = token;
        A();
        if (!token.token.equals("FinFichero")) {
            throw new ParserException(Error(token.secuencia, "fin de fichero", token.pos));
        }
    }

    public void A() throws ParserException {
        Emparejar("programa");
        Emparejar("identificador");
        semantico.nombrePrograma = ultimoToken.secuencia;
        lexico.getC().setTitulo(semantico.nombrePrograma);
        B();
        //semantico.printtabla();
        Emparejar("inicio");
        C();
        Emparejar("fin");
        //semantico.printtabla();

    }

    public void B() throws ParserException {
        if (token.token.equals("PR"))
        {
            if (token.secuencia.equals("constantes")) {
                Emparejar("constantes");
                Cons();
               semantico.constantesInterfaz();
                D();
            }
            if (token.secuencia.equals("arreglos")) {
                Emparejar("arreglos");
                Arreg();
                semantico.arreglosInterfaz("arreglo agregado en "+ ultimoToken.pos);
            }
        }
    }
    
   
    
    
    

    public void C() throws ParserException
    {
        if (token.token.equals("PR"))
        {
            switch (token.secuencia){
                case "escribe":
                        J(); //Funcionando al 100 papu
                    break;
                case "para":
                        K(); //Funcionando al 100 papu
                    break;
                case "si":
                        L(); //Funcionando al 100 papu
                    break;
                case "lee":
                        M();
                    break;
                default:
                    //vacio
            }
        }
        else if(token.token.equals("identificador")){
            N(); //Funcionando al 100 papu
        }
        else{
            //vacio
        }
    }
    
    //Aqui se debe hacer la funcion escribe :3
    public void J()throws ParserException{
        Emparejar("escribe");
        Emparejar("parA");
        Variables aux = new Variables();
        U(aux);
        Emparejar("parC");
        Emparejar("punto y coma");
        // controlador
        funcionEscribe(aux.value);
        C();
    }

    public void U(Variables aux)throws ParserException{
        Q(aux);
    }

    public void Q(Variables aux)throws ParserException{
        if(token.token.equals("identificador")){
            Emparejar("identificador");
            Variables otra = semantico.getByName(ultimoToken.secuencia);
            if (otra == null){
                throw new ParserException(ErrorSemantico(ultimoToken.secuencia,"La variable no ha sido inicializada",ultimoToken.pos));
            }
            //System.out.println(otra.tipo);
            T(otra);
            //Verificar arreglo
            if(otra.valorsel != -1)
            {
                if(!otra.charsel.equals(""))
                {
                    aux.value = String.valueOf(otra.charsel);
                }
                else
                {
                    aux.value = String.valueOf(otra.valorsel);
                }
                
                aux.tipo=otra.tipo;

            }else{
                if(!otra.charsel.equals("")){
                    aux.value = otra.charsel;
                    aux.tipo=otra.tipo;
                }else{
                    if(otra.isArreglo){
                        throw new ParserException(ErrorSemantico(otra.nombre,"No se puede asignar un arreglo",ultimoToken.pos));
                    }else{
                        aux.value = otra.value;
                        aux.tipo = otra.tipo;
                    }

                }
            }
        }else {
            Valor(aux);
        }
    }

    public void T(Variables aux)throws ParserException{
        if (token.token.equals("punto")){
            Emparejar("punto");
            Emparejar("length");
            if(aux.isArreglo){
                aux.valorsel = aux.length();
            }else{
                throw new ParserException(ErrorSemantico(aux.nombre,"No se puede aplicar .length, no es un arreglo",ultimoToken.pos));
            }
        }else if(token.token.equals("CuadA")) {
            if(!aux.isArreglo){
                throw new ParserException(ErrorSemantico(aux.nombre,"Arreglo no declarado",token.pos));
            }
            Emparejar("CuadA");
            Variables otro = new Variables();
            Lim(otro);
            P(otro);
            //System.out.println(otro.valorsel);
            if(otro.valorsel>=aux.length()){
                throw new ParserException(ErrorSemantico(aux.nombre,"Indice fuera del rango del arreglo",ultimoToken.pos));
            }
            if(otro.tipo.equals("char")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero recibio caracter",ultimoToken.pos));
            }else{
                if(aux.tipo.equals("char")){
                    aux.charsel = aux.getValuebyIndex(otro.valorsel);
                }else {
                    aux.valorsel = Integer.parseInt(aux.getValuebyIndex(otro.valorsel));
                }
            }
            Emparejar("CuadC");
        }else{
            //nada
        }
    }

    public void Lim(Variables aux)throws ParserException{
        if(token.token.equals("entero")){
            Emparejar("entero");
            aux.valorsel = Integer.parseInt(ultimoToken.secuencia);
        }else if(token.token.equals("identificador")){
            //variable, constante o arreglo.
            Emparejar("identificador");
            Variables otra = semantico.getByName(ultimoToken.secuencia);
            if (otra == null){
                throw new ParserException(ErrorSemantico(ultimoToken.secuencia,"La variable no ha sido inicializada",ultimoToken.pos));
            }
            T(otra);
            //Si entro un arreglo con caracteres trae un caracter seleccionado.
            //Si entro un arreglo con enteros trae un caracter seleccionado.
            //en otro caso es una variable entonces obtenemos el valor.
            if(otra.valorsel != -1){
                aux.valorsel = otra.valorsel;
            }else{
                if(!otra.charsel.equals("")){
                    aux.charsel = otra.charsel;
                }else{
                    if(otra.isArreglo){
                        throw new ParserException(ErrorSemantico(otra.nombre,"No se puede asignar un arreglo",ultimoToken.pos));
                    }
                    if(otra.tipo.equals("int")){
                        aux.valorsel= Integer.parseInt(otra.value);
                        aux.tipo = "int";
                    }else{
                        aux.charsel= otra.value;
                        aux.tipo = "char";
                    }
                }
            }

        }else{
            throw new ParserException(Error(token.secuencia, "entero o identificador", token.pos));
        }
    }

    public void K()throws ParserException{
        Emparejar("para");
        Emparejar("identificador");
        Variables aux= semantico.getByName(ultimoToken.secuencia);
        if(aux == null){
            aux= new Variables();
            aux.nombre= ultimoToken.secuencia;
            aux.tipo = "int";
            aux.pos = ultimoToken.pos;
        }
        Emparejar("asignacion");
        Variables otro = new Variables();
        Lim(otro);
        if(otro.valorsel != -1){
            aux.value = String.valueOf(otro.valorsel);
            //semantico.variablesInterfaz("variable modificado en linea: "+ultimoToken.pos);
        }else {
            if(!otro.charsel.equals("")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero se recibio un caracter",aux.pos));
            }
            if(otro.tipo.equals("char")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero se recibio un caracter",aux.pos));
            }
            aux.value= otro.value;
        }
        int ultimaposicion= ultimoToken.pos;
        String res= semantico.intentaagregar(aux);
        if(!res.equals("")){
            throw new ParserException(ErrorSemantico("",res,aux.pos));
        }
        semantico.variablesInterfaz("variable agregada en linea: "+ultimoToken.pos);
        int inicio= Integer.parseInt(aux.value);
        Emparejar("hasta");
        Variables limite = new Variables();
        Lim(limite);
        int limitepara=0;
        if(limite.valorsel != -1){
            limitepara= limite.valorsel;
        }else {
            if(!otro.charsel.equals("")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero se recibio un caracter",aux.pos));
            }
            if(otro.tipo.equals("char")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero se recibio un caracter",aux.pos));
            }
            limitepara= Integer.parseInt(otro.value);
        }
        Emparejar("paso");
        String simbolo;
        Variables incremento=new Variables();
        simbolo= Indec(incremento);
        Emparejar("hacer");
        boolean isfirst= false;
        int previo = -1;
        //reglas para verificar un correcto for
        if(inicio != limitepara) {
            if(controlPara.isEmpty()) {
                ParasControl first = new ParasControl();
                first.indice = 0;
                getinstruccionesparas(first);
                first.AddInstruccion(token);
                first.printInstrucciones();
                controlPara.add(first);
                estadoActual = first.instrucciones;
                isfirst = true;
            }else{
                estadoPrev = estadoActual;
                indiceactual= indiceactual+1;
                ParasControl paraactual = getPara(indiceactual);
                if(paraactual==null){
                    throw new ParserException(ErrorSemantico("","Error al interpretar un ciclo para",ultimoToken.pos));
                }
                //paraactual.AddInstruccion(token);
                estadoActual= paraactual.instrucciones;
            }
            ready = true;
            if(!estadoActual.isEmpty()){
                String nombrevar = aux.nombre;
                LinkedList<Tokens> auxs=(LinkedList<Tokens>) estadoActual.clone();
                if(simbolo.equals("+")){
                    for(int i=inicio; i<limitepara; i=i+incremento.valorsel){
                        Variables auxilmas= semantico.getByName(nombrevar);
                        if(auxilmas.tipo.equals("char")){
                            throw new ParserException(ErrorSemantico("","la variable de control fue modificada a caracter no se puede incrementar un caracter",ultimaposicion));
                        }else{
                            auxilmas.value= (String.valueOf(i));
                            semantico.intentaagregar(auxilmas);
                           //semantico.variablesInterfaz("variable modificada en linea: "+ultimoToken.pos);
                        }
                        estadoActual = (LinkedList<Tokens>) auxs.clone();
                        token= estadoActual.removeFirst();
                        //System.out.println(i);
                        C();
                    }
                }else{
                    for(int i= inicio;i>limitepara; i=i-incremento.valorsel){
                        Variables auxilmas= semantico.getByName(nombrevar);
                        if(auxilmas.tipo.equals("char")){
                            throw new ParserException(ErrorSemantico("","la variable de control fue modificada a caracter no se puede decrementar un caracter",ultimaposicion));
                        }else{
                            auxilmas.value= (String.valueOf(i));
                            semantico.intentaagregar(auxilmas);
                            //semantico.variablesInterfaz("variable modificada en linea: "+ultimoToken.pos);
                        }
                        estadoActual = (LinkedList<Tokens>) auxs.clone();
                        token= estadoActual.removeFirst();
                        //System.out.println(i);
                        C();
                    }
                }
            }
            if(isfirst){
                ready = false;
                controlPara.clear();
                indiceactual = 0;
            }else{
                //System.out.println("current token");
                //System.out.println(token.secuencia);
                //System.out.println("Current estado: ");
                imprimeestados(estadoActual);
                //System.out.println("Estado Prev: ");
                imprimeestados(estadoPrev);
                estadoActual= estadoPrev;
                estadoActual.addLast(token);
                indiceactual= indiceactual-1;
            }
        }else{
            recorrerhastaelfin(false);
        }
        Emparejar("fin");
        //System.out.println(token.secuencia);
        C();
    }



    public String Indec(Variables aux)throws ParserException{
        Variables otro= new Variables();
        String ret="";
        if(token.token.equals("suma")){
            Emparejar("suma");
            ret = ultimoToken.secuencia;
            Lim(otro);
        }else if(token.token.equals("resta")){
            Emparejar("resta");
            ret=  ultimoToken.secuencia;
            Lim(otro);
        }else{
            throw new ParserException(Error(token.secuencia, "+ o -", token.pos));
        }
        if(otro.valorsel != -1){
            aux.valorsel= otro.valorsel;
        }else {
            if(!otro.charsel.equals("")){
                throw new ParserException(ErrorSemantico("","Se esperaba un entero pero se recibio un caracter",ultimoToken.pos));
            }
            if(otro.tipo.equals("char")){
                throw new ParserException(ErrorSemantico("","Se esperaba un entero pero se recibio un caracter",ultimoToken.pos));
            }
            aux.valorsel= Integer.parseInt(otro.value);
        }
        return ret;
    }

    public void L()throws ParserException{
        Emparejar("si");
        Emparejar("parA");
        Variables c1 = new Variables();
        Q(c1);
        Comp();
        String operador= ultimoToken.secuencia;
        Variables c2= new Variables();
        Q(c2);
        boolean flag= false;
        int aux1, aux2;
        if(mismoTipo(c1,c2)){
            switch (operador){
                case "==":
                    if (c1.tipo.equals("char")){
                        flag= c1.value.equals(c2.value);
                    }else{
                        aux1= Integer.parseInt(c1.value);
                        aux2= Integer.parseInt(c2.value);
                        flag= aux1 == aux2;
                    }
                    break;
                case "<=":
                    if(c1.tipo.equals("char")){
                        throw new ParserException(ErrorSemantico("","Operador <= no se puede aplicar a variables de tipo caracter",ultimoToken.pos));
                    }
                    aux1= Integer.parseInt(c1.value);
                    aux2= Integer.parseInt(c2.value);
                    flag= aux1 <= aux2;
                    break;
                case "<":
                    if(c1.tipo.equals("char")){
                        throw new ParserException(ErrorSemantico("","Operador < no se puede aplicar a variables de tipo caracter",ultimoToken.pos));
                    }
                    aux1= Integer.parseInt(c1.value);
                    aux2= Integer.parseInt(c2.value);
                    flag = aux1<aux2;
                    break;
                case ">=":
                    if(c1.tipo.equals("char")){
                        throw new ParserException(ErrorSemantico("","Operador >= no se puede aplicar a variables de tipo caracter",ultimoToken.pos));
                    }
                    aux1= Integer.parseInt(c1.value);
                    aux2= Integer.parseInt(c2.value);
                    flag= aux1 >= aux2;
                    break;
                case ">":
                    if(c1.tipo.equals("char")){
                        throw new ParserException(ErrorSemantico("","Operador > no se puede aplicar a variables de tipo caracter",ultimoToken.pos));
                    }
                    aux1= Integer.parseInt(c1.value);
                    aux2= Integer.parseInt(c2.value);
                    flag= aux1 > aux2;
                    break;
                case "!=":
                    if (c1.tipo.equals("char")){
                        flag= !c1.value.equals(c2.value);
                    }else{
                        aux1= Integer.parseInt(c1.value);
                        aux2= Integer.parseInt(c2.value);
                        flag= aux1 != aux2;
                    }
                    break;
            }
        }else{
            throw new ParserException(ErrorSemantico("","La condicional solo puede aplicarse en variables o constantes del mismo tipo",ultimoToken.pos));
        }
        Emparejar("parC");
        Emparejar("entonces");
        if(flag){
            C();
        }else{
            recorrerhastaelfin(true);
        }
        O(flag);
        Emparejar("fin");
        C();

    }

    public void Comp()throws ParserException{
        switch (token.token){
            case "menor":
                Emparejar("menor");
                break;
            case "menor igual":
                Emparejar("menor igual");
                break;
            case "mayor":
                Emparejar("mayor");
                break;
            case "mayor igual":
                Emparejar("mayor igual");
                break;
            case "igual a":
                Emparejar("igual a");
                break;
            case "diferente":
                Emparejar("diferente");
                break;
            default:
                throw new ParserException(Error(token.secuencia, "simbolo relacional o de igualdad", token.pos));
        }
    }

    public void O(boolean flag)throws ParserException{
        if(token.secuencia.equals("sino")){
            Emparejar("sino");
            if (!flag) {
                C();
            }else{
                recorrerhastaelfin(false);
            }
        }
    }

    public void M()throws ParserException{
    	boolean band=false;
        Emparejar("lee");
        Emparejar("parA");
        Emparejar("identificador");
        Variables var = semantico.getByName(ultimoToken.secuencia);
        
        if(var!=null  )
        	if(var.isConstante)
        		throw new ParserException(ErrorSemantico(ultimoToken.secuencia,"es constante", ultimoToken.pos));
        
        String aux = lexico.getC().requestInput("Ingrese un valor para la variable \""+ultimoToken.secuencia+"\":");
        int value = -1;
        
        if(var!=null) {// si es variable ya existente
        	
        	if(var.isArreglo==false) {
        		try {
        			value= Integer.parseInt(aux);
        		}catch(Exception e) {
        		    if(aux.length() !=3 && !aux.startsWith("'") && !aux.endsWith("'")){
                        throw new ParserException(ErrorSemantico(aux,"solo se aceptan caracteres o enteros ",ultimoToken.pos) );
                    }else{
                        var.value=aux;
                        var.tipo="char";
                        semantico.variablesInterfaz("variable modificada en linea "+ultimoToken.pos);
                    }
                    band=true;
        			/*if(aux.toCharArray().length!=3 && aux.toCharArray()[0]!='\'' && aux.toCharArray()[2]!='\'')
            			throw new ParserException(ErrorSemantico(aux,"solo se aceptan caracteres o enteros ",ultimoToken.pos) );
        			else if(aux.toCharArray().length==3 && aux.toCharArray()[0]=='\'' && aux.toCharArray()[2]=='\'' ){
        				var.value=aux;
        				var.tipo="char";
        			}*/
        		}
        		if (band==false) {
        			var.value=Integer.toString(value);
            		var.tipo="int";
            		semantico.variablesInterfaz("variable modificada en linea "+ultimoToken.pos);
        		}
        		
        	}else {
        		//si es arreglo
        		  Variables otro = new Variables();
        	        V(otro);
        	        if(var.isArreglo){
        	            if (otro.valorsel != -1){
        	                if(otro.valorsel>= var.length()){
        	                    throw new ParserException(ErrorSemantico(var.nombre, "El index seleccionado esta fuera del rango del arreglo", var.pos));
        	                }
        	                var.indexsel = otro.valorsel;
        	            }else{
        	                throw new ParserException(ErrorSemantico(var.nombre,"Se esta intentando reasignar un arreglo definido",var.pos));
        	            }
        	        }
        	        try {
        	        	int valor=Integer.parseInt(aux.trim());
        	        	if(semantico.getByName(var.nombre).printvalues().startsWith("\'")==false) {
        	        		var.updatevalue(otro.valorsel, Integer.toString(valor));
        	        		semantico.arreglosInterfaz("arreglo int modificado en linea: "+ultimoToken.pos);
        	        	}
        	        		
        	        	else
        	        		throw new ParserException(ErrorSemantico(aux,"se esperaba entero ", ultimoToken.pos));
        	        }
                        catch(Exception e)
                        {
        	        	if(semantico.getByName(var.nombre).printvalues().startsWith("\'")==false)
        	        		throw new ParserException(ErrorSemantico(aux,"se esperaba entero ", ultimoToken.pos));
        	        	if(aux.toCharArray().length!=3 && aux.toCharArray()[0]!='\'' && aux.toCharArray()[2]!='\'' )
                			throw new ParserException(ErrorSemantico(aux,"se esperaba caracter ",ultimoToken.pos) );
            			else if(aux.toCharArray().length==3 && aux.toCharArray()[0]=='\'' && aux.toCharArray()[2]=='\''  ){
            				var.updatevalue(otro.valorsel, aux);
            				semantico.arreglosInterfaz("arreglo char modificado en linea: "+ultimoToken.pos);
            			}else {
            				throw new ParserException(ErrorSemantico(aux,"se esperaba entero o caracter", ultimoToken.pos));
            			}
        	        		 
        	        }
        	        
        	}
        	//semantico.printtabla();
        		
        }else {
        	// si la variable no existe
        	var= new Variables();
        	var.nombre=ultimoToken.secuencia;
        	try {
    			value= Integer.parseInt(aux);
    		}catch(Exception e) {
    			if(aux.toCharArray().length!=3 && aux.toCharArray()[0]!='\'' && aux.toCharArray()[2]!='\'' )
        			throw new ParserException(ErrorSemantico(aux,"solo se aceptan caracteres o enteros ",ultimoToken.pos) );
    			else if(aux.toCharArray().length==3 && aux.toCharArray()[0]=='\'' && aux.toCharArray()[2]=='\''  ){
    				var.value=aux;
    				var.tipo="char";
    			}	
    			band=true;
    		}
        	if(band==false) {
        		var.value=Integer.toString(value);
        		var.tipo="int";
        	}
        	semantico.saltaArregloAdd(var);
        	//semantico.printtabla();
        }
        
        Emparejar("parC");
        Emparejar("punto y coma");
        C();
    }

    public void N()throws ParserException{
    	boolean banderaEsArreglo=false;
        Emparejar("identificador");
        Variables aux = semantico.getByName(ultimoToken.secuencia);
        if(aux == null){
          aux = new Variables();
          aux.nombre = ultimoToken.secuencia;
          aux.pos = ultimoToken.pos;
        }else{
            aux.pos = ultimoToken.pos;
        }
        Variables otro = new Variables();
        V(otro);
        if(aux.isArreglo){
            if (otro.valorsel != -1){
                if(otro.valorsel>= aux.length()){
                    throw new ParserException(ErrorSemantico(aux.nombre, "El index seleccionado esta fuera del rango del arreglo", aux.pos));
                }
                aux.indexsel = otro.valorsel;
            }else{
                throw new ParserException(ErrorSemantico(aux.nombre,"Se esta intentando reasignar un arreglo definido",aux.pos));
            }
           
            
        }
        //System.out.println(aux.indexsel);
        Emparejar("asignacion");
        if (token.token.equals("caracter")){
            Emparejar("caracter");
            if(aux.isArreglo){
                if(aux.tipo.equals("int")){
                    throw new ParserException(ErrorSemantico(aux.nombre,"Se esta intentando modificar un caracter a un arreglo de enteros",ultimoToken.pos));
                }else{
                    aux.updatevalue(aux.indexsel,ultimoToken.secuencia);
                    banderaEsArreglo=true;
                    semantico.arreglosInterfaz("Arreglo de tipo char modificado en linea "+aux.pos);
                }
            }else{
                aux.value= ultimoToken.secuencia;
                aux.tipo= "char";
               
            }
        }else {
            Variables otro2 = new Variables();
            Lim(otro2);
            //int
            //car
            //variable
            P(otro2);
            //int -1
            if(otro2.valorsel != -1){
            
                if(aux.isArreglo){
                	banderaEsArreglo=true;
                    String res= aux.updatevalue(aux.indexsel,String.valueOf(otro2.valorsel));
                   semantico.arreglosInterfaz("Arreglo modificado en "+aux.pos);
                    if(!res.equals("")){
                        throw new ParserException(ErrorSemantico(aux.nombre,res,aux.pos));
                    }
                }else{
                    aux.value= String.valueOf(otro2.valorsel);
                    aux.tipo= "int";
                    
                }
            }else{
                String response;
                if(!otro2.charsel.equals("")){
                    response = otro2.charsel;
                }else{
                    response = otro2.value;
                }
                if(aux.isArreglo){
                	banderaEsArreglo=true;
                    String respuesta = aux.updatevalue(aux.indexsel,response);
                    semantico.arreglosInterfaz("Arreglo modificado en "+aux.pos);
                    if(!respuesta.equals("")){
                        throw new ParserException(ErrorSemantico(aux.nombre,respuesta,aux.pos));
                    }
                }else{
                    if(otro2.tipo.equals("int")){
                        aux.tipo= "int";
                    }else{
                        aux.tipo= "char";
                    }
                    aux.value= response;
                }
            }
        }
        Emparejar("punto y coma");
        
        String res = semantico.saltaArregloAdd(aux);
        if(res.equals("") && banderaEsArreglo==false)
        	semantico.variablesInterfaz("nueva variable agregada en linea:"+aux.pos);
        if(!res.equals("")){
            throw new ParserException(ErrorSemantico(aux.nombre,res,aux.pos));
        }
        
        //semantico.printtabla();
        C();
    }
    public void V(Variables aux)throws ParserException{
        if (token.token.equals("CuadA")){
            Emparejar("CuadA");
            //Espera el indice selecionado
            Variables conseguir= new Variables();
            Lim(conseguir);
            P(conseguir);
            if(conseguir.valorsel != -1){
                aux.valorsel = conseguir.valorsel;
            }else {
                if(!conseguir.charsel.equals("")){
                    throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero se recibio un caracter",aux.pos));
                }
                if(conseguir.tipo.equals("char")){
                    throw new ParserException(ErrorSemantico(aux.nombre,"Se esperaba un entero pero se recibio un caracter",aux.pos));
                }
                aux.valorsel= Integer.parseInt(conseguir.value);
            }
            Emparejar("CuadC");
        }
    }

    public void P(Variables aux)throws ParserException{
        Variables otro = new Variables();
        if(token.token.equals("PR")){
            if(token.secuencia.equals("mod")){
                if(aux.tipo.equals("char")){
                    throw new ParserException(ErrorSemantico(aux.nombre,"Solo se puede realizar operaciones sobre numeros enteros",aux.pos));
                }
                Emparejar("mod");
                Lim(otro);
                validaOperacion(otro);
                aux.valorsel= aux.valorsel % otro.valorsel;
            }
        }else{
            switch (token.token){
                case "suma":
                        if(aux.tipo.equals("char")){
                            throw new ParserException(ErrorSemantico(aux.nombre,"Solo se puede realizar operaciones sobre numeros enteros",aux.pos));
                        }
                        Emparejar("suma");
                        Lim(otro);
                        validaOperacion(otro);
                        aux.valorsel = aux.valorsel + otro.valorsel;
                    break;
                case "resta":
                        Emparejar("resta");
                        Lim(otro);
                        validaOperacion(otro);
                        aux.valorsel = aux.valorsel - otro.valorsel;
                        if(aux.valorsel <0 ){
                            throw new ParserException(ErrorSemantico(aux.nombre,"la resta no puede efectuarse pues obtiene un indice negativo",ultimoToken.pos));
                        }
                    break;
                case "division":
                        Emparejar("division");
                        Lim(otro);
                        validaOperacion(otro);
                        aux.valorsel = aux.valorsel / otro.valorsel;
                    break;
                case "multiplicacion":
                        Emparejar("multiplicacion");
                        Lim(otro);
                        validaOperacion(otro);
                        aux.valorsel = aux.valorsel * otro.valorsel;
                    break;
                default:
               //nada
            }
        }
    }



    public void Cons()throws ParserException{
        Variables aux = new Variables();
        aux.isConstante = true;
        Emparejar("identificador");
        aux.nombre = ultimoToken.secuencia;
        aux.pos = ultimoToken.pos;
        Emparejar("asignacion");
        Valor(aux);
        if(!semantico.add(aux)){
            throw new ParserException(ErrorSemantico(aux.nombre,"la constante ya esta definida",aux.pos));
        }
        F();
    }

    public void Arreg()throws ParserException{
        Variables aux= new Variables();
        aux.isArreglo = true;
        Emparejar("identificador");
        aux.nombre = ultimoToken.secuencia;
        aux.pos= ultimoToken.pos;
        Emparejar("asignacion");
        Emparejar("corchA");
        G(aux);
        Emparejar("corchC");
        if(!semantico.add(aux)){
            throw new ParserException(ErrorSemantico(aux.nombre,"el arreglo ya esta definido",aux.pos));
        }
        H();
    }

    public void D()throws ParserException{
        if(token.token.equals("PR")){
            if(token.secuencia.equals("arreglos")){
               Emparejar("arreglos");
               Arreg();
               semantico.arreglosInterfaz("Arreglo agregado en "+ultimoToken.pos);
            }
        }
    }

    public void H()throws ParserException{
        if(token.token.equals("identificador")){
            Variables aux= new Variables();
            aux.isArreglo = true;
            Emparejar("identificador");
            aux.nombre = ultimoToken.secuencia;
            aux.pos= ultimoToken.pos;
            Emparejar("asignacion");
            Emparejar("corchA");
            G(aux);
            Emparejar("corchC");
            if(!semantico.add(aux)){
                throw new ParserException(ErrorSemantico(aux.nombre,"el arreglo ya esta definido",aux.pos));
            }
            H();
        }
    }

    public void G(Variables aux)throws ParserException{
        Valor(aux);
        I(aux);
    }

    public void I(Variables aux)throws ParserException{
        if(token.token.equals("coma")) {
            Emparejar("coma");
            Valor(aux);
            I(aux);
        }
    }

    public void F()throws ParserException{
        if(token.token.equals("identificador")){
            Variables aux= new Variables();
            aux.isConstante = true;
            Emparejar("identificador");
            aux.nombre = ultimoToken.secuencia;
            Emparejar("asignacion");
            Valor(aux);
            if(!semantico.add(aux)){
                throw new ParserException(ErrorSemantico(aux.nombre,"la constante ya esta definida",aux.pos));
            }
            F();
        }
    }

    public void Valor(Variables aux)throws ParserException{
        switch (token.token){
            case "entero":
                    Emparejar("entero");
                    if(aux.isArreglo){
                        aux.tipo = aux.tipo.equals("")? "int": aux.tipo;
                        String response= aux.addNewValortoArreglo(ultimoToken.secuencia);
                        if(!response.equals("")){
                            throw new ParserException(ErrorSemantico(aux.nombre,response,aux.pos));
                        }
                    }else {
                        aux.tipo = "int";
                        aux.value = ultimoToken.secuencia;
                    }
                break;
            case "caracter":
                    Emparejar("caracter");
                    if(aux.isArreglo){
                        aux.tipo= aux.tipo.equals("") ? "char" : aux.tipo;
                        String res= aux.addNewValortoArreglo(ultimoToken.secuencia);
                        if(!res.equals("")){
                            throw new ParserException(ErrorSemantico(aux.nombre,res,aux.pos));
                        }
                    }else{
                        aux.tipo = "char";
                        aux.value = ultimoToken.secuencia;
                    }
                break;
                default:
                    throw new ParserException(Error(token.secuencia,"entero o caracter",token.pos));
        }
    }

    public void Emparejar(String tok)throws ParserException{
        ultimoToken = token;
        if (token.token.equals("PR")){
            if (!token.secuencia.equals(tok)){
                throw new ParserException(Error(token.secuencia,tok,token.pos));
            }
            switch (tok){
                case "programa":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else{
                        token = lexico.getToken();
                    }
                    break;
                case "inicio":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "fin":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "constantes":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "arreglos":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "para":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "hasta":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "paso":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "hacer":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "si":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "entonces":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "sino":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "escribe":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "lee":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "mod":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                case "length":
                    if(ready){
                        token = estadoActual.removeFirst();
                    }else {
                        token = lexico.getToken();
                    }
                    break;
                default:
                    throw new ParserException(Error(token.secuencia,tok,token.pos));
            }
        }else{
            if(token.token.equals(tok)){
                if(ready){
                    if(!estadoActual.isEmpty()) {
                        token = estadoActual.removeFirst();
                    }
                }else {
                    token = lexico.getToken();
                }
            }else{
                throw new ParserException(Error(token.secuencia,tok,token.pos));
            }
        }
    }

    //Metodos del semantico que son usados aqui:
    public void validaOperacion(Variables aux) throws ParserException{
        if(aux.valorsel == -1){
            if(!aux.charsel.equals("")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Solo se puede realizar operaciones sobre numeros enteros",aux.pos));
            }
            if(aux.tipo.equals("char")){
                throw new ParserException(ErrorSemantico(aux.nombre,"Solo se puede realizar operaciones sobre numeros enteros",aux.pos));
            }
            aux.valorsel = Integer.parseInt(aux.value);
        }
    }

    public void recorrerhastaelfin(boolean issino)throws ParserException{
        boolean flag=true;
        int needfin= 0;
        while(flag) {
            if(token.token.equals("PR")){
                /* Posibles casos:
                para fin
                inicio fin
                si fin
                si sino fin
                */
                if(token.secuencia.equals("para")){
                    needfin++;
                }
                if(token.secuencia.equals("si")){
                    needfin++;
                }
                if(token.secuencia.equals("sino")){
                    if(issino){
                        if(needfin == 0){
                            flag = false;
                        }
                    }
                }
                if(token.secuencia.equals("fin")){
                    if(needfin == 0) {
                        flag = false;
                    }else{
                        needfin--;
                    }

                }
            }else{
                if(token.token.equals("FinFichero")){
                    throw new ParserException(Error(token.secuencia,"Fin o Sino",token.pos));
                }
            }
            if(flag){
                if(ready){
                    if(estadoActual.isEmpty()){
                        throw new ParserException(ErrorSemantico("","Se esperaba fin",token.pos));
                    }
                    token= estadoActual.removeFirst();
                }else{
                    token=lexico.getToken();
                }

            }
        }
    }

    public boolean mismoTipo(Variables t1, Variables t2){
        //System.out.println(t1.tipo+" "+t2.tipo);
        return t1.tipo.equals(t2.tipo);
    }


    public void getinstruccionesparas(ParasControl first)throws ParserException{
        boolean flag=true;
        int needfin= 0;
        while(flag) {
            if(token.token.equals("PR")){
                if(token.secuencia.equals("para")){
                    needfin++;
                }
                if(token.secuencia.equals("hacer")){
                    first.AddInstruccion(token);
                    token=lexico.getToken();
                    ParasControl otro =  new ParasControl();
                    otro.indice = first.indice +1;
                    getinstruccionesparas(otro);
                    otro.AddInstruccion(token);
                    controlPara.add(otro);
                    //otro.printInstrucciones();
                }
                if(token.secuencia.equals("si")){
                    needfin++;
                }
                if(token.secuencia.equals("fin")){
                    if(needfin == 0) {
                        flag = false;
                    }else{
                        needfin--;
                    }
                }
                if(flag) {
                    first.AddInstruccion(token);
                }
            }else{
                if(token.token.equals("FinFichero")){
                    throw new ParserException(Error(token.secuencia,"Fin",token.pos));
                }
                first.AddInstruccion(token);
            }
            if(flag) {
                token = lexico.getToken();
            }
        }
    }

    public ParasControl getPara(int index){
        boolean match=false;
        int matchindex= -1;
        for(ParasControl para : controlPara){
            if(para.indice == index){
                matchindex = controlPara.indexOf(para);
                match = true;
            }
        }
        if(!match){
            return null;
        }
        return controlPara.get(matchindex);
    }

    public void imprimeestados(LinkedList<Tokens> current){
        String res= "";
        for(Tokens inst : current){
            res=res.concat(inst.secuencia+"|");
        }
    }
    //Fin Metodos

    private String Error(String lexema, String esperado, int linea)
    {
    	lexico.getC().printMsgError("Error sintáctico: Encontrado lexema "+lexema+" se esperaba "+ esperado+ " en linea: "+String.valueOf(linea));
        
        return "Error Sintactico:\nEncontrado lexema "+lexema+" se esperaba "+ esperado+ " en linea: "+String.valueOf(linea);
    }

    private String ErrorSemantico(String lexema, String esperado, int linea)
    {
    	lexico.getC().printMsgError("Error semántico: "+lexema+" "+esperado+" en linea: "+String.valueOf(linea));
        
        return "Error Semantico:\n"+lexema+" "+esperado+" en linea: "+String.valueOf(linea);
    }
    
    private void funcionEscribe(String cadena) {
        lexico.getC().printOutput(cadena);
    }
}