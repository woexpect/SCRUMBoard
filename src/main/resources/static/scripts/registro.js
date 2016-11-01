/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function registro () {
    var usuario = $("#user").val();
    var email = $("#email").val();
    var contra = $("#pass1").val();
    var contraConfirmacion = $("#pass2").val();
    var nombre = $("#nombre").val().concat($("#apellido").val());
    
    if(usuario !== null && email !== null && contra !== null && contraConfirmacion !== null && nombre !== null){
        if(contra === contraConfirmacion) { 
                chainedPromises = function(){
            putRequest();
          };
          
          putRequest = function() {
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
                      alert("Bienvenido: " + data.name);
                  },
                  error: function(xhr, status){
                      alert("Usted no se ha autenticado correctamente.");
                  }
               });
          };        

          chainedPromises();    
        }
        else{
            alert("Las contraseñas no coinciden.");
        }
        
    }
    
    else{
        alert("Porfavor verifique que ha ingresado todos los campos del formulario.");
    }
   
}