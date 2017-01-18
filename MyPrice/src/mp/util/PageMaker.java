package mp.util;

public class PageMaker {
	
	private int perPage;
	private int curPage;
	private int perBlock;
	
	private int startRow;
	private int lastRow;
	
	private int curBlock;
	private int totalBlock;
	private int startNum;
	private int lastNum;
	
	private int totalCount;
		
	
	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerBlock() {
		return perBlock;
	}

	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getLastNum() {
		return lastNum;
	}

	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PageMaker(int curPage, int perPage, int totalCount) {
		this.curPage=curPage;
		this.perPage=perPage;
		this.totalCount = totalCount;
		//perBlock=5;	// 아래 3번에서 설정
		
	}
	
	public void setRowNum(){
		startRow=(curPage-1)*perPage+1;
		lastRow=curPage*perPage;
		
	}

	public void makePageing() {
		// 1. 총 글의 수	?? 게시판 생길때마다 새로 만듬??
		/*
		NoticeDAO nDao = new NoticeDAO();
		int totalCount = nDao.getTotalCnt();
		
		MemberDAO mDao = new MemberDAO();
		int totalCount = mDao.getTotalCnt();
		*/
		//2. 총 페이지수
		int totalPage=0;
		if(totalCount%perPage ==0){
			totalPage = totalCount/perPage;
		}else {
			totalPage = totalCount/perPage+1;
		}
		//3. 총 블럭수
		totalBlock=0;
		int perBlock = 10;
		if(totalPage%perBlock==0){
			totalBlock=totalPage/perBlock;
		}else {
			totalBlock=totalPage/perBlock+1;
		}
		
		//4. curPage이용 현재 블럭 구하기
		curBlock = 0;
		if(curPage%perBlock==0){
			curBlock =curPage/perBlock;
		}else {
			curBlock = curPage/perBlock+1;
		}
		
		//5. 현재 블럭을 이용해서 start, last
		startNum=(curBlock-1)*perBlock+1;
		
		lastNum=curBlock*perBlock;
		if(curBlock==totalBlock){
			lastNum=totalPage;
		}
	}
	
		
	}


