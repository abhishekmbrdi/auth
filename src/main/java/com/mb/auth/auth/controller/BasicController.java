/**
 * 
 */
package com.mb.auth.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KABHISH
 *
 */

@RestController
public class BasicController {

	@GetMapping("/")
	public String home() {
		return "Welcome Home!!!";
	}
	
	@GetMapping("/secured")
	public String secured() {
		return "Welcome secured!!!";
	}
}
