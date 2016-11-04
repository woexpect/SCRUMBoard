$(document).ready(function(){
    var boards;
    var username = sessionStorage.getItem("user");
    var mail = sessionStorage.getItem("mail");
    $("#username").text(username);

    function cargarBoards(){
        return new Promise(function(done, reject){
            setTimeout(function (){
                done()
            }, 100)
        })
    }
    cargarBoards()
    .then(function (){
        $.get("http://localhost:8080/board/userboard/"+mail, function(data, status){
            boards = data;
        });
        return cargarBoards()
    })
    .then(function (){
        for(i = 0; i < boards.length; i++){
            $("#recuadro_boards").append("<div class='board' id='board" + i + "'> <div><div class='circle' style='background: #FEE3AC;'></div><div class='inline'><h1>" + boards[i].nombre + "</h1><p>" + boards[i].descripcion + "</p></div></div></div>");
        }
    })
    .catch(function(err){
        alert("ERROR" + err);
    });
});