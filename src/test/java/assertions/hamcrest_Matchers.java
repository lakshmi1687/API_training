package assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.*;

public class hamcrest_Matchers {

	@Test
	void getUsers() {
		given()
		.contentType("ContentType.JSON")
		
		.when()
		.get("http://localhost:3000/employee")
		
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8")
		.assertThat().body("data[0].address.city",equalTo("Chicago"))
		.and()
		.body("data[0].email",is("michael.lawson@reqres.in"))
		.and().body("data[2].address.city", equalToIgnoringCase("Hyderabad"))
		.and().body("data[3].email", containsString("byron.fields@reqres.in"))
		.and().body("data[1].first_name", startsWith("L"))
		.and().body("data[2].address.city", endsWith("d"));
				
	}
//	@Test
	public void getUserAndValidateUsingAssertions() {
		
		Response res = given()
		 .contentType("ContentType.JSON")
		
		.when()
		  .get("http://localhost:3000/employee");
		
		Assert.assertEquals(res.statusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		String cityname = res.jsonPath().get("data[0].address.city").toString();
		Assert.assertEquals(cityname, "Chicago");
	}
//	@Test
	public void getcityNameUsingName() {
		
		Response res = given() // return type is in the form of response
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/employee");
		
		JSONObject jo = new JSONObject(res.asString()); // converting response into json object
		JSONArray arrlen = jo.getJSONArray("data");
		 boolean status=false;
		 
		 for(int i=0; i<arrlen.length();i++) {
			 String fname = arrlen.getJSONObject(i).get("first_name").toString();
			 if (fname.equals("Lindsay")) { 
				  String addres = arrlen.getJSONObject(i).getJSONObject("address").get("city").toString();
				  System.out.println(addres);
				  if (addres.equals("newyork")) {
					  status=true;
					  break;
				  } 
			 }
			 
		 }
		 Assert.assertEquals(status, true);
		
	}

}
