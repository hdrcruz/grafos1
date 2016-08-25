package grafos.model;

/**
 * Created by hdrcruz on 19/08/2016.
 */
public class Vertice {
    private int id;
    private int grau;

    public Vertice(int id) {
        this.id = id;
        this.grau = 0;
    }

    public int getNome() {
        return id;
    }

    public void setNome(int id) {
        this.id = id;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public void aumentarGrau(){
        grau++;
    }

    public void diminuirGrau(){
        grau--;
    }

    @Override
    public String toString() {
        return "Vertice " + this.getNome();
    }
}
