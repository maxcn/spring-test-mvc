package edu.uci.inf215.newbug.testcases;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.web.support.JsonPathExpectationsHelper;

public class JsonPathExpectationHelperTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	String jsonExmp1 = "{\"glossary\":{\"title\":\"exampleglossary\",\"GlossDiv\":{\"title\":\"S\",\"GlossList\":{\"GlossEntry\":{\"ID\":\"SGML\",\"SortAs\":\"SGML\",\"GlossTerm\":\"StandardGeneralizedMarkupLanguage\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO8879:1986\",\"GlossDef\":{\"para\":\"Ameta-markuplanguage,usedtocreatemarkuplanguagessuchasDocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"GlossSee\":\"markup\"}}}}}";
	String simplePath = "D:\\KLive\\GitRepository\\spring-test-mvc\\src\\test\\java\\edu\\uci\\inf215\\bddtestcases\\mockhttptestcases";
	
	@Test(expected=StackOverflowError.class)
	public void testPrint() throws Exception {
		JsonPathExpectationsHelper helper = new JsonPathExpectationsHelper("TestString");
		String s1 =jsonExmp1;
		helper.assertValue(s1);
		String s2 =simplePath;
		helper.assertValue(s2);
	}
}
