package com.example.combogui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.Scanner;

public class MainController {
    @FXML
    public TextArea japaneseSentBox,englishSentBox, romajiSentBox, japaneseBox1, englishBox1, romajiBox1,japaneseBox2, englishBox2,romajiBox2,outputBox, sentenceBox, yBox, zBox;

    @FXML
    public RadioButton r1,r2,r3,r4,r5,r6,r7,r8;

    @FXML
    private ToggleGroup t1;

    @FXML
    private ToggleButton toggle1;

    @FXML
    protected void onGenerateButtonClick() {

        if (toggle1.isSelected()){
            String fullText = sentenceBox.getText();
            String[] sentence = extractText(fullText);
            japaneseSentBox.setText(sentence[0]);
            englishSentBox.setText(sentence[1]);
            romajiSentBox.setText(sentence[2]);

            fullText = yBox.getText();
            sentence = extractText(fullText);
            japaneseBox1.setText(sentence[0]);
            englishBox1.setText(sentence[1]);
            romajiBox1.setText(sentence[2]);

            fullText = zBox.getText();
            sentence = extractText(fullText);
            japaneseBox2.setText(sentence[0]);
            englishBox2.setText(sentence[1]);
            romajiBox2.setText(sentence[2]);
        }


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

        }else if (rb.equals(r6)){
            SentenceCombos tripleSwap = new SentenceCombos.Builder(japaneseSentBox.getText(),englishSentBox.getText(),romajiSentBox.getText(),japaneseBox1.getText(),englishBox1.getText(),romajiBox1.getText(),"Z").sideCharTwo("Y").japValuesZ(japaneseBox2.getText()).engValuesZ(englishBox2.getText()).romaValuesZ(romajiBox2.getText()).sideCharTwo("Z").build();
            outputBox.setText(tripleSwap.runTripleSwap());

        }  else if (rb.equals(r7)) {
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

        sentenceBox.setText("");
        yBox.setText("");
        zBox.setText("");
    }

    @FXML
    protected void onRadioClick(){
        RadioButton rb = (RadioButton)t1.getSelectedToggle();
        String name = rb.getText();
        String text = switch (name) {
            case "Single (Z)", "Single (Y)" -> "1(one) Value\nit is a _____\nit is a a Y/Z";
            case "Swapped (Y Z)", "Swapped (Z Y)" ->
                    "1(one) Value Swapped\nit is a _____ and a ____\nit is a Y and a Z -> it is a Z and a Y";
            case "Swapped (X Y Z)" ->
                    "1(one) Value Swapped\nList: a [X#] a [Y] and a [Z]\nswaps the values between 3 Y (X1) or 3 Z (X2)";
            case "Ordered (Y Z)" ->
                    ("2(two) Values Ordered\n[color] and a [object]\nred and a umbrella -> red and a marble");
            case "Ordered (X Y Z)" ->
                    ("3(three) Values Ordered\na [X#] [Y] and a [Z]\nX1 == Y values && X2 == Z values");
            case "Ordered (W X Y Z)" ->
                    ("4(four) Values Ordered\na [W#] a [X#] and a [Y] [Z]\nW1 & X1 == Y values <--> W2 & X2 == Z values");
            default -> "";
        };
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


    @FXML
    protected void onToggleClick(){
        if (toggle1.isSelected()){
            toggle1.setText("Mode: Grouped ");
            englishSentBox.setVisible(false);
            japaneseSentBox.setVisible(false);
            romajiSentBox.setVisible(false);

            englishBox1.setVisible(false);
            japaneseBox1.setVisible(false);
            romajiBox1.setVisible(false);

            englishBox2.setVisible(false);
            japaneseBox2.setVisible(false);
            romajiBox2.setVisible(false);

            sentenceBox.setVisible(true);
            yBox.setVisible(true);
            zBox.setVisible(true);


        } else {
            toggle1.setText("Mode: Individual ");
            sentenceBox.setVisible(false);
            yBox.setVisible(false);
            zBox.setVisible(false);

            englishSentBox.setVisible(true);
            japaneseSentBox.setVisible(true);
            romajiSentBox.setVisible(true);

            englishBox1.setVisible(true);
            japaneseBox1.setVisible(true);
            romajiBox1.setVisible(true);

            englishBox2.setVisible(true);
            japaneseBox2.setVisible(true);
            romajiBox2.setVisible(true);
        }
    }

    public String[] extractText(String text) {
        int count = 0;
        String japaneseLine = "";
        String englishLine = "";
        String romajiLine = "";

        Scanner scanner = new Scanner(text);
        while (scanner.hasNextLine()) {
            count += 1;
            switch (count) {
                case 1:
                    japaneseLine = scanner.nextLine();
                case 2:
                    englishLine = scanner.nextLine();
                case 3:
                    romajiLine = scanner.nextLine();
            }

        }
        return new String[]{japaneseLine, englishLine, romajiLine};
    }
}