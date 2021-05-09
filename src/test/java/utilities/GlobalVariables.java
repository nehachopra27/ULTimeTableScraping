package utilities;




import org.apache.log4j.Logger;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GlobalVariables {

	

	public RequestSpecification request;
	public Response response;
	
	public static Logger log;
	public static final String seprator = System.getProperty("file.separator");
	private static final String dirPath = System.getProperty("user.dir");
	public static final String dirTestResource = dirPath + seprator + "src" + seprator + "test" + seprator + "resources"
			+ seprator;

	
}
