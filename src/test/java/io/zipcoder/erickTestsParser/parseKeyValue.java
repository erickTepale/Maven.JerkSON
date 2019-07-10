package io.zipcoder.erickTestsParser;


import io.zipcoder.ItemParser;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Tests parsing 1 key value to object
 *
 */
public class parseKeyValue {

    @Test
    public void test1() throws ItemParseException {
        // given


        ItemParser itemParser = new ItemParser();
        String valueToParse = new StringBuilder()
                .append("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##")
                .toString();

        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");

        // when
        Item actualList = itemParser.parseSingleItem(valueToParse);

        // then
        assertEquals(item1, actualList);
    }

    @Test
    public void test2() throws ItemParseException {

            String text = "The Quick Brown Fox";
            String patternString = ".";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(text);
            for (int i = 0; matcher.find(); i++) {
                System.out.println(new StringBuilder()
                        .append("\n-------------------")
                        .append("\nValue = " + matcher.group())
                        .append("\nMatch Number = " + i)
                        .append("\nStarting index = " + matcher.start())
                        .append("\nEnding index = " + matcher.end())
                        .toString());

            }

    }

    @Test
    public void testMatcher() throws ItemParseException {
        ItemParser a = new ItemParser();
        String valueToParse = new StringBuilder()
                .append("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##")
                .append("NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##")
                .append("NAMe:eggs;price:1.24;type:Food;expiration:2/25/2016##")
                .append("NAMe:BacoN;price:1.25;type:Food;expiration:2/25/2016##")
                .toString();

        a.parseItemList(valueToParse);
    }

//    @Test
//    public void testTrim(){
//        ItemParser a = new ItemParser();
//
//        Assert.assertEquals("Milk", a.trim("^Milk;"));
//        Assert.assertEquals("Milk", a.trim("%Milk##"));
//    }


    @Test
    public void presentTest1(){
        Pattern pattern = Pattern.compile(":([^;]*);");
        Matcher matcher = pattern.matcher("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##");
        try {
            for (int i = 0; matcher.find(); i++) {

                System.out.println(new StringBuilder()
                        .append("\n-------------------")
                        .append("\nValue = " + matcher.group())
                        .append("\nMatch Number = " + i)
                        .append("\nStarting index = " + matcher.start())
                        .append("\nEnding index = " + matcher.end())
                        .toString());

            }

        }catch (Exception e){
            System.out.println("yerr");
        }
    }

    @Test
    public void presentTest2(){
        Pattern pattern = Pattern.compile("[:@^*%]([^;]*)[;#]");
        Matcher matcher = pattern.matcher("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##");
        try {
            for (int i = 0; matcher.find(); i++) {

                System.out.println(new StringBuilder()
                        .append("\n-------------------")
                        .append("\nValue = " + matcher.group())
                        .append("\nMatch Number = " + i)
                        .append("\nStarting index = " + matcher.start())
                        .append("\nEnding index = " + matcher.end())
                        .toString());

            }

        }catch (Exception e){
            System.out.println("yerr");
        }
    }

    @Test
    public void presentTest3(){
        Pattern pattern = Pattern.compile("(?<=[:@^*%])(.*?)(?=[;#])"); //matches everything between :@^*% and ; OR #, not including them
        Matcher matcher = pattern.matcher("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##");
        try {
            for (int i = 0; matcher.find(); i++) {

                System.out.println(new StringBuilder()
                        .append("\n-------------------")
                        .append("\nValue = " + matcher.group())
                        .append("\nMatch Number = " + i)
                        .append("\nStarting index = " + matcher.start())
                        .append("\nEnding index = " + matcher.end())
                        .toString());

            }

        }catch (Exception e){
            System.out.println("yerr");
        }
    }

    @Test
    public void presentTest1Group(){
        String valueToParse = new StringBuilder()
                .append("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##")
                .append("NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##")
                .append("NAMe:eggs;price:1.24;type:Food;expiration:2/25/2016##")
                .append("NAMe:BacoN;price:1.25;type:Food;expiration:2/25/2016##")
                .toString();

        Pattern pattern = Pattern.compile("([^#]*)##");
        Matcher matcher = pattern.matcher(valueToParse);
        try {
            for (int i = 0; matcher.find(); i++) {

                System.out.println(new StringBuilder()
                        .append("\n-------------------")
                        .append("\nValue = " + matcher.group())
                        .append("\nMatch Number = " + i)
                        .append("\nStarting index = " + matcher.start())
                        .append("\nEnding index = " + matcher.end())
                        .toString());

            }

        }catch (Exception e){
            System.out.println("yerr");
        }
    }
}
