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