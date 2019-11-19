package Modelo;

public class Tokens {
    public final String token;
    public final String secuencia;
    public final int pos;

    public Tokens(String token, String secuencia, int pos){
        super();
        this.token = token.trim();
        this.secuencia = secuencia.trim();
        this.pos = pos;
    }
}

