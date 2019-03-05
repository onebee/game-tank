package org.hand.game.model

import org.hand.game.Config
import org.hand.game.business.Blockable
import org.itheima.kotlin.game.core.Painter

// 铁墙
class Steel(override var x: Int, override var y: Int) : View, Blockable {

    override var width: Int = Config.block
        set(value) {}
    override var height: Int = Config.block
        set(value) {}

    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }
}