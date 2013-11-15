/**
 * 
 */

function allowDrop(ev) {
	ev.preventDefault();
}
function dontAllowDrop(ev) {
	ev.target=$("#list_rooms_users");
}
function drag(ev) {
	ev.dataTransfer.setData("Text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("Text");
	channel = $("#select_room_list option:selected");
	if (ev.target.id == "list_rooms_users") {
		$("#list_rooms_users").append(document.getElementById(data));
		addUserToChannel(data, channel);
	} else if (ev.target.id == "list_users") {
		$("#list_users").append(document.getElementById(data));
		removeUserFromChannel(data, channel);
	}
}


function addUserToChannel(userName, channel) {
	var user = document.getElementById(userName);
	var channelUserJson=new Object();
	channelUserJson.username=user.id;
	channelUserJson.channel=channel.val();
	
	$.ajax({
		url: _basePath+ "channel/addUserToRoom",
		headers: {
			'Accept': 'text/plain',
			'Content-type':'application/json',
		},
		method: 'POST',
		contentType: 'application-json',
		data: JSON.stringify(channelUserJson),
		success:function(res, status,xhr){
			if(res=="success"){
				//alert("User added to room.");
			}else{
				alert("Error.");
			}
		}
	});


}

function removeUserFromChannel(userName, channel) {
	var user = document.getElementById(userName);
	var channelUserJson=new Object();
	channelUserJson.username=user.id;
	channelUserJson.channel=channel.val();

	$.ajax({
		url: _basePath+ "channel/removeUserFromRoom",
		headers: {
			'Accept': 'text/plain',
			'Content-type':'application/json',
		},
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(channelUserJson),
		success:function(res, status,xhr){
			if(res=="success"){
				//alert("User removed from room.");
			}else{
				alert("Error.");
			}
		}
	});
}


documentReady = function(basePath) {
	$('#loading').show();
	fetchAllChannels();
	showAllUsers();
	showTenUsers(1);
    allStats();
    statsRoom();
	$('#loading').hide();
};