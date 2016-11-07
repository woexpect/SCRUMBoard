function login(){
    var c = $("#email").val();
    var p = $("#pass").val();

    $.ajax({
        type: 'POST', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
        url: "/login/user/", // A valid URL
        headers: {"X-HTTP-Method-Override": "POST", "Content-Type": "application/json"},
        data: JSON.stringify({
            name: "",
           pwd: p,
           uname: "",
           mail: c
        })
    }).then(function(data){
        timeout: 3000;
        sessionData(data);
        alert("Bienvenido, " + data.name);
        timeout: 3000;
        $(window).attr('location', 'dashboard.html');
    }).fail(function(response){
        alert("Usted no se ha autenticado correctamente.");
    });
}

function sessionData(data){
    sessionStorage.setItem("user", data.name);
    sessionStorage.setItem("mail", data.mail);
}