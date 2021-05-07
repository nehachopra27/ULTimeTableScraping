package yearList;

import java.util.List;

public interface YearList {
	public void setBaseURI(String uri);
	public void setCookie(String key,String value);
	public void set__EVENTTARGET(String key,String value);
	public void set__VIEWSTATE(String key,String value);
	public void set__VIEWSTATEGENERATOR(String key,String value);
	public void set__EVENTVALIDATION(String key,String value);
	public void set__CourseName(String key,String value);
	public void sendPostCall(String queryString);
	public String getResponseBody();
	public List<String> getYearList(String htmlBody);
	public String get__VIEWSTATE(String htmlBody);
	public String get__VIEWSTATEGENERATOR(String htmlBody);
	public String get__EVENTVALIDATION(String htmlBody);
}
