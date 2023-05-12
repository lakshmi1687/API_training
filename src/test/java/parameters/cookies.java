package parameters;

import static io.restassured.RestAssured.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class cookies {
//	@Test(priority=1)
	void CookiesInfo() {
		
		given()
		
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	@Test(priority=2)
	void fetchCookies() {
		
		Response res = get("https://www.google.com/");
		
		String cookieValue = res.getCookie("AEC").toString();
		System.out.println(cookieValue);
		
		Map<String,String> cookies = res.getCookies();
		 
		for(String k:cookies.keySet()) {
			
			String cookie_value = res.getCookie(k);
			System.out.println(k  +  "is"  + cookie_value);
		}
	}

}
