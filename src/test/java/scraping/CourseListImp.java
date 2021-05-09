package scraping;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utilities.JSoupImp;
import utilities.RestAssuredImp;


public class CourseListImp {

	RestAssuredImp restAssuredActions=new RestAssuredImp();
	JSoupImp jsoupAction=new JSoupImp();
	
	
	public void setBaseURI(String uri) {
		restAssuredActions.createRequestEndPoint(uri);
	}

	public void setCookie(String key, String value) {
		restAssuredActions.addRequestHeader(key, value);
		
	}

	public void sendGetcall() {
		restAssuredActions.sendGetRequest();
		
	}

	public String getResponseBody() {
		
		return restAssuredActions.getResponseBody();
	}

	public List<String> getCourseList(String htmlBody) {
		List<String> courseList=new ArrayList<String>();
		Document document=getDoumnet(htmlBody);
		Elements elementList=getElementsByTagName(document, "option");
		courseList.addAll(getElementValueByAttribute(elementList, "value"));
		courseList.remove(0);
		return courseList;
	}
	
	private Document getDoumnet(String htmlBody) {
		return jsoupAction.htmlToJsoupDoument(htmlBody);
	}
	
	
	private Elements getElementsByTagName(Document document,String tagname) {
		return jsoupAction.getElementByTagName(document,tagname);
	}
	
	private List<String> getElementValueByAttribute(Elements elements,String attribute){
		return jsoupAction.getElementValueByAttribute(elements, attribute);
	}

	private Element getElementByID(Document document, String id) {
		return jsoupAction.getElementByID(document, id);
	}
	
	private String getElementValueByAttribute(Element element, String attribute) {
		return jsoupAction.getElementValueByAttribute(element, attribute);
	}
	
	public String get__VIEWSTATE(String htmlBody) {
		Document document=getDoumnet(htmlBody);
		Element element=getElementByID(document, "__VIEWSTATE");
		return getElementValueByAttribute(element, "value");
	}

	
	
	public String get__VIEWSTATEGENERATOR(String htmlBody) {
		Document document=getDoumnet(htmlBody);
		Element element=getElementByID(document, "__VIEWSTATEGENERATOR");
		return getElementValueByAttribute(element, "value");
	}

	public String get__EVENTVALIDATION(String htmlBody) {
		Document document=getDoumnet(htmlBody);
		Element element=getElementByID(document, "__EVENTVALIDATION");
		return getElementValueByAttribute(element, "value");
	}
	
}
