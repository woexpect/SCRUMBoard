function addSprint(){
    var clave = sessionStorage.getItem("claveboard");
    var name = $("#nombre").val();
    var desc = $("#descripcion").val();
    
    if(name !== "" && desc !== "" && clave !== ""){

        console.log(clave);
        $.ajax({
            //timeout: 3000,
            //async: false,
            type: 'POST', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
            url: "/board/sprint/"+clave, // A valid URL
            headers: {"X-HTTP-Method-Override": "POST", "Content-Type": "application/json"},
            data: JSON.stringify({
                nombre: name,
                descripcion: desc,
                status: "En Curso."
            })
        }).done(function(data){
            alert(data);
            window.location.href = "sprintView.html";
        }).fail(function(response){
            console.log(response);
            alert("No se ha podido registrar su información, por favor, verifiquela. ERR -- " + response);
        });
        
    }else{
        alert("Asegure que todos los campos están completos.");
    }
}