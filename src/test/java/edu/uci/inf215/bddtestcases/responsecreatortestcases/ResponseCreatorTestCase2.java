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

/**
 * Tests for the {@link ResponseCreators} static factory methods.
 *
 * @author Rossen Stoyanchev
 */
public class ResponseCreatorTestCase2 {

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
	 *	Given a mock client http response with no content
	 *  we should check if there is no content
	 */
	
	@Test
	public void responseCreatorNoContent() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withNoContent();
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
		assertNull(response.getBody());
	}

	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response with bad request
	 *  we should check this is a bad request
	 */
	
	@Test
	public void responseCreatorBadRequest() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withBadRequest();
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
		assertNull(response.getBody());
	}

	

}
