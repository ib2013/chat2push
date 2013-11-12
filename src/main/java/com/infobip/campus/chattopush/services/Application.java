package com.infobip.campus.chattopush.services;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import com.infobip.campus.chattopush.models.ChannelModel;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		
		List<ChannelModel> ch = ChannelModel.findAllChannelModels();
		
		for (ChannelModel c : ch)
			System.out.println(c.getName());
		}
}