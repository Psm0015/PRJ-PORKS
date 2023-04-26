function fazerlogin() {
    var cpf = document.getElementById('email-login').value;
    var senha = document.getElementById('senha-login').value;
    
    const data = JSON.stringify({
        "cpf": cpf,
        "senha": senha
      });
      
      $.ajax({
          type: "POST",
          url: "auth/login",
          data: data,
          contentType: "application/json",
          dataType: "json",
          success: function(response) {
              sessionStorage.setItem("token",response.token);
              window.location.href='index.html';
          }
      });
}