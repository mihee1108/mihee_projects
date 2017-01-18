package mp.common;

public class GetInfoDTO {
	private String code; //업체 코드
	private double doubleCode; //entcode와 같은 숫자형 code를 받는 변수
	private String codeName; //업체 코드 한글명
	
	
	
	
	public double getDoubleCode() {
		return doubleCode;
	}
	public void setDoubleCode(double doubleCode) {
		this.doubleCode = doubleCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	

	
}
