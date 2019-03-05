package org.hand.game.business

import org.hand.game.enums.Direction
import org.hand.game.model.View

/****
 * 移动能力的物体
 *
 * **/
interface Moveable :View{


    val currentDirection: Direction

    val speed: Int

    /**
     * 返回要碰撞的方向,如果为null 说明没有碰
     * **/
    fun willCollision(blockable: Blockable): Direction?

//    通知碰撞
    fun notifyCollision(direction: Direction?,block: Blockable?)
}