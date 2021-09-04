package com.kingtan.store.inventory.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

//import org.junit.BeforeClass;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class AppInventoryControllerTest {
	
	@BeforeAll
	public void init() {
		WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089));
		wireMockServer.start();
	}

	@Test
	public void testt() {
		
	}
}