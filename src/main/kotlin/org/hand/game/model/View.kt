package org.hand.game.model

interface View {

    var x: Int
    var y: Int

    var width: Int
    var height: Int

    fun draw()
}