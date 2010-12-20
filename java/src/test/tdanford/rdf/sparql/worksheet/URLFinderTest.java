package tdanford.rdf.sparql.worksheet;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.everyItem;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;


public class URLFinderTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testURLFinder() {
		assertThat(finder("http://foo.bar/blah"), 
				locations(
						allOf(
								everyItem(array(0, 19))
								, size(is(equalTo(1)))
						)
					)
				);		
		
		assertThat(finder("http://foo.bar/blah"), method("locations", everyItem(array(0, 19))));
		assertThat(finder(" http://foo.bar/blah").locations(), everyItem(array(1, 20)));
		assertThat(finder("http://foo.bar/blah ").locations(), everyItem(array(0, 19)));

		assertThat(finder("mailto:tdanford@nonce.com").matchCount(), is(equalTo(1)));
	}
	
	public static <T> Matcher<Iterable<T>> size(Matcher <T> m) { 
		return new MethodMatcher<Iterable<T>,T>(m, "size");
	}
	
	public static <T> Matcher<URLFinder> locations(Matcher<T> m) { 
		return new MethodMatcher<URLFinder,T>(m, "locations");
	}
	
	public static <S,T> MethodMatcher<S,T> method(String mname, Matcher<T> m, Object... args) { 
		return new MethodMatcher<S,T>(m, mname, args);
	}
	
	public static URLFinder finder(String t) { 
		return new URLFinder(t);
	}
	
	public static <T> BaseMatcher<T[]> array(T... v) {  
		return new ArrayValueMatcher<T>(v);
	}
	
}

class MethodMatcher<S, T> extends BaseMatcher<S> { 
	
	private String methodName;
	private Object[] args;
	private Class[] parameterTypes;
	
	private Matcher<T> innerMatcher;
	
	public MethodMatcher(Matcher<T> m, String methodName, Object... args) {
		innerMatcher = m;
		this.methodName = methodName;
		this.args = args.clone();
		parameterTypes = new Class[args.length];
		for(int i = 0; i < args.length; i++) { 
			if(args[i] == null) { 
				parameterTypes[i] = Object.class;
			} else { 
				parameterTypes[i] = args[i].getClass();
			}
		}
	}

	public boolean matches(Object value) {	
		try {
			Method m = value.getClass().getDeclaredMethod(methodName, parameterTypes);
			T methodValue = (T)m.invoke(value, args);
			
			return innerMatcher.matches(methodValue);
			
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		}
	}

	public void describeTo(Description desc) {
		desc.appendText(methodName);
	}

}

class ArrayValueMatcher<T> extends BaseMatcher<T[]> {
	
	private T[] baseArray;
	
	public ArrayValueMatcher(T[] array) {
		baseArray = array;
	}

	public boolean matches(Object value) {	
		if(value == null || !value.getClass().isArray()) {
			return false; 
		}
		if(Array.getLength(value) != baseArray.length) {
			return false; 
		}
		
		for(int i = 0; i < baseArray.length; i++) { 
			if(!baseArray[i].equals(Array.get(value, i))) {
				return false;
			}
		}
		return true;
	}

	public void describeTo(Description desc) {
		desc.appendText(Arrays.asList(baseArray).toString());
	}	
}
