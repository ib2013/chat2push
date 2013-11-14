<div id="tab-container" class="tab-container">
	<%@ page pageEncoding="UTF-8"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<spring:url value="/" var="_basePath" />
	<div id="loading">
		<img id="loading-image" src="resources/images/loading.gif"
			alt="Loading..." />
	</div>

	<script type="text/javascript">
		var _basePath = '${_basePath}';

		$(document).ready(function() {
			$('#tab-container').easytabs();
			documentReady(_basePath);
		});
		
		
		
		
		
	/*	
		$(document).ready(function(){
			$('#paging_container1').pajinate();
		});		*/
		
	</script>

	<ul class='etabs'>
		<li class='tab'><a href="#tabscp">Control Panel</a></li>
		<li class='tab'><a href="#tabsum">User management</a></li>
		<li class='tab'><a href="#tabscm">Room management</a></li>
		<li class='tab'><a onclick="statsByDays()" href="#tabsstat">Statistics</a></li>
	</ul>
	<div id="tabscp" style="padding-top: 20px; padding-bottom: 20px;">
		<div id='central'>
			<div id='centralContent' class="centralContent">
				<div id='headers'>

					<div id='room_header' style="float: left;">
						<div id="room_list_tab" class='listRoom'>

							<select id="select_room_list" style="width: 140px;"
								onChange="onChangeFetchUsersByRoom()">
							</select> <img id="keyImage" src="resources/images/key.gif" />
							<!-- <input
								type='button' id='deleteRoom' value='Delete'
								onclick='deleteChannel()' class='removeList'
								style="float: right;" /> -->
						</div>
					</div>

					<div id='user_header'>
						<label id='user_list_tab' class='listOptions'>List of
							Users <input type='button' id='deleteRoom' value='Refresh'
							onclick='refreshUserList()' class='removeList'
							style="float: right;" />
						</label>
					</div>
				</div>
				<div id='lists' style="clear: both;">

					<div id='list_rooms_users' class='listContent' style="float: left;"
						ondrop="drop(event)" ondragover="allowDrop(event)"></div>

					<div id='list_users' class="listContent" ondrop="drop(event)"
						ondragover="allowDrop(event)"></div>
				</div>

				<br /> <br />
				<div id='headers'></div>
			</div>
			<br /> <br />

		</div>

		
	</div>
	<div id="tabsum">
		<div id="paging_container1" class="container">

			<h2>List of Users</h2>


			<ul id="UserList" class="content" style="text-align: center;">
					 <li><p>One</p></li> 
					 <li><p>Two</p></li> 
					 <li><p>Three</p></li> 
					 <li><p>Four</p></li> 
					 <li><p>Five</p></li> 
					 <li><p>Six</p></li> 
					 <li><p>Seven</p></li> 
					 <li><p>Eight</p></li> 
					 <li><p>Nine</p></li> 
					 <li><p>Ten</p></li> 
					 <li><p>Eleven</p></li> 
					 <li><p>Twelve</p></li> 
					 <li><p>Thirteen</p></li> 
					 <li><p>Fourteen</p></li> 
					 <li><p>Fifteen</p></li> 
					 <li><p>Sixteen</p></li> 
			</ul>
			<div class="page_navigation"></div>
		</div>
	</div>
	<div id="tabscm" style="padding-top: 20px; padding-bottom: 20px;">
		<h2></h2>

		<div id='centralCM'>
			<div id='centralContentCM' class="centralContent">
				<div id='headersCM'>

					<div id='channel_header' style="float: left;">
						<label id='channel_list_tab' class='listOptions'>List of
							Rooms </label>
					</div>

					<div id='room_header' style="margin-left: 50px;">
						<label id='user_list_tab' class='listOptions2'
							style="margin-left: 50px;">Add new room</label>
					</div>
				</div>
				<div id='listsCM' style="clear: both;">
					<div id='list_channels' class="listContent" style="float: left;"></div>

					<div id='lists' class="listContent2">
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
								<td colspan='2'><input type='button' id='addNewChanelBtn'
									value='Add' class='submitBtn ' onclick='addNewChannel()' /></td>
							</tr>
						</table>
					</div>
				</div>

				<br /> <br />
				<div id='headers'></div>
			</div>
			<br /> <br />

		</div>
	</div>
	<div id="tabsstat" style="padding-top: 20px; padding-bottom: 20px;">
	<div id="stats"
			style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	</div>
</div>

<script>
			/*$(document).ready(function(){
				$('li:odd, .content > *:odd').css('background-color','#FFD9BF');
			});*/
		</script>
