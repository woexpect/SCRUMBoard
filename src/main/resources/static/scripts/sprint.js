var username = sessionStorage.getItem("user");
var obj = sessionStorage.getItem("boardSprint");
var boards;

$(document).ready(function(){    
    mail = sessionStorage.getItem("mail");
    $("#username").text(username);
    cargaSprints();
    

});

cargaSprints = function () {
    var promesaBoardsDisponibles = $.getJSON("/board/userboard/"+mail);   
    
    promesaBoardsDisponibles.done(function(data, status) {

        boards = data;
        $("#nombreboard").text(boards[obj].nombre);
        sessionStorage.setItem("claveboard", boards[obj].clave);
        for(i = 0; i < boards[obj].backLog.sprints.length; i++){
            $("#recuadro_boards").append("<div class='sprint' onclick='goToSprint(this)' id='" + boards[obj].backLog.sprints[i].nombre + i + "'><div class='innersprint'><h1>" + boards[obj].backLog.sprints[i].nombre + "</h1><p><br>" + boards[obj].backLog.sprints[i].descripcion + "</p><p class='horas'><br>Horas estimadas: 888</p><a href='' class='versprint'><br><b>Ver Sprint</b></a></div></div>");
        }
      
    });     
};

function logout(){
    sessionStorage.clear();
}

function goToSprint(elem){
    
    var id = elem.id;
    alert(id);
    sessionStorage.setItem("sprintName",id.substring(0, id.length-1));
    sessionStorage.setItem("sprintTask",id.substring(id.length-1, id.length));
    window.location = "task.html";   
}