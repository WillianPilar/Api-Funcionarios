package com.ApiFuncionarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetHealthController {

	@GetMapping("health")
	public ResponseEntity<String> getHealth(){
		return ResponseEntity.ok("OK");
	}
}
