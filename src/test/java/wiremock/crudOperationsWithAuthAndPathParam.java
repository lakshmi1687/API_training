package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import authentication.Tokens;
import io.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class crudOperationsWithAuthAndPathParam implements Tokens {
	
	public static final int PORT =8080;
	public static final String HOST = "localhost";
	
	private static WireMockServer server = new WireMockServer(PORT);
	
	@BeforeClass
	public static void setUp() {
		if(server.isRunning() && server != null) {
			server.shutdownServer();
		}
		server.start();
		
		WireMock.configureFor("localhost", 8080);
	}
	@Test(priority=1)
	public void postStub() {
		 stubFor(post(urlEqualTo("/users"))
		  .willReturn(aResponse()
				  .withStatus(201)
				  .withHeader("content-type","application/json")
				  .withHeader(userName, password)
				  .withBody("{\"id\": 234, \"name\": \"Tom williams\"}")));
	}
//	@Test(priority=1)
	public void postStubWithFile() {
		stubFor(post(urlEqualTo("/users"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("content-type","application/json")
						.withBodyFile("src/test/resources/__files/postStub.json")));
	}
	@Test(priority=2)
	public void postEndPoint() {
		RestAssured.given()
		.auth().basic(userName, password)
		.when().post("http://localhost:8080/users").then()
		.assertThat().statusCode(201)
		.assertThat().header("Content-Type", "application/json")
		.assertThat().body("id", Matchers.equalTo(234))
		.assertThat().body("name", Matchers.equalTo("Tom williams"))
		.log().all();
	}
	@Test(priority=3)
	public static void getStub() {
		 stubFor(get(urlEqualTo("/get/users/1"))
		  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("content-type","application/json")
				  .withHeader("Authentication", "Bearer "+bearerToken)
				  .withBody("Created First User")));
		
	}
	@Test(priority=4)
	public void getEndPoint() {
		RestAssured.given().pathParam("id", 1)
		.when().get("http://localhost:8080/get/users/{id}").then()
		.assertThat().statusCode(200)
		.assertThat().header("Content-Type", "application/json")
		.log().all();
		
	}
	@Test(priority=5)
	public static void putStub() {
		stubFor(put(urlEqualTo("/users"))
		.willReturn(aResponse()
		  .withStatus(200)
		  .withHeader("Authentication", "Bearer "+bearerToken)
		  .withHeader("content-type","application/json")
		  .withBody("{\"id\": 234, \"name\": \"Klaus Mikealson\"}")));
	}
	@Test(priority=6)
	public void putRequest() {
		RestAssured.given()
		 .when().put("/users").then()
		  .assertThat().statusCode(200)
		  .assertThat().header("Content-Type", "application/json")
		  .assertThat().body("id",Matchers.equalTo(234))
		  .assertThat().body("name", Matchers.equalTo("Klaus Mikealson"))
		  .log().all();
	}
	@Test(priority=7)
	public static void delStub() {
		stubFor(delete(urlEqualTo("/users"))
				.willReturn(aResponse()
						.withStatus(204)));
				
	}
	@Test(priority=8)
	public void delRequest() {
		RestAssured.given()
		 .when().delete("/users").then()
		    .assertThat().statusCode(204);
		    
	}
	
	@AfterClass
	public static void tearDown() {
		if(server.isRunning() && server != null) {
			server.shutdownServer();
		}
	}

}
