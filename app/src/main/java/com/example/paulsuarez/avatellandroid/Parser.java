package com.example.paulsuarez.avatellandroid;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser{
    String currSearch = "";

    LinkedList<String> currVals = new LinkedList<String>();
    public LinkedList<String> loadList (){
        try {
            Pattern p = Pattern.compile("^[A-Z]");
            ClassLoader classloader = getClass().getClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("com/example/paulsuarez/avatellandroid/data/Avalara_Good_Service.txt");
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNext()){
                String currString = scanner.nextLine();
                Matcher m = p.matcher(currString);
                if(m.find()&&currString.length()>8) {
                    int indexTo = currString.indexOf(',',9);
                    int length = currString.length();
                    currString = currString.substring(0,indexTo);
                    this.currVals.add(currString);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.currVals;
    }

    public LinkedList<String> refine(String input){
        this.currSearch = input;
        LinkedList<String> newVals = new LinkedList<String>();
        for(String curr : this.currVals){
            if(curr.toLowerCase().contains(currSearch.toLowerCase())){
                newVals.add(curr);
            }
        }
        this.currVals = newVals;
        return newVals;
    }

    public LinkedList<String> backSpaceHandler(){
        this.currSearch = this.currSearch.substring(0,this.currSearch.length()-1);
        loadList();
        return refine(this.currSearch);
    }

}
