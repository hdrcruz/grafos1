package grafos.controller;

import grafos.model.Aresta;
import grafos.model.Grafo;
import grafos.model.Vertice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hdrcruz on 25/08/16.
 */
public class EditVerticeController implements Initializable{

    private Grafo grafo;

    @FXML
    TextField qtdVertices;
    @FXML
    ComboBox cmbOrigem;
    @FXML
    ComboBox cmbDestino;
    @FXML
    TextArea listaArestas;


    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void showMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vertice Info");
        alert.setHeaderText(null);
        alert.setContentText("TESTE");
        alert.showAndWait();

    }

    public void criarVertices(){
        grafo.clearGrafo();
        double lol = Double.parseDouble(qtdVertices.getText());
        for (int i = 0; i < (int)lol; i++) {
            Vertice v = new Vertice(i+1);
            grafo.addVertice(v);
        }
        ObservableList<Vertice> listavertices = FXCollections.observableArrayList(grafo.getVertices());
        cmbOrigem.setItems(null);
        cmbDestino.setItems(null);
        cmbOrigem.setItems(listavertices);
        cmbDestino.setItems(listavertices);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Grafo Inserido");
        alert.setHeaderText(null);
        alert.setContentText("Grafo com " + listavertices.size() + " vertice(s) criado com sucesso!");
        alert.showAndWait();
    }

    public void criarArestas(){
        if (cmbOrigem.getSelectionModel().getSelectedIndex() != cmbDestino.getSelectionModel().getSelectedIndex()){
            Vertice origem = (Vertice) cmbOrigem.getSelectionModel().getSelectedItem();
            Vertice destino = (Vertice) cmbDestino.getSelectionModel().getSelectedItem();
            Aresta a = new Aresta(origem.getNome(),destino.getNome());
            grafo.addAresta(a);
            listaArestas.appendText(a.toString());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText("Origem e Destino da aresta devem ser diferentes!");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
