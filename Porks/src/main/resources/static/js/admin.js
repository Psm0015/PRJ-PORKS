$.get("convidado/listall", function (data, status) {
    tbdata=''
    for (let i = 0; i < data.length; i++) {
        const prd = data[i];
        // console.log(prd.id);
        // console.log(prd.nome);
        // console.log(prd.ingredientes);
        tbdata+= '<tr>'
        tbdata+=`<td>${prd.id}</td>`
        tbdata+=`<td>${prd.nome}</td>`
        tbdata+=`<td>${prd.ingredientes}</td>`
        tbdata+=`<td><i style='color: red;cursor: pointer;' class="fa fa-trash" aria-hidden="true"></i></td>`
        tbdata+='</tr>'
    }
    document.getElementById('tabela').innerHTML = tbdata
});