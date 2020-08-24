package kr.or.ddit.vo;

import java.util.List;

public class PagingVO<T> { // T --> 타입변수 
	public PagingVO() { // 기본값
		super();
	}
	
	
	
	public PagingVO(int screenSize, int blockSize) { // 변경값
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}


	private int screenSize = 10; // 기본값으로 줌
	private int blockSize = 5;
	private int currentPage;
	private int totalRecord;
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList; /// 다른 VO가 들어와도 사용 할수 잇다. 
	
	private SearchVO searchVO;
	




	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (int)Math.ceil(totalRecord/(double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage * screenSize;
		this.startRow = endRow - (screenSize - 1);
		this.startPage = blockSize * ((currentPage - 1) /blockSize) +1;
		this.endPage = startPage + (blockSize-1);
	}
	// 블럭을 만들어주는  li태그 만드는거? previe, next 만들어주는 부분 
	String blockPattern = 
	         "<li class='page-item %1$s'>\r\n" + 
	         "      <a data-page='%2$d' class='page-link' href='?page=%2$d' tabindex='-1'>%3$s</a>\r\n" + 
	         "    </li>";
	// 숫자 1,2,3,
	   String pagePattern = "<li class='page-item'><a data-page='%1$d' class='page-link' href='?page=%1$d'>%2$d</a></li>"; 
		
	
	// 토탈페이지에 따라 엔드페이지가 결정 
	public String getPagingHTML() {
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		StringBuffer html = new StringBuffer("<ul class='pagination'>");
		String liClass = startPage>blockSize?"":"disabled";
		
		// 이전으로 
			html.append(
					String.format(blockPattern, liClass, startPage-blockSize, "previous")
//					String.format(pattern, startPage-blockSize, "prev") // 첫화면으로 가는거 
				);
		
		for(int page = startPage; page <= endPage; page++) {
			if(page == currentPage) {
				html.append("<li class='page-item active' aria-current='page'>");
				html.append("<li class='page-link' hef='#'>"+page+"<span class='sr-only'>(current)</span></a>");
				html.append("</li>");
			}else {
				html.append(String.format(pagePattern, page, page));
			}
		}// 다음블럭으로 갈수 잇는 링크
		liClass = endPage <totalPage?"":"disabled";
		
			html.append(
					String.format(blockPattern, liClass, endPage+1, "next")
				);
			html.append("</ul>");
			return html.toString();
					
		}
		
	

	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}


	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
	
	
	
	
	
	
}
