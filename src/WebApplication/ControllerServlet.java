package WebApplication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

    
    // init
    public void init(ServletConfig config) throws ServletException {
    	
    	
    	
    	
    	super.init(config);
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
			String username  = request.getParameter("username");
			String password = request.getParameter("pass");
			
			//TODO: Check if username and password matches in database.
			//TODO: Send to search/home page
			// session.setAttribute("currentUser", sql hent);
		
		}
		
		// Button in signIn.jsp
		else if(action.equals("Create new user")){
			
			// Redirects to register.jsp
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("/register.jsp");
			requestdispatcher.forward(request, response);
		}
		
		
		// Button in register.jsp
		else if (action.equals("Register user")){
			//TODO: Sjekk at alle inputs er ok
			
			//TODO: Send mail til konto med confirmation link. 
			// Ikke opprett i DB før bruker trykker på godkjenningslinken.
		}
		
		// Button in confirmationPage.jsp
		else if(action.equals("Sign in here")){
			
			//Sends user to signIn.jsp
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("/signIn.jsp");
			requestdispatcher.forward(request, response);	
		}
		
		else if(action.equals("Forgot password")){
			
			
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
			requestdispatcher.forward(request, response);	
		}
		
		else if(action.equals("Get password")){
			// Send email
		}
		
		doGet(request, response);
	}

}
