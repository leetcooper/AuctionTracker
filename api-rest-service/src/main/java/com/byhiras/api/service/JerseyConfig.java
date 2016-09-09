package com.byhiras.api.service;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.byhiras.api.service.register.RegisterRestService;

@Configuration
@ApplicationPath("/services")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig(){		
		register(RegisterRestService.class);
	}
}
