package controller

import java.net.URL
import java.util
import javafx.{fxml => jfxf}
import javafx.{event => jfxe}
import javafx.{scene => jfxs}
import javafx.scene.{control => jfxsc}
import javafx.fxml.FXML
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}
import javafx.scene.{control => jfxsc}
import view._

class TestFormController extends jfxf.Initializable{
  val inGameViewController = new InGameViewController
  val inGameController = new InGameController(inGameViewController)

  @FXML
  private var mainText: jfxsc.Label = _

  @FXML
  private def handleDebugButton(event: jfxe.ActionEvent){
    println("fire DebugButton")
    mainText.setText("DEBUG!")
  }

  @FXML
  private def handleKeyTyped(event: jfxs.input.KeyEvent){
    // インゲームへ入力移譲
    // チャタリングを後日実装
    //inGameController.handleKeyInput(event)
  }

  @FXML
  private def handleKeyReleased(event: jfxs.input.KeyEvent): Unit ={
    // インゲームへ入力移譲
    inGameController.handleKeyInput(event)
  }

  def initialize(url:URL, rb : util.ResourceBundle){
    mainText.setText("When Initialize inject TEXT")
    inGameViewController.setLabel(mainText)
    inGameController.setNewgame()
  }

}
