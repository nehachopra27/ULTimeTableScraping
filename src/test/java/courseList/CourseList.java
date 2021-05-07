package courseList;

import java.util.List;

public interface CourseList {

	
	public void setBaseURI(String uri);
	public void setCookie(String key,String value);
	public void sendGetcall();
	public String getResponseBody();
	public List<String> getCourseList(String htmlBody);
	public String get__VIEWSTATE(String htmlBody);
	public String get__VIEWSTATEGENERATOR(String htmlBody);
	public String get__EVENTVALIDATION(String htmlBody);
}
