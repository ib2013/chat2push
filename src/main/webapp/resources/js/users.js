function showAllUsers() {
	$('#loading').show();
	
	$
			.get(
					_basePath + "user/fetchAllUsers",
					function(data, status, xhr) {
						$('#list_users').html("");

						for (var i = 0; i < data.length; i++) {
							var username = data[i].username;
						
							$('#list_users')
									.append(
											"<p class='plistelem' draggable='true' ondragstart='drag(event)' id='"
													+ username
													+ "'>"
													+ username
													+ "<label id='"
													+ username
													+ "' class='removeList' onclick='deleteUser(this)'>&nbsp;x&nbsp;<label></p>");
						}

					});
	$('#loading').hide();
}

function deleteUser(user) {

	if (isNaN(user.id)) {
		if (confirm('Are you sure you want to delete ' + user.id + '?')) {
			$('#loading').show();
			var userJson = new Object();
			userJson.username = user.id;

			$.ajax({
				url : _basePath + "user/delete",
				headers : {
					'Accept' : 'text/plain',
					'Content-Type' : 'application/json'
				},
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(userJson),
				success : function(rez, status, xhr) {
					if (status == "success") {
						$('#list_users').html("");
						user.parent.remove();
						$('#loading').hide();
					} else {
						$('#loading').hide();
						alert('Error!');
					}
				}
			});
		
		}
	} else {
		$('#loading').hide();
		alert('Error to delete user.');
	}

	$('#loading').hide();

}
function onChangeFetchUsersByRoom() {
	var roomname = $("#select_room_list option:selected").val();
	// alert(roomname);
	fetchUsersByRoom(roomname);
}
function fetchUsersByRoom(roomname) {

	// var roomname = $("#select_room_list option:selected").val();
	//
	if (isNaN(roomname)) {
		// alert(roomname);
		var room = new Object();
		room.name = roomname;
		// $('#loading').show();
		$
				.ajax({
					url : _basePath + "channel/fetchUsersByRoom",
					headers : {
						'Accept' : 'text/plain',
						'Content-Type' : 'application/json'
					},
					method : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(room),
					success : function(data, status, xhr) {
						if (data != null) {
							$('#list_rooms_users').html("");

							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								$('#list_rooms_users')
										.append(
												"<p class='plistelem' draggable='true' ondragstart='drag(event)' id='"
														+ username
														+ "'>"
														+ username
														+ "'<label id='"
														+ username
														+ "' class='removeList' onclick='deleteUser(this)'>Remove<label></p>");
							}
							$('#loading').hide();
						} else {
							$('#loading').hide();
							alert('Error');
						}
					}
				});
		//alert(roomname);
	} else {
		// $('#loading').hide();
		// alert('Error show users by room.');
	}
}