import javafx.application.Application
import javafx.scene.input.KeyEvent
import core.Window

class myWindow : Window(){
    override fun onCreate() {


    }

    override fun onDisplay() {

    }

    override fun onKeyPressed(event: KeyEvent) {
    }

    override fun onRefresh() {
    }
}

fun main(args: Array<String>) {
    Application.launch(myWindow::class.java)

}