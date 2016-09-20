package WebApplication;

import java.util.ArrayList;

public class CartBean {
	
	private ArrayList<PublicationBean> shoppingCart = new ArrayList<PublicationBean>();
	
	public ArrayList<PublicationBean> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ArrayList<PublicationBean> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public void addToShoppingCart(PublicationBean pb){
		shoppingCart.add(pb);
	}
	public void removeFromShoppingCart(PublicationBean pb){
		shoppingCart.remove(pb);
	}

	public CartBean() {
	// TODO Auto-generated constructor stub
	}
}
