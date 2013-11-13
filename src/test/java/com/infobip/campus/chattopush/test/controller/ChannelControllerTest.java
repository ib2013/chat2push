package com.infobip.campus.chattopush.test.controller;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;

import com.infobip.campus.chattopush.controllers.ChannelController;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.services.ChannelService;

public class ChannelControllerTest {

	ChannelController controller;
	ChannelService channelService;

	@Before
	public void setup() {
		channelService = mock(ChannelService.class);
		when(channelService.addChannel(any(ChannelModel.class))).thenReturn(
				true);

		controller = new ChannelController();
		controller.setChannelService(channelService);
	}

	@Test
	public void testAddChannel() {
		String result = controller.addChannel(new ChannelModel());
		assertEquals("success", result);
	}

}
