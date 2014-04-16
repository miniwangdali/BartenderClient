package com.artificialarm.bartenderclient.common;

public class Variable {

	private static String tasteOrder = "None", seatOrder = "None";
	private static String[] taste = {"Capriccio", "Vivalto Lungo", "Vrosabaya de Colombia", "Livanto"};

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
