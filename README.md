# Pessoa-Endereco-Api

### API simples para gerenciar Pessoas. Esta API deve permitir:  
    • Criar uma pessoa
    • Editar uma pessoa
    • Consultar uma pessoa
    • Listar pessoas
    • Criar endereço para pessoa
    • Listar endereços da pessoa
    • Poder informar qual endereço é o principal da pessoa  

### Uma Pessoa deve ter os seguintes campos:  
    • Nome
    • Data de nascimento
    • Endereço:
        ◦ Logradouro
        ◦ CEP
        ◦ Número
        ◦ Cidade

### Requisitos  
    • Todas as respostas da API devem ser JSON  
    • Banco de dados H2

### Instruções para o uso da API

> POST: http://localhost:8080/pessoas/cadastrarPessoa -> Cadastra nova pessoa e seu(s) endereco(s)

> DELETE: http://localhost:8080/pessoas/{id} -> Deleta uma pessoa. Necessario informar o {id} da pessoa!

> GET: http://localhost:8080/pessoas/listarPessoas -> Retorna uma lista de pessoas. 

> GET: http://localhost:8080/pessoas/{id} -> Retorna uma pessoa. Necessario informar o {id} da pessoa!

> PUT: http://localhost:8080/pessoas/{id} -> Atualiza uma pessoa. Necessario informar o {id} da pessoa!

> GET: http://localhost:8080/pessoas/listarEnderecos -> Retorna uma lista de enderecos de cada pessoa. 

### Observação

Todas as requisicoes podem ser testadas via Postman (https://www.postman.com/)
