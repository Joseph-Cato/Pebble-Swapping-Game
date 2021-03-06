package pebbelgame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {

    private ArrayList<Integer> pebbles;
    private final char bagIdentifier;
    private Bag siblingBag;


    public Bag(char bagIdentifier) {
        pebbles = new ArrayList<>(100);
        this.bagIdentifier = bagIdentifier;
    }

    public char getBagIdentifier() {
        return bagIdentifier;
    }

    public void setSiblingBag(Bag siblingBag) {
        this.siblingBag = siblingBag;
    }

    public Bag getSiblingBag() {
        return siblingBag;
    }

    public void setPebbles(ArrayList<Integer> pebbles) {
        this.pebbles = pebbles;
    }

    public ArrayList<Integer> getPebbles() {
        return pebbles;
    }

    public synchronized void emptyBag() {


        this.setPebbles(siblingBag.getPebbles() );

        siblingBag.setPebbles(new ArrayList<>() );

    }


    // ! Throwing NullPointer when bag is empty, player class can catch exception and try another bag
    public synchronized int draw() throws NullPointerException {

        try {

            // Using ThreadLocalRandom for performance reasons
            int randomIndex = ThreadLocalRandom.current().nextInt(pebbles.size());

            int pebble = pebbles.get(randomIndex);

            pebbles.remove(randomIndex);

            return pebble;

        } catch (IllegalArgumentException e) {
            throw new NullPointerException();
        }
    }

    public void place(int i) {

        // Pebble is added to the end of the ArrayList so the indexes being used by draw() are not interfered with
        pebbles.add(i);

    }




}
