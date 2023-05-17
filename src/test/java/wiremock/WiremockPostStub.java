package wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.WireMockServer;



public class WiremockPostStub {

	    private static final int PORT = 8080;
	    private static final String HOST = "localhost";
	    
	    private static WireMockServer server = new WireMockServer(PORT);

	    @BeforeClass
	    public static void setup() {
	    	if(server.isRunning() && server != null) {
				server.shutdownServer();
			}
	    	server.start();
	        WireMock.configureFor(HOST, PORT);
	        stubFor(post(urlEqualTo("/api/example"))
	                .willReturn(aResponse()
	                        .withStatus(201)
	                        .withHeader("Content-Type", "application/json")
	        		        .withBody("{\"id\": 234, \"name\": \"Tom williams\"}")));
	                
	    }

	    @Test
	    public void testMockedApi() {
	        RestAssured.given()
	                .baseUri("http://" + HOST)
	                .port(PORT)
	                .contentType(ContentType.JSON)
	                .body("{\"id\": 789, \"name\": \"Alice Johnson\"}")
	                .when()
	                .post("/get/users/1")
	                .then()
	                .statusCode(201)
	                .contentType(ContentType.JSON)
	                .body("id", Matchers.equalTo(234))
	                .body("name", Matchers.equalTo("Tom williams"));
	    }

//	    @AfterClass
//	    public static void teardown() {
//	    	if(server.isRunning() && server != null) {
//				server.shutdownServer();
//			}
//	    }
	}