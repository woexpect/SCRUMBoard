function registro() {
    var usuario = $("#user").val();
    var email = $("#email").val();
    var contra = $("#pass1").val();
    var contraConfirmacion = $("#pass2").val();
    var nombre = $("#nombre").val().concat(" " + $("#apellido").val());
    
    if(usuario !== "" && email !== "" && contra !== "" && contraConfirmacion !== "" && nombre !== ""){
        if(contra === contraConfirmacion) { 
            putRequest(nombre, contra, usuario, email);
        }
        else{
            alert("Las contraseñas no coinciden.");
        }
    }
    else{
        alert("Porfavor verifique que ha ingresado todos los campos del formulario.");
    }
   
}

putRequest = function(nombre, contra, usuario, email) {
    return $.ajax({
        url: '/register/user',
        type: 'POST',
        data: JSON.stringify({
            name: nombre,
            pwd: contra,
            uname: usuario,
            mail: email
        }),
        contentType: "application/json",
        success: function(data) {
            window.location = "http://localhost:8080/pages/login.html";
            alert(data);
        },
        error: function(xhr, status){
            alert("No se ha podido registrar su información, por favor, verifiquela.");
        }
    });
};