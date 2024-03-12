package io.javabrains.ratingsdataservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestLists {
    @Test
    void test1() {
        //Write a Java program to create an array list, add some colors (strings) and print out the collection.
        ArrayList<String> colorsArrayList = new ArrayList<>();
        colorsArrayList.add("Blue");
        colorsArrayList.add("Red");
        colorsArrayList.add("Green");
        colorsArrayList.add("Yellow");
        colorsArrayList.add("Purple");

        for (String element : colorsArrayList) {
            System.out.println(element);

//        for (int i = 0; i < colorsArrayList.size(); i++ ) {
//
//            System.out.println("Item at index " + i + " is " + colorsArrayList.get(i).toString());

//        System.out.println(colorsArrayList.get(2));

        }
    }

    @Test
    void test2() {
        // Write a Java program to iterate through all elements in an array list.
        ArrayList<String> nameArrayList = new ArrayList<String>();
        nameArrayList.add("Queen");
        nameArrayList.add("Nana");
        nameArrayList.add("Lyla");
        nameArrayList.add("Jose");
        nameArrayList.add("Steph");
        // Print the list
        for (String element : nameArrayList) {
            System.out.println(element);
        }
    }

    @Test
    void test3() {
//Write a Java program to insert an element into the array list at the first position.
        ArrayList<String> colorsArrayList = new ArrayList<>();
        colorsArrayList.add("Blue");
        colorsArrayList.add("Red");
        colorsArrayList.add("Green");
        colorsArrayList.add("Yellow");
        colorsArrayList.add("Purple");
        colorsArrayList.add(0, "Orange");
        for (String element : colorsArrayList) {
            System.out.println(element);
        }

    }

    @Test
    void test4() {
        //Write a Java program to retrieve an element (at a specified index) from a given array list.
        ArrayList<String> iceCreamArrayList = new ArrayList<>();
        iceCreamArrayList.add("Vanilla");
        iceCreamArrayList.add("Chocolate");
        iceCreamArrayList.add("Strawberry");
        iceCreamArrayList.add("Caramel");

        System.out.println(iceCreamArrayList.get(1));
    }

    @Test
    void test5() {
    //Write a Java program to update an array element by the given element.
        ArrayList<String> coctelsArrayList = new ArrayList<>();
        coctelsArrayList.add("Piña Colada");
        coctelsArrayList.add("Margarita");
        coctelsArrayList.add("Mojito");
        coctelsArrayList.add("Sangria");

        coctelsArrayList.set(2, "Duende");

        for (String element : coctelsArrayList) {
            System.out.println(element);
        }
    }

    @Test
    void test6() {
        //Write a Java program to remove the third element from an array list.
        ArrayList<String> dogBreedArrayList = new ArrayList<>();
        dogBreedArrayList.add("Chihuahua");
        dogBreedArrayList.add("Pitbull");
        dogBreedArrayList.add("Husky");
        dogBreedArrayList.add("French Poodle");
        dogBreedArrayList.add("Dalmata");

        dogBreedArrayList.remove(2);
        System.out.println(dogBreedArrayList);

        for (String element : dogBreedArrayList) {
            System.out.println(element);
        }
    }

    @Test
    void test7() {
    //Write a Java program to search for an element in an array list.
        List<String> menuArrayList = new ArrayList<>();
        menuArrayList.add("Gallo Pinto");
        menuArrayList.add("Casado");
        menuArrayList.add("Pollo con papas");
        menuArrayList.add("Pescado");
        menuArrayList.add("Ensalada");
        // Search the value Gallo Pinto in the menu
        if (menuArrayList.contains("Gallo Pinto")) {
            System.out.println("Found the dish");
        } else {
            System.out.println("There is no Gallo Pinto in the menu");
        }
    }

    @Test
    void test8() {
    //Write a Java program to sort a given array list.
        List<String> countiesArrayList = new ArrayList<>();
        countiesArrayList.add("Costa Rica");
        countiesArrayList.add("Puerto Rico");
        countiesArrayList.add("Nicaragua");
        countiesArrayList.add("Cuba");
        countiesArrayList.add("Republica Dominicana");
        System.out.println("List before sort: "+countiesArrayList);

        Collections.sort(countiesArrayList);
        System.out.println("List after sort: "+countiesArrayList);
    }

    @Test
    void test9() {
    //Write a Java program to copy one array list into another.
        List<String> animalsArrayList = new ArrayList<>();
        animalsArrayList.add("Elephante");
        animalsArrayList.add("Zebra");
        animalsArrayList.add("Dog");
        animalsArrayList.add("Cat");
        animalsArrayList.add("Tiger");
        System.out.println("List1: " + animalsArrayList);

        List<String> ejemplo = new ArrayList<>(animalsArrayList);


        List<String> seaAnimalsArrayList = new ArrayList<String>();
        seaAnimalsArrayList.add("Dolphin");
        seaAnimalsArrayList.add("Shark");
        seaAnimalsArrayList.add("Turtle");
        seaAnimalsArrayList.add("Sea Horse");
        seaAnimalsArrayList.add("Fish");
        //seaAnimalsArrayList.addAll(animalsArrayList); agregar todos los elementos de una lista en otra
        System.out.println("List2: " + seaAnimalsArrayList);

        // Copy List2 to List1
        Collections.copy(animalsArrayList, seaAnimalsArrayList);
        System.out.println("Copy List1 to List2,\nAfter copy:");
        System.out.println("List1: " + animalsArrayList);
        System.out.println("List2: " + seaAnimalsArrayList);
    }

    @Test
    void test10() {
    //Write a Java program to extract a portion of an array list.
        List<String> fastFoodArrayList = new LinkedList<>();
        fastFoodArrayList.add("Burger");
        fastFoodArrayList.add("Hot Dog");
        fastFoodArrayList.add("French Fries");
        fastFoodArrayList.add("Tacos");
        fastFoodArrayList.add("Pizza");
        fastFoodArrayList.add("Quesadilla");


        System.out.println("Original list: " + fastFoodArrayList);

         ArrayList<String> sub_List = new ArrayList<>(fastFoodArrayList.subList (0,3));
        System.out.println("List of first three elements: " + sub_List);

    }
}


