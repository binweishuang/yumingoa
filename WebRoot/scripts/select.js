$().ready(function() {
  $('#ex3c')
    .jqDrag('.jqDrag')
    .jqResize('.jqResize')
    .jqm({
      trigger:'#ex3cTrigger',
      overlay: 0,
      onShow: function(h) {
        h.w.css('opacity',1).slideDown(); 
		height=$("body").height();
		width=$(window).width();
		$("#ex3c").css("position","absolute");
		$("#ex3c").css("top",100+$(window).scrollTop())
		$("#ex3c").css("left",(width-$("#ex3c").width())/2);
        },
      onHide: function(h) {
        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); 
		} 
      });
});
$(window).scroll(function() { 
		height=$("body").height();
		width=$(window).width();
		$("#ex3c").css("position","absolute");
		$("#ex3c").css("top",200+$(window).scrollTop())
		$("#ex3c").css("left",(width-$("#ex3c").width())/2);
});
function selectCounty(id,len){
	$(this).css("color","red");
	for(i=1;i<=len;i++){
		$("#county"+i).css("display","none");
		//$("#county"+i+" input").attr("checked","");
	}
	
	$("#county"+id).css("display","block");
	//$("#county"+id+" input").attr("checked","checked");
	$("#tools").css("display","block");
}
function checkSelect(len){
	$("#source").val("");
	for(i=1;i<=len;i++){
		for(j=1;j<=$("#county"+i+" input").length;j++){
			obj=$("#county"+i+" input:nth-child("+j+")");
			str=$("#source").val();
			if(obj.attr("checked")){
				if(str==""){	
					$("#source").val(obj.attr("value"));
				}else{
					$("#source").val(str+","+obj.attr("value"));
				}
			}
		}
	}
	$('#ex3c').jqmHide();
}

function SelectAll(){
	if($("#infoId").attr("checked")){
		$("#infoId").attr("checked","");
	}else{
		$("#infoId").attr("checked","checked");	
	}
	if($("#infoId1").attr("checked")){
		$("#infoId1").attr("checked","");
	}else{
		$("#infoId1").attr("checked","checked");	
	}
	if($("#infoId2").attr("checked")){
		$("#infoId2").attr("checked","");
	}else{
		$("#infoId2").attr("checked","checked");	
	}
	if($("#infoId3").attr("checked")){
		$("#infoId3").attr("checked","");
	}else{
		$("#infoId3").attr("checked","checked");	
	}
}