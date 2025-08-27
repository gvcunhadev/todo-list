
# Projeto Todo-List (Full-Stack Multiplataforma)

## 🎯 Objetivo

Desenvolver uma aplicação completa para gerenciar uma lista de tarefas (Todo List), demonstrando uma arquitetura de micro-serviços com um backend central e múltiplos clientes. O projeto consiste em:

1.  **Backend REST API**: Um servidor robusto feito em Spring Boot que centraliza todas as regras de negócio e o acesso aos dados.
2.  **Cliente Web**: Uma aplicação moderna e reativa (SPA) feita em Angular.
3.  **Cliente Desktop**: Uma aplicação nativa e funcional feita com JavaFX.

## ✨ Features

* **Gerenciamento de Tarefas**: Funcionalidades completas de CRUD (Criar, Ler, Atualizar, Deletar).
* **API RESTful**: Backend desacoplado que serve os dados para múltiplos clientes de forma consistente.
* **Cliente Web Reativo**: Interface web fluida, sem recarregamento de página.
* **Cliente Desktop Nativo**: Experiência de uso integrada ao sistema operacional.
* **Sincronização Manual**: Botão de "Atualizar" no cliente desktop para buscar as últimas alterações feitas por outros clientes (como o da web).

## 🛠️ Tecnologias Utilizadas

| Camada | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Backend (API)** | Java 17, Spring Boot | Criação da API REST e regras de negócio. |
| | Spring Data JPA | Persistência de dados de forma simplificada. |
| | H2 Database | Banco de dados em memória para desenvolvimento. |
| | Maven | Gerenciador de dependências e build do projeto Java. |
| **Frontend (Web)** | Angular, TypeScript | Construção da interface de usuário (SPA). |
| | HTML & CSS | Estrutura e estilização da aplicação. |
| **Frontend (Desktop)** | JavaFX | Criação da interface gráfica nativa. |
| | Scene Builder | Ferramenta visual para design das telas (`.fxml`). |
| | Maven | Gerenciador de dependências e build. |

## 📁 Estrutura do Projeto

O projeto é dividido em três pastas principais, cada uma representando uma parte da aplicação:

1.  `todo-list-api/`: Contém todo o código-fonte do backend (servidor).
2.  `todo-list-web/`: Contém todo o código-fonte do cliente web.
3.  `todo-list-desktop/`: Contém todo o código-fonte do cliente desktop.

## 🚀 Como Executar a Aplicação Completa

Siga os passos abaixo para executar todo o ecossistema do projeto em seu ambiente local.

### Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas:
* [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versão 21 ou superior)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Node.js e npm](https://nodejs.org/en/) (para o cliente web)
* [Angular CLI](https://angular.io/cli) (`npm install -g @angular/cli`) (para o cliente web)
* Uma IDE Java como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/downloads/) (recomendado para o cliente desktop)

---

### Passo a Passo

**1. Clone o Repositório**
```
git clone https://github.com/gvcunhadev/todo-list
cd todo-list
```
2. Execute o Backend (API)
O backend deve estar em execução para que os clientes web e desktop funcionem.

Abra um terminal na pasta do projeto.

```

# Navegue até o diretório da API
cd todo-list-api

# Compile o projeto e inicie o servidor Spring Boot
mvn spring-boot:run

```
O backend estará em execução em http://localhost:8080. Mantenha este terminal aberto.

3. Execute o Cliente Web (Angular)

Abra um novo terminal.
```
# Navegue até o diretório da aplicação web
cd todo-list-web

# Instale as dependências
npm install

# Inicie o servidor de desenvolvimento
ng serve
A aplicação web estará acessível no seu navegador em http://localhost:4200.
```

4. Execute o Cliente Desktop (JavaFX)

A forma mais simples de executar a aplicação desktop é através de uma IDE Java.

Abra sua IDE (ex: IntelliJ IDEA).

Vá em File > Open... e selecione a pasta todo-list-desktop para abri-la como um novo projeto.

Aguarde a IDE carregar e baixar as dependências do Maven.

Navegue no painel de projeto até o arquivo:
src/main/java/br/com/curso/todo-list/desktop/MainApp.java

Clique com o botão direito sobre o arquivo MainApp.java e selecione "Run 'MainApp.main()'".

A janela da aplicação desktop irá aparecer na sua tela, já conectada à API.

Agora você pode usar tanto a versão web quanto a desktop para gerenciar sua lista de tarefas e ver como o botão "Atualizar" do cliente desktop busca as mudanças feitas pela web!

👨‍💻 Desenvolvido por
Gabriela Viana Cunha
