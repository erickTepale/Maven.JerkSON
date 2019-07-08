package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public static Integer wackEntry = 0;
    public static Integer totalMatches = 0;

    public List<Item> parseItemList(String valueToParse) throws ItemParseException {
        List<Item> items = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^#]*)##"); // idk why but it works!
        Matcher matcher = pattern.matcher(valueToParse);
        for(int i = 0; matcher.find(); i++){
            Item temp = parseSingleItem(matcher.group());
            if(temp != null) items.add(temp);
        }


        return items;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        String name = "";
        Double price = 0.0;
        String type = "";
        String expiration = "";

        //Pattern pattern = Pattern.compile("[:@^*%]");
        Pattern pattern = Pattern.compile("[:@^*%]([^;]*)[;|##]"); // matches everything between :@^*% and ;
        Matcher matcher = pattern.matcher(singleItem);
        try {
            for (int i = 0; matcher.find(); i++) {
                if (i == 0){ name = trim(matcher.group()).toLowerCase(); totalMatches++;}
                else if (i == 1) {price = Double.parseDouble(trim(matcher.group())); totalMatches++;}
                else if (i == 2){ type = trim(matcher.group()).toLowerCase(); totalMatches++;}
                else if (i == 3) {expiration = trim(matcher.group()); totalMatches++;}
                else{ System.out.println("error parsing?"); }
            }
            if (totalMatches.equals(0))
                throw new ItemParseException();
            if (name.equals("") || price.equals(0.0) || type.equals("") || expiration.equals(""))
                return null;

            return new Item(name, price, type, expiration);
        }catch (Exception e){
            wackEntry++;
            throw new ItemParseException();
        }

       //return null;
    }

    public String trim(String word){
        if(word.charAt(word.length() - 2) == '#'){
            return word.substring(1, word.length()-2);
        }
        return word.substring(1, word.length() - 1);
    }
}
