REST API

Adicionar Jogador
POST - localhost:8080/jogadores - JSON { "nome": "Daniel" }

Listar Jogadores
GET - localhost:8080/jogadores

Alterar Jogador 
PUT - localhost:8080/jogadores/{id}

Remover Jogador 
DELETE - localhost:8080/jogadores/{id}

Para jogar adicione 3 jogadores em "localhost:8080/jogadores" - POST, faça um GET e copie a lista de jogadores. 

Para realizar uma jogada, envie uma lista e realize um POST em "localhost:8080/jogadas". 
Exemplo:
[
	{
		"id":"1",
		"nome":"Daniel"
	},{
		"id":"2",
		"nome":"Felipe"
	},{
		"id":"3",
		"nome":"Davi"
	}	
]

Regras da API:
 - Retorna uma lista de jogadores com as respectivas jogadas e o vencedor.

 - Se dois jogadores realizarem a mesma jogada, ou jogadas que uma elimine a outra, jogue jovamente. 

