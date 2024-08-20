var x=document.getElementById("login");
var y=document.getElementById("signup");
var z=document.getElementById("btn");

function signup(){
    x.style.left="-400px";
    y.style.left="50px";
    z.style.left="110px";
}
function login(){
    x.style.left="50px";
    y.style.left="450px";
    z.style.left="0px";
}
document.addEventListener('DOMContentLoaded',function(){
    const errorMessages=document.querySelectorAll('.error-message');
    errorMessages.forEach(message=>{
        if(message.textContent.trim() !== ''){
            message.classList.add('show');
            setTimeout(()=>{
                message.classList.remove('show');
            },3000);
        }
    });
});