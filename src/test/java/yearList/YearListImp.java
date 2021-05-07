package yearList;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jsoup.JSoupAction;
import jsoup.JSoupImp;
import restassured.RestAssuredActions;
import restassured.RestAssuredImp;

public class YearListImp implements YearList{

	RestAssuredActions restAssuredActions=new RestAssuredImp();
	JSoupAction jsoupAction=new JSoupImp();
	
	public void setBaseURI(String uri) {
		restAssuredActions.createRequestEndPoint(uri);
		
	}

	public void setCookie(String key, String value) {
		restAssuredActions.addRequestHeader(key, value);
		
	}

	public void set__EVENTTARGET(String key, String value) {
		restAssuredActions.addJsonParam(key, value);
		
	}

	public void set__VIEWSTATE(String key, String value) {
		restAssuredActions.addJsonParam(key, value);
		
	}

	public void set__VIEWSTATEGENERATOR(String key, String value) {
		restAssuredActions.addJsonParam(key, value);
		
	}

	public void set__EVENTVALIDATION(String key, String value) {
		restAssuredActions.addJsonParam(key, value);
		
	}

	public void set__CourseName(String key, String value) {
		restAssuredActions.addJsonParam(key, value);
	}

	public void sendPostCall(String queryString) {
		restAssuredActions.sendPostRequest(queryString);
	}

	public String getResponseBody() {
		return restAssuredActions.getResponseBody();
	}

	public List<String> getYearList(String htmlBody) {
		List<String> yearList=new ArrayList<String>();
		Document document=getDoumnet(htmlBody);
		Element elementSelect=getElementByID(document, "HeaderContent_CourseYearDropdown");
		Elements elementList=getElementsByTagName(elementSelect, "option");
		yearList.addAll(getElementValueByAttribute(elementList, "value"));
		yearList.remove(0);
		return yearList;
	}
	
	private Document getDoumnet(String htmlBody) {
		return jsoupAction.htmlToJsoupDoument(htmlBody);
	}
	
	
	
	private Elements getElementsByTagName(Element element,String tagname) {
		return jsoupAction.getElementByTagName(element,tagname);
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
