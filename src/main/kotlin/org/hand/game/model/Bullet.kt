package org.hand.game.model


import org.hand.game.Config
import org.itheima.kotlin.game.core.Painter

class Bullet(override var x: Int, override var y: Int) : View {

    override val width: Int = Config.block
    override val height: Int = Config.block

    override fun draw() {

        Painter.drawImage("/img/shot_left.gif", x, y)
    }
}