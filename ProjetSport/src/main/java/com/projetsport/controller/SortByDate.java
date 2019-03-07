package com.projetsport.controller;

import java.util.Comparator;

import com.projetsport.entities.Evenement;

public class SortByDate implements Comparator<Evenement> {
	public int compare(Evenement e1, Evenement e2) {
		String date1 = e1.getDateEvent();
		String date2 = e2.getDateEvent();
		
		//String date1reordered = date1.substring(0, 2) + date1.substring(3, 5) + date1.substring(6);
		//String date2reordered = date2.substring(0, 2) + date2.substring(3, 5) + date2.substring(6);
		
		//int date1int = Integer.parseInt(date1reordered);
		//int date2int = Integer.parseInt(date2reordered);
		
		int result = date1.compareTo(date2);
		return result;
	}
}
