package Database;

public class Variable {

	private String drink;
	private String time;
	private static String tasteOrder;
	private static String seatOrder;
	private static String sizeOrder;
	private static String categoryOrder;
	private static String[] taste = {"coffee", "juice", "tea"};
	private static String[] category = {"Coffee", "Juice", "Tea"};
	private static int refill;
	
	public Variable(){
		
	}
	
	public Variable (String drink, String time){
		
		this.drink = drink;
		this.time = time;
		
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public static String getTasteOrder() {
		return tasteOrder;
	}

	public static void setTasteOrder(String tasteOrder) {
		Variable.tasteOrder = tasteOrder;
	}

	public static String getSeatOrder() {
		return seatOrder;
	}

	public static void setSeatOrder(String seatOrder) {
		Variable.seatOrder = seatOrder;
	}

	public static String getSizeOrder() {
		return sizeOrder;
	}

	public static void setSizeOrder(String sizeOrder) {
		Variable.sizeOrder = sizeOrder;
	}
	
	
	public static String[] getTaste() {
		return taste;
	}

	public static void setTaste(String[] taste) {
		Variable.taste = taste;
	}
	
	public static String[] getCategory() {
		return category;
	}

	public static void setCategory(String[] category) {
		Variable.category = category;
	}
	
	
	public String toString(){
		return drink +" " +time;
	}

	public static String getCategoryOrder() {
		return categoryOrder;
	}

	public static void setCategoryOrder(String categoryOrder) {
		Variable.categoryOrder = categoryOrder;
	}

	public static int getRefill() {
		return refill;
	}

	public static void setRefill(int refill) {
		Variable.refill = refill;
	}



	
	
	
}
