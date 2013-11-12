/**
 * 
 */

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("Text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("Text");
	ev.target.appendChild(document.getElementById(data));
	channel=$("#select_room_list option:selected");
	addUserToChannel(data,channel);
}

function addUserToChannel(userName,channel){
	var user=document.getElementById(userName);
	alert("Dodajem "+user.id+" u "+channel.val());
	user.draggable=false;
	var child=$(":first-child", user);
	child.get(0).onclick=""; //dodati novu funkciju za brisanje usera iz sobe

}
documentReady = function(basePath) {
	boolFeedClick = true;
	boolChanalClick = false;
	$('#loading').show();
	//var definitionWindowWidth = $(window).width();
	//var definitionWindowHeight = $(window).height();
	//$('#content').css('width', definitionWindowWidth);
	//$('#content').css('height', definitionWindowHeight);
	//addListElement(1);
	fetchAllChannels();
	showAllUsers();
	
};