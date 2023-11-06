package org.userValidation.pancakeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PancakeShopConcurrent {
    public static void main(String[] args) {
        PancakeShopConcurrent pancakeShop = new PancakeShopConcurrent();

        for (int i = 1; i <= 10; i++) {
            System.out.println("Starting 30 seconds slot " + i);
            pancakeShop.simulate30SecondsConcurrently();
            System.out.println("Ending 30 seconds slot " + i);
        }
    }

    public void simulate30SecondsConcurrently() {
        List<Integer> pancakesMadeList = new ArrayList<>();
        List<Integer> pancakesEatenList = new ArrayList<>();

        // Concurrency takes place here using parallel streams
        shopkeepersMakePancakes(pancakesMadeList);
        usersEatPancakes(pancakesEatenList);

        int pancakesMade = pancakesMadeList.stream().mapToInt(Integer::intValue).sum();
        int pancakesEaten = pancakesEatenList.stream().mapToInt(Integer::intValue).sum();

        System.out.println("Shopkeeper made " + pancakesMade + " pancakes.");
        System.out.println("Total pancakes eaten by users: " + pancakesEaten);

        int wastedPancakes = pancakesMade - pancakesEaten;
        if (wastedPancakes > 0) {
            System.out.println("Wasted pancakes: " + wastedPancakes);
        }

        if (pancakesEaten <= pancakesMade) {
            System.out.println("Shopkeeper met the needs of all users.");
        } else {
            System.out.println("Shopkeeper could not meet all needs.");
            int unmetOrders = pancakesEaten - pancakesMade;
            System.out.println("Unmet pancake orders: " + unmetOrders);
        }
    }

    // Simulate the shopkeepers making pancakes concurrently
    private void shopkeepersMakePancakes(List<Integer> pancakesMadeList) {
        Random random = new Random();
        pancakesMadeList.add(random.nextInt(13));
    }

    // Simulate users eating pancakes concurrently
    private void usersEatPancakes(List<Integer> pancakesEatenList) {
        Random random = new Random();
        pancakesEatenList.add(random.nextInt(6)); // Up to 5 pancakes
        pancakesEatenList.add(random.nextInt(6)); // Up to 5 pancakes
        pancakesEatenList.add(random.nextInt(6)); // Up to 5 pancakes
    }
}
