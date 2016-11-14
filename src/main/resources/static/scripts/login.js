function login(){
    var c = $("#email").val();
    var p = $("#pass").val();

    $.ajax({
        timeout: 3000,
        async: false,
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
        sessionStorage.setItem("user", data.name);
        sessionStorage.setItem("mail", data.mail);
        alert("Bienvenido, " + data.name);
        setTimeout(function() {
            window.location.replace("http://www.rapidtables.com/web/dev/javascript-redirect.htm");
        }, 1000);
    }).fail(function(response){
        alert("Usted no se ha autenticado correctamente.");
    });
}