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
import model.param.PanelParam._

import view._

class TestFormController extends jfxf.Initializable{
  //class TestPlayer extends Character {
  //  val position = Position(20,40)
  //  val cp = CharacterParameter()
  //}

  //val testPlayer = new TestPlayer
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
  private def handleKeyRelease(event: jfxs.input.KeyEvent){
    println("TestPlayer:ReleaseKey : " + event.getCharacter())
    //mainText.setText(mainText.getText() + "\nDEBUG! key input : " + event.getCharacter())

    // インゲームへの入力
    inGameController.handleKeyInput(event)

    // 描画
    val dungeon = dungeonConvert(DungeonGenerator.makeTestDungeon)
    mainText.setText(atPlayer(dungeon).map {_.mkString}.mkString("\n"))
  }

  def atPlayer(dungeon:Array[Array[String]]):Array[Array[String]] = {
    dungeon(inGameController.testPlayer.getPosition._2)(inGameController.testPlayer.getPosition._1) = "@"
    dungeon
  }

  implicit class PanelParam2String(p: Panel) {
    def appearance = p match {
      case Wall  => "%"
      case Door  => "+"
      case Way   => "*"
      case Floor => "."
      case _     => " "
    }
  }

  def dungeonConvert(dungeon:Array[Array[Panel]]):Array[Array[String]] = {
    dungeon.map {_.map(_.appearance)}
  }

  def initialize(url:URL, rb : util.ResourceBundle){
    mainText.setText("When Initialize inject TEXT")
  }

}
