package com.daxton.fancychat;


import com.daxton.fancycore.api.other.CountWords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args){
        String outputString = "{#f77703}test"; //


        System.out.println(randomHex());




    }

    public static String randomHex(){
        int k = (int) (Math.random() *16) +1;
        return toHex(k);
    }
    public static String toHex(int in){
        String out = "0";
        if(in == 10){
            out = "A";
        }
        if(in == 11){
            out = "B";
        }
        if(in == 12){
            out = "C";
        }
        if(in == 13){
            out = "D";
        }
        if(in == 14){
            out = "E";
        }
        if(in == 15){
            out = "F";
        }
        if(in < 10 && in > 0){
            out = String.valueOf(in);
        }

        return out;
    }

}
