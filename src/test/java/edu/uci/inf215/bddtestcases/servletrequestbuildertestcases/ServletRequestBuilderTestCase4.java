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


 
public class ServletRequestBuilderTestCase4 {

	private MockHttpServletRequestBuilder builder;

	private ServletContext servletContext;


	@Before
	public void setUp() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo/bar");
		servletContext = new MockServletContext();
	}

	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri and fragment
	 *  we should check if fields of this request are correct.
	 */
	@Test
	public void servletRequestBuilderWithRequestUriAndFragment() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo#bar");
		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("/foo", request.getRequestURI());
	}
	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request with parameter
	 *  we should check if parameters are correct.
	 */
	
	@Test
	public void servletRequestBuilderWithRequestParameter() {
		this.builder.param("foo", "bar", "baz");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		Map<String, String[]> parameterMap = request.getParameterMap();

		assertArrayEquals(new String[]{"bar", "baz"}, parameterMap.get("foo"));
	}

	/*
	 *  Scenario: servlet request builder test
	 *	Given a request with parameter from query
	 *  we should check if parameters from the query are correct.
	 */
	
	@Test
	public void servletRequestBuilderWithRequestParameterFromQuery() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/?foo=bar&foo=baz");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		Map<String, String[]> parameterMap = request.getParameterMap();

		assertArrayEquals(new String[]{"bar", "baz"}, parameterMap.get("foo"));
		assertEquals("foo=bar&foo=baz", request.getQueryString());
	}

	

	/*
	 *  Scenario: servlet request builder test
	 *	Given a request with parameter from query with encoding
	 *  we should check if encodings are correct.
	 */
	
	@Test
	public void servletRequestBuilderWithRequestParameterFromQueryWithEncoding() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/?foo={value}", "bar=baz");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("foo=bar=baz", request.getQueryString());
		assertEquals("bar=baz", request.getParameter("foo"));
	}

    

}

