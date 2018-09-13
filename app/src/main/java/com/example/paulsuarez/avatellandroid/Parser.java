package com.example.paulsuarez.avatellandroid;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser{
    String currSearch = "";

    LinkedList<String> currVals = new LinkedList<String>();

    public static void main(String[] args, Context context) {
        Parser parser = new Parser();
        parser.loadList(context);
        parser.refine("a");
        parser.refine("ar");
        parser.refine("art");
        System.out.print(parser.currVals);
    }

    public LinkedList<String> loadList (Context context){
        try {

            Pattern p = Pattern.compile("^[A-Z]");
            //AssetManager am = context.getAssets();
            //InputStream inputStream = am.open("Avalara_Good_Service.txt");
            InputStream inputStream = context.getResources().openRawResource(R.raw.table);

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

    public LinkedList<String> backSpaceHandler(Context context){
        this.currSearch = this.currSearch.substring(0,this.currSearch.length()-1);
        loadList(context);
        return refine(this.currSearch);
    }

}
