function registro() {
    var usuario = $("#user").val();
    var email = $("#email").val();
    var contra = $("#pass1").val();
    var contraConfirmacion = $("#pass2").val();
    var nombre = $("#nombre").val().concat(" " + $("#apellido").val());
    
    if(usuario !== "" && email !== "" && contra !== "" && contraConfirmacion !== "" && nombre !== ""){
        if(contra === contraConfirmacion) { 
            $.ajax({
                type: 'POST', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
                url: "/register/user", // A valid URL
                headers: {"X-HTTP-Method-Override": "POST", "Content-Type": "application/json"},
                data: JSON.stringify({
                    name: nombre,
                    pwd: contra,
                    uname: usuario,
                    mail: email
                })
            }).then(function(data){
                alert(data);
            }).fail(function(response){
                alert("No se ha podido registrar su información, por favor, verifiquela. Err: " + response);
            });
        }
        else{
            alert("Las contraseñas no coinciden.");
        }
    }
    else{
        alert("Porfavor verifique que ha ingresado todos los campos del formulario.");
    }
   
}