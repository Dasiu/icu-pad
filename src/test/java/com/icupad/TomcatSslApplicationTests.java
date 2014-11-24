package com.icupad;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class TomcatSslApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testHome() throws Exception {
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
				new SSLContextBuilder().loadTrustMaterial(null,
						new TrustSelfSignedStrategy()).build());

		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
				.build();

		TestRestTemplate testRestTemplate = new TestRestTemplate();
		((HttpComponentsClientHttpRequestFactory) testRestTemplate.getRequestFactory())
				.setHttpClient(httpClient);
		ResponseEntity<String> entity = testRestTemplate.getForEntity(
				"https://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("Hello, world", entity.getBody()); // FIXME
	}

}
