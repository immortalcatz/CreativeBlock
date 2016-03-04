# CreativeBlock
Utility for 'dynamically' generating mod blocks from resourcepack-like containers
___
#### Create a Blockpack
CreativeBlock includes a built-in java app for creating blockpacks.  
Double click the mod jar to launch.


##### 1. Selecting a workspace
From the setup window you can select a directory in which to start creating your blockpack.  
When you hit 'ok', the app will create the necessary folder structure for your blockpack, a default 'pack.mcmeta' file, and then take you to the editor

![setup](https://raw.githubusercontent.com/dags-/CreativeBlock/resources/image/setup.png)
___
##### 2. Creating block definitions
A block definition requires the following information:
- **name** - the basic name for a block definition. Different blocktypes will append a suffix to this base name. For example 'wood', would become 'wood_stairs', 'wood_slab', etc.
- **sound** - the sound the block makes when placed/stepped on. The editor provides a list of all the available sounds.
- **textures** - the name of the textures the block should use. These textures should be placed in the assets/mod_name/textures/blocks/ directory within the blockpack structure.
- **types** - the different shapes/types of blocks that the definition should take. At-least one type should be selected.

![editor](https://raw.githubusercontent.com/dags-/CreativeBlock/resources/image/editor.png)

Once these four required pieces of information have been provided, the 'create' button will become active.  
Clicking the 'create' button will generate the block definition and put it in the correct location within the blockpack structure.   

Blockstate and Model files are also generated and placed in the correct directories. These files are required by Minecraft, and can be overriden to include more intricate models via resourcepacks.
___
##### 3. Finishing off
Once you have created all your block definitions and added all your texture files, your new blockpack is ready to test.  
The blockpack directory can be placed directly in the .minecraft/blockpacks folder, or can be zipped first - note zipped blockpacks must conform to Minecraft's structure for resourcepacks.
___
#### Tips

**categories**  
The category field can be used to help organise block definitions into sub-folders within the definitions directory. In-game, the blocks will be organised into separate creative tabs in the inventory.

_Example: for the category 'decorations/chairs', the block definition will be placed in a directory of the same name, and will show up in a creative tab called 'decorations'._

**textures**  
Before creating your block definitions, you can place your textures in the textures/blocks folder. While typing in the texture fields, the editor will automatically highlight your text when you have typed a valid texture name.
![textures](https://raw.githubusercontent.com/dags-/CreativeBlock/resources/image/texture.gif)

**types**  
For convenience, shift clicking a block type will automatically select/deselect all the available block types.
