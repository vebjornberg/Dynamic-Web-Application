package WebApplication;

public class PublicationBean {
	
	private String firstname, lastname, title, year, price, type;
	private int publicationid, authorid, sale, numsold, numremoved;
	
	
	public PublicationBean() {
		
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String date) {
		this.year = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getPublicationid() {
		return publicationid;
	}
	public void setPublicationid(int publicationid) {
		this.publicationid = publicationid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public int getNumsold() {
		return numsold;
	}
	public void setNumsold(int numsold) {
		this.numsold = numsold;
	}
	public void incrementNumsold(){
		this.numsold++;
	}
	public int getNumremoved() {
		return numremoved;
	}
	public void setNumremoved(int numremoved) {
		this.numremoved = numremoved;
	}
	public void incrementNumremoved(){
		this.numremoved++;
	}
	
	
	
	
}

