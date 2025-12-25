# 📍 Endpoits

**Response padrão:**

````
{
    "status": "success"
    "data": []
}
````




## 👤 Usuários

- GET `/api/v1/users`

    Buscar todos usuários.


- GET `/api/v1/users/{id}`

    Buscar usuário por ID.


- GET `/api/v1/users/document/{document}`

    Buscar usuário por documento (CPF/CNPJ).


- POST `/api/v1/users`
    
    Criar usuário.

    ````
    POST /api/v1/users
    
    {
        "firstName": "Filipe",
        "lastName": "Martins Andrade",
        "email": "filipe@gmail.com",
        "document": "12312312312",
        "password": "password",
        "balance": 1000.99,
        "userType": "COMMON"
    }
    ````

- PUT `/api/v1/users/{id}`

    Substituir usuário existente.

    ````
    PUT /api/v1/users/1
    
    {
        "firstName": "Filipe",
        "lastName": "Martins",
        "email": "filipe2@gmail.com",
        "document": "12312312312",
        "password": "password",
        "balance": 1000.99,
        "userType": "MERCHANT"
    }
    ````

- DELETE `/api/v1/users/{id}`

## 💸 Transações

- GET `/api/v1/transactions`

    Buscar todas as transações.


- GET `/api/v1/transactions/{id}`

    Buscar transação por ID;



- POST  `/api/v1/transactions/transfer`

    Solicitar transação.

    ````
    POST /api/v1/transactions/transfer
  
    {
        "sender": 1,
        "receiver": 2,
        "amount": 350.99
    }
    ````
