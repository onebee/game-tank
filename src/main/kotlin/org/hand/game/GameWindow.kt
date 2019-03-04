package org.hand.game

import javafx.scene.input.KeyEvent
import org.hand.game.model.*
import org.itheima.kotlin.game.core.Window
import java.io.File

class GameWindow : Window(title = "坦克大战",
        icon = "icon/logo.png",
        width = Config.gameWidth,
        height = Config.gameHeight
) {

    val views = arrayListOf<View>()

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

    }

    override fun onDisplay() {

        views.forEach {
            it.draw()
        }


    }

    override fun onKeyPressed(event: KeyEvent) {
    }

    override fun onRefresh() {
    }
}

