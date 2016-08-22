package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by hdrcruz on 22/08/2016.
 */
public class LeitorTxt {
    int[][] dados;

    public Matriz readTxt(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader(file)).useDelimiter("]");
        String str = scanner.next().replace("[", " ");
        String[] strin = str.split("  | ");
        dados = new int[strin.length-1][strin.length-1];
        for (int j = 1; j < strin.length; j++) {

            dados[0][j-1] = Integer.parseInt(strin[j]);
        }
        for (int i = 1; i < strin.length; i++) {
            str = scanner.next().replace("[", " ");
            strin = str.split("  | ");
            for (int j = i; j < strin.length; j++) {
                dados[i][j-1] = Integer.parseInt(strin[j]);
            }
        }
        Matriz m = new Matriz(dados);
        return m;
    }
}
