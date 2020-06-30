package iducs.spring.blog202012703.utils;

public class Pagination {
	private int curPage; // 현재 페이지
	private int perPage; // 한 페이지당 블로그 수
	
	private int totalRowCount; // 총 블로그 수 
	private int totalPage; // 총 페이지 수
	
	private int pageCount; // 페이지 하단에 나타나는 페이지 번호 갯수
	private int startPage; // 시작 페이지
	private int endPage; // 마지막 페이지
	private boolean prev; // 이전 페이지 유무
	private boolean next; // 다음 페이지 유무
	
	private int startRow; // 페이지의 시작 행, 한 행이 블로그 하나임 
	private int endRow; // 페이지의 마지막 행
	
	private String keyword;
	private String orderBy;
	
	public Pagination() {
		this(1,3,3, "");// 한 페이지에 3개의 블로그, 한 화면에 3개의 페이지 번호 갯수
	}
	
	public Pagination(int curPage, int perPage, int pageCount, String keyword) {
		this.curPage = curPage; 
		this.perPage = perPage; 
		this.pageCount = pageCount;
		this.keyword = keyword;
	}
	
	public int getCurPage() {
		return curPage; 
	}
	
	public void setCurPage(int curPage) { 
		if(curPage <= 0) {
			this.curPage = 1;
			return; 
		}
		this.curPage = curPage; 
	}
	
	public int getPerPage() { 
		return perPage;
	}
	
	public void setPerPage(int perPage) { // 성능을 위해 한 페이지는 100개 이하로 설정
		if (perPage <= 0 || perPage > 100) { 
			this.perPage = 10;
			return;
		}
		this.perPage = perPage; 
	}
	
	public int getTotalRowCount() { 
		return totalRowCount;
	}
	
	public void setTotalRowCount(int totalRowCount) { // 총 블로그 수를 설정하면 다른 값 들은 계산이 가능함
		this.totalRowCount = totalRowCount;
		this.calcPageVariables(); 
	}
	
	public int getTotalPage() { 
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage; 
	}
	
	public int getPageCount() { 
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount; 
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
	
	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
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

	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	private void calcPageVariables() {
		totalPage = (int) (Math.ceil(totalRowCount / (double) perPage)); 
		startPage = ((curPage - 1) / pageCount) * pageCount + 1; 
		endPage = startPage + pageCount - 1;
		prev = startPage == 1? false: true;
		next = endPage * perPage >= totalRowCount ? false: true;
		if(endPage > totalPage) { 
			endPage = totalPage; next = false;
		}
		startRow = (curPage - 1) * perPage + 1; 
		endRow = startRow + perPage - 1; 
		if(endRow > totalRowCount)
			endRow = totalRowCount; 
	}
	
	@Override
	public String toString() {
		return "Pagination [curPage=" + curPage + ", perPage=" + perPage + ", totalRowCount=" + totalRowCount
				+ ", totalPage=" + totalPage + ", pageCount=" + pageCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", keyword=" + keyword + ", startRow=" + startRow + ", endRow=" + endRow + "]";
	} 
	
}
