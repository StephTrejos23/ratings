package io.javabrains.ratingsdataservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SetTest {
    @Test
    void test1() {
        //Duplicates : ArrayList allows duplicate values while HashSet doesn’t allow duplicates values.
        //Null Object: ArrayList not apply any restriction, we can add any number of null value while HashSet allow one null value.

        //Write a Java program to append the specified element to the end of a hash set.
        Set<String> colorHashSet = new HashSet<>();

        colorHashSet.add("Red");
        colorHashSet.add("Green");
        colorHashSet.add("Black");
        colorHashSet.add("White");
        colorHashSet.add("Pink");
        colorHashSet.add("Yellow");

        // print the hash set
        System.out.println("The Hash Set: " + colorHashSet);


        Set<String> strings = new LinkedHashSet<>();

        strings.add("Red");
        strings.add("Green");
        strings.add("Black");
        strings.add("White");
        strings.add("Pink");
        strings.add("Yellow");

        // print the hash set
        System.out.println();
        System.out.println("The Linked Hash Set: " + strings);

        Set<String> treeSet = new TreeSet<>();

        treeSet.add("Red");
        treeSet.add("Green");
        treeSet.add("Black");
        treeSet.add("White");
        treeSet.add("Pink");
        treeSet.add("Yellow");

        // print the hash set
        System.out.println();
        System.out.println("The Tree Set: " + treeSet);
    }

    @Test
    void test2() {
        //Write a Java program to iterate through all elements in a hash set.

        HashSet<String> nameHashSet = new HashSet<>();

        nameHashSet.add("Ana");
        nameHashSet.add("Maybel");
        nameHashSet.add("Kimberly");
        nameHashSet.add("Stephanie");
        nameHashSet.add("Mariel");
        nameHashSet.add("Isabel");

        // set Iterator
        // Iterate the hash set
//        nameHashSet.forEach(System.out::println);
        for (String s : nameHashSet) {
            System.out.println(s);
        }
    }

    @Test
    void test3() {
        //Write a Java program to get the number of elements in a hash set.
        HashSet<String> lastnameHashSet = new HashSet<String>();
        lastnameHashSet.add("Trejos");
        lastnameHashSet.add("Gonzalez");
        lastnameHashSet.add("Chaves");
        lastnameHashSet.add("Rivera");

        System.out.println("Original Hash Set: " + lastnameHashSet);
        System.out.println("Size of the Hash Set: " + lastnameHashSet.size());
    }

    @Test
    void test4() {
        //Write a Java program to empty an hash set.
        HashSet<String> animalHashSet = new HashSet<String>();
        animalHashSet.add("Dog");
        animalHashSet.add("Cat");
        animalHashSet.add("Bear");
        animalHashSet.add("Gorilla");
        animalHashSet.add("Bird");
        System.out.println("Original Hash Set: " + animalHashSet);

        HashSet<String> animals = new HashSet<String>();
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Bear");

        // Remove all elements
        animalHashSet.removeAll(animals);
//        animals.clear();
        System.out.println("Hash Set after removing all the elements " + animalHashSet);

    }

    @Test
    void test5() {
        //Write a Java program to test if a hash set is empty or not.
        HashSet<String> movie_set = new HashSet<>();
        movie_set.add("Me Before You");
        movie_set.add("Titanic");
        movie_set.add("Twilight");
        movie_set.add("Fast and Furious");
        System.out.println("Original Hash Set: " + movie_set);
        System.out.println("Checking the above array list is empty or not! " + movie_set.isEmpty());
        System.out.println("Remove all the elements from a Hash Set: ");
        movie_set.clear();
        System.out.println("Hash Set after removing all the elements " + movie_set.isEmpty());
    }

    @Test
    void test6() {
        //Write a Java program to clone a hash set to another hash set.
        HashSet<String> menNameHashSet = new HashSet<>();

        menNameHashSet.add("Luis");
        menNameHashSet.add("Kevin");
        menNameHashSet.add("Ronald");
        menNameHashSet.add("Alvaro");
        menNameHashSet.add("Claudio");
        menNameHashSet.add("Anderson");
        System.out.println("Original Hash Set: " + menNameHashSet);
        HashSet<String> new_menName_set = (HashSet) menNameHashSet.clone();
        System.out.println("Cloned Hash Set: " + new_menName_set);
    }

    @Test
    void test7() {
        //Write a Java program to convert a hash set to an array.
        HashSet<String> exerciseHashSet = new HashSet<String>();

        exerciseHashSet.add("Hip Thrust");
        exerciseHashSet.add("Press");
        exerciseHashSet.add("Thruster");
        exerciseHashSet.add("Burpee");
        exerciseHashSet.add("Push ups");
        exerciseHashSet.add("Ab Crunches");
        System.out.println("Original Hash Set: " + exerciseHashSet);
            // Creating an Array
        String[] new_array = new String[exerciseHashSet.size()];
        exerciseHashSet.toArray(new_array);

        //Array elements
        System.out.println("Array elements: ");
        for (String element : new_array) {
            System.out.println(element);
        }
    }

    @Test
    void test8() {
        //Write a Java program to convert a hash set to a tree set.
        HashSet<String> socialMediaHashSet = new HashSet<>();

        socialMediaHashSet.add("YouTube");
        socialMediaHashSet.add("Instagram");
        socialMediaHashSet.add("FaceBook");
        socialMediaHashSet.add("Twitter");
        socialMediaHashSet.add("TikTok");
        socialMediaHashSet.add("WhatsApp");
        System.out.println("Original Hash Set: " + socialMediaHashSet);

        // Creat a TreeSet of HashSet elements
        Set<String> tree_set = new TreeSet<>(socialMediaHashSet);

        // Display TreeSet elements
        System.out.println("TreeSet elements: ");
        for (String element : tree_set) {
            System.out.println(element);
        }
    }

    @Test
    void test9() {
        //Write a Java program to find numbers less than 7 in a tree set.
        // creating TreeSet
        TreeSet<Integer> tree_num = new TreeSet<>();
        TreeSet<Integer> treeheadset;

        // Add numbers in the tree
        tree_num.add(1);
        tree_num.add(2);
        tree_num.add(3);
        tree_num.add(5);
        tree_num.add(6);
        tree_num.add(7);
        tree_num.add(8);
        tree_num.add(9);
        tree_num.add(10);

        // Find numbers less than 7
        treeheadset = (TreeSet) tree_num.headSet(7);

        // create an iterator
        Iterator iterator;
        iterator = treeheadset.iterator();

        //Displaying the tree set data
        System.out.println("Tree set data: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }

    }

    @Test
    void test10() {
        //Write a Java program to compare two hash set.
        // Create a empty hash set
        HashSet<String> fruitHashSet = new HashSet<>();

        fruitHashSet.add("Apple");
        fruitHashSet.add("Pear");
        fruitHashSet.add("Orange");
        fruitHashSet.add("Watermelon");

        HashSet<String> vegetableHashSet = new HashSet<>();
        vegetableHashSet.add("Brocoli");
        vegetableHashSet.add("Carrot");
        vegetableHashSet.add("Spinach");
        vegetableHashSet.add("Watermelon");
        //comparison output in hash set
        HashSet<String> result_set = new HashSet<>();
        for (String element : fruitHashSet) {
            System.out.println(vegetableHashSet.contains(element) ? "Yes" : "No");
        }

    }

    @Test
    void test11() {
        //Write a Java program to compare two sets and retain elements that are the same.
        HashSet<String> emotionsHashSet1 = new HashSet<>();

        emotionsHashSet1.add("Happy");
        emotionsHashSet1.add("Confused");
        emotionsHashSet1.add("Sad");
        emotionsHashSet1.add("Angry");
        System.out.println("Frist HashSet content: " + emotionsHashSet1);
        HashSet<String> emotionsHashSet2 = new HashSet<String>();
        emotionsHashSet2.add("Angry");
        emotionsHashSet2.add("Serious");
        emotionsHashSet2.add("Happy");
        emotionsHashSet2.add("Dissapointed");
        System.out.println("Second HashSet content: " + emotionsHashSet2);
        emotionsHashSet1.retainAll(emotionsHashSet2);
        System.out.println("HashSet content:");
        System.out.println(emotionsHashSet1);
    }


    @Test
    void test12() {
        //Write a Java program to remove all elements from a hash set.
        HashSet<String> diseaseHashSet = new HashSet<>();
        // use add() method to add values in the hash set
        diseaseHashSet.add("Cancer");
        diseaseHashSet.add("Corona Virus");
        diseaseHashSet.add("Lyme");
        diseaseHashSet.add("Alzeimer");
        System.out.println("Original hash set contains: "+ diseaseHashSet);
        //  clear() method removes all the elements from a hash set
        // and the set becomes empty.
        diseaseHashSet.clear();

        // Display original hash set content again
        System.out.println("HashSet content: "+diseaseHashSet);
    }
}









