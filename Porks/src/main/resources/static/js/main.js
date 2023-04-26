function dadosusr() {
    tk = sessionStorage.getItem("token");
    $.ajax({
        type: "GET",
        url: "user/info",
        headers: {
            "Authorization": `Bearer ${tk}`
        },
        success: function(response) {
            console.log(response);
        }
    });
    
}
dadosusr()