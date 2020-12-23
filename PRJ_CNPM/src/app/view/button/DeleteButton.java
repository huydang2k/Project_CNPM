package app.view.button;

import app.controller.CommonController;
import javafx.scene.control.Button;

public class DeleteButton extends Button {
    CommonController commonController;

    public DeleteButton() {
        commonController = new CommonController();
        commonController.csslize(this);
        this.getStyleClass().add("deletebtn");
    }

    public DeleteButton(String text) {
        super(text);
        commonController = new CommonController();
        commonController.csslize(this);
        this.getStyleClass().add("deletebtn");
    }
}
