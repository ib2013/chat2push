
function showAllUsers(){
	$('#loading').show();
	$.get(_basePath+"user/fetch", function(data,status,xhr){
	  if(status="succes"){
		 $('#list_view_users').html("");
		
		 for (var i=0; i<data[0].length; i++){
			 var username=data[0][i].username;
			 $('#list_view_users').append("<li draggable='true' ondragstart='drag(event)' id='"+username+"'>"+username+"'</li>");
		 }
	  }
	}	
	);
	$('#loading').hide();
}