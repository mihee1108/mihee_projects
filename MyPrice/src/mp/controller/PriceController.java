package mp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/PriceController")
public class PriceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    private HashMap<String, Object> command; //맵생성
    
   @Override
    public void init(ServletConfig config) throws ServletException {
	   command = new HashMap<>();
	   //properties 파일명 가져오기
	   String fileName=config.getInitParameter("properties");
	   //properties 파일이 있는 실제 경로
	   String realPath=config.getServletContext().getRealPath("WEB-INF");
	   //파일 읽는 객체
	   FileInputStream fi=null;
	   //파일 내용 읽어서 '=' 기준으로 파싱 후 저장
	   Properties prop = new Properties();
	   try {
		fi = new FileInputStream(new File(realPath, fileName));
		//파일 읽어서 파싱
		   prop.load(fi);
		   //prop에서 key들을 저장
		   Iterator<Object> keys = prop.keySet().iterator();
		   while(keys.hasNext()){
			   //key가 있다면 그 중에 key하나를 가져오기
			   String key = (String)keys.next();
			   //그 key를 이용해서 prop에서 value를 가져오기
			   String insName = (String)prop.get(key);
			   //value를 이용해서 class생성
			   Class obj = Class.forName(insName);
			   //그 class를 이용해서 객체 생성
			   Object result =obj.newInstance();
			   //key와 만들어진 객체를 맵에 저장
			   command.put(key, result);
			   
			   System.out.println("key:" +key);
			   System.out.println("object:"+obj);
			   System.out.println(insName); //클래스 주소 확인
			   System.out.println(result); //객체 주소 확인
			   System.out.println("store컨트롤러 들어왔음 맵에서 프로퍼티 다함..");
		   		}
		   }
		   catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   }
	   }
	   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		Action action=null;
		ActionForward af = null;
		
		String path= request.getRequestURI();
		String rpath= request.getServletContext().getContextPath();
		System.out.println("path:"+path);
		System.out.println("rpath:"+rpath);
		path = path.substring(rpath.length());
		
		action =(Action)command.get(path);
		
		System.out.println("path 명령함!!!!!");
		System.out.println("request값: "+request);
		System.out.println("response값: "+response);
		System.out.println("action: "+action);
		System.out.println("path: "+path);
		
		af = action.process(request, response);
		System.out.println("request, response로 보냄.. ");
		
		if(af.isRedirect()){
			RequestDispatcher view= request.getRequestDispatcher(af.getPath());
			view.forward(request, response);
			System.out.println("redirect 성공");
		}else{
			response.sendRedirect(af.getPath());
			System.out.println("forward 성공");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
