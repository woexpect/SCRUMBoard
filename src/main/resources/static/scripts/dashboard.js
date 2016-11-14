var stompClient = null;
var mail = null;
var boards;

function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        //alert(mail);
        //stompClient.subscribe('/topic/userlogin/{'+mail+'}', function (data) {
        stompClient.subscribe('/topic/userboard/{sebasp95@hotmail.com}', function (data) {    
            var objetoJson = JSON.parse(data.body);
            console.log(objetoJson);
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


$(document).ready(function(){
    var username = sessionStorage.getItem("user");
    mail = sessionStorage.getItem("mail");
    $("#username").text(username);
    connect();

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
        timeout: 3000;
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
});

function logout(){
    sessionStorage.clear();
}

function goToBoard(elem){
    var id = elem.id;
    sessionStorage.setItem("boardSprint",id.substring(id.length-1, id.length));
    window.location = "sprintView.html";   
}