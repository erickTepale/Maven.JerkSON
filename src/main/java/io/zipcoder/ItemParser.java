package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {


    public List<Item> parseItemList(String valueToParse) throws ItemParseException {
        List<Item> items = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^#]*)##"); // idk why but it works!
        Matcher matcher = pattern.matcher(valueToParse);
        for(int i = 0; matcher.find(); i++){
            items.add(parseSingleItem(matcher.group()));
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
        for (int i = 0; matcher.find(); i++) {
            if(i == 0) name = trim(matcher.group()).toLowerCase();
            else if (i == 1) price = Double.parseDouble(trim(matcher.group()));
            else if (i==2) type = trim(matcher.group()).toLowerCase();
            else if(i == 3) expiration = trim(matcher.group());
            else System.out.println("error parsing?");
        }

        return new Item(name, price, type, expiration);
    }

    public String trim(String word){
        if(word.charAt(word.length() - 2) == '#'){
            return word.substring(1, word.length()-2);
        }
        return word.substring(1, word.length() - 1);
    }
}
