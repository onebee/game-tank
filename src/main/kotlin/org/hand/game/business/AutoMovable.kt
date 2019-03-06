package org.hand.game.business

import org.hand.game.enums.Direction
import org.hand.game.model.View

interface AutoMovable : View {

    val currentDirection: Direction

    val speed: Int

    fun antoMove()
}