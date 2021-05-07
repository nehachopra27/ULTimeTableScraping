package restassured;

public interface RestAssuredActions {

	public boolean createRequestEndPoint(String baseURI);
	public boolean addJsonParam(String key,String value);
	public boolean addRequestHeader(String key, String value);
	public boolean sendPostRequest(String queryString);
	public boolean validateResponseCode(int responseCode);
	public boolean sendGetRequest();
	public String getResponseBody();
	
}
