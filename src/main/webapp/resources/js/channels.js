function addNewChannel() {

	var channelTitle = $('#title').val();
	var channelDescription = $('#channel_description').val();
	var typeOfChannel = $("#typeOfChannel option:selected").text();
	if (typeOfChannel == "Public") {
		isPublic = true;
	} else {
		isPublic = false;
	}
	if (channelTitle.length != 0) {
		channelJson = new Object();
		channelJson.name = channelTitle;
		channelJson.isPublic = isPublic;
		channelJson.description = channelDescription;
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
				alert(status);
				alert(rez);
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

function fetchAllChannels() {
	$('#loading').show();
	$.get(_basePath + "channel/fetch", function(data, status, xhr) {
						if (JSON.stringify(data) != "") {
							$('#select_room_list').html("");
							for (var i = 0; i < data.length; i++) {
								//var chaneelCounter = data[i].counter;
								var channelName = data[i].channelModel.name;
								//var channelDescription = data[i].channelModel.description;
								//var channelisPublic = data[i].channelModel.isPublic;
								$('#loading').hide();
								$('#select_room_list').append(
												"<option value='"
														+ channelName
														+ "'>"
														+ channelName.substr(0,	40)
														//+ "  <label id='"
														//+ chaneelName
														//+ "' class='removeList' onclick='removeChannel(this)'>Remove</label> <span class='badge'>"
														//+ chaneelCounter
														//+ "</span></li>"
														+ "</option>");
							}
						} else {
							alert('Error loading channel.');
						}
					});
}
