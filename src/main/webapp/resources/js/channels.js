function addNewChannel() {
	var channelTitle = $('#title').val();
	var channelDescription = $('#channel_description').val();
	var typeOfChannel = $("#typeOfChannel option:selected").text();

	var ind = false;
	$("#select_room_list option").each(function(i){
		if ($(this).val() == channelTitle) {
			ind = true;
		} 
    });
	if (ind) {
		alert("Room with same name alredy exists");
		return;
	}	
		
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
	var channel=$("#select_room_list option:selected");
	var channelName=channel.val();
	if (isNaN(channelName)) {
		$('#loading').hide();
		if (confirm('Are you sure you want to delete ' + channelName + '?')) {
			var channelJson = new Object();
			channelJson.name = channelName;
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
						channel.remove();
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
							var ind = true;
							for (var i = 0; i < data.length; i++) {
								var channelName = data[i].name;
								var channelisPublic = data[i].isPublic;
								var priv = "";
								if (channelisPublic == false) {
									priv = "PR";
								}
								if (ind == true) {
									//alert("Prvi elem");
									
									$('#select_room_list').append(
											"<option selected id='"+priv+"' value='"
													+ channelName
													+ "'>"
													+ channelName.substr(0,	40)
													+ "</option>");
									//alert("Prvi elem je: " + channelName);
									//fetchUsersByRoom(channelName);
									ind = false;
									
								} else {
									//alert("Ostali elem");
									$('#select_room_list').append(
											"<option id='"+priv+"' value='"
													+ channelName
													+ "'>"
													+ channelName.substr(0,	40)
													+ "</option>");
								}
								
							}
							$('#loading').hide();
						} else {
							$('#loading').hide();
							//alert('Error loading channel.');
						}
						

					});	
	onChangeFetchUsersByRoom();

}

