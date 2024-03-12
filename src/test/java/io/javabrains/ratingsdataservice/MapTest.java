package io.javabrains.ratingsdataservice;

import io.javabrains.ratingsdataservice.models.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class MapTest {
    @Test
    void test1() {
        //Write a Java program to associate the specified value with the specified key in a HashMap.
        HashMap<Integer,String> colorHash_map= new HashMap<>();
        colorHash_map.put(1, "Red");
        colorHash_map.put(2, "Green");
        colorHash_map.put(3, "Black");
        colorHash_map.put(4, "White");
        colorHash_map.put(5, "Blue");
        for(Map.Entry x:colorHash_map.entrySet()){
            System.out.println(x.getKey()+" "+x.getValue());
        }
    }

    @Test
    void test2() {
        //Write a Java program to count the number of key-value (size) mappings in a map.
        HashMap<Integer,String> shapes_map= new HashMap<>();
        shapes_map.put(1, "Square");
        shapes_map.put(2, "Triangle");
        shapes_map.put(3, "Rombos");
        shapes_map.put(4, "Rectangle");

        System.out.println("Size of the hash map: "+shapes_map.size());
    }


    @Test
    void test3() {
        //Write a Java program to copy all mappings from the specified map to another map.
        HashMap <Integer,String> jewlery_map1 = new HashMap <> ();
        HashMap <Integer,String> clothes_map2 = new HashMap <> ();
        // populate hash maps
        jewlery_map1.put(1, "Ring");
        jewlery_map1.put(2, "Necklace");
        jewlery_map1.put(3, "Toe Ring");
        System.out.println("\nValues in first map: " + jewlery_map1);
        clothes_map2.put(4, "Shirt");
        clothes_map2.put(5, "Jeans");
        clothes_map2.put(6, "Underwear");
        System.out.println("\nValues in second map: " + clothes_map2);

        // put all values in secondmap
        clothes_map2.putAll(jewlery_map1);
        System.out.println("\nNow values in second map: " + clothes_map2);
    }


    @Test
    void test4() {
        //4. Write a Java program to remove all mappings from a map.
        HashMap <Integer,String> coffee_map = new HashMap <> ();
        coffee_map.put(1, "Black Coffee");
        coffee_map.put(2, "Mochachino");
        coffee_map.put(3, "Capuchino");
        coffee_map.put(4, "Vanilla Capuchino");
        coffee_map.put(5, "Mint Coffee");
        // print the map
        System.out.println("The Original linked map: " + coffee_map);
        // Removing all the elements from the linked map
        coffee_map.clear();
        System.out.println("The New map: " + coffee_map);
    }


    @Test
    void test5() {
        //Write a Java program to check whether a map contains key-value mappings (empty) or not.
        HashMap <Integer,String> flower_map = new HashMap <> ();
        flower_map.put(1, "Tulip");
        flower_map.put(2, "Rose");
        flower_map.put(3, "Sunflower");
        flower_map.put(4, "Lily");
        flower_map.put(5, "Daffodil");
        // check if map is empty
        boolean result = flower_map.isEmpty();
        // check the result
        System.out.println("Is hash map empty: " + result);
        // Removing all the elements from the linked map
        flower_map.clear();
        // check if map is empty
        result = flower_map.isEmpty();
        // check the result
        System.out.println("Is hash map empty: " + result);
    }


    @Test
    void test6() {
        //Write a Java program to get a shallow copy of a HashMap instance.
        HashMap<Integer,String> treeTypes_map= new HashMap<>();
        treeTypes_map.put(1, "The Maples");
        treeTypes_map.put(2, "Red Pine");
        treeTypes_map.put(3, "The Oaks");
        treeTypes_map.put(4, "Eastern White Pine");
        treeTypes_map.put(5, "Black Willow");
        // print the map
        System.out.println("The Original map: " + treeTypes_map);
        HashMap<Integer,String> new_hash_map;
        new_hash_map = (HashMap)treeTypes_map.clone();
        System.out.println("Cloned map: " + new_hash_map);
    }


    @Test
    void test7() {
        //7. Write a Java program to test if a map contains a mapping for the specified key.
        HashMap < String, Integer > energyTypes_map = new HashMap <> ();
        energyTypes_map.put("Nuclear Energy", 1);
        energyTypes_map.put("Chemical energy", 2);
        energyTypes_map.put("Solar energy", 3);
        energyTypes_map.put("Electrical energy", 4);
        energyTypes_map.put("Gravitational energy", 5);
        // print the map
        System.out.println("The Original map: " + energyTypes_map);
        System.out.println("1. Is key 'Chemical energy' exists?");
        if (energyTypes_map.containsKey("Chemical energy")) {
            //key exists
            System.out.println("yes! - " + energyTypes_map.get("Chemical energy"));
        } else {
            //key does not exists
            System.out.println("no!");
        }

        System.out.println("\n2. Is key 'Hydropower' exists?");
        if (energyTypes_map.containsKey("Hydropower")) {
            System.out.println("yes! - " + energyTypes_map.get("Hydropower"));
        } else {
            System.out.println("no!");
        }
    }


    @Test
    void test8() {
        //Write a Java program to test if a map contains a mapping for the specified value.
        HashMap < Integer, String > businessType_map = new HashMap <> ();
        businessType_map.put(1, "Business Analyst");
        businessType_map.put(2, "Finance");
        businessType_map.put(3, "Marketing");
        businessType_map.put(4, "Accounting");
        businessType_map.put(5, "Sales");
        // print the map
        System.out.println("The Original map: " + businessType_map);
        System.out.println("1. Is value \'Sales\' exists?");
        if (businessType_map.containsValue("Sales")) {
            //value exists
            System.out.println("Yes! ");
        } else {
            //value does not exists
            System.out.println("no!");
        }

        System.out.println("\n2. Is value \'Finance Manager\' exists?");
        if (businessType_map.containsValue("Finance Manager")) {
            System.out.println("yes! - " );
        } else {
            System.out.println("No!");
        }
    }

    @Test
    void test9() {
        //Write a Java program to create a set view of the mappings contained in a map.
        HashMap < Integer, String > doctorType_map = new HashMap <> ();
        doctorType_map.put(1, "Dermatologist");
        doctorType_map.put(2, "Cardiologist");
        doctorType_map.put(3, "Obstetricians and Gynecologists");
        doctorType_map.put(4, "Emergency Medicine");
        // create set view for the map
        Set set = doctorType_map.entrySet();
        // check set values
        System.out.println("Set values: " + set);
    }


    @Test
    void test10() {
        //Write a Java program to get the value of a specified key in a map.
        HashMap<Integer,String> girlName_map= new HashMap<>();
        girlName_map.put(1,"Stephanie");
        girlName_map.put(2,"Liliana");
        girlName_map.put(3,"Fernanda");
        girlName_map.put(4,"Alexa");
        girlName_map.put(5,"Naomy");
        // get value of key 3
        String val=(String)girlName_map.get(3);
        // check the value
        System.out.println("Value for key 3 is: " + val);
    }


    @Test
    void test11() {
        //Write a Java program to get a set view of the keys contained in this map.
        HashMap<Integer,String> languages_map= new HashMap<>();

        languages_map.put(1,"Spanish");
        languages_map.put(2,"English");
        languages_map.put(3,"Chinese");
        languages_map.put(4,"Portuguese");
        languages_map.put(5,"Dutch");

        // get keyset value from map
        Set keyset = languages_map.keySet();

        // check key set values
        System.out.println("Key set values are: " + keyset);
    }


    @Test
    void test12() {
        //Write a Java program to get a collection view of the values contained in this map.
        HashMap<Integer,String> series_map= new HashMap<>();
        series_map.put(1,"Prison Break");
        series_map.put(2,"Pretty Little Liars");
        series_map.put(3,"Greys Anatomy");
        series_map.put(4,"Revenge");
        series_map.put(5,"The Walking Dead");

        // checking collection view of the map
        System.out.println("Collection view is: "+ series_map.values());
    }

    @Test
    void equalsAndHashcode() {

        Rating valor = new Rating("11", 1);
        Rating valor2 = new Rating("11", 1);

        System.out.println(valor.hashCode());
        System.out.println(valor2.hashCode());
        System.out.println(valor.equals(valor2));
    }
 }

