package programa.ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import programa.dados.*;
import programa.negocio.Controle;

import java.io.IOException;

public class JanelaPrincipal extends Application {

    public JanelaPrincipal() {
    }

    public static UICliente uiCliente;
    public static UICidade uiCidade;
    public static UIProduto uiProduto;

    public static void main(String args[]) {
        IRepositorioCliente repoCliente = new ClienteDAO();
        IRepositorioCidade repoCidade = new CidadeDAO();
        IRepositorioProduto repoProduto = new ProdutoDAO();

        Controle ctr = new Controle(repoCliente, repoCidade, repoProduto);

        uiCliente = new UICliente(ctr);
        uiCidade = new UICidade(ctr);
        uiProduto = new UIProduto(ctr);
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
        loader.setController(new JanelaPrincipalController());

        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Tela Principal");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
