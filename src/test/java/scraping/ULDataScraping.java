package scraping;

import org.testng.annotations.Test;

public class ULDataScraping {

	CourseListImp courseList=new CourseListImp();
	public static String requestURI="https://www.timetable.ul.ie/UA/CourseTimetable.aspx";
	public static String requestCookie="ASP.NET_SessionId=3lg5ee0c3xdfjwxdelhj4jo5; _ga=GA1.2.2029842301.1619890427; _gid=GA1.2.106052734.1619890427; CookieConsent={stamp:'UpYUnmTTHNbSj6SKaUzE/Dq9d62otM97q/LG1jA/Nms39ZnSBe8yRA==',necessary:true,preferences:true,statistics:true,marketing:true,ver:1,utc:1619890433081,region:'in'}; .ASPXFORMSAUTH=D0855D9E70E9DDD028B8AC7B99E2CDC03174A6A63F905447C639777A6699620D29D499449ACA9B9D1CADF4F1D3060655B53B3850DF13E1AFD23446D929C2EA2B4220A9D8C9B986733E77FF6AEBBA5A5F829CD8DA310DCAF88665BED5115620DC0029309C34AF764FDF0C1C6882A09C454BBC09DD71B5AE1CAE75B1E1C3252827FCA5669C37BCCEE29351E63CA2C2492D; _gat_gtag_UA_179391946_1=1";
	

	@Test(priority = 1)
	public void getCourseList() {
		courseList.setBaseURI(requestURI);
		courseList.setCookie("Cookie", requestCookie);
		courseList.sendGetcall();
		String s=courseList.getResponseBody();
		System.out.println(s);
	}
}
