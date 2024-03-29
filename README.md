# Surface Protection

**Prevent players from breaking surface's blocks**

## How it work :
It check for 10 blocks above the current block that player is trying to break.
If a solid block is detected above, the player can destroy the current block.

There is some exceptions that prevent players to break block anyway.

### 1 - First Exception
<details>
<summary>If current block is one of this :</summary>

clay, dirt, coarse_dirt, podzol, farmland, grass_block, gravel, mycelium, sand, red_sand, snow_block, snow, soul_sand, dirt_path, white_concrete_powder, orange_concrete_powder, magenta_concrete_powder, light_blue_concrete_powder, yellow_concrete_powder, lime_concrete_powder, pink_concrete_powder, gray_concrete_powder, light_gray_concrete_powder, cyan_concrete_powder, purple_concrete_powder, blue_concrete_powder, brown_concrete_powder, green_concrete_powder, red_concrete_powder, black_concrete_powder, soul_soil, rooted_dirt, muddy_mangrove_roots, mud

</details>
<details>
<summary>And have above it one of this :</summary>

jungle_leaves, oak_leaves, spruce_leaves, dark_oak_leaves, acacia_leaves, birch_leaves, azalea_leaves, flowering_azalea_leaves, mangrove_leaves

</details>
It will cancel the breaking block event.


### 2 - Second Exception
<details>
<summary>If current block is one of this :</summary>

anything

</details>
<details>
<summary>And have above it one of this :</summary>

oak_planks, spruce_planks, birch_planks, jungle_planks, acacia_planks, dark_oak_planks, crimson_planks, warped_planks, mangrove_planks

</details>

It will cancel the breaking block event.
