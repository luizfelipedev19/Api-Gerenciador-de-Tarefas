# 📋 API de Gerenciamento de Tarefas

API REST desenvolvida em **Java com Spring Boot**, focada no gerenciamento de tarefas, utilizando **autenticação JWT** para garantir segurança no acesso aos endpoints.

Este projeto foi criado com o objetivo de praticar conceitos de **backend**, **segurança**, **boas práticas** e **arquitetura em camadas**.

---

## 🚀 Funcionalidades

- 👤 Cadastro de usuários
- 🔐 Autenticação de usuários com **JWT**
- 🧾 Criação de tarefas
- 📄 Listagem de tarefas do usuário autenticado
- 🔄 Atualização de status das tarefas
- 🗑️ Exclusão de tarefas específicas
- 🔒 Proteção de rotas com Spring Security

---

## 🧠 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.5
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL / PostgreSQL
- Maven
- Git

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

- **Controller** → Camada responsável pelas requisições HTTP
- **Service** → Regra de negócio
- **Repository** → Acesso ao banco de dados
- **DTOs** → Transferência de dados entre camadas
- **Security** → Configuração de autenticação e autorização com JWT
- **Exception Handler** → Tratamento global de exceções

---

## 🔐 Autenticação

A API utiliza autenticação baseada em **JWT**.

### Fluxo de autenticação:
1. O usuário realiza o cadastro
2. Efetua o login
3. A API retorna um **token JWT**
4. O token deve ser enviado no header das requisições protegidas:

```http
Authorization: Bearer <token>
