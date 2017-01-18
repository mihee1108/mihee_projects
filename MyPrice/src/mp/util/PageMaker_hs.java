package mp.util;

public class PageMaker_hs {
	
	// PageMaker와 변수, 메서드명이 다름. 김혜선이 작업한 내용(action에서 메서드 호출, jsp에서 페이징 할 때 사용한 부분 등)을 모두 수정할려면 시간이 걸려서 우선 pm은 별도로 사용 중
	private int perPage;
	private int curPage;
	private int rowStart;
	private int rowLast;
	private int curBlock;
	private int totalBlock;
	private int startPage;
	private int lastPage;
	private int totalCount;
		
	public int getRowStart() {
		return rowStart;
	}
	public int getRowLast() {
		return rowLast;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getLastPage() {
		return lastPage;
	}

	public PageMaker_hs(int curPage, int perPage, int totalCount) {
		this.curPage=curPage;
		this.perPage=perPage;
		this.totalCount = totalCount;
		//perBlock=5;	// 아래 3번에서 설정
		this.makeRowNum();
		
	}
	
	private void makeRowNum() {
		rowStart=(curPage-1)*perPage+1;
		rowLast= curPage*perPage;
		
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
		int perBlock = 5;
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
		startPage = (curBlock-1)*perBlock+1;
		lastPage = curBlock*perBlock;
		if(curBlock==totalBlock){
			lastPage=totalPage;
		}else if (totalCount==0) {
			lastPage=0;	// 글 갯수 0일 때 마지막 페이지 0
		}
		//System.out.println("curBlock : "+curBlock + " / startPage : "+startPage+" / perPage : "+perPage+"/lastPage : "+lastPage+" / totalBlock : "+totalBlock);

	}


}
