var stompClient = null;
var mail = null;
var boards;
var i;

function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/userboard.'+mail, function (data) {
            var objetoJson = JSON.parse(data.body);
            var color = "#FF1744";
            $("#recuadro_boards").append("<div class='board' onclick='goToBoard(this)' id='board" + i + "'> <div><div class='circle' style='background: " + color + ";'></div><div class='inline'><h1>" + objetoJson.nombre + "</h1><p>" + objetoJson.descripcion + "</p></div></div></div>");
            $("#menuside").append("<li class='sidemenu' onclick='goToBoard(this)'><a class='menuizq' href ='#'>" + objetoJson.nombre + "</a></li>");
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

cargaBoards = function () {
    var promesaBoardsDisponibles = $.getJSON("/board/userboard/"+mail);   
    
    promesaBoardsDisponibles.done(function(data, status) {
        var color;
        console.log(data);
        boards = data;
        for(i = 0; i < boards.length; i++){
            for(j = 0; j < boards[i].collaborators.length; j++){
                var yyy = boards[i].collaborators[j].substring(0,boards[i].collaborators[j].length-1);

                if(mail === yyy){

 
                    if(boards[i].collaborators[j].substring(boards[i].collaborators[j].length-1,boards[i].collaborators[j].length) === "1"){
                        color = "#03A9F4";
                    }else if(boards[i].collaborators[j].substring(boards[i].collaborators[j].length-1,boards[i].collaborators[j].length) === "2"){
                        color = "#4CAF50";
                    }else if(boards[i].collaborators[j].substring(boards[i].collaborators[j].length-1,boards[i].collaborators[j].length) === "3"){
                        color = "#FFEB3B";
                    }
                    $("#recuadro_boards").append("<div class='board' onclick='goToBoard(this)' id='board" + i + "'> <div><div class='circle' style='background: " + color + ";'></div><div class='inline'><h1>" + boards[i].nombre + "</h1><p>" + boards[i].descripcion + "</p></div></div></div>");
                    $("#menuside").append("<li class='sidemenu' onclick='goToBoard(this)'><a class='menuizq' href ='#'>" + boards[i].nombre + "</a></li>");
                }
            }
        }
      
    });     
};


$(document).ready(function(){
    var username = sessionStorage.getItem("user");
    mail = sessionStorage.getItem("mail");
    $("#username").text(username);
    connect();
    cargaBoards();
    /*
    function cargarBoards(){
        return new Promise(function(done, reject){
            setTimeout(function (){
                done();
            }, 500);
        });
    }
    
    cargarBoards()
    .then(function (){
        $.when($.get("/board/userboard/"+mail)).done(function (data, status){
            boards = data;
        }).fail(function() {
        // will be called if one (or both) requests fail.  If a request does fail,
        // the `done` callback will *not* be called even if one request succeeds.
        }); 
        
        /*$.get("/board/userboard/"+mail, function(data, status){
            timeout: 8000;
            boards = data;
        });
        return cargarBoards();
    })
    .then(function (){

        var color;
        for(i = 0; i < boards.length; i++){
            for(j = 0; j < boards[i].collaborators.length; j++){
                if(mail == boards[i].collaborators[j].substring(0,boards[i].collaborators[j].length-1)){
                    if(boards[i].collaborators[j].substring(boards[i].collaborators[j].length-1,boards[i].collaborators[j].length) == "1"){
                        color = "#03A9F4";
                    }else if(boards[i].collaborators[j].substring(boards[i].collaborators[j].length-1,boards[i].collaborators[j].length) == "2"){
                        color = "#4CAF50";
                    }else if(boards[i].collaborators[j].substring(boards[i].collaborators[j].length-1,boards[i].collaborators[j].length) == "3"){
                        color = "#FFEB3B";
                    }
                    $("#recuadro_boards").append("<div class='board' onclick='goToBoard(this)' id='board" + i + "'> <div><div class='circle' style='background: " + color + ";'></div><div class='inline'><h1>" + boards[i].nombre + "</h1><p>" + boards[i].descripcion + "</p></div></div></div>");
                    $("#menuside").append("<li class='sidemenu' onclick='goToBoard(this)'><a class='menuizq' href ='#'>" + boards[i].nombre + "</a></li>")
                }
            }
        }
    })
    .catch(function(err){
        alert("ERROR ---> " + err);
    });
    */
});

function logout(){
    sessionStorage.clear();
}

function goToBoard(elem){
    var id = elem.id;
    sessionStorage.setItem("boardSprint",id.substring(id.length-1, id.length));
    window.location = "sprintView.html";   
}