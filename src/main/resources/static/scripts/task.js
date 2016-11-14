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
    var data = ev.dataTransfer.getData("text");
    table = document.getElementById("resultados");
    var row = table.insertRow();

	// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);

	// Add some text to the new cells:
	var a = document.getElementById(data);
	var prec = a.rows[0].cells[1].innerHTML;
	var nom = a.rows[0].cells[0].innerHTML;
	cell1.innerHTML = a.rows[0].cells[0].innerHTML;
	cell2.innerHTML = a.rows[0].cells[1].innerHTML;
	var s = document.getElementById("secctorden");
	var total = document.getElementById("totalcuenta");
	var seleccion = s.options[s.selectedIndex].value;

	$.ajax({
	   type: "PUT",
	   url: "https://mysterious-fjord-48815.herokuapp.com/ordenes/" + seleccion,
	   data: JSON.stringify({"precio": prec,"nombre": nom}),
	   contentType: "application/json",
	   dataType: 'json',
	   complete: function(data) {
	  }
	})

	function recalcularTotal(){
		return new Promise(function(done, reject){
			setTimeout(function (){
				done()
			}, 100)
		})
	}
	recalcularTotal()
	.then(function (){
		$.get("https://mysterious-fjord-48815.herokuapp.com/ordenes/" + seleccion + "/total", function(data, status){
			total.innerHTML = "Total cuenta: " + data
		});
		return recalcularTotal()
	})
	.then(function(){
		$.get("https://mysterious-fjord-48815.herokuapp.com/ordenes", function(data, status){
			datos = data;
		});
	})
	.catch(function(err){
		alert("ERROR" + err);
	})

}

var datos;

var lolol = {
    "ordenes": [
        {
            "platos": [
                {
                    "precio": 7500,
                    "nombre": "pizza"
                },
                {
                    "precio": 3900,
                    "nombre": "gaseosa"
                },
                {
                    "precio": 8000,
                    "nombre": "hamburguesa"
                },
                {
                    "precio": 200,
                    "nombre": "gaseosa 350"
                }
            ]
        },
        {
            "platos": [
                {
                    "precio": 7500,
                    "nombre": "pizza"
                },
                {
                    "precio": 7500,
                    "nombre": "pizza"
                },
                {
                    "precio": 7500,
                    "nombre": "pizza"
                },
                {
                    "precio": 4000,
                    "nombre": "gaseosa litro"
                }
            ]
        }
    ]
};

window.onload = function cargarElementos(){
	var s = document.getElementById("secctorden");
	var opt = document.createElement("option");
	opt.text = "Seleccione una orden";
	s.add(opt);

	$.get("https://mysterious-fjord-48815.herokuapp.com/ordenes", function(data, status){
		datos = data;
	});

	/*console.log("Comenzando post de prueba")
	$.ajax({
	   type: "POST",
	   url: "/ordenes",
	   data: JSON.stringify({
	      Name: "Test",
	       Credits: 0
	   }),
	   contentType: "application/json",
	   dataType: 'json',
	   complete: function(data) {
	       alert(data);
	  }
	})
	console.log("Terminando post")*/
	var $msg = $('#mensaje')
	function waitTwoSeconds(){
		return new Promise(function(done, reject){
			setTimeout(function (){
				done()
			}, 100)
		})
	}
	waitTwoSeconds()
	.then(function (){
		$msg.text('primera promesa')
		return waitTwoSeconds()
	})
	.then(function(){
			$msg.text('segunda promesa llena select')
			for(i=0; i < datos.ordenes.length; i++){
				var opt = document.createElement("option");
				opt.text = i;
				s.add(opt);
			}
			waitTwoSeconds()
	})
	.catch(function(err){
		$msg.text(err).css('color', 'red')
	})

}

function httpGet(theUrl)
{
    var xmlhttp = new XMLHttpRequest();
    var s = document.getElementById("secctorden");
    var seleccion = s.options[s.selectedIndex].value;

    var o = document.getElementById('output');
	var strTable;
	strTable = '';
	strTable += '<table id="resultados" border="2" ondrop="droptable(event)" id="div2" ondragover="allowDrop(event)">';
	strTable += '<tr>';
	strTable += '<th> Plato </th>';
	strTable += '<th> Precio </th>';
	strTable += '</tr>';
	for(i=0; i < datos.ordenes[seleccion].platos.length; i++){
		strTable += '<tr>';
		strTable += '<th>' + datos.ordenes[seleccion].platos[i].nombre + '</th>';
		strTable += '<th>' + datos.ordenes[seleccion].platos[i].precio + '</th>';
		strTable += '</tr>';
	}

	strTable += '</table>';
	o.innerHTML = strTable;

	var total = document.getElementById("totalcuenta");
	$.get("https://mysterious-fjord-48815.herokuapp.com/ordenes/" + seleccion + "/total", function(data, status){
		total.innerHTML = "Total cuenta: " + data
	});
    
}