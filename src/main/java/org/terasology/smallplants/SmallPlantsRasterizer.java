/*
 * Copyright 2017 MovingBlocks
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

package org.terasology.smallplants;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizerPlugin;
import org.terasology.world.generator.plugin.RegisterPlugin;

import java.util.Random;

@RegisterPlugin
public class SmallPlantsRasterizer implements WorldRasterizerPlugin {
    private Block plant;
    private String[] plantList;
    private Random random;

    @Override
    public void initialize() {
        random = new Random();
        plantList = new String[]{"core:TallGrass1", "core:TallGrass2", "core:TallGrass3", "core:Dandelion", "core:BrownShroom"};
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SmallPlantsFacet herbFacet = chunkRegion.getFacet(SmallPlantsFacet.class);
        for (Vector3i position: chunkRegion.getRegion()) {
            if (herbFacet.getWorld(position)) {
                int index =  random.nextInt(plantList.length);
                plant = CoreRegistry.get(BlockManager.class).getBlock(plantList[index]);
                chunk.setBlock(ChunkMath.calcBlockPos(position), plant);
            }
        }
    }
}