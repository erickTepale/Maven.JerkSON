package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
     private Integer wackEntry = 0;
     private Integer totalMatches = 0;

    public List<Item> parseItemList(String valueToParse) throws ItemParseException {
        List<Item> items = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^#]*)##");
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

        Pattern pattern = Pattern.compile("(?<=[:@^*%])(.*?)(?=[;#])"); //matches everything between :@^*% and ; OR #, not including them
        Matcher matcher = pattern.matcher(singleItem);
        try {
            for (int i = 0; matcher.find(); i++) {
                if (i == 0){ name = matcher.group().toLowerCase(); totalMatches++;}
                else if (i == 1) {price = Double.parseDouble(matcher.group()); totalMatches++;}
                else if (i == 2){ type = matcher.group().toLowerCase(); totalMatches++;}
                else if (i == 3) {expiration = matcher.group(); totalMatches++;}
                else{ System.out.println("error parsing?"); }

            }

            if (totalMatches < 4){
                wackEntry++;
                throw new ItemParseException();

            }

            if (name.equals("") || price.equals(0.0) || type.equals("") || expiration.equals("")){
                wackEntry++;
                return null;
            }

            return new Item(name, price, type, expiration);

        }catch (Exception e){
            wackEntry++;
            throw new ItemParseException();
        }

    }

    public Integer getWackEntry(){
        return wackEntry;
    }
}
