package com.example.combogui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class MainController {
    @FXML
    public TextArea japaneseSentBox,englishSentBox, romajiSentBox, japaneseBox1, englishBox1, romajiBox1,japaneseBox2, englishBox2,romajiBox2,outputBox;

    @FXML
    public RadioButton r1,r2,r3,r4,r5,r6,r7,r8;

    @FXML
    private ToggleGroup t1;


    @FXML
    protected void onFinishButtonClick() {

        RadioButton rb = (RadioButton)t1.getSelectedToggle();
        if (rb.getText().equals(r1.getText())){
            SentenceCombos single = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox1.getText(),englishBox1.getText(),romajiBox1.getText(),"Y").build();
            outputBox.setText(single.runSingle());

        } else if (rb.equals(r2)) {
            SentenceCombos doubleSwap = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox1.getText(),englishBox1.getText(),romajiBox1.getText(),"Y").sideCharTwo("Z").build();
            outputBox.setText(doubleSwap.runDoubleSwap());

        } else if (rb.equals(r3)) {
            SentenceCombos doubleOrder = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox1.getText(),englishBox1.getText(),romajiBox1.getText(),"Y").japValuesZ(japaneseBox2.getText()).engValuesZ(englishBox2.getText()).romaValuesZ(romajiBox2.getText()).sideCharTwo("Z").build();
            outputBox.setText(doubleOrder.runDoubleOrder());


        } else if (rb.equals(r4)){
            SentenceCombos single = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox2.getText(),englishBox2.getText(),romajiBox2.getText(),"Z").build();
            outputBox.setText(single.runSingle());

        } else if (rb.equals(r5)){
            SentenceCombos doubleSwap = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox2.getText(),englishBox2.getText(),romajiBox2.getText(),"Z").sideCharTwo("Y").build();
            outputBox.setText(doubleSwap.runDoubleSwap());

        }  else if (rb.equals(r6)){
            SentenceCombos doubleOrder = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox2.getText(),englishBox2.getText(),romajiBox2.getText(),"Z").japValuesZ(japaneseBox1.getText()).engValuesZ(englishBox1.getText()).romaValuesZ(romajiBox1.getText()).sideCharTwo("Y").build();
            outputBox.setText(doubleOrder.runDoubleOrder());

        } else if (rb.equals(r7)) {
            SentenceCombos tripleOrder = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox2.getText(),englishBox2.getText(),romajiBox2.getText(),"Z").japValuesZ(japaneseBox1.getText()).engValuesZ(englishBox1.getText()).romaValuesZ(romajiBox1.getText()).sideCharTwo("Y").build();
            outputBox.setText(tripleOrder.runTripleOrder());

        } else if (rb.equals(r8)) {
            SentenceCombos quadOrder = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox2.getText(),englishBox2.getText(),romajiBox2.getText(),"Z").japValuesZ(japaneseBox1.getText()).engValuesZ(englishBox1.getText()).romaValuesZ(romajiBox1.getText()).sideCharTwo("Y").build();
            outputBox.setText(quadOrder.runQuadOrder());

        }

    }

    @FXML
    protected void onClearButtonClick(){
        japaneseSentBox.setText("");
        englishSentBox.setText("");
        romajiSentBox.setText("");
        japaneseBox1.setText("");
        englishBox1.setText("");
        romajiBox1.setText("");
        japaneseBox2.setText("");
        englishBox2.setText("");
        romajiBox2.setText("");
        outputBox.setText("");
    }

    @FXML
    protected void onRadioClick(){
        RadioButton rb = (RadioButton)t1.getSelectedToggle();
        String name = rb.getText();
        String text = "";
        switch(name){
            case "Single (Z)":
            case "Single (Y)":
                text = "1(one) Value\nit is a _____\ni am a Y";
                break;
            case "Swapped (Y Z)":
            case "Swapped (Z Y)":
                text = "1(one) Value Swapped\nit is a _____ and a ____\ni am a Y and a Z -> i am a Z and a Y";
                break;
            case "Ordered (Y Z)":
            case "Ordered (Z Y)":
                text = ("2(two) Values Ordered\n[color] and a [object]\nred and a umbrella -> red and a marble");
                break;
            case "Ordered (X Y Z)":
                text = ("3(three) Values Ordered\na [X#] [Y] and a [Z]\nX1 == Y values && X2 == Z values");
                break;
            case "Ordered (W X Y Z)":
                text = ("4(four) Values Ordered\na [W#] a [X#] and a [Y] [Z]\nW1 & X1 == Y values <--> W2 & X2 == Z values");
                break;
        }
        outputBox.setText(text);
    }

    @FXML
    protected void onCopyButtonClick(){
        outputBox.selectAll();

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(outputBox.getSelectedText());
        clipboard.setContent(content);

        outputBox.deselect();

        outputBox.setText("Copied");
    }



}