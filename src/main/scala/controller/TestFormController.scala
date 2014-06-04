package controller

import java.net.URL
import java.util
import javafx.{fxml => jfxf}
import javafx.{event => jfxe}
import javafx.{scene => jfxs}
import javafx.scene.{control => jfxsc}
import javafx.fxml.FXML
import model._
import model.panel._
import model.DungeonGenerator
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}
import javafx.scene.{control => jfxsc}

class TestFormController extends jfxf.Initializable{
  @FXML
  private var mainText: jfxsc.Label = _
  
  @FXML
  private def handleDebugButton(event: jfxe.ActionEvent){
    println("fire DebugButton")
    mainText.setText("DEBUG!")
  }
  
  @FXML
  private def handleKeyRelease(event: jfxs.input.KeyEvent){
    println("releaseKey : " + event.getCharacter())
    mainText.setText(mainText.getText() + "\nDEBUG! key input : " + event.getCharacter())
    mainText.setText(DungeonGenerator.makeDungeon("src/main/resources/test_dungeon.dun").map {_.map(_.parse).mkString}.mkString("\n"))
  }
  
  def initialize(url:URL, rb : util.ResourceBundle){
    mainText.setText("When Initialize inject TEXT")
  }

}
