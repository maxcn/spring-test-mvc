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


public class MockHttpTestCase2 {

	private MockHttpServletRequestBuilder builder;

	private ServletContext servletContext;


	@Before
	public void setUp() throws Exception {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo/bar");
		servletContext = new MockServletContext();
	}



	/*
	 *  Scenario: mock http  test
	 *	Given some request headers
	 *  we should check if format of headers is correct
	 */
	@Test
	public void MockHttpWithHeaders() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.put("foo", Arrays.asList("bar", "baz"));
		this.builder.headers(httpHeaders);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		List<String> headers = Collections.list(request.getHeaders("foo"));

		assertEquals(2, headers.size());
		assertEquals("bar", headers.get(0));
		assertEquals("baz", headers.get(1));
		assertEquals(MediaType.APPLICATION_JSON.toString(), request.getHeader("Content-Type"));
	}

	
	/*
	 *  Scenario: mock http test
	 *	Given a particular cookie
	 *  we should check if content of cookie is correct
	 */
	@Test
	public void MockHttpWithCookie() throws Exception {
		Cookie cookie1 = new Cookie("foo", "bar");
		Cookie cookie2 = new Cookie("baz", "qux");
		this.builder.cookie(cookie1, cookie2);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		Cookie[] cookies = request.getCookies();

		assertEquals(2, cookies.length);
		assertEquals("foo", cookies[0].getName());
		assertEquals("bar", cookies[0].getValue());
		assertEquals("baz", cookies[1].getName());
		assertEquals("qux", cookies[1].getValue());
	}

	/*
	 *  Scenario: mock http test
	 *	Given a particular locale
	 *  we should check if content of locale is correct
	 */
	@Test
	public void MockHttpWithLocale() throws Exception {
		Locale locale = new Locale("nl", "nl");
		this.builder.locale(locale);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals(locale, request.getLocale());
	}

	/*
	 *  Scenario: mock http test
	 *	Given a particular encoding
	 *  we should check if the encoding is correct
	 */
	@Test
	public void characterEncoding() throws Exception {
		String encoding = "UTF-8";
		this.builder.characterEncoding(encoding);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals(encoding, request.getCharacterEncoding());
	}

	
}
