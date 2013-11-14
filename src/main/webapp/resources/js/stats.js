function statsByDays(){
	
	var byDays;
	var statsSent;
	var statsReceived;
	$(function () {
		$.ajax({
			url: "https://pushapi.infobip.com/1/statistics/application/9cabf301d3db/notifications?time-type=month&month=11&year=2013",
			headers: {
				'Accept': 'text/plain',
				'Content-type':'application/json',
				'Authorization': 'Basic cHVzaGRlbW86cHVzaGRlbW8=',
			},
			method: 'GET',
			contentType: 'application/json',
			success:function(data, status,xhr){
				byDays=data.data[0];
				alert(JSON.stringify(byDays));
				statsSent='['+byDays.countSent+']';
				alert(statsSent);
				statsReceived='['+byDays.countReceived+']';
				alert(statsReceived);
				}
		});
	

		$(function () {
	        $('#container').highcharts({
	            chart: {
	                type: 'area'
	            },
	            title: {
	                text: 'Statistics'
	            },
	            subtitle: {
	                text: 'Source: <a href="http://infobip.com">'+
	                    'infobip</a>'
	            },
	            xAxis: {
	                labels: {
	                    formatter: function() {
	                        return this.value; // clean, unformatted number for year
	                    }
	                }
	            },
	            yAxis: {
	                title: {
	                    text: 'Messages'
	                },
	                labels: {
	                    formatter: function() {
	                        return this.value;
	                    }
	                }
	            },
	            tooltip: {
	                pointFormat: '{series.name} <b>{point.y:,.0f}</b>'
	            },
	            plotOptions: {
	                area: {
	                    pointStart: 1,
	                    marker: {
	                        enabled: false,
	                        symbol: 'circle',
	                        radius: 1,
	                        states: {
	                            hover: {
	                                enabled: true
	                            }
	                        }
	                    }
	                }
	            },
	            series: [{
	                name: 'Sent',
	                data: [100,200,300]
	            }, {
	                name: 'Received',
	                data: [200,500,600]
	            }]
	        });
	    });
    });
}