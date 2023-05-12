package authentication;

public interface Tokens {
	
	String basicAuthUrl = "https://postman-echo.com/basic-auth";
	String userName = "postman";
	String password = "password";
	
	String bearerTokenURL = "https://api.github.com/user/repos";
	String bearerToken = "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";
	
	String noAuthURL = "https://dummyapi.io/";
	
	String ntlmAuthURL = "https://postman-echo.com/";
	String workStation = "workstation/abc-10";
	String domain = "domain"; 

}
