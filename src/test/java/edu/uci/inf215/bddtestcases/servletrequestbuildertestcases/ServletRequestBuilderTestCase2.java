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

/**
 * Tests building a MockHttpServletRequest with {@link MockHttpServletRequestBuilder}.
 *
 * @author Rossen Stoyanchev
 */
public class ServletRequestBuilderTestCase2 {

	private MockHttpServletRequestBuilder builder;

	private ServletContext servletContext;


	@Before
	public void setUp() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo/bar");
		servletContext = new MockServletContext();
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

	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path servlet path 
	 *  we should check if fields of this request are correct.
	 */
	@Test
	public void servletRequestBuilderWithContextPathServletPath() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/travel/main/hotels/42");
		this.builder.contextPath("/travel");
		this.builder.servletPath("/main");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("/travel", request.getContextPath());
		assertEquals("/main", request.getServletPath());
		assertEquals("/hotels/42", request.getPathInfo());
	}
	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path servlet path info empty
	 *  we should check if fields of this request are correct.
	 */
	
	@Test
	public void servletRequestBuilderWithContextPathServletPathInfoEmpty() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/travel/hotels/42");

		this.builder.contextPath("/travel");
		this.builder.servletPath("/hotels/42");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("/travel", request.getContextPath());
		assertEquals("/hotels/42", request.getServletPath());
		assertNull(request.getPathInfo());
	}
	
	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path servlet path info
	 *  we should check if fields of this request are correct.
	 */
	
	@Test
	public void servletRequestBuilderWithContextPathServletPathInfo() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/");
		this.builder.servletPath("/index.html");
		this.builder.pathInfo(null);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("", request.getContextPath());
		assertEquals("/index.html", request.getServletPath());
		assertNull(request.getPathInfo());
	}

	
}

