function showAllUsers() {
	$('#loading').show();
	$
			.get(
					_basePath + "user/fetch",
					function(data, status, xhr) {
						if (status = "succes") {
							$('#list_view_users').html("");

							for (var i = 0; i < data[0].length; i++) {
								var username = data[0][i].username;
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

	$('#loading').hide();

}