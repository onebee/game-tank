package org.hand.game.model

import org.hand.game.Config
import org.hand.game.business.Blockable
import org.itheima.kotlin.game.core.Painter

class Water(override var x: Int, override var y: Int) : View, Blockable {

    override var width: Int = Config.block
        set(value) {}
    override var height: Int = Config.block
        set(value) {}

    override fun draw() {
        Painter.drawImage("img/water.gif", x, y)
    }
}