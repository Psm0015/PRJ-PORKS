# PRJ-PORKS
#APIS

Convidado{
 GET : http://localhost:8080/convidado/listall -- Lista todos os Produtos
}




Admin{
 PUT : http://localhost:8080/admin/editarprd -- Editar Produto
 Body Json[
  {
	  "id":1552,
	  "nome":"teste1",
	  "ingredientes":"teste1"
  }
 ]
 
 
 
 
 POST : http://localhost:8080/admin/cadprd -- Cadastrar Produto
 Body Json[
  {
	  "nome":"teste",
	  "ingredientes":"teste"
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
	    "nome":"Pedro Mota2",
	    "email":"pedro.mota2@df.estudante.senai.br",
	    "cpf":"074520561592",
	    "nascimento":"2003-06-29"
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
	    "cpf":"07452056159",
	    "senha":"123010203"
    }
   }
   
   
   
   POST : http://localhost:8080/auth/registrar -- Se Registra
   Body Json{
    {
	    "nome":"Pedro Mota",
	    "email":"pedro.mota@df.estudante.senai.br",
	    "cpf":"07452056159",
	    "nascimento":"2003-06-29",
	    "senha":"123010203"
    }
   }
   
   
}
