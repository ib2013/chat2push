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
					if (rez == "success") {
						var deleted=document.getElementById(user.id);
						deleted.remove();
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
	var room = $("#select_room_list option:selected");
	if(room.attr("id")=="PR") $("#keyImage").show();
	else $("#keyImage").hide();
	var roomname=room.val();
	fetchUsersByRoom(roomname);
}
function fetchUsersByRoom(roomname) {

	$('#loading').show();
	$('#list_rooms_users').html("");
	
	if (isNaN(roomname)) {
		var room = new Object();

		room.name = roomname;
		showAllUsers();
		$
				.ajax({
					url : _basePath + "channel/fetchUsersByRoom",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					method : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(room),
					success : function(data, status, xhr) {
						
						if (data.length != 0) {
							
							$('#list_rooms_users').html("");
							alert(data);
						
							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								var elementForRemove=document.getElementById(username);
								elementForRemove.remove();
								$('#list_rooms_users')
										.append(
												"<p class='plistelem' draggable='true' ondragstart='drag(event)' id='"
												+ username
												+ "'>"
												+ username
												+ "<label id='"
												+ username
												+ "' class='removeList' onclick='deleteUser(this)'>&nbsp;x&nbsp;<label></p>"
												);
							}
						
						} else {
							$('#loading').hide();
							alert('Room is empty');
						}
					}
				});
	} else {
		// $('#loading').hide();
		// alert('Error show users by room.');
	}
	$('#loading').hide();
}

function refershUserList(){
	showAllUsers();
	onChangeFetchUsersByRoom();
}