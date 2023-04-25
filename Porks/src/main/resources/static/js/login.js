function fazerlogin() {
    cpf= `cpf:${document.getElementById('email-login').value},`;
    senha= `senha:${document.getElementById('senha-login').value}`;
    $.ajax({
        type: "POST",
        url: "/auth/login",
        data: data,
        success: function(data,status) {
            console.log(data)
        },
        dataType: "json"
    });
}