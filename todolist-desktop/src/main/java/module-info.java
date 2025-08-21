module br.com.curso.todolistdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    exports br.com.curso.todolist.desktop;

    opens br.com.curso.todolist.desktop.controller to javafx.fxml;

    opens br.com.curso.todolist.desktop.model to com.fasterxml.jackson.databind;
}