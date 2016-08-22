package grafos;

/**
 * Created by hdrcruz on 19/08/2016.
 */
public class Aresta implements  Comparable<Aresta>{
    private int origem;
    private int destino;

    public Aresta(int origem, int destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    @Override
    public int compareTo(Aresta o) {
        if (this.origem == o.getOrigem()) return destino - o.getDestino();
        return this.origem - o.getOrigem();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Aresta)) return false;
        Aresta a = (Aresta) o;
        return a.getOrigem() == this.origem && a.getDestino() == this.destino;
    }

    @Override
    public String toString() {
        return "(" + this.origem + "," + this.destino + ")";
    }
}
