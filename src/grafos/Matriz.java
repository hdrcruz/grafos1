package grafos;

import java.util.Random;

/**
 * Created by hdrcruz on 19/08/2016.
 */
public class Matriz {
    private int linhas;
    private int colunas;
    private int dados[][];

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        dados = new int[linhas][colunas];
    }

    public Matriz(int dados[][]){
        linhas = dados.length;
        colunas = dados[0].length;
        this.dados = new int[linhas][colunas];
        for (int i = 0; i < linhas ; i++)
            for (int j = 0; j < colunas; j++) this.dados[i][j] = dados[i][j];
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public int[][] getDados() {
        return dados;
    }

    public void setDados(int[][] dados) {
        this.dados = dados;
    }

    public int getElemento(int linha, int coluna){
        return dados[linha][coluna];
    }

    public void setElemento(int linha, int coluna, int valor){
        dados[linha][coluna] = valor;
    }

    @Override
    public String toString() {
        StringBuilder strbuilder = new StringBuilder();
        for (int i = 0; i < linhas; i++) {
            strbuilder.append("[ ");
            for (int j = 0; j < colunas ; j++) {
                strbuilder.append(dados[i][j] + " ");
            }
            strbuilder.append("]\r\n");
        }
        return strbuilder.toString();
    }

    public void printMatriz(){
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas ; j++) {
                System.out.print(dados[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Matriz addMatriz(Matriz b){
        if (b.getLinhas() != linhas || b.getColunas() != colunas) throw new RuntimeException("Matrizes precisam ser de mesma ordem");
        Matriz C = new Matriz(linhas, colunas);
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++)
                C.dados[i][j] = dados[i][j] + b.dados[i][j];
        return C;

    }

    public Matriz subMatriz(Matriz b){
        if (b.getLinhas() != linhas || b.getColunas() != colunas) throw new RuntimeException("Matrizes precisam ser de mesma ordem");
        Matriz C = new Matriz(linhas, colunas);
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++)
                C.dados[i][j] = dados[i][j] - b.dados[i][j];
        return C;

    }

    public void popRandom(int max, int min){
        Random rand = new Random();
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++) dados[i][j] = rand.nextInt((max - min) + 1) + min;
    }

    public int detLaplace() {
        int determinante = 0;
        if (linhas == 1) {
            determinante = dados[0][0];
        } else if (linhas == 2) {
            determinante = dados[0][0] * dados[1][1] - dados[0][1] * dados[1][0];
        } else if (linhas == 3) {
            determinante = dados[0][0] * dados[1][1] * dados[2][2]
                    + dados[0][1] * dados[1][2] * dados[2][0]
                    + dados[0][2] * dados[1][0] * dados[2][1]
                    - dados[0][2] * dados[1][1] * dados[2][0]
                    - dados[0][0] * dados[1][2] * dados[2][1]
                    - dados[0][1] * dados[1][0] * dados[2][2];
        } else {
            Matriz aux;
            int i_aux, j_aux, linha, coluna, i;
            for (i = 0; i < linhas; i++) {
                if (dados[0][i] != 0) {
                    aux = new Matriz(linhas-1,colunas-1);
                    i_aux = 0;
                    j_aux = 0;
                    for (linha = 1; linha < linhas; linha++) {
                        for (coluna = 0; coluna < linhas; coluna++) {
                            if (coluna != i) {
                                aux.dados[i_aux][j_aux] = dados[linha][coluna];
                                j_aux++;
                            }
                        }
                        i_aux++;
                        j_aux = 0;
                    }
                    determinante += Math.pow(-1, i) * dados[0][i] * aux.detLaplace();
                }
            }
        }
        return determinante;
    }


    public Matriz multMatriz(Matriz m){
        int[][] c = new int[linhas][m.getColunas()];
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < m.getColunas(); j++){
                for (int k = 0; k < linhas; k++) {
                    c[i][j] += (dados[i][k] * m.getElemento(k,j));
                }
            }
        }
        Matriz result = new Matriz(c);
        return result;
    }

    public int tracoMatriz(){
        int total = 0;
        for (int i = 0; i < linhas; i++) {
            total += dados[i][i];
        }
        return total;
    }

    public Matriz produtoIdentidade(int valor, int tamanho){
        int[][] c = new int[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if(i == j) c[i][j] = valor;
                else c[i][j] = 0;
            }
        }
        Matriz m = new Matriz(c);
        return m;
    }

    public String polinomioCaracteristico(){


        Matriz a = new Matriz(this.dados);
        int[] p = new int[a.linhas];
        p[0] = -1 * this.tracoMatriz();
        Matriz anterior;
        Matriz aux1;
        Matriz aux2;
        Matriz aux3;
        anterior = new Matriz(a.getDados());

        for (int i = 1; i < a.linhas; i++) {
            aux3 = this.produtoIdentidade(p[i-1], a.linhas);
            aux1 = anterior.addMatriz(aux3);
            aux2 = a.multMatriz(aux1);
            int d = i+1;
            try{
                p[i] = aux2.tracoMatriz()/d;
                p[i] *= -1;
            }catch(Exception x){
                System.out.println("erro");
            }
            anterior = aux2;
        }
        String exibir = "\n\n  Polinômio Característico da Matriz Laplaciana: \n  p(t) = 1 ";
        for (int i = 0; i < a.linhas; i++) {
            exibir = exibir.concat(String.valueOf(p[i])+ "  ");
        }
        return exibir;
    }


}
