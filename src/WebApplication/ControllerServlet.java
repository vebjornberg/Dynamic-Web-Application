package WebApplication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	// DO GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//torjussa test
		
	}

	
	
	
	// DO POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		 
		System.out.println("requested action: " + action);
		
		
		
		// Button in signIn.jsp
		if (action.equals("Sign in")){
			//Check if username and password matches in database.
			//Send to search/home page
		
		}
		
		// Button in signIn.jsp
		else if(action.equals("Create new user")){
			
			// Redirects to register.jsp
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("/register.jsp");
			requestdispatcher.forward(request, response);
		}
		
		
		// Button in register.jsp
		else if (action.equals("Register user")){
			//Sjekk at alle inputs er ok
			
			//send mail til konto med confirmation link
		}
		
		
		doGet(request, response);
	}

}
