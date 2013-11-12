function showAllUsers() {
	$('#loading').show();
	$
			.get(
					_basePath + "user/fetch",
					function(data, status, xhr) {
						if (status = "succes") {
							$('#list_view_users').html("");

							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								$('#list_view_users')
										.append(
												"<p class='plistelem' draggable='true' ondragstart='drag(event)' id='"
														+ username
														+ "'>"
														+ username
														+ "'<label id='"
														+ username
														+ "' class='removeList' onclick='deleteUser(this)'>Remove<label></p>");
							}
						}
					});
	$('#loading').hide();
}

function deleteUser(user) {
	$('#loading').show();

	if (isNaN(user.username)) {
		if (confirm('Are you sure you want to delete ' + user.username + '?')) {
			$('#loading').show();
			var userJson = new Object();
			userlJson.name = user.username;

			$('#loading').show();
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
					if (rez == 'success') {
						$('#list_users').html("");
						// addListElement(2); ponovo ucitati usere, tj izvrsiti
						// update liste
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

function fetchUsersByRoom(roomname) {
	
	//var roomname = $("#select_room_list option:selected").val();
	alert(roomname);
	//if (isNaN(roomname)) {
		
		var room = new Object();
		room.name = roomname;
		$('#loading').show();
		$
				.ajax({
					url : _basePath + "user/fetchUsersByRoom",
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
	//} else {
		// $('#loading').hide();
		// alert('Error show users by room.');
	//}
}