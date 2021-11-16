package FinalDESALG;

/**
 *
 * @author Sawicki Mauricio
 */
public class Planta {

    //Atributos 
    private int codigo;
    private double precio, alturaMax, tempMin;
    private String lugarIdeal;
    private boolean tieneFlores, esMedicinal;

    //Constructores 
    public Planta(int unCodigo) {
        codigo = unCodigo;
    }

    public Planta(int unCod, double unPrecio) {
        codigo = unCod;
        precio = unPrecio;
    }
    
    @Override
    //Observadoras
    public String toString() {
        String res;
        res = "Planta: " + codigo + " Precio: " + precio + " Altura maxima: " + alturaMax + " Temp minima: " + tempMin + " Lugar ideal: " + lugarIdeal + " Tiene flores?: " + tieneFlores + " Es medicinal? " + esMedicinal;
        return res;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public boolean getTienesFlores() {
        return this.tieneFlores;
    }

    public double getAlturaMaxima() {
        return this.alturaMax;
    }

    public String getLugarIdeal() {
        return this.lugarIdeal;
    }

    public double getTempMin() {
        return this.tempMin;
    }

    public boolean getEsMedicinal() {
        return this.esMedicinal;
    }

    //Modificadoras
    public void setAlturaMaxima(double altura) {
        alturaMax = altura;
    }

    public void setPrecio(double unPrecio) {
        precio = unPrecio;
    }

    public void setTempMin(double unaTemp) {
        tempMin = unaTemp;
    }

    public void setLugarIdeal(String unLugar) {
        lugarIdeal = unLugar;
    }

    public void setTienesFlores(boolean tiene) {
        tieneFlores = tiene;
    }

    public void setEsMedicinal(boolean esMed) {
        esMedicinal = esMed;
    }

    // Propias del tipo
    public boolean equals(Planta unaPlanta) {
        return this.codigo == (unaPlanta.getCodigo());
    }
}
