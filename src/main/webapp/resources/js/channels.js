function addNewChannel(callback) {
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
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			method : 'POST',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(channelJson),
			success : function(rez, status, xhr) {
				callback();
			}			
		});
	} else {
		$('#loading').hide();
		alert("Title required!");
	}
}
/*TREBA PROMENITI STATUS CODE*/
function deleteChannel(channel) {
	//var channel=$("#select_room_list option:selected");
	//alert(channel.id);
	var channelName=channel.id;
	if (isNaN(channelName)) {
		$('#loading').hide();
		if (confirm('Are you sure you want to delete ' + channelName + '?')) {
			var channelJson = new Object();
			channelJson.name = channelName;
			$.ajax({
				url : _basePath + "channel/delete",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(channelJson),
				success : function(rez, status, xhr) {
					/*if (rez == "SUCCESS") {
						channel.remove();
						$('#loading').hide();
						//alert('Room deleted.');
						fetchAllChannels();
						statsRoom();
					} else {
						$('#loading').hide();
						alert('Error!');
					}*/
					/*if (rez >300) {
						$('#loading').hide();
						alert("Error in adding room.");
					} else {
						callback();
					}*/
					callback();
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
	$('#list_channels').html("");
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
									if (channelisPublic == true) {
										$("#list_channels").append(
												"<p class='plistelem' id='"
												+ channelName
												+ "'>"
												+ channelName
												+ " <img id=\"keyImage\" src=\"\" width=\"16px\" height=\"16px\" style=\"float:right;\"/>"
												+ "<label id='"
												+ channelName
												+ "' class='removeList' onclick='deleteChannel(this)'>&nbsp;x&nbsp;<label></p>"
												);
									} else {
									$("#list_channels").append(
											"<p class='plistelem' id='"
											+ channelName
											+ "'>"
											+ channelName
											+ "<img id=\"keyImage\" src=\"resources/images/key.gif\" style=\"float:right;\"/>" 											
											+ "<label id='"
											+ channelName
											+ "' class='removeList' onclick='deleteChannel(this)'>&nbsp;x&nbsp;<label></p>"
											);
									}
									//alert("Prvi elem je: " + channelName);
									//fetchUsersByRoom(channelName);
									onChangeFetchUsersByRoom();
									ind = false;
									
								} else {
									//alert("Ostali elem");
									$('#select_room_list').append(
											"<option id='"+priv+"' value='"
													+ channelName
													+ "'>"
													+ channelName.substr(0,	40)
													+ "</option>");
									
									if (channelisPublic == true) {
										$("#list_channels").append(
												"<p class='plistelem' id='"
												+ channelName
												+ "'>"
												+ channelName
												+ "<img id=\"keyImage\" src=\"\" width=\"16px\" height=\"16px\" style=\"float:right;\"/>"
												+ "<label id='"
												+ channelName
												+ "' class='removeList' onclick='deleteChannel(this)'>&nbsp;x&nbsp;<label></p>"
												);
									} else {
									$("#list_channels").append(
											"<p class='plistelem' id='"
											+ channelName
											+ "'>"
											+ channelName
											+ "<img id=\"keyImage\" src=\"resources/images/key.gif\" style=\"float:right;\"/>" 
											+ "<label id='"
											+ channelName
											+ "' class='removeList' onclick='deleteChannel(this)' style=\"float:right;\">&nbsp;x&nbsp;<label></p>"
											);
									}
								}
								
							}
							statsRoom();
							$('#loading').hide();
						} else {
							$('#loading').hide();
							//alert('Error loading channel.');
						}
						
					});	
	

}

