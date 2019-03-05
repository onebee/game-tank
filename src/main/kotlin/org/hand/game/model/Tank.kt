package org.hand.game.model

import org.hand.game.Config
import org.hand.game.business.Blockable
import org.hand.game.business.Moveable
import org.hand.game.enums.Direction
import org.itheima.kotlin.game.core.Painter

/***
 *
 *
 * 我方坦克
 * **/
class Tank(override var x: Int, override var y: Int) : Moveable {

    override var width: Int = Config.block
    override var height: Int = Config.block


    override var currentDirection: Direction = Direction.UP
    override var speed = 8

    //    坦克不可以走的方向
    private var badDirection: Direction? = null

    override fun draw() {

        val imagePath = when (currentDirection) {
            Direction.UP -> "/img/tank_u.gif"
            Direction.DOWN -> "/img/tank_d.gif"
            Direction.LEFT -> "/img/tank_l.gif"
            Direction.RIGHT -> "/img/tank_r.gif"
        }

        Painter.drawImage(imagePath, x, y)
    }

    fun move(direction: Direction) {
        // 判断是否要往碰撞的方向走
        if (direction == badDirection) {
            return
        }


        // 当前方向和希望的方向不一致时候才去改变方向
        if (this.currentDirection != direction) {
            this.currentDirection = direction
            return
        }
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        //边界处理
        if (x < 0) x = 0
        if (x > Config.gameWidth - width) x = Config.gameWidth - width
        if (y < 0) y = 0
        if (y > Config.gameHeight - height) y = Config.gameHeight - height
    }

    override fun willCollision(blockable: Blockable): Direction? {
        // 检测碰撞

        return Direction.UP
    }

    override fun notifyCollision(direction: Direction?, block: Blockable?) {
        this.badDirection = direction
    }

}