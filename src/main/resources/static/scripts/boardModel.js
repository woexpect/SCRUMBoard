/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Board(nombre,descripcion,clave){
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.clave = clave;
    this.colaboradores = [];
}


Board.prototype.agregarColaborador=function(email){
    this.colaboradores.push(email);
};

Board.prototype.getColaborador = function(i) {
    return this.platos[i];
};

Board.prototype.getNombre = function (){
    return this.nombre;
};

Board.prototype.getDescripcion = function () {
    return this.descripcion;
};

Board.prototype.getClave = function () {
    return this.clave;
};