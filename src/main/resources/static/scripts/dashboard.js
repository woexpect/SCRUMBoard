var stompClient = null;
var mail = null;

function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        
        //stompClient.subscribe('/topic/userboard/{'+mail+'}', function (data) {
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
    
    var boards;
    var username = sessionStorage.getItem("user");
    //mail = sessionStorage.getItem("mail");
    mail = "sebasp95@hotmail.com";
    $("#username").text(username);
    connect();

    function cargarBoards(){
        return new Promise(function(done, reject){
            setTimeout(function (){
                done()
            }, 1000)
        })
    }
    cargarBoards()
    .then(function (){
        $.get("/board/userboard/"+mail, function(data, status){
            //alert("correo: " + mail + " datos: " + data);
            boards = data;
        });
        return cargarBoards();
    })
    .then(function (){
        for(i = 0; i < boards.length; i++){
            $("#recuadro_boards").append("<div class='board' id='board" + i + "'> <div><div class='circle' style='background: #FEE3AC;'></div><div class='inline'><h1>" + boards[i].nombre + "</h1><p>" + boards[i].descripcion + "</p></div></div></div>");
        }
    })
    .catch(function(err){
        alert("ERROR ---> " + err);
    });
});