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
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(userJson),
				success : function(rez, status, xhr) {
					if (rez == 'SUCCESS') {
						showAllUsers();
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
	$('#loading').show();
	var room = $("#select_room_list option:selected");
	if(room.attr("id")=="PR") $("#keyImage").show();
	else $("#keyImage").hide();
	var roomname=room.val();
	$('#list_rooms_users').html("");
	$('#list_users').html("");
	fetchUsersByRoom(roomname);
<<<<<<< HEAD
<<<<<<< HEAD
	fetchOpositeUsersByRoom(roomname);
}
function fetchUsersByRoom(roomname) {

	$('#loading').show();
=======



=======
>>>>>>> cd118b8fac1cf7892657e3ffd0d229168b2db8e2
	fetchOpositeUsersByRoom(roomname);
	$('#loading').hide();
}
function fetchUsersByRoom(roomname) {

	
>>>>>>> 4960c91abbc3906ff507579290d7f80b15420c5b
	$('#list_rooms_users').html("");
	//$('#list_users').html("");
	//showAllUsers();
	if (isNaN(roomname)) {
		var room = new Object();
		room.name = roomname;
		
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
							
							//$('#list_rooms_users').html("");
						
							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								//var elementForRemove=document.getElementById(username);
<<<<<<< HEAD
=======
								//alert(username);
								//if(elementForRemove!=null)

>>>>>>> 4960c91abbc3906ff507579290d7f80b15420c5b
								//elementForRemove.remove();
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
							
						}
					}
				});
	} else {
		
	}

}




function fetchOpositeUsersByRoom(roomname) {

	$('#loading').show();
	$('#list_users').html("");
	//$('#list_users').html("");
	//showAllUsers();
	if (isNaN(roomname)) {
		var room = new Object();
		room.name = roomname;
		
		$
				.ajax({
					url : _basePath + "channel/fetchOpositeUsersByRoom",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					method : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(room),
					success : function(data, status, xhr) {
						
						if (data.length != 0) {
							
							//$('#list_rooms_users').html("");
						
							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								//var elementForRemove=document.getElementById(username);
								//elementForRemove.remove();
								$('#list_users')
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
						}
					}
				});
	} else {
		
<<<<<<< HEAD
	}
	$('#loading').hide();
}

function fetchOpositeUsersByRoom(roomname) {

	$('#loading').show();
	$('#list_users').html("");
	//$('#list_users').html("");
	//showAllUsers();
	if (isNaN(roomname)) {
		var room = new Object();
		room.name = roomname;
		
		$
				.ajax({
					url : _basePath + "channel/fetchOpositeUsersByRoom",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					method : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(room),
					success : function(data, status, xhr) {
						
						if (data.length != 0) {
							
							//$('#list_rooms_users').html("");
						
							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								//var elementForRemove=document.getElementById(username);
								//elementForRemove.remove();
								$('#list_users')
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
						}
					}
				});
	} else {
		
	}
	$('#loading').hide();
}

function refershUserList(){
	//showAllUsers();
=======
	}
	$('#loading').hide();
}
function refreshUserList(){
<<<<<<< HEAD
	showAllUsers();
>>>>>>> 4960c91abbc3906ff507579290d7f80b15420c5b
=======
	//showAllUsers();
>>>>>>> cd118b8fac1cf7892657e3ffd0d229168b2db8e2
	onChangeFetchUsersByRoom();

}