var allusers = new Array();
var numofpages = 4;

function getColor(username) {
	var red = (username.charCodeAt(0)) * 5 % 200 + 50;
	var green = (username.charCodeAt(1) * 6) % 200 + 50;
	var blue = (username.charCodeAt(2) * 7) % 200 + 50;
	return "rgb(" + red + "," + green + "," + blue + ")";
}

function pageCount(callback) {
	$.ajax({
			    type: 'GET',
			    url:  _basePath + "user/fetchAllUsers",
			    success: function(data, status, xhr) {
					numofpages = Math.ceil(data.length/10.0);	
					callback();
				},	
			    error: function() {
			        callback(null);
			    }
			  });
}


function setPaging() {
	$('#page-selection').bootpag({
		total : numofpages
	}).on("page", function(event, num) {
		showAllUsers();
		showTenUsers(num);
	});
}

function showAllUsersSync(callback) {
	$.ajax({
	    type: 'GET',
	    url:  _basePath + "user/fetchAllUsers",
	    success: function(data, status, xhr) {
	    	for (var i = 0; i < data.length; i++) {
				var username = data[i].username;
				allusers[i] = username;
	    	}
			callback(1);
		},	
	    error: function() {
	        callback(null);
	    }
	  });
}

function showAllUsers() {

	$('#loading').show();
	$.get(_basePath + "user/fetchAllUsers", function(data, status, xhr) {
		for (var i = 0; i < data.length; i++) {
			var username = data[i].username;
			allusers[i] = username;
		}
	});
	$('#loading').hide();
}

function showTenUsers(num) {
	var number = (num - 1) * 10;
	$('#content').html("");
	for (var i = number; i < number + 10; i++) {
		if (allusers[i] != null) {
			//$('#content').append("<p>" + allusers[i] + "</p>");
			$('#content').append(
					"<p class='plistelem' onclick='statsByUser(this.id)' id='"
					+ allusers[i]
					+ "'><label style='float: left; width:20px; height:20px; background-color:"
					+ getColor(allusers[i])
					+ "'></label> &nbsp;"
					+ allusers[i] 
					+ "<label id='"
					+ allusers[i]
					+ "' class='removeList' onclick='deleteUser(this)'>&nbsp;x&nbsp;<label></p>"
					
				/*	
					
			"<p class='plistelem' id='"
			+ allusers[i]
			+ "'>"
			+ allusers[i]
			+ "<label id='"
			+ allusers[i]
			+ "' class='removeList' onclick='deleteUser(this)'>&nbsp;x&nbsp;<label></p>"*/);
		}
	}
}

function deleteUser(user) {

	//if (isNaN(user.id)) {
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
				
					showAllUsersSync(showTenUsers);
					
				},
				error: function(rez, status, xhr) {
					alert(xhr);
				}
			});

		}
/*	} else {
		$('#loading').hide();
		alert('Error to delete user.');
	}*/

	$('#loading').hide();

}
function onChangeFetchUsersByRoom() {
	$('#loading').show();
	var room = $("#select_room_list option:selected");
	if (room.attr("id") == "PR")
		$("#keyImage").show();
	else
		$("#keyImage").hide();
	var roomname = room.val();
	$('#list_rooms_users').html("");
	$('#list_users').html("");
	fetchUsersByRoom(roomname);
	fetchOpositeUsersByRoom(roomname);
	$('#loading').hide();
}
function fetchUsersByRoom(roomname) {

	$('#list_rooms_users').html("");
	// $('#list_users').html("");
	// showAllUsers();
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

							// $('#list_rooms_users').html("");

							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								// var
								// elementForRemove=document.getElementById(username);
								// alert(username);
								// if(elementForRemove!=null)

								// elementForRemove.remove();
								$('#list_rooms_users')
										.append(

												"<p class='plistelem'  draggable='true' ondragstart='drag(event)' id='"
														+ username
														+ "'><label style='float: left; width:20px; height:20px; background-color:"
														+ getColor(username)
														+ "'></label> &nbsp;"
														+ username + "</p>"
										/*
										 * + "<label id='" + username + "'
										 * class='removeList'
										 * onclick='deleteUser(this)'>&nbsp;x&nbsp;<label></p>"
										 */
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
	// $('#list_users').html("");
	// showAllUsers();
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

							// $('#list_rooms_users').html("");

							for (var i = 0; i < data.length; i++) {
								var username = data[i].username;
								// var
								// elementForRemove=document.getElementById(username);
								// elementForRemove.remove();
								$('#list_users')
										.append(

												"<p class='plistelem' draggable='true' ondragstart='drag(event)' id='"
														+ username
														+ "'><label style='float: left; width:20px; height:20px; background-color:"
														+ getColor(username)
														+ "'></label> &nbsp;"
														+ username + "</p>");
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
function refreshUserList() {
	// showAllUsers();
	onChangeFetchUsersByRoom();
	showAllUsers();
	showTenUsers(1);

}
