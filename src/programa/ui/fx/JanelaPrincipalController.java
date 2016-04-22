package programa.ui.fx;

import programa.Programa;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class JanelaPrincipalController implements Initializable {
    @FXML
    private Button btFechar, btCliente, btProduto;
    @FXML
    private MenuItem itemClienteInserir, itemClienteControle, itemProdutoInserir, itemProdutoControle;



    public JanelaPrincipalController() {

    }

    public void initialize(URL url, ResourceBundle bundle) {
        btFechar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.exit(0);
                // JOptionPane.showMessageDialog(null, "Mensagem");
            }
        });

        btCliente.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                JanelaPrincipal.uiCliente.listarClientes();
            }
        });

        btProduto.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JanelaPrincipal.uiProduto.listarProdutos();
            }
        });

        itemClienteInserir.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JanelaPrincipal.uiCliente.lerCliente();
            }
        });

        itemClienteControle.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JanelaPrincipal.uiCliente.listarClientes();
            }
        });

        itemProdutoInserir.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JanelaPrincipal.uiProduto.lerProduto();
            }
        });

        itemProdutoControle.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JanelaPrincipal.uiProduto.listarProdutos();
            }
        });


    }
}
