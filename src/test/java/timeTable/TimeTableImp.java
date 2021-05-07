package timeTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jsoup.JSoupAction;
import jsoup.JSoupImp;
import restassured.RestAssuredActions;
import restassured.RestAssuredImp;

public class TimeTableImp implements Timetable {

	RestAssuredActions restAssuredActions = new RestAssuredImp();
	JSoupAction jsoupAction = new JSoupImp();

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

	public void set__CourseYear(String key, String value) {
		restAssuredActions.addJsonParam(key, value);

	}

	public void sendPostCall(String queryString) {
		restAssuredActions.sendPostRequest(queryString);
	}

	public String getResponseBody() {
		return restAssuredActions.getResponseBody();
	}

	public List<String[]> getTimeTable(String htmlBody, String course, String year) throws ParseException {
		Document document = getDoumnet(htmlBody);
		List<String[]> timeTable = new ArrayList<String[]>();
		Element elementSelect = getElementByID(document, "MainContent_CourseTimetableGridView");
		Elements elementList = getElementsByTagName(elementSelect, "tr");
		for (Element daysList : elementList) {
			Elements day = getElementsByTagName(daysList, "td");
			if (!day.isEmpty()) {

				timeTable.addAll(gettimeTable(day, course, year));
			}
		}

		return timeTable;
	}

	private List<String[]> gettimeTable(Elements days, String course, String year) throws ParseException {
		List<String[]> timeTableset = new ArrayList<String[]>();
		for (Element timetable : days) {
			if (timetable.text().contains("Online")) {
				timeTableset.addAll(gettimeTable(timetable.outerHtml().replace("<br>", " : "), course, year));
			}
		}
		return timeTableset;
	}

	private List<String[]> gettimeTable(String timeTable, String course, String year) throws ParseException {
		List<String[]> timeTableset = null;
		try {
			timeTableset = new ArrayList<String[]>();
			timeTable = jsoupAction.htmlToJsoupDoument(timeTable).text();
			String[] timetablevalues = timeTable.split(" : ");
			StringBuilder createTimeTable = new StringBuilder();
			for (String values : timetablevalues) {
				values = values.replace(": ", "");
				// Time parsing
				if (values.contains(":") && !values.contains("Wks") && values.contains("-")) {
					createTimeTable.append(values);
					createTimeTable.append(",");
					String fromtime = values.split("-")[0];
					String totime = values.split("-")[1];
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					Date fromdate = format.parse(fromtime);
					Date todate = format.parse(totime);
					long difference = todate.getTime() - fromdate.getTime();
					difference = difference / (60 * 60 * 1000) % 24;
					createTimeTable.append(difference);
					createTimeTable.append(",");
				}
				// Week Parsing
				else if (values.contains("Wks")) {
					createTimeTable.append(values.toString().replace(",", ";"));
					createTimeTable.append(",");
					values = values.replace("Wks:", "").replace(":", "");
					String[] weeks = values.split(",");
					int weekcount = 0;
					for (String week : weeks) {
						if (week.contains("-")) {
							int fromweek = Integer.parseInt(week.split("-")[0].trim());
							int toweek = Integer.parseInt(week.split("-")[1].trim());
							weekcount = weekcount + (toweek - fromweek);
							weekcount++;
						} else {
							weekcount = weekcount + 1;
						}

					}
					createTimeTable.append(weekcount);
					createTimeTable.append(",");
					createTimeTable.append(course.toString().replace(",", ""));
					createTimeTable.append(",");
					createTimeTable.append(year);
					timeTableset.add(createTimeTable.toString().split(","));
					createTimeTable.delete(0, createTimeTable.length());
				}
				else if (!values.contains("Online")&&(values.contains("- LEC")||values.contains("- TUT")||values.contains("- LAB"))) {
					createTimeTable.append(values);
					createTimeTable.append(",");
					if(values.contains("LEC")) {
						createTimeTable.append("LEC");
						createTimeTable.append(",");
					}else if(values.contains("TUT")){
						createTimeTable.append("TUT");
						createTimeTable.append(",");
					}
					else if(values.contains("LAB")){
						createTimeTable.append("LAB");
						createTimeTable.append(",");
					}
					
					}
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeTableset;

	}

	private Document getDoumnet(String htmlBody) {
		return jsoupAction.htmlToJsoupDoument(htmlBody);
	}

	private Elements getElementsByTagName(Element element, String tagname) {
		return jsoupAction.getElementByTagName(element, tagname);
	}

	private Element getElementByID(Document document, String id) {
		return jsoupAction.getElementByID(document, id);
	}
}
