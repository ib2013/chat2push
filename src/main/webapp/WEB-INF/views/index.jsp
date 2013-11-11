<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/" var="_basePath" />
<!-- <div id="loading">
	  <img id="loading-image" src="resources/images/loading.gif" alt="Loading..." />
</div> -->
<div id='central'>
	<div id='centralContent'>
		<div id='headers'>
			<!-- <label id='rss_feed_list_tab' class='listOptions' onclick='changeTab(1)' >List of RSS feed</label> -->
			<!-- <label id='rss_chanal_tab' class='listOptions' onclick='changeTab(2)' >List of channel</label> -->
			<div id='room_header' style="float: left;">
				<label id='room_list_tab' class='listOptions'>List of Chat
					Rooms</label>
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

			<div id='list_users' class="listContent" ondrop="drop(event)"
				ondragover="allowDrop(event)">
			<!-- 	<ul id='list_view_users' class='listview'>
					<li  dragondragstart="drag(event)" draggable="true" id="user1">User1&nbsp;&nbsp;&nbsp;5</li>
					<li>User 2</li>
					<li>User 3</li>
				</ul> -->
				<p draggable="true" ondragstart="drag(event)" id="User1">User1</p>
				<p draggable="true" ondragstart="drag(event)" id="User2">User2</p>
				<p draggable="true" ondragstart="drag(event)" id="User3">User3</p>	
			</div>
			</div>
		</div>
	</div>
</div>
<div id='forms'>
	<!-- Privremeno ova opcija nije dostupna. Ukoliko se implementira dinamicko dodavanje
	adaptera ova opcija bice omogucena 
	<table  >
		<tr><th colspan='2'><h3>Add new RSS source name</h3></th></tr>
		<tr><td>Source name:</td><td> <input type='text' id='rss_source_new' class='text_value' name='rss_source_new' /></td></tr>
		<tr><td colspan='2'><input type='button' value='Insert' class='submitBtn' onclick='useSourceFormValue()' /></td></tr>
	</table>
	-->
	<!-- <table>
		<tr><th colspan='2'><h3>Add new RSS adress</h3></th></tr>
		<tr><td>RSS link:</td><td><input type='text' id='rss_uri' class='text_value' name='rss_uri' placeholder='http://example.rss' /></td></tr>
		<tr><td>RSS description:</td><td><input id='rss_description' class='text_value' name='rss_description'  ></td></tr>
		<tr><td colspan='2'><input type='button' value='Submit' class='submitBtn ' onclick='addNewFeed()' /></td></tr>					

	</table> -->

	<table>
		<tr>
			<th colspan='2'><h3>Add new channel</h3></th>
		</tr>
		<tr>
			<td>Title:</td>
			<td><input type='text' id='title' class='text_value'
				name='title' /></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><input id='chaneel_description' class='text_value'
				name='canal_description'></td>
		</tr>
		<tr>
			<td colspan='2'><input type='button' id='addNewChanelBtn'
				value='Submit' class='submitBtn ' onclick='addNewChannel()' /></td>
		</tr>
	</table>
</div>
</div>
<script type="text/javascript">
	//var _basePath = '${_basePath}';

	//$(document).ready(function() {
	//	documentReady(_basePath);
	//});

	function allowDrop(ev) {
		ev.preventDefault();
	}

	function drag(ev) {
		ev.dataTransfer.setData("Text", ev.target.id);
	}

	function drop(ev) {
		ev.preventDefault();
		var data = ev.dataTransfer.getData("Text");
		ev.target.appendChild(document.getElementById(data));
	}
</script>