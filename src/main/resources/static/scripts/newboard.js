var mails = [];
$(document).ready(function(){
    var mail = sessionStorage.getItem("mail");
    mails.push(mail + "1");
    $("#colab").append("<li class='roboto'>" + mail + ", SCRUMMaster</li>");
});

function add(){
    var mail = sessionStorage.getItem("mail");
    alert(mail);
    var name = $("#boardname").val();
    var desc = $("#descripcion").val();
    var clv = $("#clave").val();
    var res;

    if(name != "" && desc != "" && clave != ""){
        $.ajax({
            async: false,
            timeout: 3000,
            type: 'POST', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
            url: "/board/create", // A valid URL
            headers: {"X-HTTP-Method-Override": "POST", "Content-Type": "application/json"},
            data: JSON.stringify({
                nombre: name,
                descripcion: desc,
                clave: clv
            })
        }).then(function(data){
            timeout: 3000;
            for(i = 0; i < mails.length; i++){
                if(data === "Se ha registrado el board correctamente."){
                    $.ajax({
                        timeout: 3000,
                        url: '/board/'+clv,
                        type: 'POST',
                        data: mails[i],
                        contentType: "application/json"
                    });
                }
            }
        }).fail(function(response){
            alert("No se ha podido registrar su información, por favor, verifiquela. ERR -- " + response);
        });
    }
}

function addColab(){
    var rol = $( "#roles option:selected" ).text();
    var mail = $( "#email").val();
    if(rol != "Seleccione rol del colaborador"){
        $("#colab").append("<li class='roboto'>" + mail + ", " + rol + "</li>");
        if(rol == "SCRUMDeveloper"){
            mails.push(mail + "2");
        }else{
            mails.push(mail + "3");
        }
    }
}
//$.ajax({
  //          url: '/board/create',
    //        type: 'POST',
      //      data: JSON.stringify({
        //        nombre: name,
          //      descripcion: desc,
            //    clave: clv
           // }),
//            contentType: "application/json",
  //          success: function(data) {
    //            alert(data);
      //          if(data === "Se ha registrado el board correctamente."){
        //            $.ajax({
          //              url: '/board/'+clv,
            //            type: 'POST',
              //          data: mail,
                //        contentType: "application/json"
                  //  });
                //}
            //},
            //error: function(xhr, status){
             //   alert("No se ha podido registrar su información, por favor, verifiquela.");
            //}
        //});