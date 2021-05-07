package timeTable;

import java.text.ParseException;
import java.util.List;

public interface Timetable {
	public void setBaseURI(String uri);
	public void setCookie(String key,String value);
	public void set__EVENTTARGET(String key,String value);
	public void set__VIEWSTATE(String key,String value);
	public void set__VIEWSTATEGENERATOR(String key,String value);
	public void set__EVENTVALIDATION(String key,String value);
	public void set__CourseName(String key,String value);
	public void set__CourseYear(String key,String value);
	public void sendPostCall(String queryString);
	public String getResponseBody();
	public List<String[]> getTimeTable(String htmlBody,String course,String year) throws ParseException;
}
