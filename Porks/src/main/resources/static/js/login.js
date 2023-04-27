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
        success: function (response, status) {
            console.log(status)
            sessionStorage.setItem("token", response.token);
            window.location.href = 'index.html';
        },
        error: function (response) {
            Swal.fire({
                icon: 'error',
                title: 'Erro de autenticação',
                text: 'O nome de usuário ou senha estão incorretos. Verifique suas credenciais e tente novamente',
                footer: '<a href="">Esqueceu sua senha?</a>'
            })
        }
    });
}