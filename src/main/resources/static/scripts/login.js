function login(){
    var c = $("#correo").val();
    var p = $("#pass").val();
    $.ajax({
        type: "POST",
        url: "/login/user",
        data: JSON.stringify({
           name: "",
           pwd: p,
           uname: "",
           mail: c
        }),
        contentType: "application/json",
        dataType: 'json',
        success: function(data) {
            window.location = "http://localhost:8080/pages/registro.html";
            alert("Bienvenido: " + data.name);
        },
        error: function(xhr, status){
            alert("Usted no se ha autenticado correctamente.");
        }
     })
}