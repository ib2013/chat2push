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
				if (rez == "success") {
					$('#title').val("");
					$('#channel_description').val("");
					fetchAllChannels();
					$('#loading').hide();
					alert("New room is added.");
				} else {
					$('#loading').hide();
					alert("Error in adding room.");
				}
			}
		});
	} else {
		$('#loading').hide();
		alert("Title required!");
	}

}

function deleteChannel() {
	var channelName = $("#select_room_list option:selected").text();
	if (isNaN(channel)) {
		if (confirm('Are you sure you want to delete ' + channelName + '?')) {
			$('#loading').show();
			var channelJson = new Object();
			channelJson.name = channeName;
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
						fetchAllChannels();
						$('#loading').hide();
						alert('Room deleted.');
					} else {
						$('#loading').hide();
						alert('Error!');
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
							for (var i = 0; i < data.length; i++) {
								var channelName = data[i].name;
								var channelisPublic = data[i].isPublic;
								var priv = "";
								if (channelisPublic == false) {
									priv = "PR";
								}
								$('#select_room_list').append(
												"<option value='"
														+ channelName
														+ "'>"
														+ channelName.substr(0,	40)
														+ "&nbsp;&nbsp;&nbsp;"
														+ priv
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

