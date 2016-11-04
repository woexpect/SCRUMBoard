function add(){
    var mail = sessionStorage.getItem("mail");
    alert(mail);
    var name = $("#boardname").val();
    var desc = $("#descripcion").val();
    var clv = $("#clave").val();

    if(name != "" && desc != "" && clave != ""){
        $.ajax({
            url: '/board/create',
            type: 'POST',
            data: JSON.stringify({
                nombre: name,
                descripcion: desc,
                clave: clv
            }),
            contentType: "application/json",
            success: function(data) {        
                alert(data);
            },
            error: function(xhr, status){
                alert("No se ha podido registrar su información, por favor, verifiquela.");
            }
        });

        $.ajax({
            url: '/board/'+clv,
            type: 'POST',
            data: mail,
            contentType: "application/json",
            success: function(data) {        
                alert(data);
            },
            error: function(xhr, status){
                alert("No se ha podido registrar su información, por favor, verifiquela.");
            }
        });
    }

}