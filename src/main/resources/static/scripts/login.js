function login(){
    var c = $("#email").val();
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
            window.location = "http://localhost:8080/pages/dashboard.html";
            sessionData(data);
            alert("Bienvenido: " + data.name);
        },
        error: function(xhr, status){
            alert("Usted no se ha autenticado correctamente.");
        }
     })
}

function sessionData(data){
    sessionStorage.setItem("user", data.name);
    sessionStorage.setItem("mail", data.mail);
}