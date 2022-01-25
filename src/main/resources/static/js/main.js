let verclave=document.getElementById("verclave");
let clave=document.getElementById("clave")
let icono=document.getElementById("icono")
let con=true

verclave.addEventListener('click', function(){

if (con==true){
clave.type="text"
icono.classList.add("fa-eye-slash")
con=false
}   else{
clave.type="password"
icono.classList.remove("fa-eye-slash")
con=true
}
    
})


