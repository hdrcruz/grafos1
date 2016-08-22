package grafos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    LeitorTxt leitor;
    private Grafo grafo;
    Matriz laplaciana;

    @FXML
    private TextArea textArea;


    public Controller() throws FileNotFoundException {

        leitor = new LeitorTxt();
        this.grafo = new Grafo();
        grafo.laplaciana2Grafo(leitor.readTxt());
    }

    public void showAdjacencia() throws FileNotFoundException {
        textArea.clear();
        Matriz m = grafo.getMatrizAdjacencia();
        textArea.setText(m.toString());
        leitor.readTxt();

    }

    public void showIncidencia(){
        textArea.clear();
        Matriz m = grafo.getMatrizIncidencia();
        textArea.setText(m.toString());
        if (grafo.hasCaminhoEuleriano()) textArea.appendText("Possui Caminho Euleriano!\n");
        if (grafo.hasCircuitoEuleriano()) textArea.appendText("Possui Circuito Euleriano!");
    }

    public void showDiagonal(){
        textArea.clear();
        Matriz m = grafo.getMatrizDiagonal();
        textArea.setText(m.toString());
    }

    public void showLaplaciana(){
        textArea.clear();
        Matriz m = grafo.getMatrizLaplaciana();
        textArea.setText(m.toString());
    }

    public void openFile(){
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
