function addNewChannel() {

	var channelTitle = $('#title').val();
	var chaneelDescription = $('#channel_description').val();
	var typeOfChannel = $("#typeOfChannel option:selected").text();
	
	if (channelTitle.length != 0) {
		channelJson = new Object();
		channelJson.name = channelTitle;
		channelJson.description = chaneelDescription;
		$.ajax({
			url : _basePath + "channel/add",
			headers : {
				'Accept' : 'text/plain',
				'Content-Type' : 'application/json'
			},
			method : 'POST',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(channelJson),
			success : function(rez, status, xhr) {
				if (rez == "success") {
					$('#title').val("");
					$('#channel_description').val("");
					addListElement(2);
					changeTab(2);
					$('#loading').hide();
					alert("New channel is inserted");
				} else {
					$('#loading').hide();
					alert("Fail add channel");
				}
			}
		});
	} else {
		$('#loading').hide();
		alert("Title required ");
	}

}