<div id="tab-container" class="tab-container">
	<%@ page pageEncoding="UTF-8"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<spring:url value="/" var="_basePath" />
	<!--  <div id="loading">
		<img id="loading-image" src="resources/images/loading.gif"
			alt="Loading..." />
	</div>-->


	<script type="text/javascript">
	/*$(document).ready(function() {
		$('#username').val();
		$('#username').val();
	}*/
		function checkAdmin() {
			var username = $('#username').val();
			var password = $('#password').val();
			if (username == "") return;
			if (password == "") return;
			alert("Provera admina " + username + " " + password);
			window.location.href = "/index";
		}
	</script>
	<div id='loginform'
		style="border: 0px solid; height: 500px; margin-top: 50px; margin-left: 350px;">
		<div id='channel_header'
			style="float: left; clear: both; width: 450px;">
			<label id='channel_list_tab' class='listOptions2'
				style="width: 450px;">Login form</label>
		</div>
		<div id='lists' class="listContent"
			style="width: 450px; height: 200px; clear: both;">
			<form method="post">
				<p>
					<input id='username' type="text" name="login" value="" placeholder="Username">
				</p>
				<p>
					<input id='password' type="password" name="password" value=""
						placeholder="Password">
				</p>
				<!-- <p class="remember_me">
					<label> <input type="checkbox" name="remember_me"
						id="remember_me"> Remember me on this computer
					</label>
				</p> -->
				<p class="submit">
					<input type="button"  value="Login" onClick="checkAdmin()">
				</p>
			</form>
		</div>

	</div>

	<!-- <div id='lists' class="listContent2" style="width: 450px; clear:both; margin-left:300px;">
			<table>

				<tr>
					<td>Name:</td>
					<td><input type='text' id='title' class='text_value'
						name='title' /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><input id='channel_description' class='text_value'
						name='channel_description'></td>
				</tr>
				<tr>
					<td>Type:</td>
					<td><select id="typeOfChannel">
							<option value="public" selected="selected">Public</option>
							<option value="private">Private</option>
					</select></td>
				<tr>
					<td colspan='2'><input type='submit' id='addNewChanelBtn'
						value='Add' class='submitBtn ' action="destination.html"
						onclick='addNewChannel(fetchAllChannels)' /></td>
				</tr>
			</table> 
		</div>-->

</div>

