package kr.or.ddit.enums;

public enum IndextType {
	STANDARDJSP("/01/standard.jsp"),CALCULATE("/01/calForm.jsp"),
	CALENDAR("/02/calendar.jsp"),SESSIONTIMER("/05/sessionTimer.jsp"),
	CONTEXTBROWSING("/ddit/contextBrowse.do");
	
	private IndextType(String desc) {
		this.desc = desc;
	}
	
	private String desc;
	public String getDesc() {
		return desc;
	}
	
	public static String findContentUrl(String serviceName) {
		IndextType indext =  valueOf(serviceName);
		return indext.getDesc();
		
	}

	
	
}
