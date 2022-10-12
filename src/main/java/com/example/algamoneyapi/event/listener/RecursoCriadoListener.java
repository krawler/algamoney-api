package com.example.algamoneyapi.event.listener;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoneyapi.event.RecursoCriadoEvent;

import jakarta.servlet.http.HttpServletResponse;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
											 .path("/{id}")
											 .buildAndExpand(id)
											 .toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
