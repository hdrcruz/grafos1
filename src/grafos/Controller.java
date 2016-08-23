package grafos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    LeitorTxt leitor;
    private Grafo grafo;

    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private ComboBox comboBox;


    public Controller() throws FileNotFoundException {
        leitor = new LeitorTxt();
        this.grafo = new Grafo();
    }

    public void showAdjacencia() throws FileNotFoundException {
        textArea.clear();
        Matriz m = grafo.getMatrizAdjacencia();
        textArea.setText(m.toString());
    }

    public void showIncidencia(){

        textArea.clear();
        Matriz m = grafo.getMatrizIncidencia();
        textArea.setText(m.toString());
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
        textArea.appendText(m.polinomioCaracteristico());
    }

    public void showVerticeInfo(){
        Vertice v = (Vertice) comboBox.getSelectionModel().getSelectedItem();
        StringBuilder str = new StringBuilder();
        str.append("Vertice " + v.getNome() + ":\n");
        str.append("Grau: " + v.getGrau() + "\n");
        str.append("Vertices Adjacentes: { " + grafo.verticesAdjacentes(v).replace(" ", ", ") + " }\n");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vertice Info");
        alert.setHeaderText(null);
        alert.setContentText(str.toString());
        alert.showAndWait();

    }

    public void openFile() throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        if(file!=null){
            grafo.clearGrafo();
            grafo.laplaciana2Grafo(leitor.readTxt(file));
            textField.setText(grafo.getFormulaMatematica());
            ObservableList<Vertice> listavertices = FXCollections.observableArrayList(grafo.getVertices());
            comboBox.setItems(null);
            comboBox.setItems(listavertices);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ARQUIVO NULO");
            alert.setHeaderText(null);
            alert.setContentText("Arquivo escolhido é nulo!");
            alert.showAndWait();
        }
    }

    public void showEuleriano(){
        StringBuilder str = new StringBuilder();
        if (grafo.hasCaminhoEuleriano()) str.append("O grafo possui Caminho Euleriano!\n");
        if (grafo.hasCircuitoEuleriano()) str.append("O grafo possui Circuito Euleriano!\n");
        if (str.length() == 0) str.append("O grafo não possui caminho, nem circuito Euleriano!\n");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Caminho/Circuito Euleriano");
        alert.setHeaderText(null);
        alert.setContentText(str.toString());
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
