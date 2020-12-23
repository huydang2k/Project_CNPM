package app.view.button;

import app.controller.CommonController;
import javafx.scene.control.Button;

public class EditButton extends Button {
    CommonController commonController;

    public EditButton() {
        commonController = new CommonController();
        commonController.csslize(this);
        this.getStyleClass().add("editbtn");
    }

    public EditButton(String text) {
        super(text);
        commonController = new CommonController();
        commonController.csslize(this);
        this.getStyleClass().add("editbtn");
    }
}
