package com.infobip.campus.chattopush.test;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static junit.framework.Assert.*;

import com.google.appengine.api.search.StatusCode;
import com.infobip.campus.chattopush.controllers.ChannelController;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.services.ChannelService;
import com.infobip.campus.chattopush.services.exceptions.ChannelException;

public class ChannelControllerTest {
	
	ChannelController controller;
	ChannelService channelService;
	
	@Before
    public void setup() throws ChannelException {
		channelService = mock(ChannelService.class);
		when(channelService.addChannel(any(ChannelModel.class))).thenReturn(true);
		
		controller = mock(ChannelController.class);
		controller.setChannelService(channelService);
	}
	
	@Test
	public void testAddChannel() throws ChannelException {
		com.infobip.campus.chattopush.services.enums.StatusCode result = controller.addChannel(new ChannelModel());
		assertEquals("success", result);
	}

}
