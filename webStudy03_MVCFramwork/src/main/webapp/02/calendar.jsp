<%@page import="java.util.TimeZone"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	table{ height: 500px; }  .blue{ color:blue;} .red{ color:red;} 	.green{	background-color: green;}
</style>
<% // 입력 받은 날짜 
String yearStr = request.getParameter("year"); 
String monthStr = request.getParameter("month");
String lang = request.getParameter("language");
String zone = request.getParameter("zone");
TimeZone currentZone =   TimeZone.getDefault();   // 현재 위치에 맞는 시간 셋팅 

if(StringUtils.isNotBlank(zone)){currentZone =  TimeZone.getTimeZone(zone);  // 클라이언트가 선택한 타임존의 객체가 형성된다. 
}
Locale clientLocale = request.getLocale(); // 언어 선택을 안했을경우 기본값으로 
if(StringUtils.isNotBlank(lang)){ clientLocale =  Locale.forLanguageTag(lang);}
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
Calendar cal = getInstance(currentZone); 	// 객체 생성
int currentYear = cal.get(YEAR); // 현재 년도
int currentMonth = cal.get(MONTH);	//현재  달
int currentDay = cal.get(DAY_OF_MONTH);  // 현재 일 

try{
	sdf.parse(yearStr+"-"+monthStr); // sdf객체 형식에 맞게 바꿔주는거 
	// 년도 와  월을 파라미터에 따라 변경을 해줘야ㅐ 한다. 
	cal.set(YEAR, Integer.parseInt(yearStr));  // 입력 받은 연도를 현재 시간에 셋팅
	cal.set(MONTH,Integer.parseInt(monthStr)); // 입력 받은 월을  현재 시간에 셋팅
}catch(ParseException e){// 통과	
}
out.println(String.format("<h4>%d:%d</h4>",cal.get(HOUR_OF_DAY),cal.get(MINUTE))); // 현재시간 출력
cal.set(DAY_OF_MONTH, 1);		// 5월의 1일로 셋팅
int dayOfWeek = cal.get(DAY_OF_WEEK);			// 요일에 대한 셋팅
int offset = dayOfWeek - 1; // 해당 숫자에 대한 요일을 구하는거  일요일 1 월 2 화 3 수 4 .... 
// 1보다 작으면 공백으로 처리 
// 마지막 날짜는 현재 월에서 큰 날짜를 공백
int maxDay =cal.getActualMaximum(DAY_OF_MONTH); // 월에서 가장큰 날짜를 구하는거 
int year = cal.get(YEAR); // 현재 년도
int month = cal.get(MONTH);	// 현재 월
boolean currentFlag = currentYear==year && currentMonth == month;

cal.add(MONTH, -1); //  전달 구하기
int beforYear = cal.get(YEAR); // 이전 년도
int beforMonth = cal.get(MONTH);	// 이전 월
cal.add(MONTH, 2);// 다시 돌오는거 
int nextYear = cal.get(YEAR); // 다음 년도
int nextMonth = cal.get(MONTH);	// 다음  월

//날짜 표현 텍스트 
DateFormatSymbols  dfs = new DateFormatSymbols(clientLocale);
%>
<!-- 파라미터로 넘어가는 값을 하나씩 넣어주는게 아니라  form태그를 이용한 방법 /  데이터 속성  / 'data-'  class를 이용해서 그룹화 시킨다. -->
<a class="aLink" href="#" data-year="<%=beforYear%>" data-month="<%=beforMonth%>">이전달</a>
<h4><%=year %>,<%=month+1 %></h4>
<a class="aLink" href="#" data-year="<%=nextYear%>" data-month="<%=nextMonth%>">다음달</a>
<form id="calForm">
year : <input type="number" name="year" value="<%=year%>"/>
month : <select  name="month" >
		<%
			String[] months = dfs.getMonths();
			for(int idx = 0; idx<months.length-1; idx++ ){// select 박스에 13개 가 나와서 -1을 해줌 
			String selected= month ==idx? "selected":"";
			out.println(// 선택한 월을 유지하는거 
					String.format("<option %s value='%d'>%s</option>", selected, idx, months[idx]));
		}%>
		</select>
language : <select name="language">
			<option value="">언어선택</option>
	<%		Locale[] locales =  Locale.getAvailableLocales(); // locale의 배열
			for(Locale tmp :locales) {
				String display = tmp.getDisplayLanguage(tmp);
				if(StringUtils.isBlank(display)) continue;
				String selected = tmp.equals(clientLocale)? "selected":"";
				out.println(
						String.format("<option %s value='%s'>%s</option>", selected, tmp.toLanguageTag(), display));}%>
			</select>	
timeJone : 	<select name="zone">
			<%
				String[] ids = TimeZone.getAvailableIDs();// 로케일 코드를 받아오는거
				for (String zoneId : ids) {
					TimeZone tz = TimeZone.getTimeZone(zoneId);
					String display = tz.getDisplayName();
					String selected = zoneId.equals(zone) ? "selected" : "";
					out.println(String.format("<option %s value='%s'>%s</option>", selected, zoneId, display));
				}
			%>
		</select>		
</form>
<table class="table table-bordered">
<thead>
	<tr>
		<%//  일주일을 표현하는 거
		String[] weekdays = dfs.getShortWeekdays();  // getShortWeekdays 월요일~일요일까지를 나타내는 부분 
		for(int idx=Calendar.SUNDAY; idx<=Calendar.SATURDAY; idx++){
			out.println(String.format("<th>%s</th>",weekdays[idx])); //int를 string으로 바꾸는거 
		}%>
	</tr>
</thead>
<tbody>
<%// 현재 월에 해당하는  월을 그려줌 
	int count = 1;
	int today = cal.get(Calendar.DATE); // 오늘날짜 
	for(int row=1; row<=6; row++){
		out.print("<tr>");
		for(int col=1; col<=7; col++){
			int number = count++ -offset; // 주에서 1을 빼는거 
			 //1일부터 마지막날까지 숫자만 나오도록 하는거 
			String displayNumber= (number > 0 && number <= maxDay) ? number+"" : "&nbsp;";
			String clzName = col==1 ?"red":(col == 7?"blue":"normal"); // col 즉 일~토까지 부분에서  일(1), 토(7) 에 색 넣는거
			// 오늘날짜에 초록색 넣기 
			if(currentFlag && number == currentDay){
				clzName += " green";}
			out.println(
				String.format("<td class='%s'>%s</td> ", clzName, displayNumber) //  공백처리
					);
		}out.println("</tr>");
	}%>
</tbody>
</table>
<script type="text/javascript">
	var calForm = $("#calForm");
	$(".aLink").on("click", function(){
		let year = $(this).data("year");
		let month = $(this).data("month");
		calForm.find("[name='year']").val(year);
		calForm.find("[name='month']").val(month);
		calForm.submit();
	});
	$(":input").on("change", function(){
		$(this).closest("form").submit();
	});
</script>
