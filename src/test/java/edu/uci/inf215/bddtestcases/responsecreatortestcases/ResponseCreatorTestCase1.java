package edu.uci.inf215.bddtestcases.responsecreatortestcases;



import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.ResponseCreators;
import org.springframework.util.FileCopyUtils;


public class ResponseCreatorTestCase1 {
	
	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response,
	 *  we should check if the status is successful
	 */
	
	@Test
	public void responseCreatorShouldSucceed() throws Exception {
		MockClientHttpResponse response = (MockClientHttpResponse) ResponseCreators.withSuccess().createResponse(null);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
		assertNull(response.getBody());
	}
	
	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response and a particular content,
	 *  we should check if the status is successful
	 */
	
	@Test
	public void responseCreatorShouldSucceedWithContent() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withSuccess("foo", MediaType.TEXT_PLAIN);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.TEXT_PLAIN, response.getHeaders().getContentType());
		assertArrayEquals("foo".getBytes(), FileCopyUtils.copyToByteArray(response.getBody()));
	}
	
	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response and a particular content, without content type
	 *  we should check if the status is successful
	 */
	
	@Test
	public void responseCreatorShouldSucceedWithContentWithoutContentType() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withSuccess("foo", null);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNull(response.getHeaders().getContentType());
		assertArrayEquals("foo".getBytes(), FileCopyUtils.copyToByteArray(response.getBody()));
	}
	
	/*
	 *  Scenario: response creator test
	 *	Given a url location,
	 *  we should check if the response is created.
	 */

	@Test
	public void responseCreatorCreated() throws Exception {
		URI location = new URI("/foo");
		DefaultResponseCreator responseCreator = ResponseCreators.withCreatedEntity(location);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(location, response.getHeaders().getLocation());
		assertNull(response.getBody());
	}

	
}
