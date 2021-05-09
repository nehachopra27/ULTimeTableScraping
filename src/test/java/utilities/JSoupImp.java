package utilities;


import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupImp{

	public Document htmlToJsoupDoument(String htmlbody) {
		
		return Jsoup.parse(htmlbody);
	}


	public Elements getElementByTagName(Document document, String tagname) {
		return document.select(tagname);
	}
	
	public List<String> getElementValueByAttribute(Elements elements,String attribute){
		List<String> courseList=new ArrayList<String>();
		for(Element element:elements) {
			courseList.add(element.attr(attribute));
		}
		return courseList;
	}


	public Element getElementByID(Document document, String id) {
		return document.getElementById(id);
	}


	public String getElementValueByAttribute(Element element, String attribute) {
		return element.attr(attribute);
	}


	public Elements getElementByTagName(Element element, String tagname) {
		
		return element.getElementsByTag(tagname);
	}
	
	
}
