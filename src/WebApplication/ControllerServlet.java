package WebApplication;

import java.io.IOException;

import javax.mail.MessagingException;
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
   
	EmailSender es = new EmailSender();
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
		session.setMaxInactiveInterval(-1);
		session.setAttribute("wrongPassword", false);
		
		
		
	}

	
	
	
	// DO POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("wrongPassword", false);
		
		String action = request.getParameter("action");
		 
		System.out.println("requested action: " + action);
		
		RequestDispatcher requestdispatcher;
		
		// Checks parameter action
		switch(action){
		
		
		case "Sign in":
			
			String username  = request.getParameter("username");
			String password = request.getParameter("pass");
			session.setAttribute("currentUser", username);
						
			//TODO: Check if username and password matches in database.
			//TODO: Send to search/home page
			// session.setAttribute("currentUser", sql hent);
			if(true){ // (isvalid(username, password){
				
		
				requestdispatcher = request.getRequestDispatcher("/search.jsp");
				requestdispatcher.forward(request, response);
			}
			else{
				session.setAttribute("wrongPassword", true);
				requestdispatcher = request.getRequestDispatcher("/signIn.jsp");
				requestdispatcher.forward(request, response);
			}
			break;
			
		case "Create new user":
			
			// Redirects to register.jsp
			requestdispatcher = request.getRequestDispatcher("/register.jsp");
			requestdispatcher.forward(request, response);
			
			break;
			
		case "Forgot password":
			
			requestdispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
			requestdispatcher.forward(request, response);
			
			break;
		
			
		
		
		
		
		
		
		
		case "Register user":
			
			//TODO: Sjekk at alle inputs er ok
			
			
			//	es.sendEmail(mottaker Email, Confirmation link);
		
			// Ikke opprett i DB før bruker trykker på godkjenningslinken.
			
			break;
			
			
		case "Sign in here": // Button in confirmationPage.jsp

			//Sends user to signIn.jsp
			requestdispatcher = request.getRequestDispatcher("/signIn.jsp");
			requestdispatcher.forward(request, response);	
			
			break;
			
			
		case "Get password":
			break;
		
		
		
		
		
		}
		
		

		
		doGet(request, response);
	}

}
