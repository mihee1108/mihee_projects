package mp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	public ActionForward process(HttpServletRequest request, HttpServletResponse response);
}
