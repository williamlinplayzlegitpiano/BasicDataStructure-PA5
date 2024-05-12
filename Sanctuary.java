import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Sanctuary {
    
    private final int maxAnimals;
    private final int maxSpecies;
    HashMap<String, Integer> sanctuary;

    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals <= 0 || maxSpecies <= 0 || maxSpecies > maxAnimals) {
            throw new IllegalArgumentException();
        }

        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
        this.sanctuary = new HashMap<>();
    }

    public int countForSpecies(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        }

        return this.sanctuary.getOrDefault(species, 0);
    }

    public int getTotalAnimals() {
        int total = 0;
        for (int count : this.sanctuary.values()) {
            total += count;
        }
        return total;
    }

    public int getTotalSpecies() {
        return this.sanctuary.size();
    }

    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    public int getMaxSpecies() {
        return this.maxSpecies;
    }
    
    public int rescue(String species, int num) {
        if (num <= 0 || species == null) {
            throw new IllegalArgumentException();
        }

        if (this.sanctuary.containsKey(species)) {
            int current = this.sanctuary.get(species);
            int remaining = this.maxAnimals - getTotalAnimals();
            int add = Math.min(num, remaining);
            this.sanctuary.put(species, current + add);
            return num - add;
        } else {
            if (this.sanctuary.size() >= maxSpecies) {
                return num;
            } else {
                int remaining = this.maxAnimals - getTotalAnimals();
                int add = Math.min(num, remaining);
                this.sanctuary.put(species, add);
                return num - add;
            }
        }

    }

    public void release(String species, int num) {

        int originalCount = this.sanctuary.getOrDefault(species, 0);
        int newCount = originalCount - num;

        if (num <= 0 || num > originalCount || species == null || !this.sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }

        if (num == originalCount) {
            this.sanctuary.remove(species);
        } else {
            this.sanctuary.put(species, newCount);
        }

    }

    public int helpClosingSanctuary(Sanctuary closingSanctuary) {

        if (closingSanctuary == null) {
            throw new IllegalArgumentException();
        }

        int totalRescued = 0;

        ArrayList<String> sortedSpecies =new ArrayList<>(closingSanctuary.sanctuary.keySet());
        Collections.sort(sortedSpecies);

        for (String species: sortedSpecies) {
            int numberAnimals = closingSanctuary.sanctuary.getOrDefault(species, 0);

            int total = 0;
            for (int count : this.sanctuary.values()) {
                total += count;
            }

            int remainingCapacity = this.maxAnimals - total;

            int rescued = Math.min(numberAnimals, remainingCapacity);
            totalRescued += rescued;

            this.rescue(species, rescued);
            closingSanctuary.release(species, rescued);

            if (closingSanctuary.countForSpecies(species) == 0) {
                closingSanctuary.sanctuary.remove(species);
            }

        }

        return totalRescued;

    }

}
