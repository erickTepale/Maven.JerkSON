package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroceryReporter {
    private final String originalFileText;
    private List<Item> list;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
        this.list = new ArrayList<>();
    }

    @Override
    public String toString() {
        try {
            list = new ItemParser().parseItemList(originalFileText);
        } catch (ItemParseException e) {
            e.printStackTrace();
        }

        //create map<String, Integer> map that counts item names and one that counts item prices :( out of time.
        Map<String, Integer> itemNameMap = new HashMap<>();
        Map<Double, Integer> priceMap = new HashMap<>();

        for (Item each : list) {
            if(!itemNameMap.containsKey(each.getName()))
                itemNameMap.put(each.getName(), 1);
            else
                itemNameMap.put(each.getName(), itemNameMap.get(each.getName()) + 1);

            if(!priceMap.containsKey(each.getPrice()))
                priceMap.put(each.getPrice(), 1);
            else
                priceMap.put(each.getPrice(), priceMap.get(each.getPrice()) + 1);

        }

        //format output
        StringBuilder output = new StringBuilder();

        //get stream of unique values
        list.stream().distinct().forEach(//have to edit for distinct itemNames!
                list->{
                    //so grab only MILK in stream and print milk from map, then comapare all milk price in pricemap and add to output
                }
        );

        return null;
    }
}
