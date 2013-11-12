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
		documentReady(_basePath);
	});
</script>

<div id='central'>
	<div id='centralContent' class="centralContent">
		<div id='headers'>
			
			<div id='room_header' style="float: left;">
				<div id="room_list_tab" class='listRoom'>

					<select id="select_room_list" style="width: 160px;" onChange="onChangeFetchUsersByRoom()">
					</select> 
					<input type='button' id='deleteRoom' value='Delete'
						onclick='deleteChannel()' class='removeList' style="float: right;" />
				</div>
			</div>
			<div id='user_header'>
				<label id='user_list_tab' class='listOptions'>List of ALL
					Users</label>
			</div>
		</div>
		<div id='lists' style="clear: both;">

			<div id='list_rooms_users'  class='listContent' style="float: left;"
				ondrop="drop(event)" ondragover="allowDrop(event)">
	
	
			</div>

			<div id='list_users' class="listContent">
			
				<p class="plistelem" draggable="true" ondragstart="drag(event)"
					id="User1">
					User1<label class="removeList">&nbsp;x&nbsp;</label>
				</p>
				<p class="plistelem" draggable="true" ondragstart="drag(event)"
					id="User2">
					User2<label class="removeList">&nbsp;x&nbsp;</label>
				</p>
				<p class="plistelem" draggable="true" ondragstart="drag(event)"
					id="User3">
					User3<label class="removeList">&nbsp;x&nbsp;</label>
				</p>

			</div>
		</div>

<br/><br/>
<div id='headers'>
			
		<div id='room_header' class="centralContent">
<label id='user_list_tab' class='listOptions'>Add new room</label>
</div>
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
	</div>
	<br /> <br />

</div>

