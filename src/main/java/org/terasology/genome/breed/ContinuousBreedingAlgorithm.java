/*
 * Copyright 2020 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.genome.breed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.genome.breed.mutator.GeneMutator;
import org.terasology.utilities.random.FastRandom;

/**
 * A breeding algorithm for continuous traits It uses mutations for values outside of values between parent traits
 * Slightly higher mutation values are recommended with this breeding algorithm
 */
public class ContinuousBreedingAlgorithm implements BreedingAlgorithm {
    private float mutationChance;
    private GeneMutator geneMutator;

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscreteBreedingAlgorithm.class);

    public ContinuousBreedingAlgorithm(float mutationChance, GeneMutator geneMutator) {
        this.mutationChance = mutationChance;
        this.geneMutator = geneMutator;
    }

    /**
     * Check whether two organisms with the given genes can breed.
     *
     * @param genes1 The genes of the first organism
     * @param genes2 The genes of the second organism
     * @return Whether the two organisms can breed
     */
    @Override
    public boolean canCross(String genes1, String genes2) {
        if (!(validateGenes(genes1, genes2))) {
            return false;
        }

         return (genes1.length()==genes2.length());
    }

    /**
     * Produces the genes of an offspring from parent organisms with the genes specified.
     *
     * @param genes1 The genes of the first parent organism
     * @param genes2 The genes of the second parent organism
     * @return The genes of an offspring from the two parent organisms
     */
    @Override
    public String produceCross(String genes1, String genes2) {
        String cross1 = "";
        String cross2 = "";

        FastRandom rand = new FastRandom();

        StringBuilder result = new StringBuilder(genes1.length());

        for (int i = 0; i < genes1.length(); i++) {
            if (mutationChance >= rand.nextFloat()) {
                char gene = geneMutator.mutateGene(rand.nextFloat(), i, genes1.charAt(i));
                cross1 += "" + gene;
            } else {
                cross1 += "" + genes1.charAt(i);
            }

            if (mutationChance >= rand.nextFloat()) {
                int geneIndex = rand.nextInt(genes2.length());
                char gene = geneMutator.mutateGene(rand.nextFloat(), i, genes2.charAt(i));
                cross2 += "" + gene;
            } else {
                cross2 += "" + genes2.charAt(i);
            }
        }

        char[] chars1 = cross1.toCharArray();
        char[] chars2 = cross2.toCharArray();

        for (int j = 0; j < genes1.length(); j++) {
            result.append(rand.nextBoolean() ? chars1[j] : chars2[j]);
        }

        return result.toString();
    }

    private boolean validateGenes(String genes1, String genes2) {
        if (genes1 == null || genes2 == null || genes1.length() != genes2.length()) {
            LOGGER.error("Genomes not defined or of incorrect length");
            return false;
        }
        return true;
    }
}
