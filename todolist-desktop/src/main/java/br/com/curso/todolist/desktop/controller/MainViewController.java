package br.com.curso.todolist.desktop.controller;

import br.com.curso.todolist.desktop.model.Tarefa;
import br.com.curso.todolist.desktop.service.TarefaApiService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private TableView<Tarefa> tabelaTarefas;
    @FXML
    private TableColumn<Tarefa, Long> colunaId;
    @FXML
    private TableColumn<Tarefa, String> colunaDescricao;
    @FXML
    private TableColumn<Tarefa, Boolean> colunaConcluida;
    @FXML
    private TextField campoDescricao;

    private TarefaApiService tarefaApiService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tarefaApiService = new TarefaApiService();
        configurarTabela();
        carregarTarefas();
    }

    private void configurarTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaConcluida.setCellValueFactory(new PropertyValueFactory<>("concluida"));

        // Renderiza a coluna "Concluída" como um CheckBox
        colunaConcluida.setCellFactory(CheckBoxTableCell.forTableColumn(colunaConcluida));

        // Adiciona uma coluna de "Ações" com um botão de deletar
        TableColumn<Tarefa, Void> colunaAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Tarefa, Void>, TableCell<Tarefa, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnDeletar = new Button("Deletar");

            {
                btnDeletar.setOnAction(event -> {
                    Tarefa tarefa = getTableView().getItems().get(getIndex());
                    deletarTarefa(tarefa);
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDeletar);
                }
            }
        };
        colunaAcoes.setCellFactory(cellFactory);
        tabelaTarefas.getColumns().add(colunaAcoes);
    }

    private void executarEmBackground(Runnable acao) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                acao.run();
                return null;
            }
        };
        task.setOnFailed(e -> task.getException().printStackTrace());
        new Thread(task).start();
    }

    @FXML
    void adicionarTarefa() {
        String descricao = campoDescricao.getText();
        if (descricao == null || descricao.trim().isEmpty()) {
            // Opcional: mostrar um alerta para o usuário
            return;
        }

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setDescricao(descricao);
        novaTarefa.setConcluida(false);

        executarEmBackground(() -> {
            try {
                Tarefa tarefaAdicionada = tarefaApiService.adicionarTarefa(novaTarefa);
                Platform.runLater(() -> {
                    tabelaTarefas.getItems().add(tarefaAdicionada);
                    campoDescricao.clear();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void marcarComoConcluida() {
        Tarefa tarefaSelecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada == null) {
            return;
        }

        tarefaSelecionada.setConcluida(true);

        executarEmBackground(() -> {
            try {
                tarefaApiService.atualizarTarefa(tarefaSelecionada);
                Platform.runLater(() -> tabelaTarefas.refresh());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void carregarTarefas() {
        executarEmBackground(() -> {
            try {
                var tarefas = tarefaApiService.listarTarefas();
                Platform.runLater(() -> tabelaTarefas.getItems().setAll(tarefas));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void deletarTarefa(Tarefa tarefa) {
        executarEmBackground(() -> {
            try {
                tarefaApiService.deletarTarefa(tarefa.getId());
                Platform.runLater(() -> tabelaTarefas.getItems().remove(tarefa));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}