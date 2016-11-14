function addTask(){
    var clave = sessionStorage.getItem("claveboard");
    var name = $("#nombre").val();
    var desc = $("#descripcion").val();
    var id = sessionStorage.getItem("sprintTask");
    alert("/board/task/"+clave+"/"+id);
    
    if(name != "" && desc != "" && clave != ""){
        $.ajax({
            timeout: 3000,
            async: false,
            type: 'POST', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
            url: "/board/task/"+clave+"/"+id, // A valid URL
            headers: {"X-HTTP-Method-Override": "POST", "Content-Type": "application/json"},
            data: JSON.stringify({
                nombre: name,
                descripcion: desc,
                status: "Pendiente"
            })
        }).then(function(data){
            alert(data);
        }).fail(function(response){
            alert("No se ha podido registrar su información, por favor, verifiquela. ERR -- " + response);
        });
    }else{
        alert("Asegure que todos los campos están completos.");
    }
}