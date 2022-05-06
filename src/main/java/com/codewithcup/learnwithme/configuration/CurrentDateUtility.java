package com.codewithcup.learnwithme.configuration;

import java.util.Date;

public class CurrentDateUtility {
     String strdate;
    public String currentDate(){
        Date currdate = new Date();
        strdate = String.format("%1$td %1$tB, %1$tY", currdate);
//        System.out.printf(strdate);
        return strdate;
    }
}
