package mp.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileMaker {
	
	public void makeExcelFile(ArrayList<HashMap<String,Object>> list, ArrayList<String> columnList, String excelFileName) {
		@SuppressWarnings("resource")
		//1차로 workbook을 생성 
		XSSFWorkbook workbook=new XSSFWorkbook();
		//2차는 sheet생성 
		XSSFSheet sheet=workbook.createSheet(excelFileName);
		//엑셀의 행 
		XSSFRow row=null;
		//엑셀의 셀 
		XSSFCell cell=null;
		//임의의 DB데이터 조회 
		if(list !=null &&list.size() >0){
			// 컬럼 쓰기
			row=sheet.createRow((short)0);
			if(columnList !=null &&columnList.size() >0){
	            for(int j=0;j<columnList.size();j++){
	                //생성된 row에 컬럼을 생성한다 
	                cell=row.createCell(j);
	                //map에 담긴 데이터를 가져와 cell에 add한다 
	                cell.setCellValue(columnList.get(j));
	            }
	        }
			
			// 데이터 쓰기
		    int i=1;
		    for(HashMap<String,Object>mapobject : list){
		        // 시트에 하나의 행을 생성한다(i 값이 0이면 첫번째 줄에 해당) 
		        row=sheet.createRow((short)i);
		        i++;
		        if(columnList !=null &&columnList.size() >0){
		            for(int j=0;j<columnList.size();j++){
		                //생성된 row에 컬럼을 생성한다 
		                cell=row.createCell(j);
		                //map에 담긴 데이터를 가져와 cell에 add한다 
		                cell.setCellValue(String.valueOf(mapobject.get(columnList.get(j))));
		            }
		        }
		    }
		}
		
		FileOutputStream fileoutputstream;
		try {
			fileoutputstream = new FileOutputStream("F:\\" + excelFileName + ".xlsx");
			//파일을 쓴다
			workbook.write(fileoutputstream);
			//필수로 닫아주어야함 
			fileoutputstream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
