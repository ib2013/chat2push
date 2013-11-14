
function graph(statsSent, statsReceived) {
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

function statsByDays() {


	var statsSent = null;
	var statsReceived = null;

	$
			.ajax({
				url : "https://pushapi.infobip.com/1/statistics/application/1a6cfc8b976c/notifications?time-type=month&month=11&year=2013",
				headers : {
					'Accept' : 'text/plain',
					'Content-type' : 'application/json',
					'Authorization' : 'Basic cHVzaGRlbW86cHVzaGRlbW8=',
				},
				method : 'GET',
				contentType : 'application/json',
				success : function(data, status, xhr) {

					statsSent = data.data[0].countSent;
					statsReceived=data.data[0].countReceived;
					//alert(byDays2);
					//statsSent=JSON.stringify(byDays);
					//statsReceived=JSON.stringify(byDays2);
					// statsSent=JSON.stringify(byDays.countSent[0]);

					// alert(statsSent);
					// statsReceived=byDays.countReceived;
					// alert(statsReceived);
					graph(statsSent, statsReceived);
				}

			});
}

