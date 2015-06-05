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


public class ResponseCreatorTestCase4 {

	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response with unauthorized request
	 *  we should check this is an unauthorized request
	 */
	@Test
	public void responseCreatorUnauthorized() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withUnauthorizedRequest();
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
		assertNull(response.getBody());
	}
	
	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response with an error server
	 *  we should check the server is error
	 */
	@Test
	public void responseCreatorServerError() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withServerError();
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
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

	/*
	 *  Scenario: response creator test
	 *	Given a mock client http response with a particular status
	 *  we should check the status is correct
	 */
	@Test
	public void responseCreatorWithStatus() throws Exception {
		DefaultResponseCreator responseCreator = ResponseCreators.withStatus(HttpStatus.FORBIDDEN);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
		assertNull(response.getBody());
	}

}
