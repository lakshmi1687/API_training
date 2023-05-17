package wiremock;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;


public class WiremockGetStub {
	
	public static final int PORT =8080;
	public static final String HOST = "localhost";
	
	private static WireMockServer server = new WireMockServer(PORT);
	
	@BeforeClass
	public static void setUp() {
		server.start();
		
		ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
		WireMock.configureFor(HOST, PORT);
		WireMock.stubFor(WireMock.get("/get/users/1").willReturn(mockResponse.withStatus(200)));
	}
	
	@Test
	public void TestEndPoint() {
		RestAssured.given()
		.when().get("http://localhost:8080/get/users/1").then()
		.assertThat().statusCode(200);
	}
	
//	@AfterClass
	public static void tearDown() {
		if(server.isRunning() && server != null) {
			server.shutdownServer();
		}
	}
	
	

}
