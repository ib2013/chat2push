var boolFeedClick;
var boolChanalClick;
var refreshIntervalId;


documentReady = function(basePath){
	boolFeedClick = true;
	boolChanalClick = false;
	$('#loading').show();
	$.get(basePath + "/RssSourceServlet", function(data, status, xhr) {
		if(data != "") {
			d = jQuery.parseJSON(data);
			for(var i = 0; i<d.length; i++) {
				var newValue = d[i].id;
				var newSourceName = d[i].type; 
				$('#rss_source').append("<option value='"+newValue+"'>"+newSourceName+"</option>");
				$('#loading').hide();
			}
		}
	});
	var definitionWindowWidth = $( window ).width();
	var definitionWindowHeight = $( window ).height();
	$('#content').css('width', definitionWindowWidth);
	$('#content').css('height', definitionWindowHeight);
	addListElement(1);
};

$(function () {
	$('#rss_source_new').keypress(function (event) {
		if (event.which == 13) {
			useSourceFormValue();
		}
	});
});

function useSourceFormValue() {
	var newSourceName = $('#rss_source_new').val();
	var newValue = parseInt($('#rss_source option:last-child').val())+1;
	if(newSourceName.length == 0) {
		alert("Insert value name!");
	}
	else {
		$('#loading').show();
		$.post(_basePath +"/RssSourceServlet", { source_name: newSourceName }, function(rez, status, xhr) {
			if(rez == "success"){
					$('#rss_source').append("<option value='"+newValue+"'>"+newSourceName+"</option>");
					$('#rss_source_new').val("");
					$('#rss_source').val(newValue);
					$('#rss_uri').val("");
					$('#rss_description').val("");
					$('#loading').hide();
					$('#rss_uri').prop('disabled', false);
					$('#rss_description').prop('disabled', false);
					
					alert("New RSS source is inserted");
				}
				else {
					$('#loading').hide();
					alert("Fail");
				}				
		});
	}
}

function useSelectValue() {
	var selectValue = parseInt($('#rss_source').val());
	if(selectValue != 0) {
		$('#rss_uri').prop('disabled', false);
		$('#rss_description').prop('disabled', false);
	}
	else {
		$('#rss_uri').prop('disabled', true);
		$('#rss_description').prop('disabled', true);
	}
		
}

function useAddFormValue() {
	//var selectValue = parseInt($('#rss_source').val());
	var rssUri = $('#rss_uri').val();
	var rssDescription = $('#rss_description').val();
		var rssJson = new Object();
		//rssJson.rss_fk = selectValue;
		if(rssUri.length !=0 && rssDescription.length !=0) {
			rssJson.rss_uri = rssUri;
			rssJson.rss_description = rssDescription;
			$('#loading').show();
			$.post(_basePath +"feed/add", { rss_adress: JSON.stringify(rssJson) }, function(rez, status, xhr) {
				if(rez == "success"){
						$('#rss_source_new').val("");
						$('#rss_uri').val("");
						$('#rss_description').val("");
						$('#rss_uri').prop('disabled', false);
						$('#rss_description').prop('disabled', false);
						addListElement(1);
						$('#loading').hide();
						alert("New RSS adress is inserted");
					}
					else {
						$('#loading').hide();
						alert("Fail");
					}

			});
		}
		else {
			$('#loading').hide();
			alert("Fields are required ");
		}
	}
	else {
		$('#loading').hide();
		alert("Insert all values!");
	}
}

function addNewCanal() {
	var chaneelTitle = $('#title').val();
	var chaneelDescription = $('#chaneel_description').val();
	if (chaneelTitle.length != 0 ) {
		var chaneelJson = new Object();
		chaneelJson.title = chaneelTitle;
		chaneelJson.description = chaneelDescription;
		$('#loading').show();
		$.post(_basePath +"channel/add", { rss_chaneel: JSON.stringify(chaneelJson) }, function(rez, status, xhr) {
			if(rez == "success"){
					$('#title').val("");
					$('#chaneel_description').val("");
					addListElement(2);
					changeTab(2);
					$('#loading').hide();
					alert("New chaneel is inserted");
				}
				else {
					$('#loading').hide();
					alert("Fail");
				}
		});
	}
	else {
		$('#loading').hide();
		alert("Title required ");
	}
}

