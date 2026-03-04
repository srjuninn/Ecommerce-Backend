
---

# 📦 Ecommerce Backend

Este projeto é um backend para um sistema de e-commerce, desenvolvido em **Java + Spring Boot**, utilizando **JPA/Hibernate** para persistência e **DTOs** para transferência de dados entre cliente e servidor.

## 🚀 Tecnologias utilizadas
- Java 25+
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- Spring DevTools
- MySQL
- Lombok

## 📂 Estrutura do projeto
```
src/main/java/com/projeto/ecommerce
│
├── controllers        # Controllers (camada de entrada da API)
├── entities           # Entidades JPA (representam tabelas do banco)
├── repositories       # Interfaces de acesso ao banco (JpaRepository)
├── requests           # DTOs de entrada (UserRequestDTO)
├── responses          # DTOs de saída (UserResponseDTO)
└── services           # Regras de negócio (UserService)
```

## 🔑 Endpoints disponíveis

### 1. Criar usuário
```http
POST /user/create
```
**Body (JSON):**
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "11999999999",
  "password": "123456",
  "roles": "USER"
}
```

### 2. Buscar usuário por ID
```http
GET /user/show/{id}
```
**Response:**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "11999999999"
}
```

### 3. Atualizar usuário
```http
PUT /user/update/id/{id}
```
**Body (JSON):**
```json
{
  "name": "Maria Oliveira",
  "email": "maria@email.com",
  "phone": "11988887777",
  "password": "novaSenha123",
  "roles": "ADMIN"
}
```

### 4. Deletar usuário
```http
DELETE /user/delete/id/{id}
```
**Response:**
```json
"usuário deletado com sucesso!"
```

### 🌐1. Criar Produto
`POST /products/create`

**Body:**
```json
{
  "name": "Notebook Gamer",
  "description": "RTX 4060, 16GB RAM",
  "price": 5999.90,
  "imgURL": "http://imagem.com/notebook.png"
}
```

**Resposta:**
```json
{
  "name": "Notebook Gamer",
  "description": "RTX 4060, 16GB RAM",
  "price": 5999.90
}
```

---

### 2. Consultar Produto por ID
`GET /products/show/id/{id}`

**Resposta:**
```json
{
  "name": "Notebook Gamer",
  "description": "RTX 4060, 16GB RAM",
  "price": 5999.90
}
```

---

### 3. Atualizar Produto
`PUT /products/update/id/{id}`

**Body:**
```json
{
  "name": "Notebook Gamer Atualizado",
  "description": "RTX 4070, 32GB RAM",
  "price": 7999.90,
  "imgURL": "http://imagem.com/notebook2.png"
}
```

**Resposta:**
```json
{
  "name": "Notebook Gamer Atualizado",
  "description": "RTX 4070, 32GB RAM",
  "price": 7999.90
}
```

---

### 4. Deletar Produto
`DELETE /products/delete/id/{id}`

**Resposta:**
```json
{
  "message": "produto deletado com sucesso!"
}
```



## ⚙️ Como rodar o projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/srjuninn/Ecommerce-Backend.git
   ```
2. Entre na pasta do projeto:
   ```bash
   cd Ecommerce-Backend
   ```
3. Compile e rode:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse a API em:
   ```
   http://localhost:8080
   ```

Ou, se preferir, abra o projeto no **IntelliJ IDEA**, rode a classe `EcommerceApplication` e teste os endpoints no **Postman** ou **Insomnia**.

---

## 🛡️ Tratamento de erros
A API retorna mensagens claras em caso de erro:
- **404 Not Found** → Usuário não encontrado.
- **409 Conflict** → Email já cadastrado.
- **400 Bad Request** → Dados inválidos.

Exemplo de resposta de erro:
```json
{
  "error": "Usuário não encontrado com id: 99"
}
```

---

## 📌 Próximos passos
- Implementar autenticação e autorização (Spring Security + JWT).
- Criar endpoints para pedidos.
- Documentar a API com Swagger/OpenAPI.

---