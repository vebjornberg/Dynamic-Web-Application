package WebApplication;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
			
		
		case "simple search":
			String searchWord = request.getParameter("keyWord");
			//ArrayList<PublicationBean> result = getResultsSimpleSearch(String searchWord);
			//request.setParameter("results", result)";

			
			requestdispatcher = request.getRequestDispatcher("/results.jsp");
			requestdispatcher.forward(request, response);
			
		
		
		
		
		
		
		
		case "Register user":
			
			if (!isValidDOB(request.getParameter("dateOfBirth"))){
				
			}
			if (!isValidEmailAddress("email")){
				
			}
			if(!isValidUsername("username")){
				
			}
			
			
			//	es.sendEmail(mottaker Email, Confirmation link, "Hi and welcome to DBL, \n Please click the below link to confirm your email and create your account\n\n" + link + "\nRegards, \nDBL tema :)");
		
			// Ikke opprett i DB før bruker trykker på godkjenningslinken.
			
			break;
			
			
		case "Sign in here": // Button in confirmationPage.jsp

			//Sends user to signIn.jsp
			requestdispatcher = request.getRequestDispatcher("/signIn.jsp");
			requestdispatcher.forward(request, response);	
			
			break;
			
			
		case "Get password":
			//if email exists in db, get password and send
				//es.sendEmail(email, content)
			break;
		
		
		
		
		
		}
		
		

		
		doGet(request, response);
	}

	
	public boolean isCorrectLogin(String username, String password){
		return true;
	}
	
	
	
	public boolean isValidUsername(String username){
		//if (!getAllUserNames().contains(username)) {
			//return false;}
        if(!username.matches("^[a-zA-Z0-9]*$")){
            return false;}
		return true;
	}
	
	
	
	
	public boolean isValidDOB(String dob){
		if(!(dob.length()==8)){
			return false;
		}
		if(!dob.matches("[0-9]+")){
			return false;
		}
		if(  (Integer.parseInt(dob.substring(2)) >31) || 
				(Integer.parseInt(dob.substring(2,4)) >12) ||
				Integer.parseInt(dob.substring(4,8)) >2016 || 
				Integer.parseInt(dob.substring(4,8)) <1900) {
					return false;
				}
		return true;
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		 //if (!getAllEmails().contains(email)) {
			//result = false;}
		   return result;
	}
	
	
	
}
