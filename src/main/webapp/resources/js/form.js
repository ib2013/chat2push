var boolFeedClick;
var boolChanalClick;
var refreshIntervalId;

documentReady = function(basePath) {
	boolFeedClick = true;
	boolChanalClick = false;
	$('#loading').show();
	var definitionWindowWidth = $(window).width();
	var definitionWindowHeight = $(window).height();
	$('#content').css('width', definitionWindowWidth);
	$('#content').css('height', definitionWindowHeight);
	addListElement(1);
};

function addNewFeed() {
	var rssUri = $('#rss_uri').val();
	var rssDescription = $('#rss_description').val();
	var rssJson = new Object();
	if (rssUri.length != 0 && rssDescription.length != 0) {
		rssJson.rssUrl = rssUri;
		rssJson.description = rssDescription;
		$('#loading').show();
		$.ajax({
			url : _basePath + "feed/add",
			headers : {
				'X-AppEngine-Cron' : true,
				'Accept' : 'text/plain',
				'Content-Type' : 'application/json'
			},
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(rssJson),
			success : function(rez, status, xhr) {
				if (rez == "success") {
					addListElement(1);
					changeTab(1);
					$('#rss_source_new').val("");
					$('#rss_uri').val("");
					$('#rss_description').val("");
					$('#rss_uri').prop('disabled', false);
					$('#rss_description').prop('disabled', false);
					boolFeedClick = false;

					$('#loading').hide();
					alert("New RSS adress is inserted");
				} else {
					$('#loading').hide();
					alert("Fail add feed");
				}
			}
		});
	} else {
		$('#loading').hide();
		alert("Fields are required ");
	}
}

function addNewChannel() {
	var channelTitle = $('#title').val();
	var chaneelDescription = $('#channel_description').val();
	var typeOfChannel = $("#typeOfChannel option:selected").text();

	alert(typeOfChannel);
	/*
	 * if (channelTitle.length != 0 ) { channelJson = new Object();
	 * channelJson.name = channelTitle; channelJson.description =
	 * chaneelDescription; $.ajax({ url: _basePath +"channel/add", headers: {
	 * 'Accept': 'text/plain', 'Content-Type': 'application/json' }, method:
	 * 'POST', contentType: 'application/json; charset=utf-8', data:
	 * JSON.stringify(channelJson), success: function(rez, status, xhr) { if(rez ==
	 * "success"){ $('#title').val(""); $('#chaneel_description').val("");
	 * addListElement(2); changeTab(2); $('#loading').hide(); alert("New channel
	 * is inserted"); } else { $('#loading').hide(); alert("Fail add channel"); } }
	 * }); } else { $('#loading').hide(); alert("Title required "); }
	 */
}

function changeTab(value) {
	clearInterval(refreshIntervalId);
	if (value == 1) {
		clearInterval(refreshIntervalId);
		if (boolFeedClick) {
			boolFeedClick = true;
			boolChanalClick = false;
			$('#rss_feed_list_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_chanal_tab').css('opacity', '0.7');
		} else {
			boolFeedClick = true;
			boolChanalClick = false;
			$('#rss_feed_list_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_chanal_tab').css('opacity', '0.7');
			$('#rss_feed_list_tab').css('opacity', '1');
			$('#rss_chanal_tab').css('box-shadow', '0px 0px 0px #888888');
			$('#list_view').html("");
			addListElement(1);
		}
	} else if (value == 2) {
		refreshIntervalId = setInterval(function() {
			addListElement(2);
		}, 20000);
		if (boolChanalClick) {
			boolFeedClick = false;
			boolChanalClick = true;
			$('#rss_chanal_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_feed_list_tab').css('opacity', '0.7');
		} else {
			boolFeedClick = false;
			boolChanalClick = true;
			$('#rss_chanal_tab').css('box-shadow', '8px 8px 5px #888888');
			$('#rss_chanal_tab').css('opacity', '1');
			;
			$('#rss_feed_list_tab').css('opacity', '0.7');
			$('#rss_feed_list_tab').css('box-shadow', '0px 0px 0px #888888');
			$('#list_view').html("");
			addListElement(2);
		}
	}
}

function addListElement(value) {
	if (value == 1) {
		$('#loading').show();
		$
				.get(
						_basePath + "feed/fetch",
						function(data, status, xhr) {
							if (status == "success") {
								$('#list_view').html("");
								for (var i = 0; i < data[0].length; i++) {
									var rssName = data[0][i].rssUrl;
									var rssDescription = data[0][i].description;
									$('#list_view')
											.append(
													"<li title='"
															+ rssName
															+ " - "
															+ rssDescription
															+ "'>"
															+ rssName.substr(0,
																	40)
															+ " - "
															+ rssDescription
																	.substr(0,
																			15)
															+ "<label id='"
															+ rssName
															+ "' class='removeList' onclick='removeRssFeed(this)'>Remove<label> </li>");

								}
								$('#loading').hide();
							} else {
								$('#loading').hide();
								alert('Error loading Rss element.');
							}
						});
	} else if (value == 2) {
		$('#loading').show();
		$
				.get(
						_basePath + "channel/fetch",
						function(data, status, xhr) {

							if (JSON.stringify(data) != "") {
								$('#list_view').html("");
								for (var i = 0; i < data.length; i++) {
									var chaneelCounter = data[i].counter;
									var chaneelName = data[i].channelModel.name;

									$('#loading').hide();
									$('#list_view')
											.append(
													"<li title='"
															+ chaneelName
															+ "'>"
															+ chaneelName
																	.substr(0,
																			40)
															+ "  <label id='"
															+ chaneelName
															+ "' class='removeList' onclick='removeChannel(this)'>Remove</label> <span class='badge'>"
															+ chaneelCounter
															+ "</span></li>");
								}
							} else {
								alert('Error loading channel.');
							}
						});
	}
}

function removeRssFeed(listElement) {
	if ((isNaN(listElement.id))) {
		if (confirm('Are you sure you want to delete RSS Feed?')) {
			var rssJson = new Object();
			rssJson.rssUrl = listElement.id;
			rssJson.description = "";
			$('#loading').show();
			$.ajax({
				url : _basePath + "feed/delete",
				headers : {
					'Accept' : 'text/plain',
					'Content-Type' : 'application/json'
				},
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(rssJson),
				success : function(rez, status, xhr) {
					if (rez == 'success') {
						$('#list_view').html("");
						addListElement(1);
						changeTab(1);
						$('#loading').hide();
					} else {
						$('#loading').hide();
						alert('Error');
					}
				}
			});
		}
	} else {
		$('#loading').hide();
		alert('Error to delete RSS Feed');
	}
}

function removeChannel(listElement) {
	if (isNaN(listElement.id)) {
		if (confirm('Are you sure you want to delete ' + listElement.id + '?')) {
			$('#loading').show();
			var channelJson = new Object();
			channelJson.name = listElement.id;
			channelJson.description = "";
			$('#loading').show();
			$.ajax({
				url : _basePath + "channel/delete",
				headers : {
					'Accept' : 'text/plain',
					'Content-Type' : 'application/json'
				},
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(channelJson),
				success : function(rez, status, xhr) {
					if (rez == 'success') {
						$('#list_view').html("");
						addListElement(2);
						changeTab(2);
						$('#loading').hide();
					} else {
						$('#loading').hide();
						alert('Error');
					}
				}
			});
		}
	} else {
		$('#loading').hide();
		alert('Error to delete channel');
	}
}