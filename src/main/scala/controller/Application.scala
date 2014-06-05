package controller

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import java.io.IOException
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}



object World extends JFXApp {
  val fxml:String = "/TestForm.fxml"
  val resource = getClass.getResource(fxml)
  if(resource == null){
    throw new IOException("Cannot open fxml")
  }
  
  val root: jfxs.Parent = jfxf.FXMLLoader.load(resource)
  
  stage = new JFXApp.PrimaryStage(){
    title = "Test, FXML"
    scene = new Scene(root)
  }
}
