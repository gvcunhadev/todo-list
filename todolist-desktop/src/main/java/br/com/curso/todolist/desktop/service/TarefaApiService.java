package br.com.curso.todolist.desktop.service;

import br.com.curso.todolist.desktop.model.Tarefa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class TarefaApiService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String API_URL = "http://localhost:8080/api/tarefas";

    public List<Tarefa> listarTarefas() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        }
        // Em um app real, seria melhor lançar uma exceção customizada aqui
        return Collections.emptyList();
    }

    public Tarefa adicionarTarefa(Tarefa novaTarefa) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(novaTarefa);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return objectMapper.readValue(response.body(), Tarefa.class);
        }
        return null;
    }

    public void atualizarTarefa(Tarefa tarefa) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(tarefa);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/" + tarefa.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deletarTarefa(Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/" + id))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}