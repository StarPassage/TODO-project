package davem.controllers;

import davem.Main;
import davem.models.Task;
import davem.request.TaskRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private Main mainApp;
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    public TableView<Task> taskTableView = new TableView<>();
    @FXML
    public TableColumn<Task, Long> taskIdColumn;
    @FXML
    public Button deleteTaskButton;
    @FXML
    public Button editTaskButton;
    @FXML
    public Button addTaskButton;
    @FXML
    public Text descriptionText;
    @FXML
    public Text tagsText;
    @FXML
    public Text categoriesText;

    @FXML
    public void initialize(){
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        showTask(null);
        taskTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTask(newValue)
        );
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        refreshTasks();
    }

    public void refreshTasks() {
        try {
            tasks.clear();
            tasks.addAll(TaskRequest.getAllTasks());
            taskTableView.setItems(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTask(Task task) {
        if (task != null) {
            descriptionText.setText(task.getDescription());
            tagsText.setText(task.getTags().toString());
            categoriesText.setText(task.getCategories().toString());
        } else {
            descriptionText.setText("");
            tagsText.setText("");
            categoriesText.setText("");
        }
    }
}
