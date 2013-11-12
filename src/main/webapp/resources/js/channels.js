function addNewChannel() {

	var channelTitle = $('#title').val();
	var channelDescription = $('#channel_description').val();
	var typeOfChannel = $("#typeOfChannel option:selected").text();
	alert(typeOfChannel);
	if (typeOfChannel == "Public") {
		isPublic = true;
	} else {
		isPublic = false;
	}
	alert(isPublic);
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
					//addListElement(2); ponovo ucitati kanale, tj izvrsiti update liste
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


function deleteChannel(channel) {

	if (isNaN(channel.name)) {
		if (confirm('Are you sure you want to delete ' + channel.name + '?')) {
			$('#loading').show();
			var channelJson = new Object();
			channelJson.name = channel.name;
			channelJson.description = channel.description;
			channelJson.isPublic = channel.isPublic;
			$('#loading').show();
			$.ajax({
				url : _basePath + "channel/delete",
				headers : {
					'Accept' : 'text/plain',
					'Content-Type' : 'application/json'
				},
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(channelJson),
				success : function(rez, status, xhr) {
					if (rez == 'success') {
						$('#select_room_list').html("");
						// addListElement(2); ponovo ucitati kanale, tj izvrsiti
						// update liste
						$('#loading').hide();
					} else {
						$('#loading').hide();
						alert('Error');
					}
				}
			});
		}
	} else {
		$('#loading').hide();
		alert('Error to delete channel.');
	}

}

function fetchAllChannels() {
	$('#loading').show();
	$.get(_basePath + "channel/fetch", function(data, status, xhr) {
						if (JSON.stringify(data) != "") {
							$('#select_room_list').html("");
							for (var i = 0; i < data[0].length; i++) {
								//var chaneelCounter = data[i].counter;
								var channelName = data[0][i].name;
								//var channelDescription = data[i].channelModel.description;
								//var channelisPublic = data[i].channelModel.isPublic;
								//$('#loading').hide();
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
							$('#loading').hide();
						} else {
							$('#loading').hide();
							alert('Error loading channel.');
						}
					});
	$('#loading').hide();
}

