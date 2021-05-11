package com.crafts.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class dateutil {

	public static Date getCurrentTime() throws ParseException {
		LocalDateTime myDateObj = LocalDateTime.now();  
	    //System.out.println("Before formatting: " + myDateObj);  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	    
	    String formattedDate = myDateObj.format(myFormatObj);  
	    //System.out.println("After formatting: " + formattedDate);  
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date c= sdf.parse(formattedDate);
		//String date=sdf.format(c);
		//System.out.println(date);
		
		return c;
	}
}
