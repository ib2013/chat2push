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
			pageCount(setPaging);
			showAllUsersSync(showTenUsers);
			$('#tab-container').easytabs();
			documentReady(_basePath);
			//	showAllUsers();
			//	showTenUsers(1);
		});
	</script>

	<ul class='etabs'>
		<li class='tab'><a href="#tabscp">Control Panel</a></li>
		<li class='tab'><a href="#tabsum">User management</a></li>
		<li class='tab'><a href="#tabscm">Room management</a></li>
		<li class='tab'><a onclick="allStats()" href="#tabsstat">Statistics</a></li>
	</ul>
	<div id="tabscp" style="padding-top: 20px; padding-bottom: 20px;">
		<div id='central'>
			<div id='centralContent' class="centralContent">
				<div id='headers' style="margin-left: 150px;">

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
						<div id="room_list_tab" class='listOptionsUsers'>
							<label id='user_list_tab'>List of Users <!-- <input type='button' id='deleteRoom' value='Refresh'
							onclick='refreshUserList()' class='removeList'
							style="float: right;" /> -->
							</label>
						</div>
					</div>
				</div>
				<div id='lists' style="clear: both;">

					<div id='list_rooms_users' class='listContent'
						style="float: left; margin-left: 200px;" ondrop="drop(event)"
						ondragover="allowDrop(event)"></div>

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
		<!-- <div id="paging_container1" class="container">
-->

		<div id='lists2' style="clear: both;">
			<div id='centralContent' class="centralContent"
				style="margin-top: 20px;">
				<div id="content" class='listContent' style="float: left;"></div>
				<div id='user_graph' class="listContent" style="width: 450px;">
				</div>
			</div>
		</div>
		<div id="page-selection"></div>
		<script>
			//showAllUsers();
			//doThis(doThat);
		</script>

	</div>
	<div id="tabscm" style="padding-top: 20px; padding-bottom: 20px;">
		<h2></h2>

		<div id='centralCM'>
			<div id='centralContentCM' class="centralContent">
				<div id='headersCM'>

					<div id='channel_header' style="float: left; margin-left: 50px;">
						<label id='channel_list_tab' class='listOptions'>List of
							Rooms </label>
					</div>

					<div id='room_header' style="margin-left: 50px;">
						<label id='user_list_tab' class='listOptions2'
							style="margin-left: 50px; width: 450px;">Add new room</label>
					</div>
				</div>
				<div id='listsCM' style="clear: both;">
					<div id='list_channels' class="listContent"
						style="float: left; height: 630px"></div>

					<div id='lists' class="listContent2" style="width: 450px;">
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
									value='Add' class='submitBtn '
									onclick='addNewChannel(fetchAllChannels)' /></td>
							</tr>
						</table>
					</div>
					<div id='room_graph' class="listContent2"
						style="margin-top: 20px; width: 450px; height: 450px;">
						
						
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
			style="height: 400px;  margin: auto"></div>
			<hr/>
			<div id="stats2"
			style="height: 400px;  margin: auto"></div>
	</div>
</div>

