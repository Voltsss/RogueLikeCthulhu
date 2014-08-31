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

import model._
import model.DungeonGenerator._

import controller.InGameController._

class TestFormController extends jfxf.Initializable{
  class TestPlayer extends Chracter {
    val position = Position(20,40)
    val cp = ChracterParameter()
  }

  val testPlayer
  val inGameController

  @FXML
  private var mainText: jfxsc.Label = _

  @FXML
  private def handleDebugButton(event: jfxe.ActionEvent){
    println("fire DebugButton")
    mainText.setText("DEBUG!")
  }

  @FXML
  private def handleKeyRelease(event: jfxs.input.KeyEvent){
    println("TestPlayer:ReleaseKey : " + event.getCharacter())
    //mainText.setText(mainText.getText() + "\nDEBUG! key input : " + event.getCharacter())
    //mainText.setText(DungeonGenerator.makeTestDungeon.toAppearance)

    // インゲームへの入力
    inGameController.handleKeyInput(event)
  }

  def initialize(url:URL, rb : util.ResourceBundle){
    mainText.setText("When Initialize inject TEXT")
    // インゲームリソースのセットアップ
    testPlayer = new TestPrayer // 仮データ
    // インゲームビューを立ち上げ
    inGameViewController = new InGameViewController
    // インゲーム部分のコントローラを立ち上げ
    inGameController = new InGameController(inGameViewController)

  }

}
