package WebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
	mySQLconnection sql = new mySQLconnection();
	
	
	

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
		System.out.println("This is doGet();");
	
	}

	
	
	
	// DO POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is doPost();");

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
			
						
			//TODO: Check if username and password matches in database.
			//TODO: Send to search/home page
			session.setAttribute("currentUser", sql.getUserInfo(username));
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
			
		case "simpleSearch":
			String searchWord = request.getParameter("search");
			System.out.println(searchWord);
			
			ArrayList<PublicationBean> results = sql.getPublications(searchWord);
			request.setAttribute("searchResults", results);

			
			requestdispatcher = request.getRequestDispatcher("/results.jsp");
			requestdispatcher.forward(request, response);
			
			break;
			
			
		case "advancedSearch":
			String firstName = request.getParameter("authorFirstName");
			String lastName = request.getParameter("authorLastName");
			String title = request.getParameter("title");
			String year = request.getParameter("year");
			String type = request.getParameter("pubType");
			System.out.println(firstName + lastName + title + year + type );
			
			ArrayList<PublicationBean> advancedResults = sql.getPublicationsAdvanced(type, title, firstName, lastName, year);
			request.setAttribute("AdvancedResults", advancedResults);
			

			
			requestdispatcher = request.getRequestDispatcher("/advancedResults.jsp");
			requestdispatcher.forward(request, response);
			
			break;
			
			
		case "Create new user":
			
			// Redirects to register.jsp
			requestdispatcher = request.getRequestDispatcher("/register.jsp");
			requestdispatcher.forward(request, response);
			
			break;
			
		case "Forgot password":
			session.setAttribute("getPasswordPressed", false);
			requestdispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
			requestdispatcher.forward(request, response);
			
			break;
	
		
		
		case "Register user":
			
			boolean ok = true;
			session.setAttribute("registrationError", "");
			
			if (isAvailableUsername(request.getParameter("username"))){
				session.setAttribute("registrationError", "Not valid username, already taken.");
				ok=false;
			}
			else if(!isValidUsername(request.getParameter("username"))){
				session.setAttribute("registrationError", "Not valid username, only letters a-z and numbers.");
				ok=false;
			}
			else if (!isValidDOB(request.getParameter("bDate"))){
				session.setAttribute("registrationError", "Not valid date of birth, ex: 01031989.");
				ok=false;
			}
//			else if (!isValidEmailAddress("email")){
//				session.setAttribute("registrationError", "Not valid email, ex: john@gmail.com.");
//				ok=false;
//			}
			
			System.out.println(ok + " "+ session.getAttribute("registrationError"));
			if(ok){
				UserBean newCurrentUser = new UserBean();
				
				session.setAttribute("currentUser", newCurrentUser);
				newCurrentUser.setUsername(request.getParameter("username"));
				
				newCurrentUser.setAddress(request.getParameter("adress"));
				
				newCurrentUser.setCreditCard(request.getParameter("creditCardNr"));
				newCurrentUser.setEmail(request.getParameter("email"));
				newCurrentUser.setDateOfBirth(request.getParameter("bDate"));
				newCurrentUser.setFirstname(request.getParameter("fname"));
				newCurrentUser.setLastname(request.getParameter("lname"));
				newCurrentUser.setPassword(request.getParameter("pass"));
				
				String confirmLink = getConfirmationLink(request.getParameter("username"));
				String hash = confirmLink.substring(confirmLink.length()-8);
				System.out.println(hash);
				
				newCurrentUser.setConfirmationHash(hash);
				System.out.println("usr: " + newCurrentUser.getUsername() + "\nadress: " + newCurrentUser.getAddress() + "\nemail: "
						+ newCurrentUser.getEmail() + "\nDoB: " + newCurrentUser.getDateOfBirth()
				);
			
				sql.setUserBean(newCurrentUser);
				
				requestdispatcher = request.getRequestDispatcher("/register.jsp");
				requestdispatcher.forward(request, response);
				
				
				
				
			try {
				EmailSender.sendEmail(request.getParameter("email"), "Hi and welcome to DBL, \n Please click the below link to confirm your email and create your account\n\n" + confirmLink + "\n\nRegards, \nDBL team :)");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			}
			requestdispatcher = request.getRequestDispatcher("/register.jsp");
			requestdispatcher.forward(request, response);
			//TODO: vis registration error
			
			
			
			break;
			
			
		case "Sign in here": // Button in confirmationPage.jsp

			//Sends user to signIn.jsp
			requestdispatcher = request.getRequestDispatcher("/signIn.jsp");
			requestdispatcher.forward(request, response);	
			
			break;
			
			
		case "Get password":
			
			String emailForgot = request.getParameter("forgottenPwMail");
			
			UserBean userForgot = sql.getUserInfoFromEmail(emailForgot);
			if (sql.getEmails().contains(emailForgot)){
				
				try {
					EmailSender.sendEmail(emailForgot, "Hi,\n\n\nYour password is: \n\n" + userForgot.getPassword() + "\n\nRegards,\nTeam dbl.\n\n#secure");
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
			session.setAttribute("getPasswordPressed", true);
			requestdispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
			requestdispatcher.forward(request, response);
		
		break;
		
		
		
		}
		
		

		
		doGet(request, response);
	}

	
	
	
	
	
	// HELP METHODS
	
	public boolean isCorrectLogin(String username, String password){
		return true;
	}
	
	
	
	public boolean isValidUsername(String username){
        if(!username.matches("^[a-zA-Z0-9]*$")){
            return false;}
		return true;
	}
	
	
	public boolean isAvailableUsername(String username){
		if (!sql.getUsernames().contains(username)) {
			return false;
		}
		return true;
	}
	
	
	
	// Checks birthdate
	public boolean isValidDOB(String dob){
		if(!(dob.trim().length()==8)){
			System.out.println("ikke riktig antall");
			return false;
		}
		if(!dob.matches("[0-9]+")){
			return false;
		}
		if(  (Integer.parseInt(dob.substring(0,2)) >31) || 
				(Integer.parseInt(dob.substring(2,4)) >12) ||
				Integer.parseInt(dob.substring(4,8)) >2016 || 
				Integer.parseInt(dob.substring(4,8)) <1900) {
					return false;
				}
		return true;
	}
	
	// Checks email address
	public boolean isValidEmailAddress(String email) {
		   boolean result = true;
		  
		   //TODO: check email
		   
		   ArrayList<String> emails = sql.getEmails();
//		   System.out.println(emails);
		 if (emails.contains(email)) {
			result = false;}
		   return result;
	}
	
	
	
	public String getConfirmationLink(String username){
		
		
		String confirmationLinkHash = getRandomString(8);
		String res = "http://localhost:8080/Web-Applications-Engineering/confirmationPage.jsp?user=" + username + "&hash=" + confirmationLinkHash;
		
		return res;
	}
	
	
	public String getRandomString(int len){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	
}
