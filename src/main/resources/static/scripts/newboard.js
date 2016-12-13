var mails = [];
$(document).ready(function(){
    var mail = sessionStorage.getItem("mail");
    mails.push(mail + "1");
    $("#colab").append("<li class='roboto'>" + mail + ", SCRUMMaster</li>");
});

function add(){
    var mail = sessionStorage.getItem("mail");
    alert(mail);
    var name = $("#boardname").val();
    var desc = $("#descripcion").val();
    var clv = $("#clave").val();
    
    var board = new Board(name, desc, clv);
    for(i = 0; i < mails.length; i++){
        board.agregarColaborador(mails[i]);
    }
    
    var jsonBoard = JSON.stringify(board);
    console.log(jsonBoard);
   
    if(name !== "" && desc !== "" && clv !== ""){
        
            chainedPromises = function(){
                postRequest();
                
            };
            postRequest = function() {
                return $.ajax({
                    url: "/board/create",
                    type: 'POST',
                    data: jsonBoard,
                    contentType: "application/json"
                }).done(function(data){
                    alert(data);
                    window.location.href = "sprintView.html";
                });
            };
    
        chainedPromises();
      
    }
}
function addColab(){
    var rol = $( "#roles option:selected" ).text();
    var mail = $( "#email").val();
    if(rol !== "Seleccione rol del colaborador"){
        $("#colab").append("<li class='roboto'>" + mail + ", " + rol + "</li>");
        if(rol === "SCRUMDeveloper"){
            mails.push(mail + "2");
        }else{
            mails.push(mail + "3");
        }
    }
}