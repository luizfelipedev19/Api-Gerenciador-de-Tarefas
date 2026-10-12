API de Gerenciamento de Tarefas
API REST desenvolvida em Java com Spring Boot para gerenciamento de tarefas, com autenticação JWT e proteção de endpoints por meio do Spring Security.
O projeto tem como objetivo aplicar conceitos de desenvolvimento backend, segurança, boas práticas e arquitetura em camadas.
Funcionalidades
- Cadastro de usuários.
- Autenticação de usuários com JWT.
- Criação de tarefas.
- Listagem de tarefas do usuário autenticado.
- Atualização do status das tarefas.
- Exclusão de tarefas específicas.
- Proteção de rotas com Spring Security.
Tecnologias utilizadas
- Java 21
- Spring Boot 3.2.5
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL / PostgreSQL
- Maven
- Git
Arquitetura do projeto
O projeto segue uma arquitetura em camadas, com as seguintes responsabilidades:
- Controller: recebe requisições HTTP e retorna respostas ao cliente.
- Service: implementa as regras de negócio.
- Repository: realiza o acesso ao banco de dados.
- DTOs: estruturam os dados transferidos entre os componentes da aplicação.
- Security: configura a autenticação e a autorização com JWT.
- Exception Handler: centraliza o tratamento de exceções.
Autenticação
A API utiliza autenticação baseada em JWT. Após o login, o cliente recebe um token que deve acompanhar as requisições aos endpoints protegidos.
Fluxo de autenticação
1. O usuário realiza o cadastro.
2. Envia suas credenciais para efetuar o login.
3. A API valida as credenciais e retorna um token JWT.
4. O cliente envia o token no cabeçalho Authorization das requisições protegidas.
Cabeçalho obrigatório
Authorization: Bearer <token>
