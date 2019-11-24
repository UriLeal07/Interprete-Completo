package Modelo;

public class Transicion{
    public String x;
    public String y;
    public String value;

    public Transicion(String x, String y, String value){
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public String toString(){
        return ("[{"+x+"},{"+y+"},{"+value+"}]");
    }
}
