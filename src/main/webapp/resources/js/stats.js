function graphDays(statsSent, statsReceived) {
	$('#stats').highcharts({
		chart : {
			type : 'area'
		},
		title : {
			text : 'Statistics: day/messages'
		},
		subtitle : {
			text : 'Source: <a href="http://infobip.com">' + 'infobip</a>'
		},
		xAxis : {

		},
		yAxis : {
			title : {
				text : 'Messages'
			},
			labels : {
				formatter : function() {
					return this.value;
				}
			}
		},
		tooltip : {
			pointFormat : '{series.name} <b>{point.y:,.0f}</b>'
		},
		plotOptions : {
			area : {
				pointStart : 1,
				marker : {
					enabled : false,
					symbol : 'circle',
					radius : 1,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			name : 'Sent',
			data : statsSent
		}, {
			name : 'Received',
			data : statsReceived
		} ]
	});

}

function graphUser(username,data) {
	
 var stats=[];
 
	$.get(_basePath + "channel/fetch", function(data2, status, xhr) {
		JSON.stringify(data2);
		for (var i = 0; i < data2.length; i++) {
			var user=[];
			user.push(data2[i].name);
			user.push(data[data2[i].name]);
			stats.push(user);
		}

	    $('#stats').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: 'User activity:'+ username
	        },
	        tooltip: {
	    	    pointFormat: '{series.name} <b>{point.y}</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: false
	                },
	                showInLegend: true
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'Messages:',
	            data: stats
	        }]
	    });

	});
	

}

function statsByDays() {

	var date = new Date();
	var thisMonth = date.getMonth() + 1;
	var thisYear = date.getFullYear();
	var statsSent = null;
	var statsReceived = null;

	$
			.ajax({
				url : "https://pushapi.infobip.com/1/statistics/application/1a6cfc8b976c/notifications?time-type=month&month="
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
					
					graphDays(statsSent, statsReceived);
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

			graphUser(username,res);
		}
	});

}
