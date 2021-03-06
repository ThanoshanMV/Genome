/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.genome;

import org.terasology.genome.breed.BreedingAlgorithm;
import org.terasology.genome.genomeMap.GenomeMap;

/**
 * This class defines common properties of a certain organism type, namely the default breeding algorithm and the genome map.
 */
public class GenomeDefinition {
    private BreedingAlgorithm defaultBreedingAlgorithm;
    private GenomeMap genomeMap;

    public GenomeDefinition(BreedingAlgorithm defaultBreedingAlgorithm, GenomeMap genomeMap) {
        this.defaultBreedingAlgorithm = defaultBreedingAlgorithm;
        this.genomeMap = genomeMap;
    }

    /**
     * Get the default breeding algorithm used by this organism type.
     *
     * @return The default breeding algorithm used by this organism type
     */
    public BreedingAlgorithm getDefaultBreedingAlgorithm() {
        return defaultBreedingAlgorithm;
    }

    /**
     * Get the genome map of this organism type.
     *
     * @return The genome map of this organism type
     */
    public GenomeMap getGenomeMap() {
        return genomeMap;
    }
}
