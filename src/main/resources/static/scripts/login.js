function login(){
    var c = $("#email").val();
    var p = $("#pass").val();
 
    $.when(postRequest(c, p)).done(function (data){
        sessionStorage.setItem("user", data.name);
        sessionStorage.setItem("mail", data.mail);
        //alert("Bienvenido, " + data.name);
        window.location.href = "dashboard.html";
    }).fail(function(errorThrown){
        console.log(errorThrown);
        alert("Usted no se ha autenticado correctamente.");
    });
}

function postRequest(c, p) {
    return $.ajax({
        async: false,
        url: "/login/user/",
        type: 'POST',
        data: JSON.stringify({
                name: "",
                pwd: p,
                uname: "",
                mail: c}),
        contentType: "application/json"
    });
}

