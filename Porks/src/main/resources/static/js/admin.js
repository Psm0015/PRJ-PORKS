function verificarauth() {
    tk = sessionStorage.getItem("token")
    $.ajax({
        type: "GET",
        url: "user/info",
        headers: {
            "Authorization": `Bearer ${tk}`
        },
        success: function () {
            carregatb()
        },
        error: function () {
            window.location.href = 'login-1.html'
        }

    });
}
verificarauth()

function formatarReal(numero) {
    return "R$ " + numero.toFixed(2).replace(".", ",");
}

function carregatb() {
    $.get("convidado/listall", function (data, status) {
        tbdata = ''
        for (let i = 0; i < data.length; i++) {
            const prd = data[i];
            // console.log(prd.id);
            // console.log(prd.nome);
            // console.log(prd.ingredientes);
            tbdata += '<tr>'
            tbdata += `<td>${prd.id}</td>`
            tbdata += `<td>${prd.nome}</td>`
            tbdata += `<td>${prd.ingredientes}</td>`
            tbdata += `<td>${formatarReal(prd.preco)}</td>`
            tbdata += `<td>${prd.categoria}</td>`
            tbdata += `<td><i onclick='deletarprd(${prd.id})' style='color: red;cursor: pointer;' class="fa fa-trash" aria-hidden="true"></i>`
            tbdata += `    <i onclick='editarprdmdn(${prd.id},"${prd.nome}","${prd.ingredientes}",${prd.preco},"${prd.categoria}")' style='color: #0dcaf0;cursor: pointer;' class="fa-regular fa-pen-to-square"></i></td>`
            tbdata += '</tr>'
        }
        document.getElementById('tabela').innerHTML = tbdata
    });
}
function cadastro() {
    const data = JSON.stringify({
        "nome": document.getElementById('nomemd').value,
        "ingredientes": document.getElementById('ingredientesmd').value,
        "preco": document.getElementById('precomd').value,
        "categoria": document.getElementById('categoriamd').value
    });

    $.ajax({
        url: "admin/cadprd",
        method: "POST",
        data: data,
        contentType: "application/json",
        headers: {
            "cookie": "JSESSIONID=DE5FA346853874788469CE9BCBEFDEC5",
            "Authorization": `Bearer ${sessionStorage.getItem("token")}`
        },
        success: function () {
            carregatb()
            document.getElementById('nomemd').value = ''
            document.getElementById('ingredientesmd').value = ''
            document.getElementById('categoriamd').value = ''
            document.getElementById('precomd').value = ''
            $('#novoprd').modal('hide')
        },
        error: function (response) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocorreu um erro ao cadastrar o produto!',
            })
        }
    });
}
function deletarprd(id) {
    $.ajax({
        url: `admin/apgrprd/${id}`,
        method: "DELETE",
        data: null,
        headers: {
            "cookie": "JSESSIONID=DE5FA346853874788469CE9BCBEFDEC5",
            "Authorization": `Bearer ${sessionStorage.getItem('token')}`
        },
        success: function (response) {
            carregatb()
            const Toast = Swal.mixin({
                toast: true,
                position: 'top',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })

            Toast.fire({
                icon: 'success',
                title: 'Produto Apagado!'
            })
        },
        error: function (response) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocorreu um erro ao apagar o produto!',
            })
        }
    });
}
function editarprdmdn(id,nome,ingredientes,preco,categoria) {
    sessionStorage.setItem('id',id)
    $('#nomeed').val(nome);
    $('#ingredientesed').val(ingredientes);
    $('#categoriaed').val(categoria);
    $('#precoed').val(preco);
    $('#editprd').modal('show')
}

function editarprd() {
    id = sessionStorage.getItem('id');
    const data = JSON.stringify({
        "id":id,
        "nome": document.getElementById('nomeed').value,
        "ingredientes": document.getElementById('ingredientesed').value,
        "preco": document.getElementById('precoed').value,
        "categoria": document.getElementById('categoriaed').value
    });

    $.ajax({
        url: "admin/editarprd",
        method: "PUT",
        data: data,
        contentType: "application/json",
        headers: {
            "cookie": "JSESSIONID=DE5FA346853874788469CE9BCBEFDEC5",
            "Authorization": `Bearer ${sessionStorage.getItem("token")}`
        },
        success: function () {
            carregatb()
            document.getElementById('nomeed').value = ''
            document.getElementById('ingredientesed').value = ''
            document.getElementById('categoriaed').value = ''
            document.getElementById('precoed').value = ''
            $('#editprd').modal('hide')
        },
        error: function (response) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocorreu um erro ao cadastrar o produto!',
            })
        }
    });
}