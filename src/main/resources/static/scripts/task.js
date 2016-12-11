function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}

function droptable(ev) {
    ev.preventDefault();
    //trae el id del objeto donde se decarga alert(ev.target.id);
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}

var username = sessionStorage.getItem("user");
var obj = sessionStorage.getItem("boardSprint");
var obj2 = sessionStorage.getItem("sprintTask");
var boards;

$(document).ready(function(){    
    mail = sessionStorage.getItem("mail");
    $("#username").text(username);
    
    cargaTareas();
});

cargaTareas = function () {
    var promesaBoardsDisponibles = $.getJSON("/board/userboard/"+mail);   
    
    promesaBoardsDisponibles.done(function(data, status) {

        boards = data;
        console.log(boards);
        console.log(obj2);
        $("#sprintname").text(boards[obj].backLog.sprints[obj2].nombre);
        sessionStorage.setItem("claveboard", boards[obj].clave);
        for(i = 0; i < boards[obj].backLog.sprints[obj2].tasks.length; i++){
            $("#pendiente").append("<div id='task" + i + " 'class='tarea' draggable='true' ondragstart='drag(event)'><p style='color: #87A69F;font-size:20px;margin-top:5%;margin-left:5%;'>" + boards[obj].backLog.sprints[obj2].tasks[i].nombre + "</p><p style='margin-left:5%;margin-right:5%;''>" + boards[obj].backLog.sprints[obj2].tasks[i].descripcion + "</p></div>");
        }
      
    });     
};

function logout(){
    sessionStorage.clear();
}

function goToSprint(elem){
    var id = elem.id;
    sessionStorage.setItem("sprintTask",id.substring(id.length-1, id.length));
    alert(sessionStorage.getItem("sprintTask"));
    window.location = "task.html";   
}