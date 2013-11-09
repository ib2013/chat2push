<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/" var="_basePath" />

<div id='list_options'>
	<label id='rss_feed_list_tab' class='listOptions' onclick='changeTab(1)' >List of RSS feed</label>
	<label id='rss_chanal_tab' class='listOptions' onclick='changeTab(2)' >List of channel</label>
	<div id='list_content'>
	<ul id='list_view'>
	
	</ul>
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
	<table>
		<tr><th colspan='2'><h3>Add new RSS adress</h3></th></tr>
		<tr><td>Source:</td><td><select onchange='useSelectValue()' class='select_fld' id='rss_source'><option value='0'>-- Select source value --</option></select></td></tr>
		<tr><td>RSS link:</td><td><input type='text' id='rss_uri' class='text_value' name='rss_uri' placeholder='http://example.rss' /></td></tr>
		<tr><td>RSS description:</td><td><input id='rss_description' class='text_value' name='rss_description'   ></td></tr>
		<tr><td colspan='2'><input type='button' value='Submit' class='submitBtn ' onclick='useAddFormValue()' /></td></tr>					
	</table>
		
		<table>
			<tr><th colspan='2'><h3>Add new channel</h3></th></tr>
			<tr><td>Title:</td><td><input type='text' id='title' class='text_value' name='title' /></td></tr>
			<tr><td>Description:</td><td><input id='chaneel_description' class='text_value' name='canal_description'  ></td></tr>
			<tr><td colspan='2'><input type='button' value='Submit' class='submitBtn ' onclick='addNewCanal()' /></td></tr>
		</table>
</div>
	
<script type="text/javascript">
var _basePath = '${_basePath}';

$( document ).ready(function() {	
	documentReady(_basePath);
});

</script>