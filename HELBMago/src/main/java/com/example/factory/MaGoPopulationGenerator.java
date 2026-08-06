package com.example.factory;

import com.example.models.MaGo;
import com.example.models.MaGoPopulation;
import com.example.utils.MyGlobals;

/**
 * Generates a MaGo population based on the current Unix timestamp.
 * 
 * This class determines the number of MaGo to create randomly,
 * extracts digits from a timestamp, and uses these digits to select
 * the type of each MaGo through the MaGoFactory.
 * 
 * The creation process is delegated to MaGoFactory, while this class
 * is responsible only for generating the population and providing the
 * required parameters.
 */
public class MaGoPopulationGenerator {

    private static final int MIN_MAGOS = 2;
    private static final int MAX_MAGOS = 8;
    private static final int DECIMAL_BASE = 10;
    private static final int POPULATION_OFFSET = 1;
    private static final int MILLISECONDS_PER_SECOND = 1000;


    /**
     * Creates a MaGoPopulationGenerator .
     */
    public MaGoPopulationGenerator() { }

    /**
     * Creates a random MaGo population. The population size is randomly
     * chosen between 2 and 8, and the current Unix timestamp is used to
     * determine the type of each MaGo.
     *
     * @return the generated population
     */
    public MaGoPopulation createRandomPopulation() {
        return createPopulation(getRandomPopulationSize(), getCurrentUnixTimestamp());
    }

    /**
     * Creates a MaGo population of the given size, using the provided
     * timestamp to determine each MaGo type.
     *
     * Separated from createRandomPopulation() so it can be tested with
     * a fixed size and timestamp instead of relying on random values.
     *
     * @param populationSize number of MaGos to create
     * @param timestamp Unix timestamp used to determine each MaGo type
     * @return the created population
     */
    public MaGoPopulation createPopulation(int populationSize, long timestamp) {

        if (populationSize < MyGlobals.ZERO) {
            throw new IllegalArgumentException("Population size must be positive.");
        }

        MaGoPopulation population = new MaGoPopulation();
        long remainingTimestamp = timestamp;

        for (int i = MyGlobals.INITIAL_INDEX; i < populationSize; i++) {

            int digit = getLastDigit(remainingTimestamp);
            MaGo maGo = MaGoFactory.createMaGo(digit);

            population.addMaGo(maGo);
            remainingTimestamp = removeLastDigit(remainingTimestamp);
        }

        return population;
    }

    /**
     * Generates a random population size between the minimum and maximum values.
     *
     * @return random number of MaGos to create
     */
    private int getRandomPopulationSize() {
        return (int) (Math.random() * (MAX_MAGOS - MIN_MAGOS + POPULATION_OFFSET)) + MIN_MAGOS;
    }

    /**
     * Retrieves the current Unix timestamp in seconds.
     *
     * @return current Unix timestamp
     */
    private long getCurrentUnixTimestamp() {
        return System.currentTimeMillis() / MILLISECONDS_PER_SECOND;
    }

    /**
     * Extracts the last digit of a timestamp.
     *
     * @param timestamp timestamp value
     * @return last decimal digit of the timestamp
     */
    private int getLastDigit(long timestamp) {
        return (int) (timestamp % DECIMAL_BASE);
    }

    /**
     * Removes the last digit of a timestamp.
     *
     * @param timestamp timestamp value
     * @return timestamp without its last digit
     */
    private long removeLastDigit(long timestamp) {
        return (timestamp / DECIMAL_BASE);
    }
}