var appId = "1a6cfc8b976c"; // id aplikacije na infobip
function allStats() {

	statsByDays();
	statsToday();
}

function graphDays(statsSent, statsReceived, month, year) {
	var date = new Date();
	var thisMonth = date.getMonth();
	var thisYear = date.getFullYear();

	$('#stats')
			.highcharts(
					{
						chart : {
							zoomType : 'x',
							spacingRight : 20
						},
						title : {
							text : 'Stats days'
						},
						subtitle : {
							text : document.ontouchstart === undefined ? 'Click and drag in the plot area to zoom in'
									: 'Pinch the chart to zoom in'
						},
						xAxis : {
							type : 'datetime',
							maxZoom : 14 * 24 * 3600000, // fourteen days
							title : {
								text : null
							}
						},
						yAxis : {
							title : {
								text : 'Messages'
							}
						},
						tooltip : {
							shared : true
						},
						legend : {
							enabled : false
						},
						plotOptions : {
							area : {
								fillColor : {
									linearGradient : {
										x1 : 0,
										y1 : 0,
										x2 : 0,
										y2 : 1
									},
									stops : [
											[
													0,
													Highcharts.getOptions().colors[0] ],
											[
													1,
													Highcharts
															.Color(
																	Highcharts
																			.getOptions().colors[0])
															.setOpacity(0).get(
																	'rgba') ] ]
								},
								lineWidth : 1,
								marker : {
									enabled : false
								},
								shadow : false,
								states : {
									hover : {
										lineWidth : 1
									}
								},
								threshold : null
							}
						},

						series : [ {
							type : 'area',
							name : 'Messages',
							pointInterval : 24 * 3600 * 1000,
							pointStart : Date.UTC(thisYear, thisMonth, 01),
							data : statsSent
						} ]
					});

}

function graphUser(username, data) {

	var stats = [];
	var allMessages = 0;
	$.get(_basePath + "channel/fetch", function(data2, status, xhr) {
		JSON.stringify(data2);
		for (var i = 0; i < data2.length; i++) {
			var user = [];
			user.push(data2[i].name);
			allMessages += data[data2[i].name];
			user.push(data[data2[i].name]);
			stats.push(user);
		}

		$('#user_graph').highcharts(
				{
					chart : {
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : 'User:<b>' + username + '</b><br/>Messages:<b>'
								+ allMessages + '</b>'
					},
					tooltip : {
						pointFormat : '{series.name} <b>{point.y}</b>'
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							dataLabels : {
								enabled : false
							},
							showInLegend : true
						}
					},
					series : [ {
						type : 'pie',
						name : 'Messages:',
						data : stats
					} ]
				});

	});

}

function graphRooms(data) {

	var stats = [];
	var allMessages = 0;
	$.get(_basePath + "channel/fetch", function(data2, status, xhr) {
		JSON.stringify(data2);
		for (var i = 0; i < data2.length; i++) {
			var user = [];
			user.push(data2[i].name);
			allMessages += data[data2[i].name];
			user.push(data[data2[i].name]);
			stats.push(user);
		}

		$('#room_graph').highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : 'Number of messages:<b>' + allMessages + '</b>'
			},
			tooltip : {
				pointFormat : '{series.name} <b>{point.y}</b>'
			},
			xAxis : {
				title : {
					text : 'Rooms'
				},
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : false
					},
					showInLegend : true
				}
			},
			series : [ {
				type : 'pie',
				name : 'Messages:',
				data : stats
			} ]
		});

	});

}

function graphToday(statsSent, statsReceived) {

	var date = new Date();
	var thisDay = date.getDate();
	var thisMonth = date.getMonth();
	var thisYear = date.getFullYear();

	$('#stats2')
			.highcharts(
					{
						chart : {
							zoomType : 'x',
							spacingRight : 20
						},
						title : {
							text : 'Stats hours'
						},
						subtitle : {
							text : document.ontouchstart === undefined ? 'Click and drag in the plot area to zoom in'
									: 'Pinch the chart to zoom in'
						},
						xAxis : {
							type : 'datetime',
							maxZoom : 3600000, // fourteen days
							title : {
								text : null
							}
						},
						yAxis : {
							title : {
								text : 'Messages'
							}
						},
						tooltip : {
							shared : true
						},
						legend : {
							enabled : false
						},
						plotOptions : {
							area : {
								fillColor : {
									linearGradient : {
										x1 : 0,
										y1 : 0,
										x2 : 0,
										y2 : 1
									},
									stops : [
											[
													0,
													Highcharts.getOptions().colors[0] ],
											[
													1,
													Highcharts
															.Color(
																	Highcharts
																			.getOptions().colors[0])
															.setOpacity(0).get(
																	'rgba') ] ]
								},
								lineWidth : 1,
								marker : {
									enabled : false
								},
								shadow : false,
								states : {
									hover : {
										lineWidth : 1
									}
								},
								threshold : null
							}
						},

						series : [ {
							type : 'area',
							name : 'Messages',
							pointInterval : 3600 * 1000,
							pointStart : Date.UTC(thisYear, thisMonth, thisDay),
							data : statsSent
						} ]
					});

}

function statsByDays() {

	var date = new Date();
	var thisMonth = date.getMonth() + 1;
	var thisYear = date.getFullYear();
	var statsSent = null;
	var statsReceived = null;

	$.ajax({
		url : "https://pushapi.infobip.com/1/statistics/application/" + appId
				+ "/notifications?time-type=month&month=" + thisMonth
				+ "&year=" + thisYear + "",
		headers : {
			'Accept' : 'text/plain',
			'Content-type' : 'application/json',
			'Authorization' : 'Basic cHVzaGRlbW86cHVzaGRlbW8=',
		},
		method : 'GET',
		contentType : 'application/json',
		success : function(data, status, xhr) {

			statsSent = data.data[0].countSent;
			statsReceived = data.data[0].countReceived;

			graphDays(statsSent, statsReceived);
		}

	});
}

function statsToday() {

	var date = new Date();
	var thisDay = date.getDate();
	var thisMonth = date.getMonth() + 1;
	var thisYear = date.getFullYear();
	var statsSent = null;
	var statsReceived = null;

	$.ajax({
		url : "https://pushapi.infobip.com/1/statistics/application/" + appId
				+ "/notifications?time-type=day&day=" + thisDay + "&month="
				+ thisMonth + "&year=" + thisYear + "",
		headers : {
			'Accept' : 'text/plain',
			'Content-type' : 'application/json',
			'Authorization' : 'Basic cHVzaGRlbW86cHVzaGRlbW8=',
		},
		method : 'GET',
		contentType : 'application/json',
		success : function(data, status, xhr) {

			statsSent = data.data[0].countSent;
			statsReceived = data.data[0].countReceived;

			graphToday(statsSent, statsReceived);
		}

	});
}

function statsByUser(username) {

	var userJson = new Object();
	userJson.username = username;

	$.ajax({
		url : _basePath + "user/fetchUserStatistics",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify(userJson),
		success : function(res, status, xhr) {

			graphUser(username, res);
		}
	});

}

function statsRoom() {
	$.ajax({
		url : _basePath + "channel/channelStatistic",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		method : 'GET',
		contentType : 'application/json',

		success : function(res, status, xhr) {

			graphRooms(res);
		}
	});

}
