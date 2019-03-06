package org.hand.game.model


import org.hand.game.Config
import org.hand.game.business.AutoMovable
import org.hand.game.business.Destroyable
import org.hand.game.enums.Direction
import org.itheima.kotlin.game.core.Painter

/**
 *
 * 闭包:
 * 这里传入一个函数 需要返回两个值
 * */

class Bullet(override val currentDirection: Direction, create: (width: Int, height: Int) -> Pair<Int, Int> ) : AutoMovable ,Destroyable{



    override val speed: Int = 8

    // 给子弹一个方向,方向由坦克来决定
    override val width: Int
    override val height: Int

    override var x: Int = 0
    override var y: Int = 0

    private val imagePath: String = when (currentDirection) {
        Direction.UP -> "/img/shot_top.gif"
        Direction.DOWN -> "/img/shot_bottom.gif"
        Direction.LEFT -> "/img/shot_left.gif"
        Direction.RIGHT -> "/img/shot_right.gif"
    }


    init {
        val size = Painter.size(imagePath)
        width = size[0]
        height = size[1]


        val pair = create.invoke(width, height)
        x = pair.first
        y = pair.second

    }

    override fun draw() {

//        val imagePath = when (direction) {
//            Direction.UP -> "/img/shot_top.gif"
//            Direction.DOWN -> "/img/shot_bottom.gif"
//            Direction.LEFT -> "/img/shot_left.gif"
//            Direction.RIGHT -> "/img/shot_right.gif"
//        }

        Painter.drawImage(imagePath, x, y)
    }


    override fun antoMove() {
        // 根据自己的方向来改变自己的位置
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y +=speed
            Direction.RIGHT -> x +=speed
            Direction.LEFT -> x -= speed
        }

    }

    override fun isDestory(): Boolean {
        if (x < -width || x > Config.gameWidth || y < -height || y > Config.gameHeight) {
            return true
        }
        return  false

    }
}