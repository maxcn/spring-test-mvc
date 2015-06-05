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
public class ServletRequestBuilderTestCase3 {

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

	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path servlet path invalid
	 *  we should check if fields of this request are correct.
	 */
	
	@Test
	public void servletRequestBuilderWithContextPathServletPathInvalid() throws Exception {

		testContextPathServletPathInvalid("/Foo", "", "requestURI [/foo/bar] does not start with contextPath [/Foo]");
		testContextPathServletPathInvalid("foo", "", "Context path must start with a '/'");
		testContextPathServletPathInvalid("/foo/", "", "Context path must not end with a '/'");

		testContextPathServletPathInvalid("/foo", "/Bar", "Invalid servletPath [/Bar] for requestURI [/foo/bar]");
		testContextPathServletPathInvalid("/foo", "bar", "Servlet path must start with a '/'");
		testContextPathServletPathInvalid("/foo", "/bar/", "Servlet path must not end with a '/'");
	}

	/*
	 *  Scenario: servlet request builder test
	 *	Given a request uri with context path servlet path invalid
	 *  we should check if fields of this request are correct.
	 */
	
	private void testContextPathServletPathInvalid(String contextPath, String servletPath, String message) {
		try {
			this.builder.contextPath(contextPath);
			this.builder.servletPath(servletPath);
			this.builder.buildRequest(this.servletContext);
		}
		catch (IllegalArgumentException ex) {
			assertEquals(message, ex.getMessage());
		}
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

	

}

