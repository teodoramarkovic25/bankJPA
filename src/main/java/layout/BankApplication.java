package layout;

import entity.BankAccount;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BankApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("Hello" );
        StackPane root = new StackPane();
        root.getChildren().add(button);
        ChoiceBox<BankAccount> fromBankAccountChoiceBox;

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Bank Account");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
