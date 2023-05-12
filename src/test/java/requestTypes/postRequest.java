package requestTypes;
import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import org.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;

public class postRequest {
	
	
//	@Test(priority=1)
	void postRequestUsingHashMap() {
		
		
		HashMap<String,String> hm = new HashMap<String,String>();
		
		hm.put("first_name", "williams");
		hm.put("last_name", "Tony");
		hm.put("id", "13");
		
		
		given()
		.contentType("application/json")
		.body(hm)
		
		.when()
		.post("http://localhost:3000/students")
		
	    .then()
	    .log().all()
	    .body("first_name",equalTo("williams"))
	    .body("last_name", equalTo("Tony"))
		.header("Content-Type","application/json; charset=utf-8");
		
	}
	@Test(priority=2)
	void deleteCreatedUser() {
		
		given()
		
//		Created user is deleted using id
		.when().delete("http://localhost:3000/students/13")
		
		.then()
		.statusCode(404);
	}
//	@Test(priority=1)
	void postUserUsingJson() {
		
		JSONObject jobj = new JSONObject();
		
		jobj.put("first_name","williams");
		jobj.put("last_name", "Tony");
		jobj.put("id", "13");
		
		given()
		.contentType("application/json")
		.body(jobj.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("first_name", equalTo("williams"))
		.body("last_name", equalTo("Tony"))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}
//	@Test(priority=1)
	
	void postUserUsingPojoClass() {
		
		pojo_postrequest data = new pojo_postrequest();
		
		data.setFirst_name("williams");
		data.setLast_name("Tony");
		data.setId("13");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("first_name", equalTo("williams"))
		.body("last_name", equalTo("Tony"))
		.header("Content-Type", "application/json; charset=utf-8");
		
	}
	@Test(priority=1)
	void postUserUsingExternalJsonFile() throws FileNotFoundException {
		
		File f = new File(".\\body1.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject jo = new JSONObject(jt);
		
		
		given()
		.contentType("application/json")
		.body(jo.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("first_name", equalTo("williams"));
	}
	
 
}
