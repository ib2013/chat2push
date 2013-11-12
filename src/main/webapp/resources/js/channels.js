function addNewChannel() {

	var channelTitle = $('#title').val();
	var chaneelDescription = $('#channel_description').val();
	var typeOfChannel = $("#typeOfChannel option:selected").text();
	if (typeOfChannel == "Public") {
		isPublic = true;
	} else {
		isPublic = false;
	}
	if (channelTitle.length != 0) {
		channelJson = new Object();
		channelJson.name = channelTitle;
		channelJson.description = chaneelDescription;
		channelJson.isPublic = isPublic;
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
					$('#loading').hide();
					alert("New room is added.");
				} else {
					$('#loading').hide();
					alert("Error in adding channel.");
				}
			}
		});
	} else {
		$('#loading').hide();
		alert("Title required!");
	}

}

