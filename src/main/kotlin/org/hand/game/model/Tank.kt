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

    // 坦克不可以走的方向
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

    override fun willCollision(block: Blockable): Direction? {

        // 将要碰撞时 做逻辑判断  未来的坐标
        var x: Int = this.x
        var y: Int = this.y

        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }


        // 检测碰撞
        var collision = when {
            block.y + block.height <= y -> // 阻挡物在运动物的上方  不碰撞
                false
            y + height <= block.y -> // 阻挡物在运动物的下方  不碰撞
                false
            block.x + block.width <= x -> // 阻挡物在运动物的左方  不碰撞
                false

            else -> x + width > block.x
        }

        return if (collision) currentDirection else null

    }

    override fun notifyCollision(direction: Direction?, block: Blockable?) {
        this.badDirection = direction
    }

    // 发射子弹
    fun shot(): Bullet {


        // 第二个参数需要传入一个函数,并返回结果
        return Bullet(currentDirection, { bulletWidth, bulletHeight ->
            // 计算子弹的实际坐标
            val tankX = x
            val tankY = y
            val tankWidth = width
            val tankHeigh = height

            var bulletX = 0
            var bulletY = 0
//            var buttleWidth = 16
//            var bulletHeigh = 32

            when (currentDirection) {
                Direction.UP -> {
                    bulletX = tankX + (tankWidth - bulletWidth) / 2
                    bulletY = tankY - bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = tankX + (tankWidth - bulletWidth) / 2
                    bulletY = tankY + tankHeigh - bulletHeight / 2
                }
                Direction.LEFT -> {
                    bulletX = tankX - bulletWidth / 2
                    bulletY = tankY + (tankHeigh - bulletHeight) / 2
                }
                Direction.RIGHT -> {
                    bulletX = tankX + tankWidth - bulletWidth / 2
                    bulletY = tankY + (tankHeigh - bulletHeight) / 2
                }

            }


            Pair(bulletX, bulletY)

        })
    }

}