package com.example.projetoacademia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carregar a imagem
        Image image = new Image(HelloApplication.class.getResourceAsStream("/images/gym-logo.jpg"));

        // Configurar a imagem como ícone da janela principal
        stage.getIcons().add(image);

        // Carregar o arquivo FXML e definir o layout raiz
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listAlunoView.fxml"));
        VBox root = fxmlLoader.load();

        // Criar a cena
        Scene scene = new Scene(root, 800, 600);

        // Configurar a cena no palco
        stage.setMaximized(true);
        stage.setTitle("Academia FICR");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
