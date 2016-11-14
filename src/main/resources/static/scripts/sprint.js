var username = sessionStorage.getItem("user");
var obj = sessionStorage.getItem("boardSprint");
var boards;

$(document).ready(function(){    
    mail = sessionStorage.getItem("mail");
    $("#username").text(username);
    
    function cargarBoards(){
        return new Promise(function(done, reject){
            setTimeout(function (){
                done()
            }, 500)
        })
    }
    cargarBoards()
    .then(function (){
        $.get("/board/userboard/"+mail, function(data, status){
            boards = data;
        });
        return cargarBoards();
    })
    .then(function (){
        $("#nombreboard").text(boards[obj].nombre);
        sessionStorage.setItem("claveboard", boards[obj].clave);
        for(i = 0; i < boards[obj].backLog.sprints.length; i++){
            $("#recuadro_boards").append("<div class='sprint' onclick='goToSprint(this)' id='sprint" + i + "'><div class='innersprint'><h1>" + boards[obj].backLog.sprints[i].nombre + "</h1><p><br>" + boards[obj].backLog.sprints[i].descripcion + "</p><p class='horas'><br>Horas estimadas: 888</p><a href='' class='versprint'><br><b>Ver Sprint</b></a></div></div>");
        }
    })
    .catch(function(err){
        alert("ERROR ---> " + err);
    });
});

function logout(){
    sessionStorage.clear();
}

function goToSprint(elem){
    var id = elem.id;
    sessionStorage.setItem("sprintTask",id.substring(id.length-1, id.length));
    alert(sessionStorage.getItem("sprintTask"));
    window.location = "task.html";   
}