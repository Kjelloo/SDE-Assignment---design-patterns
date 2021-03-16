package com.redheads.patterns.gui.controller;

import com.redheads.patterns.be.Message;
import com.redheads.patterns.bll.IMessageFacade;
import com.redheads.patterns.bll.MessageCreatorFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField messageField;
    @FXML
    private TextArea log;

    private IMessageFacade facade;

    public Controller() {
        facade = MessageCreatorFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Message msg: facade.getAllMessages()) {
            log.setText(log.getText() + "\n" + msg.getMessage());
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        Message msg = facade.logMessage(messageField.getText());
        log.setText(log.getText() + "\n" + msg.getMessage());
    }
}
