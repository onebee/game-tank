package org.hand.game

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.hand.game.business.AutoMovable
import org.hand.game.business.Blockable
import org.hand.game.business.Destroyable
import org.hand.game.business.Moveable
import org.hand.game.enums.Direction
import org.hand.game.model.*
import org.itheima.kotlin.game.core.Window

import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow : Window(title = "汉得物联网事业部-开发组",
        icon = "icon/logo.png",
        width = Config.gameWidth,
        height = Config.gameHeight
) {

//    private val views = arrayListOf<View>()

    // 用线程安全的集合
    private val  views = CopyOnWriteArrayList<View>()

    private lateinit var tank: Tank

    override fun onCreate() {

        val file = File(javaClass.getResource("/map/1.map").path)
        val lines = file.readLines()
        var lineNum = 0
        lines.forEach { line ->
            var columnNum = 0
            line.toCharArray().forEach { column ->
                when (column) {
                    '砖' -> views.add(Wall(columnNum * Config.block, lineNum * Config.block))
                    '草' -> views.add(Grass(columnNum * Config.block, lineNum * Config.block))
                    '水' -> views.add(Water(columnNum * Config.block, lineNum * Config.block))
                    '铁' -> views.add(Steel(columnNum * Config.block, lineNum * Config.block))

                }
                columnNum++
            }
            lineNum++
        }

        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)

    }

    override fun onDisplay() {

        views.forEach {
            it.draw()
        }


    }

    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.W -> tank.move(direction = Direction.UP)
            KeyCode.S -> tank.move(direction = Direction.DOWN)
            KeyCode.A -> tank.move(direction = Direction.LEFT)
            KeyCode.D -> tank.move(direction = Direction.RIGHT)

            KeyCode.ENTER -> {
                val shot = tank.shot()
                // 交给views 管理
                views.add(shot)
            }

        }
    }


    override fun onRefresh() {


        println(views.size)
        // 业务逻辑
        //判断运动物体和阻塞物体是否发生碰撞
        views.filter { it is Moveable }.forEach { move ->
            move as Moveable
            var badDirection: Direction? = null
            var badBlock: Blockable? = null

            views.filter { it is Blockable }.forEach blockTag@{ block ->

                block as Blockable

                // 获得碰撞方向
                val direction = move.willCollision(block)

                direction?.let {
                    // 移动的发现碰撞 , 跳出当前循环
                    badDirection = direction
                    badBlock = block
                    return@blockTag
                }

            }

            //找到和move 碰撞的阻塞块和碰撞的方向
            //通知可以移动的物体 会在哪个方向和哪个物体碰撞
            move.notifyCollision(badDirection, badBlock)

        }

        views.filter { it is AutoMovable }.forEach {
            (it as AutoMovable).antoMove()
        }

        // 检测销毁
        views.filter { it is Destroyable }.forEach {

            if ((it as Destroyable).isDestory()) {
                views.remove(it)
            }
        }


    }
}

