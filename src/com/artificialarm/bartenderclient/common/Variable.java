package com.artificialarm.bartenderclient.common;

public class Variable {

	private static String bartenderOrder,tasteOrder,seatOrder;

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


	
}
