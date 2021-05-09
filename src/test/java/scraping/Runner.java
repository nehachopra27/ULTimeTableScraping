package scraping;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import utilities.CommonJavaFunctionImp;

public class Runner {

	public static String requestURI = "https://www.timetable.ul.ie/UA/CourseTimetable.aspx";
	public static String requestCookie = "ASP.NET_SessionId=3lg5ee0c3xdfjwxdelhj4jo5; _ga=GA1.2.2029842301.1619890427; _gid=GA1.2.106052734.1619890427; CookieConsent={stamp:'UpYUnmTTHNbSj6SKaUzE/Dq9d62otM97q/LG1jA/Nms39ZnSBe8yRA==',necessary:true,preferences:true,statistics:true,marketing:true,ver:1,utc:1619890433081,region:'in'}; .ASPXFORMSAUTH=D0855D9E70E9DDD028B8AC7B99E2CDC03174A6A63F905447C639777A6699620D29D499449ACA9B9D1CADF4F1D3060655B53B3850DF13E1AFD23446D929C2EA2B4220A9D8C9B986733E77FF6AEBBA5A5F829CD8DA310DCAF88665BED5115620DC0029309C34AF764FDF0C1C6882A09C454BBC09DD71B5AE1CAE75B1E1C3252827FCA5669C37BCCEE29351E63CA2C2492D; _gat_gtag_UA_179391946_1=1";

	@Test
	public static void Scrape() {
		try {
			CourseListImp courseList = new CourseListImp();
			YearListImp yearList = new YearListImp();
			TimeTableImp timetable = new TimeTableImp();
			
			String htmlbody = null;
			String __VIEWSTATE = null;
			String __VIEWSTATEGENERATOR = null;
			String __EVENTVALIDATION = null;
			List<String> courseData = new ArrayList<String>();
			courseList.setBaseURI(requestURI);
			courseList.setCookie("Cookie", requestCookie);
			courseList.sendGetcall();
			htmlbody = courseList.getResponseBody();
			courseData.addAll(courseList.getCourseList(htmlbody));
			__VIEWSTATE = courseList.get__VIEWSTATE(htmlbody);
			__VIEWSTATEGENERATOR = courseList.get__VIEWSTATEGENERATOR(htmlbody);
			__EVENTVALIDATION = courseList.get__EVENTVALIDATION(htmlbody);
			List<String[]> dataset = new ArrayList<String[]>();
			String[] header= {"Time","Duration","Class","Type","Weeks","Total Classes in Semester","Course","Year"};
			dataset.add(header);
			for (String course : courseData) {
				String __VIEWSTATE_y = null;
				String __VIEWSTATEGENERATOR_y = null;
				String __EVENTVALIDATION_y = null;
				List<String> yearData = new ArrayList<String>();
				yearList.setBaseURI(requestURI);
				yearList.setCookie("Cookie", requestCookie);
				yearList.set__EVENTTARGET("__EVENTTARGET", "ctl00$HeaderContent$CourseDropdown");
				yearList.set__VIEWSTATE("__VIEWSTATE", __VIEWSTATE);
				yearList.set__VIEWSTATEGENERATOR("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);
				yearList.set__EVENTVALIDATION("__EVENTVALIDATION", __EVENTVALIDATION);
				yearList.set__CourseName("ctl00$HeaderContent$CourseDropdown", course);
				yearList.sendPostCall("");
				htmlbody = yearList.getResponseBody();
				yearData = yearList.getYearList(htmlbody);
				__VIEWSTATE_y = yearList.get__VIEWSTATE(htmlbody);
				__VIEWSTATEGENERATOR_y = yearList.get__VIEWSTATEGENERATOR(htmlbody);
				__EVENTVALIDATION_y = yearList.get__EVENTVALIDATION(htmlbody);
				for (String year : yearData) {
					timetable.setBaseURI(requestURI);
					timetable.setCookie("Cookie", requestCookie);
					timetable.set__EVENTTARGET("__EVENTTARGET", "ctl00$HeaderContent$CourseYearDropdown");
					timetable.set__VIEWSTATE("__VIEWSTATE", __VIEWSTATE_y);
					timetable.set__VIEWSTATEGENERATOR("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR_y);
					timetable.set__EVENTVALIDATION("__EVENTVALIDATION", __EVENTVALIDATION_y);
					timetable.set__CourseName("ctl00$HeaderContent$CourseDropdown", course);
					timetable.set__CourseYear("ctl00$HeaderContent$CourseYearDropdown", year);
					timetable.sendPostCall("");
					htmlbody = timetable.getResponseBody();
					dataset.addAll(timetable.getTimeTable(htmlbody,course,year));
					System.out.println(course + " : " + year);
				}
			}
			CommonJavaFunctionImp fun=new CommonJavaFunctionImp();
			fun.writedata(dataset);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

}
