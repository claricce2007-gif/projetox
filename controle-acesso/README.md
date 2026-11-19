# Sistema de Controle de Acesso

Projeto acadêmico de back-end com Java e Spring Boot.

## Requisitos
- JDK 21
- Maven (ou Maven Wrapper gerado pelo ambiente)

## Como executar
Na pasta do projeto, execute:

```bash
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Endpoints

### 1. Cadastrar usuário
`POST /api/usuarios`

Corpo JSON:
```json
{
  "nome": "Maria",
  "senha": "senha123"
}
```

### 2. Verificar acesso
`POST /api/acesso`

Corpo JSON:
```json
{
  "senha": "senha123"
}
```

A resposta será `Acesso permitido` se a senha corresponder a algum usuário cadastrado; caso contrário, será `Acesso negado`.

### 3. Listar usuários
`GET /api/usuarios`

Retorna os nomes cadastrados.

## Observação
Esta primeira versão mantém os usuários apenas na memória. Ao reiniciar a aplicação, os cadastros são apagados. As senhas são guardadas como hash BCrypt. Para um sistema real, ainda será necessário adicionar persistência em banco de dados e autenticação mais completa.
