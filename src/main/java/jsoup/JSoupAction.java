package jsoup;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface JSoupAction {

	public Document htmlToJsoupDoument(String htmlbody);
	public Elements getElementByTagName(Document document,String tagname);
	public Elements getElementByTagName(Element element,String tagname);
	public Element getElementByID(Document document,String id);
	public List<String> getElementValueByAttribute(Elements elements,String attribute);
	public String getElementValueByAttribute(Element element,String attribute);
}
