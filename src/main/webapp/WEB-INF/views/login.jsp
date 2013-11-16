<div id="tab-container" class="tab-container">
	<%@ page pageEncoding="UTF-8"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<script type="text/javascript">
		$(document).ready(function() {
			if ($.session.get('administratorz') != null) {
				window.location.href = "/index";
			}
		});
		function loginAdmin() {
			var username = $('#username').val();
			var password = $('#password').val();
			if (username != "admin")
				return;
			if (password != "admin")
				return;
			$.session.set('administratorz', username);
			window.location.href = "/index";
		}
	</script>
	<div id='loginform'
		style="border: 0px solid; height: 500px; margin-top: 50px; margin-left: 400px;">
		<div id='channel_header'
			style="float: left; clear: both; width: 450px;">
			<label id='channel_list_tab' class='listOptions2'
				style="width: 450px;">Login form</label>
		</div>
		<div id='lists' class="listContent"
			style="width: 450px; height: 200px; clear: both;">
			<form method="post">
				<p>
					<input id='username' type="text" name="login" value=""
						placeholder="Username">
				</p>
				<p>
					<input id='password' type="password" name="password" value=""
						placeholder="Password">
				</p>
				<p>
					<input type="button" value="Login" onClick="loginAdmin()">
				</p>
			</form>
		</div>

	</div>
</div>

