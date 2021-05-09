package utilities;

import io.restassured.*;

public class RestAssuredImp extends GlobalVariables{

	public boolean createRequestEndPoint(String baseURI) {
		try {
			RestAssured.baseURI = baseURI;
			request = RestAssured.given();
			return true;
		} catch (Exception e) {
			log.error("not able to create request end point");
			log.error(e.getMessage());
			return false;
		}

	}

	public boolean addJsonParam(String key, String value) {
		try {
			request.formParams(key, value);
			return true;
		} catch (Exception e) {
			log.error("not able to add param");
			log.error(e.getMessage());
			return false;
		}
	}

	public boolean addRequestHeader(String key, String value) {
		try {
			request.header(key, value);
			return true;
		} catch (Exception e) {
			log.error("not able to add param");
			log.error(e.getMessage());
			return false;
		}
	}

	public boolean sendPostRequest(String queryString) {
		try {
			response = request.post(queryString);
			return true;
		} catch (Exception e) {
			log.error("not able to add body");
			log.error(e.getMessage());
			return false;
		}
	}
	
	public boolean sendGetRequest() {
		try {
			response = request.get();
			return true;
		} catch (Exception e) {
			log.error("not able to add body");
			log.error(e.getMessage());
			return false;
		}
	}

	public boolean validateResponseCode(int responseCode) {
		return (response.getStatusCode() == responseCode);
	}

	public String getResponseBody() {
		return response.getBody().asString();
	}

}
