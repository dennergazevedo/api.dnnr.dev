# Authentication API [dnnr.dev]

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Este projeto é uma API criada usando Java, Java Spring, Flyway Migrations, PostgreSQL como banco de dados e Spring Security e JWT para autenticação.

Essa API é um estudo do Spring e será usada no meu site [dnnr.dev](https://social.dnnr.dev/) para utilizar algumas ferramentas privadas.

## Conteúdo
- [Instalação](#instalação)
- [Como usar](#como-usar)
- [API Endpoints](#api-endpoints)
- [Autenticação](#autenticação)
- [Database](#database)

## Instalação

1. Clone o repositório

```bash
git clone https://github.com/dennergazevedo/api.dnnr.dev
```
2. Instale as dependências do Maven;

3. Instale o [PostgresSQL](https://www.postgresql.org/)


## Como Usar

1. Inicie a aplicação com o Maven;
2. A aplicação será acessível pela porta 8080 http://localhost:8080

## API Endpoints
The API provides the following endpoints:

### Auth
```markdown
POST /auth/register - Criar um novo usário
POST /auth/login - Fazer login na aplicação
```

### User
```markdown
DELETE /api/users?id={string} - Deletar um usuário
```

### To-Do
```markdown
GET /tools/todos - Listar TodoList do usuário logado
POST /tools/todos - Criar um item na TodoList
DELETE /tools/todos?id={string} - Deletar um item da TodoList
```

### Chronometer
```markdown
POST /tools/chronometer - Criar um novo cronômetro
GET /tools/chronometer - Listar cronômetros do usuário logado
DELETE /tools/chronometer?id={string} - Deletar um cronômetro
```

## Autenticação
A API usa o Spring Security para controlar a autenticação, abaixo são as regras disponíveis atualmente:

```
USER -> Usuários padrões
ADMIN -> Usuários Administradores
```
O acesso do usuário é controlado no momento da criação do usuário.

## Database
O Projeto utiliza [PostgresSQL](https://www.postgresql.org/) como base de dados.