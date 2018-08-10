// JavaScript Document
//复选按钮
function setCheck(obj){
    obj.parentNode.className = obj.checked ? "checked" : "";
}
//单选按钮
function setRadio(obj){
    var n = obj.id.split("_")[1];
    for(var i = 1; i <= 2; i++){
        var which = document.getElementById("radio_" + i);
        which.checked = false;
        if(!which.disabled){
            which.parentNode.className = "";
        }
    }
    obj.checked = true;
    obj.parentNode.className = "checked";
}