package plugin.ai.general.scriptrepository

import core.game.node.item.Item
import core.tools.Items
import plugin.skill.Skills
import plugin.skill.gather.SkillingResource

@PlayerCompatible
@ScriptName("Generic Non-banking Iron Miner")
@ScriptDescription("Mines nearby iron ores.")
@ScriptIdentifier("iron_miner")
class IronMiner : Script() {
    private val ironNodes: MutableList<Int> = mutableListOf()

    override fun tick() {
        val rock = scriptAPI.getNearestNode(ironNodes, true)
        if(rock != null){
            rock.interaction.handle(bot,rock.interaction[0])
            if(bot.inventory.isFull)
                bot.inventory.clear()
        }
    }

    override fun newInstance(): Script {
        val script = IronMiner()
        return script
    }

    init {
        for (node in SkillingResource.values()) {
            if (node.toString().startsWith("IRON_ORE")) {
                ironNodes.add(node.id)
            }
        }
        equipment.add(Item(Items.IRON_PICKAXE_1267))
        skills[Skills.MINING] = 75
    }
}