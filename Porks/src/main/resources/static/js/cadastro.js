function cadastrar() {
    let nomeCompleto = document.getElementById('nome').value + " " + document.getElementById('sobrenome').value;
    let email = document.getElementById('email').value;
    let cpf = document.getElementById('CPF').value;
    let senha = document.getElementById('senha').value;
    let confirmarSenha = document.getElementById('Con-senha').value;
    let nascimento = document.getElementById('birth').value;
    console.log(nascimento)

    if (nomeCompleto === "" || !validarEmail(email) || cpf === "" || senha === "" || confirmarSenha === "" || nascimento === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Por favor, preencha todos os campos!',
            footer: '<a href="">Por que preciso preencher todos os campos?</a>'
        })
        return; // Impede o envio dos dados para o servidor
    }

    let dadosUsuario = {
        "nome": nomeCompleto,
        "email": email,
        "cpf": cpf,
        "nascimento": nascimento,
        "senha": senha
    };

    let json = JSON.stringify(dadosUsuario);
    console.log(json);

    $("#BT").click(function (event) {
        event.preventDefault();
    });
    $.ajax({
        type: "POST",
        url: "/auth/registrar",
        data: json,
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            sessionStorage.setItem("token", response.token);
            window.location.href = 'index.html';
        },
        error: function (response) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocorreu um erro ao registrar o usuário!',
            })
        }
    });

}

function validarEmail(email) {
    let regex = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
    return regex.test(email);
}