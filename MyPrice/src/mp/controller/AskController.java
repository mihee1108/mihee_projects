package mp.controller;

import java.io.File;
import java.io.FileInputStream;
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
 * Servlet implementation class MemberController
 */
@WebServlet("/AskController")
public class AskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AskController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private HashMap<String, Object> command;
    public void init(ServletConfig config) throws ServletException {
    	command = new HashMap<>();
    	String fileName = config.getInitParameter("properties");
    	String realPath = config.getServletContext().getRealPath("WEB-INF");
    	Properties prop = new Properties();
    	FileInputStream fi= null;
    	
    	try {
			fi = new FileInputStream(new File(realPath, fileName));
			prop.load(fi);
			
			Iterator<Object> it = prop.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String className = (String)prop.get(key);
				Class ins = Class.forName(className);
				Object instance = ins.newInstance();
				command.put(key, instance);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action=null;
		ActionForward af = null;
		
		//String path = request.getRequestURI();
		//path = path.substring(request.getServletContext().getContextPath().length());
		
		String path = request.getServletPath();
				
		action = (Action)command.get(path);
		af = action.process(request, response);
		
		if(af.isRedirect()){
			RequestDispatcher view = request.getRequestDispatcher(af.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(af.getPath());
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
