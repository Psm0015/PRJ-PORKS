function dadosusr() {
    tk = sessionStorage.getItem("token");
    if (tk != null) {
        $.ajax({
            type: "GET",
            url: "user/info",
            headers: {
                "Authorization": `Bearer ${tk}`
            },
            success: function (response) {
                sessionStorage.setItem("nome", response.nome)
                sessionStorage.setItem("email", response.email)
                sessionStorage.setItem("cpf", response.cpf)
                sessionStorage.setItem("role", response.role)
                let datansc = new Date(response.nascimento);
                let dia = datansc.getDate() + 1;
                let mes = datansc.getMonth() + 1; // Adiciona 1 pois o mês começa em zero
                let ano = datansc.getFullYear();

                let dataFormatada = dia + "/" + mes + "/" + ano;
                sessionStorage.setItem("nascimento", dataFormatada);
                document.getElementById('tt').innerHTML = `Olá, ${response.nome}`
                document.getElementById('emailmodal').innerHTML = `Email: ${response.email}`
                document.getElementById('nascimentomodal').innerHTML = `Data de Nascimento: ${dataFormatada}`
                if (response.role == 'ADMIN') {
                    document.getElementById('mdbody').innerHTML += `<a href="adminpg.html">Página do Administrador</a>`
                }
            }

        });
    }
}
function loginmodal() {
    if (sessionStorage.getItem("token") != null) {
        $('#lgmdl').modal('show');
    } else {
        window.location.href = 'login-1.html'
    }
}
function encerrarSessao() {
    sessionStorage.clear()
    window.location.href = 'login-1.html'
}
if (sessionStorage.getItem("token") != null) {
    dadosusr();
}
