<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/" var="_basePath" />
<!-- <div id="loading">
	  <img id="loading-image" src="resources/images/loading.gif" alt="Loading..." />
</div> -->

<script type="text/javascript">
	//var _basePath = '${_basePath}';

	//$(document).ready(function() {
	//	documentReady(_basePath);
	//});

</script>

<div id='central'>
	<div id='centralContent' class="centralContent">
		<div id='headers'>
			<!-- <label id='rss_feed_list_tab' class='listOptions' onclick='changeTab(1)' >List of RSS feed</label> -->
			<!-- <label id='rss_chanal_tab' class='listOptions' onclick='changeTab(2)' >List of channel</label> -->
			<div id='room_header' style="float: left;">
				<!-- <label id='room_list_tab' class='listOptions'>List of Chat
					Rooms</label>  -->
					<div id="room_list_tab" class='listRoom'>
				<select id="select_room_list" style="width: 160px;">
					<option value="room1">Room1&nbsp;&nbsp;PR</option>
					<option value="room2">Room2&nbsp;&nbsp;PR</option>
					<option value="room3">Room3</option>
					<option value="room4">Room4</option>
				</select>
				<input type='button' id='deleteRoom'
						value='Delete'  onclick='' class='removeList' style="float:right;"/>
				</div>
			</div>
			<div id='user_header'>
				<label id='user_list_tab' class='listOptions'>List of Users</label>
			</div>
		</div>
		<div id='lists' style="clear: both;">

			<div id='list_rooms' class='listContent' style="float: left;"
				ondrop="drop(event)" ondragover="allowDrop(event)">
				<!--<ul id='list_view_rooms'  class='listview'>
			 	<li>ChatRoom1 asdasdasdasd</li>
				<li>ChatRoom2 asdasdasdasda</li>
				<li>ChatRoom3 asdadda</li>
			</ul> -->
			</div>

			<div id='list_users' class="listContent">
				<!-- 	<ul id='list_view_users' class='listview'>
					<li  dragondragstart="drag(event)" draggable="true" id="user1">User1&nbsp;&nbsp;&nbsp;5</li>
					<li>User 2</li>
					<li>User 3</li>
				</ul> -->
				<p class="plistelem" draggable="true" ondragstart="drag(event)" id="User1">User1</p>
				<p class="plistelem" draggable="true" ondragstart="drag(event)" id="User2">User2</p>
				<p class="plistelem" draggable="true" ondragstart="drag(event)" id="User3">User3</p>
			</div>
		</div>









		<div id='forms' class="centralContent">


			<table>
				<tr>
					<th colspan='2'><h3>Add new room</h3></th>
				</tr>
				<tr>
					<td>Title:</td>
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

</div>

