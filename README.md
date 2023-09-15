# Sistema de Gerenciamento de Vendas

Este é um sistema simples de gerenciamento de vendas, desenvolvido em Kotlin, que permite realizar operações relacionadas a clientes, vendedores, produtos e vendas. O sistema possui menus interativos para cada uma dessas áreas.

## Configuração do Banco de Dados

Certifique-se de ter um banco de dados PostgreSQL configurado e atualizado. 
Você pode configurar as informações de conexão com o banco de dados no arquivo `config.properties`.

```properties
db.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
db.usuario=seu_usuario
db.senha=sua_senha
```

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

### Menu Principal

- A área de menu oferece as seguintes opções:

    1. Área para Cliente
    2. Área para Vendedor
    3. Área para Venda
    4. Área para Produto
    5. Sair

### Área para Cliente

- A Área para Cliente oferece as seguintes opções:

    1. Adicionar Cliente
    2. Deletar Cliente
    3. Atualizar Cliente
    4. Listar Clientes
    5. Buscar Cliente por ID
    6. Buscar Cliente com o email zup.com.br
    7. Voltar ao menu principal

### Área para Vendedor

- A Área para Vendedor oferece as seguintes opções:

    1. Adicionar Vendedor
    2. Deletar Vendedor
    3. Atualizar Vendedor
    4. Listar Vendedores
    5. Buscar Vendedor por ID
    6. Salário dos vendedores de forma decrescente
    7. Voltar ao menu principal

### Área para Produto

- A Área para Produto oferece as seguintes opções:

    1. Adicionar Produto
    2. Deletar Produto
    3. Atualizar Produto
    4. Listar Produtos
    5. Buscar Produto por ID
    6. Voltar ao menu principal

### Área para Venda

- A Área para Venda oferece as seguintes opções:

    1. Adicionar Venda
    2. Deletar Venda
    3. Atualizar Venda
    4. Listar Vendas
    5. Buscar Venda por ID
    6. Itens que foram vendidos acima de 10,00
    7. Voltar ao menu principal

## Requisitos

- Este sistema foi desenvolvido em Kotlin e requer um ambiente de execução compatível com Kotlin.
- É necessário configurar a conexão com um banco de dados para que o sistema funcione corretamente.
