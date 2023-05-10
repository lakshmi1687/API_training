package assertions;

import static io.restassured.RestAssured.given;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import assertions.Constants;

import io.restassured.response.Response;

public class regularexpressions {
	@Test
	void validateEmail() {
		Response res = given()
				 .contentType("ContentType.JSON")
				
				.when()
				  .get("http://localhost:3000/employee");
		
		JSONObject jo = new JSONObject(res.asString());
		JSONArray arrlen = jo.getJSONArray("data");
		
		for(int i=0;i<arrlen.length();i++) {
			
			String emails = arrlen.getJSONObject(i).getString("email").toString();
//			System.out.println(emails);
			if (emails.equalsIgnoreCase(arrlen.getJSONObject(3).toString())) {
				MatcherAssert.assertThat(emails, Matchers.equalTo(Constants.expectedEmail));
			}
			System.out.println(emails.matches("[\\w]+[\\.][\\w]+[@][\\w]+[.][\\w]{2,3}"));
		}
	}

}
