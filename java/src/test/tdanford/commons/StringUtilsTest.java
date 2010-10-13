package tdanford.commons;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StringUtilsTest {

	@Test 
	public void test() throws UnsupportedEncodingException {
		String test = "foo\"bar";
		String coding = URLEncoder.encode(test, "UTF-8");
		assertThat(StringUtils.escape(test), is(coding));
	}
}
