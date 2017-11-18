package model.classes;

public class StockProduct extends Product{

	public StockProduct() {
		super();
	}
	
	public StockProduct(String name, int price, int quantity) {
		super(name, price, quantity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int increaseQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int decreaseQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
