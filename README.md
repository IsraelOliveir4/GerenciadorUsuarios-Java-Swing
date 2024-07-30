# Dev Teste JAVA - Germantech Sistemas
* Linguagem: JAVA
* IDE Utilizada: IntelliJ
* Conexão com o banco de dados: JDBC Connection
* Estrutura de pastas: MVC
* Algoritmo para armazenar senhas de forma segura no BD: Bcrypt
* Biblioteca utilizada para criar interface: Swing

## Faça um programa (CRUD) em JAVA com os requisitos abaixo, utilizando um banco de dados para armazenar as informações (preferencialmente PostgreSQL).


O programa deve cadastrar um usuário com as informações de: Nome, telefone, e-mail, CPF e senha.
Deverá existir outra classe chamada "CadastroUsuarios" que seja responsável por gerenciar a lista de usuários. Esta classe deve permitir as seguintes operações:

Faça uma interface para permitir ao usuário escolher entre as operações mencionadas abaixo. A tela poderá ser em JFrame ou como preferir.

1 - Adicionar um novo usuário.
2 - Editar as informações de um usuário existente (com base no CPF).
3 - Excluir um usuário (com base no CPF).
4 - Listar todos os usuários cadastrados.

Além disso, outras funcionalidades necessárias:
A conexão com o banco de dados pode ser feito preferencialmente com: JDBC Connection ou Hibernate;
Organização na estrutura de pastas (em MVC ou alguma estrutura de sua escolha);
Implementação de Interface com os respectivos métodos do CRUD;
As senhas dos usuários devem ser armazenadas de forma segura no banco de dados utilizando hashing (por exemplo, usando bcrypt ou outro algoritmo seguro);
Ao listar usuários, implemente filtros (por nome, email ou parte do CPF) para tornar a consulta mais eficiente e prática.
Deve existir tratamento de exceções adequado para casos como tentar cadastrar CPF's duplicados e/ou usuário não encontrado para edição/exclusão.

Regras básicas:
Adicionar máscara no campo de CPF.
O campo CPF deve ser validado, para permitir apenas CPF's válidos.
Os campos CPF e nome devem ser obrigatórios.
