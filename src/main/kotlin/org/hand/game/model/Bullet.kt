package org.hand.game.model


import org.hand.game.Config
import org.hand.game.enums.Direction
import org.itheima.kotlin.game.core.Painter

class Bullet(val direction: Direction, override var x: Int, override var y: Int) : View {

    // 给子弹一个方向,方向由坦克来决定
    override val width: Int = Config.block
    override val height: Int = Config.block

    override fun draw() {

        val imagePath = when (direction) {
            Direction.UP -> "/img/shot_top.gif"
            Direction.DOWN -> "/img/shot_bottom.gif"
            Direction.LEFT -> "/img/shot_left.gif"
            Direction.RIGHT -> "/img/shot_right.gif"
        }

        Painter.drawImage(imagePath, x, y)
    }
}