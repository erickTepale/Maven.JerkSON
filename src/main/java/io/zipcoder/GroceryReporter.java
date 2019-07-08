package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
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
        return null;
    }
}
