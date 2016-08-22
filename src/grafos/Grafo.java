package grafos;

import java.util.ArrayList;

/**
 * Created by hdrcruz on 19/08/2016.
 */
public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;


    public Grafo(ArrayList<Vertice> vertices, ArrayList<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public Vertice getVertice(int index){
        return vertices.get(index);
    }

    public Boolean isAdjacente(Vertice v1, Vertice v2){
        for (Aresta a: arestas) {
            if (((a.getOrigem() == v1.getNome()) && (a.getDestino() == v2.getNome())) || ((a.getOrigem() == v2.getNome()) && (a.getDestino() == v1.getNome()))) {
                return true;
            }
        }
        return false;
    }

    public void addAresta(Aresta a){
        vertices.get(a.getOrigem()-1).aumentarGrau();
        vertices.get(a.getDestino()-1).aumentarGrau();
        arestas.add(a);
    }

    public void addVertice(Vertice v){
        vertices.add(v);
    }

    public Aresta getAresta(Aresta a){
        if (arestas.contains(a)){
            int index = arestas.indexOf(a);
            return arestas.get(index);
        }
        return null;
    }

    public Matriz getMatrizAdjacencia(){
        int[][] madj = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i; j < vertices.size(); j++) {
                if (this.isAdjacente(vertices.get(i),vertices.get(j))){
                    madj[i][j] = 1;
                    madj[j][i] = 1;
                }
            }
        }
        Matriz adjacencia = new Matriz(madj);
        return adjacencia;
    }

    public Matriz getMatrizIncidencia(){
        int[][] mincid = new int[vertices.size()][arestas.size()];
        for (int i = 1; i <= arestas.size(); i++) {
            mincid[arestas.get(i-1).getOrigem()-1][i-1] = 1;
            mincid[arestas.get(i-1).getDestino()-1][i-1] = 1;
        }
        Matriz incidencia = new Matriz(mincid);
        return incidencia;
    }

    public Matriz getMatrizDiagonal(){
        int [][] mdiag = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            mdiag[i][i] = vertices.get(i).getGrau();
        }
        Matriz diagonal = new Matriz(mdiag);
        return diagonal;
    }

    public Matriz getMatrizLaplaciana(){
        Matriz diagonal = this.getMatrizDiagonal();
        Matriz adjacencia = this.getMatrizAdjacencia();
        Matriz laplaciana = diagonal.subMatriz(adjacencia);
        return laplaciana;
    }

    public void laplaciana2Grafo(Matriz laplaciana){
        for (int i = 0; i < laplaciana.getLinhas(); i++) {
            Vertice vertice = new Vertice(i+1);
            this.addVertice(vertice);
        }
        for (int i = 0; i <laplaciana.getLinhas() ; i++) {
            for (int j = i; j < laplaciana.getLinhas() ; j++) {
                if (laplaciana.getElemento(i,j) == -1){
                    Aresta aresta = new Aresta(i+1,j+1);
                    this.addAresta(aresta);
                }
            }
        }
    }

    public boolean hasCaminhoEuleriano(){
        int impares = 0;
        for (Vertice v: vertices) {
            if (v.getGrau()%2!=0) impares++;
        }
        return impares < 3;
    }

    public boolean hasCircuitoEuleriano(){
        for (Vertice v: vertices) {
            if (v.getGrau()%2!=0) return false;
        }
        return true;
    }

    public String verticesAdjacentes(Vertice ver){
        StringBuilder str = new StringBuilder();
        for (Aresta a: arestas) {
            if (ver.getNome() == a.getOrigem()) str.append(a.getDestino() + " ");
            if (ver.getNome() == a.getDestino()) str.append(a.getOrigem() + " ");
        }
        return str.toString().trim();
    }





}
