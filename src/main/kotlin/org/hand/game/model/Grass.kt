package org.hand.game.model

import org.hand.game.Config
import org.itheima.kotlin.game.core.Painter

class Grass(override var x: Int, override var y: Int) : View {

    override var width: Int = Config.block
        set(value) {}
    override var height: Int  = Config.block
        set(value) {}

    override fun draw() {
        Painter.drawImage("img/grass.gif", x, y)
    }
}