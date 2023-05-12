package requestTypes;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import java.util.HashMap;

public class HTTPrequestmethods {
	
	int id;
	
    @Test(priority = 0)
	void getUser() {
    	RestAssured.baseURI = "https://reqres.in";
		given().param("page", 2)
		
		.when()
		.get("/api/users/")
		
		.then().assertThat().statusCode(200)
		.assertThat().body("data.id[0]",equalTo(7));
//		.body("data.id",equalTo(2))
//		.log().all();
	}
	
//	@Test(priority = 1)
	void createUser() {
		HashMap <String,String> hm = new HashMap<String,String>();
        hm.put("Name", "Tom");
		hm.put("position", "leader");
		
		id=given()
		
		.contentType("application/json")
		.body(hm)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
//		.then()
//		.statusCode(201)
//		.log().all();
		
	}
//	@Test(priority = 2,dependsOnMethods = {"createUser"})
	void updateUser() {
		HashMap <String,String> hm = new HashMap<String,String>();
		hm.put("name", "Tom");
		hm.put("position", "architect");
		
		given() 
		
		.contentType("application/json")
		.body(hm)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
//	@Test(priority = 3)
	void deleteUser() {
		when() 
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
	}

}
