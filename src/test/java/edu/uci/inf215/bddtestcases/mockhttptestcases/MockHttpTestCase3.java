package edu.uci.inf215.bddtestcases.mockhttptestcases;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.server.request.MockHttpServletRequestBuilder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;


public class MockHttpTestCase3 {

	private MockHttpServletRequestBuilder builder;

	private ServletContext servletContext;


	@Before
	public void setUp() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo/bar");
		servletContext = new MockServletContext();
	}


	/*
	 *  Scenario: mock http  test
	 *	Given a request header type
	 *  we should check if format of header is correct
	 */

    @Test
    public void MockHttpWithAcceptHeader() throws Exception {
        this.builder.accept(MediaType.TEXT_HTML, MediaType.APPLICATION_XML);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		List<String> accept = Collections.list(request.getHeaders("Accept"));
		List<MediaType> result = MediaType.parseMediaTypes(accept.get(0));

		assertEquals(1, accept.size());
		assertEquals("text/html", result.get(0).toString());
		assertEquals("application/xml", result.get(1).toString());
	}

    /*
	 *  Scenario: mock http  test
	 *	Given a request content type
	 *  we should check if format of content is correct
	 */
    
	@Test
	public void MockHttpWithContentType() throws Exception {
		this.builder.contentType(MediaType.TEXT_HTML);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		String contentType = request.getContentType();
		List<String> contentTypes = Collections.list(request.getHeaders("Content-Type"));

		assertEquals("text/html", contentType);
		assertEquals(1, contentTypes.size());
		assertEquals("text/html", contentTypes.get(0));
	}
	
	/*
	 *  Scenario: mock http  test
	 *	Given a request attribute
	 *  we should check if attribute is correct
	 */
	
	@Test
	public void MockHttpWithRequestAttribute() throws Exception {
		this.builder.requestAttr("foo", "bar");
		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("bar", request.getAttribute("foo"));
	}

	/*
	 *  Scenario: mock http  test
	 *	Given a request session attribute
	 *  we should check if attribute is correct
	 */
	@Test
	public void MockHttpWithSessionAttribute() throws Exception {
		this.builder.sessionAttr("foo", "bar");
		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("bar", request.getSession().getAttribute("foo"));
	}

	

}
