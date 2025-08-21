package br.com.curso.todolist.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Carrega o FXML da pasta resources
        URL fxmlLocation = getClass().getResource("MainView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        // Cria a cena com o layout carregado do FXML
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gerenciador de Tarefas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}