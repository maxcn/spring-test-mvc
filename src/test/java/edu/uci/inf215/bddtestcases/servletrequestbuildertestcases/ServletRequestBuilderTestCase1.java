package edu.uci.inf215.bddtestcases.servletrequestbuildertestcases;


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

public class ServletRequestBuilderTestCase1 {

	private MockHttpServletRequestBuilder builder;

	private ServletContext servletContext;


	@Before
	public void setUp() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo/bar");
		servletContext = new MockServletContext();
	}

	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a uri location
	 *  we should check if the fileds of servlet request are corret
	 */
	
	@Test
	public void servletRequestBuilderUri() throws Exception {
		String uri = "https://java.sun.com:8080/javase/6/docs/api/java/util/BitSet.html?foo=bar#and(java.util.BitSet)";
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, uri);
		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("https", request.getScheme());
		assertEquals("foo=bar", request.getQueryString());
		assertEquals("java.sun.com", request.getServerName());
		assertEquals(8080, request.getServerPort());
		assertEquals("/javase/6/docs/api/java/util/BitSet.html", request.getRequestURI());
		assertEquals("https://java.sun.com:8080/javase/6/docs/api/java/util/BitSet.html",
				request.getRequestURL().toString());
	}
	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri
	 *  we should check if the encoding is correct.
	 */
	
	@Test
	public void servletRequestBuilderWithEncoding() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo bar");
		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("/foo%20bar", request.getRequestURI());
	}
	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path empty
	 *  we should check if fields of this request are correct.
	 */
	@Test
	public void servletRequestBuilderWithContextPathEmpty() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("", request.getContextPath());
		assertEquals("", request.getServletPath());
		assertEquals("/foo", request.getPathInfo());
	}
	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path servlet path empty
	 *  we should check if fields of this request are correct.
	 */

	@Test
	public void servletRequestBuilderWithContextPathServletPathEmpty() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/travel/hotels/42");
		this.builder.contextPath("/travel");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("/travel", request.getContextPath());
		assertEquals("", request.getServletPath());
		assertEquals("/hotels/42", request.getPathInfo());
	}

	

}
