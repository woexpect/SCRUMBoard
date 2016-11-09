function addSprint(){
    var clave = sessionStorage.getItem("clave");
    var name = $("#nombre").val();
    var desc = $("#descripcion").val();
    
    if(name != "" && desc != "" && clave != ""){
        $.ajax({
            timeout: 3000,
            type: 'POST', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
            url: "/board/sprint/"+clave, // A valid URL
            headers: {"X-HTTP-Method-Override": "POST", "Content-Type": "application/json"},
            data: JSON.stringify({
                nombre: name,
                descripcion: desc,
                status: "En Curso."
            })
        }).then(function(data){
            timeout: 3000;
            alert(data);
        }).fail(function(response){
            alert("No se ha podido registrar su información, por favor, verifiquela. ERR -- " + response);
        });
    }else{
        alert("Asegure que todos los campos están completos.");
    }
}