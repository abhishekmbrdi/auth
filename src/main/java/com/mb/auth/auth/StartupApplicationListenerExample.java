package com.mb.auth.auth;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.mb.auth.auth.loader.AuthLoader;

@Component
public class StartupApplicationListenerExample implements 
  ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ApplicationContext ctx;
	
    @Override 
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
        	Resource resource = ctx.getResource("classpath:roles.txt");
        	if(AuthLoader.INSTANCE.getEntitlements().isEmpty()) {
        		AuthLoader.INSTANCE.getEntitlements().addAll(Files.readAllLines(resource.getFile().toPath()));
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}