function changeTab(value) {
	clearInterval(refreshIntervalId);
	if(value == 1){
		clearInterval(refreshIntervalId);
		if(boolFeedClick){
			boolFeedClick = true;
			boolChanalClick = false;
			$('#rss_feed_list_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_chanal_tab').css('opacity', '0.7');
		}
		else {
			boolFeedClick = true;
			boolChanalClick = false;
			$('#rss_feed_list_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_chanal_tab').css('opacity', '0.7');
			$('#rss_feed_list_tab').css('opacity', '1');
			$('#rss_chanal_tab').css('box-shadow', '0px 0px 0px #888888');
			$('#list_view').html("");
			addListElement(1);
		}
	}
	else if(value == 2) {
		refreshIntervalId = setInterval(function(){addListElement(2);},5000);
		if(boolChanalClick){
			boolFeedClick = false;
			boolChanalClick = true;
			$('#rss_chanal_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_feed_list_tab').css('opacity', '0.7');
		}
		else {
			boolFeedClick = false;
			boolChanalClick = true;
			$('#rss_chanal_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_chanal_tab').css('opacity', '1');;
			$('#rss_feed_list_tab').css('opacity', '0.7');
			$('#rss_feed_list_tab').css('box-shadow', '0px 0px 0px #888888');
			$('#list_view').html("");
			addListElement(2);
		}
	}
}

function addListElement(value) {
	if(value == 1){
		$('#loading').show();
		$.get(_basePath +"/RssPopisServlet",{value:value},  function(data, status, xhr) {
			if(data != ""){
				list = jQuery.parseJSON(data);
				$('#list_view').html("");
				for(var i = 0; i<list.length; i++) {
					var rssId = list[i].id;
					var rssName = list[i].Rss;
					var rssDescription = list[i].rss_opis;
					$('#list_view').append("<li title='"+rssName+" - "+ rssDescription +"'>"+rssName.substr(0,40)+" - "+ rssDescription.substr(0,15) +"<label id='"+rssId+"' class='removeList' onclick='removeRssFeed(this)'>Remove<label> </li>");
					$('#loading').hide();
				}
			
			}
			else {
				$('#loading').hide();
				alert('Error');
			}
		});
	}
	else if (value == 2) {
		$('#loading').show();
		$.get(_basePath +"channel/fetch",{value: value},  function(data, status, xhr) {
			if(data != ""){
				$('#list_view').html("");
				list = jQuery.parseJSON(data);
				for(var i = 0; i<list.length; i++) {
					var chaneelName = list[i].ime;
					//var chaneelDescription = list[i].opis;	
					var chaneelCounter = list[i].brojac;
					$('#loading').hide();
					$('#list_view').append("<li title='"+chaneelName+"'>"+chaneelName.substr(0,40)+"  <label id='"+chaneelName+"' class='removeList' onclick='removeChannel(this)'>Remove</label> <span class='badge'>"+chaneelCounter+"</span></li>");
				}
			}
			else {
				alert('Error');
			}
		});
	}
}

function removeRssFeed(listElement){
	
	if(!(isNaN(listElement.id))){
		if(confirm('Are you sure you want to delete RSS Feed?')){
			$('#loading').show();
			$.post(_basePath +"/RssDeleteElemntListServlet", { id: listElement.id }, function(data, status, xhr) {
				if(data == 'success'){
					$('#list_view').html("");
					addListElement(1);
					$('#loading').hide();
				}
				else {
					$('#loading').hide();
					alert('Error');
				}
		});
		}
	}
	else {
		$('#loading').hide();
		alert('Error to delete RSS Feed');
	}
}

function removeChannel(listElement){
	if(isNaN(listElement.id)){
		if(confirm('Are you sure you want to delete '+ listElement.id +'?')){
			$('#loading').show();
			$.post(_basePath +"channel/delete", { channel_name: listElement.id }, function(data, status, xhr) {
				if(data == 'success'){
					$('#list_view').html("");
					addListElement(2);
					$('#loading').hide();
				}
				else {
					$('#loading').hide();
					alert('Error');
				}
		});
		}
	}
	else {
		$('#loading').hide();
		alert('Error to delete channel');
	}
}