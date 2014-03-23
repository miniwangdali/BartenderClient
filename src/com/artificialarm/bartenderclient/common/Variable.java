package com.artificialarm.bartenderclient.common;

public class Variable {

	private static String bartenderOrder,tasteOrder,seatOrder;
	private static String[] taste = {"Coffee", "Tea", "Hot Water"};

	public static String getBartenderOrder() {
		return bartenderOrder;
	}

	public static void setBartenderOrder(String bartenderOrder) {
		Variable.bartenderOrder = bartenderOrder;
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

	public static String[] getTaste() {
		return taste;
	}

	public static void setTaste(String[] taste) {
		Variable.taste = taste;
	}


	
}
