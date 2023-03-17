# PRJ-PORKS
#APIS

Convidado{
 GET : http://localhost:8080/convidado/listall -- Lista todos os Produtos
}




Admin{
 PUT : http://localhost:8080/admin/editarprd -- Editar Produto
 Body Json[
  {
	  "id":id,
	  "nome":"nome",
	  "ingredientes":"ingredientes"
  }
 ]
 
 
 
 
 POST : http://localhost:8080/admin/cadprd -- Cadastrar Produto
 Body Json[
  {
	  "nome":"nome",
	  "ingredientes":"ingredientes"
  }
 ]
 
 
 
 
 GET : http://localhost:8080/admin/listall -- Listar Usuários
 DEL : http://localhost:8080/admin/apgruser/{id} -- Apagar Usuários
 DEL : http://localhost:8080/admin/apgrprd/{id} -- Apagar Produto
}


Usuário{
  PUT : http://localhost:8080/user/editar -- Editar seu Usuário
  Body Json[
    {
	    "nome":"nome",
	    "email":"email",
	    "cpf":"cpf",
	    "nascimento":"data"
    }
  ]
  
  
  
  
  GET : http://localhost:8080/user/info -- Busca dados do Usuário
  
  GET : http://localhost:8080/user/carrinho -- Mostra Carrinho
  
  POST :  http://localhost:8080/user/carrinho?produtoId={id}&quantidade={id} -- Adiciona Item ao carrinho
}

Autenticação{
  POST : http://localhost:8080/auth/login -- Faz Login
   Body Json{
    {
	    "cpf":"cpf",
	    "senha":"senha"
    }
   }
   
   
   
   POST : http://localhost:8080/auth/registrar -- Se Registra
   Body Json{
    {
	    "nome":"nome",
	    "email":"email",
	    "cpf":"cpf",
	    "nascimento":"data",
	    "senha":"senha"
    }
   }
   
   
}
