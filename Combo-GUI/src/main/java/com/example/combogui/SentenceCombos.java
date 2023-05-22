package com.example.combogui;

public class SentenceCombos {
    private final String japSentence;
    private final String engSentence;
    private final String romaSentence;
    private final String japValues1;
    private final String engValues1;
    private final String romaValues1;
    private final String japValues2;
    private final String engValues2;
    private final String romaValues2;
    private final String givenChar1;
    private final String givenChar2;


    public static class Builder {

        private final String japSentence;
        private final String engSentence;
        private final String romaSentence;
        private final String japValuesY;
        private final String engValuesY;
        private final String romaValuesY;
        private final String sideCharOne;

        private String japValuesZ = "";
        private String engValuesZ = "";
        private String romaValuesZ = "";
        private String sideCharTwo = "";


        public Builder(String japSentence, String engSentence, String romaSentence, String japValues, String engValues, String romaValues, String sideChar) {
            this.japSentence = japSentence;
            this.engSentence = engSentence;
            this.romaSentence = romaSentence;
            this.engValuesY = engValues;
            this.japValuesY = japValues;
            this.romaValuesY = romaValues;
            this.sideCharOne = sideChar;
        }

        public Builder japValuesZ(String val) {
            japValuesZ = val;
            return this;
        }

        public Builder engValuesZ(String val) {
            engValuesZ = val;
            return this;
        }

        public Builder romaValuesZ(String val) {
            romaValuesZ = val;
            return this;
        }

        public Builder sideCharTwo(String val) {
            sideCharTwo = val;
            return this;
        }


        public SentenceCombos build() {
            return new SentenceCombos(this);
        }
    }

    private SentenceCombos(Builder builder) {
        japSentence = builder.japSentence;
        engSentence = builder.engSentence;
        romaSentence = builder.romaSentence;
        engValues1 = builder.engValuesY;
        japValues1 = builder.japValuesY;
        romaValues1 = builder.romaValuesY;
        givenChar1 = builder.sideCharOne;
        japValues2 = builder.japValuesZ;
        engValues2 = builder.engValuesZ;
        romaValues2 = builder.romaValuesZ;
        givenChar2 = builder.sideCharTwo;
    }

    //Single --> i am Y | Z - where Y | Z = a,b,c,d...
    public String runSingle() {

        //split each value
        String[] englishSepValues = engValues1.split(",");
        String[] japaneseSepValues = japValues1.split(",");
        String[] romajiSepValues = romaValues1.split(",");

        StringBuilder output = new StringBuilder();

        //iterate through each value
        for (int i = 0; i <= englishSepValues.length - 1; i++) {

            //insert the value into the sentence
            String englishTemp = engSentence.replaceAll(givenChar1, englishSepValues[i]);
            String japaneseTemp = japSentence.replaceAll(givenChar1, japaneseSepValues[i]);
            String romajiTemp = romaSentence.replaceAll(givenChar1, romajiSepValues[i]);

            //format the three sentences into CSV
            output.append(japaneseTemp).append(",").append(englishTemp).append(",").append(romajiTemp).append("\n");
        }

        return output.toString();

    }

    //Double Swap - Takes one of the same values and swaps them -> A and B <--> B and A
    //Skips same values
    public String runDoubleSwap() {
        //separate values by comma
        String[] englishSepValues = engValues1.split(",");
        String[] japaneseSepValues = japValues1.split(",");
        String[] romajiSepValues = romaValues1.split(",");

        StringBuilder output = new StringBuilder();

        //iterate for the first Y|Z variable
        for (int i = 0; i <= englishSepValues.length - 1; i++) {

            //insert the value into the sentence
            String englishTempY = engSentence.replaceAll(givenChar1, englishSepValues[i]);
            String japaneseTempY = japSentence.replaceAll(givenChar1, japaneseSepValues[i]);
            String romajiTempY = romaSentence.replaceAll(givenChar1, romajiSepValues[i]);

            //iterate for the other Y|Z variable
            for (int v = 0; v <= englishSepValues.length - 1; v++) {

                //don't let both variables be the same value
                if (!englishSepValues[i].equals(englishSepValues[v])) {

                    //insert the value into the sentence
                    String englishTempZ = englishTempY.replaceAll(givenChar2, englishSepValues[v]);
                    String japaneseTempZ = japaneseTempY.replaceAll(givenChar2, japaneseSepValues[v]);
                    String romajiTempZ = romajiTempY.replaceAll(givenChar2, romajiSepValues[v]);

                    //format the three sentences into CSV
                    output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");


                }

            }
        }
        return output.toString();
    }

    //Triple Swap - Takes one of the same values and swaps them -> it is A1 A2 and A3 - it is A1 A3 and A2
    //like a to do list where you don't repeat the same item twice
    public String runTripleSwap() {

        String[] englishSepValues,japaneseSepValues,romajiSepValues;

        String side1;
        String side2;
        String side3;

        //determine X1 or X2
        if (engSentence.contains("X1")){
            side1 = "Y";
            side2 = "Z";
            side3="X1";

            //separate variables by comma (Y values)
            englishSepValues = engValues1.split(",");
            japaneseSepValues = japValues1.split(",");
            romajiSepValues = romaValues1.split(",");

        } else {
            side1 = "Z";
            side2 = "Y";
            side3 = "X2";

            //separate variables by comma (Z values)
            englishSepValues = engValues2.split(",");
            japaneseSepValues = japValues2.split(",");
            romajiSepValues = romaValues2.split(",");
        }

        StringBuilder output = new StringBuilder();

        //iterate for the first variable
        for (int i = 0; i <= englishSepValues.length - 1; i++) {

            //insert the value into the sentence
            String englishTemp1 = engSentence.replaceAll(side1, englishSepValues[i]);
            String japaneseTemp1 = japSentence.replaceAll(side1, japaneseSepValues[i]);
            String romajiTemp1 = romaSentence.replaceAll(side1, romajiSepValues[i]);

            //iterate for the second variable
            for (int v = 0; v <= englishSepValues.length - 1; v++) {

                //skip the second value if it is the same as the first
                if (!englishSepValues[i].equals(englishSepValues[v])) {

                    //insert the value into the sentence
                    String englishTemp2 = englishTemp1.replaceAll(side2, englishSepValues[v]);
                    String japaneseTemp2 = japaneseTemp1.replaceAll(side2, japaneseSepValues[v]);
                    String romajiTemp2 = romajiTemp1.replaceAll(side2, romajiSepValues[v]);

                    //iterate for the third variable
                    for (int x = 0; x <= englishSepValues.length - 1; x++) {

                        //skip the third value if it is the same as the above
                        if (!englishSepValues[v].equals(englishSepValues[x]) & !englishSepValues[i].equals(englishSepValues[x]) ) {

                            //insert the value into the sentence
                            String englishTempX = englishTemp2.replaceAll(side3, englishSepValues[x]);
                            String japaneseTempX = japaneseTemp2.replaceAll(side3, japaneseSepValues[x]);
                            String romajiTempX = romajiTemp2.replaceAll(side3, romajiSepValues[x]);

                            //format each sentence to CVS
                            output.append(japaneseTempX).append(",").append(englishTempX).append(",").append(romajiTempX).append("\n");
                        }
                    }
                }
            }
        }
        return output.toString();
    }


    //Double Order - Swaps from an order A -> 1,2,3  B -> a,b,c ------ it is A and B
    public String runDoubleOrder() {

        //separate variables by comma
        String[] englishSepValues1 = engValues1.split(",");
        String[] japaneseSepValues1 = japValues1.split(",");
        String[] romajiSepValues1 = romaValues1.split(",");

        //separate variables by comma
        String[] englishSepValues2 = engValues2.split(",");
        String[] japaneseSepValues2 = japValues2.split(",");
        String[] romajiSepValues2 = romaValues2.split(",");

        StringBuilder output = new StringBuilder();

        //iterate through the first values
        for(int i = 0; i<=englishSepValues1.length-1;i++){

            //insert the value into the sentence
            String englishTemp1 =engSentence.replaceAll(givenChar1,englishSepValues1[i]) ;
            String japaneseTemp1 =japSentence.replaceAll(givenChar1,japaneseSepValues1[i]) ;
            String romajiTemp1 =romaSentence.replaceAll(givenChar1, romajiSepValues1[i]);

            //iterate through the second values
            for(int v = 0; v<=englishSepValues2.length-1;v++){

                //skip if values are the same
                if(!englishSepValues1[i].equals(englishSepValues2[v])){

                    //insert the value into the sentence
                    String englishTemp2 = englishTemp1.replaceAll(givenChar2,englishSepValues2[v]) ;
                    String japaneseTemp2 = japaneseTemp1.replaceAll(givenChar2,japaneseSepValues2[v]) ;
                    String romajiTemp2 = romajiTemp1.replaceAll(givenChar2, romajiSepValues2[v]);

                    //format each sentence to CVS
                    output.append(japaneseTemp2).append(",").append(englishTemp2).append(",").append(romajiTemp2).append("\n");
                }
            }
        }
        return output.toString();
    }

    //X1 X2 Y Z --- (Y2 | Z2) Y Z
    //Order three variables. X values are copied from Y (X1) or Z (X2)
    //values can repeat
    public String runTripleOrder() {

        String [] english_x = {},japanese_x = {},romaji_x = {};
        String givenChar3 = "";

        //determine X1 or X2
        if (engSentence.contains("X1")){
            //separate variables by comma
            english_x = engValues2.split(",");
            japanese_x = japValues2.split(",");
            romaji_x = romaValues2.split(",");

            givenChar3 = "X1";
        } else if (engSentence.contains("X2")) {
            english_x = engValues1.split(",");
            japanese_x = japValues1.split(",");
            romaji_x = romaValues1.split(",");
            givenChar3 = "X2";
        }


        //separate variables by comma
        String[] english_y = engValues1.split(",");
        String[] japanese_y = japValues1.split(",");
        String[] romaji_y = romaValues1.split(",");

        //separate variables by comma
        String[] english_z = engValues2.split(",");
        String[] japanese_z = japValues2.split(",");
        String[] romaji_z = romaValues2.split(",");

        StringBuilder output = new StringBuilder();

        //iterate through the first (X) values
        for(int p = 0; p<=english_x.length-1;p++) {

            //insert the value into the sentence
            String englishTempX = engSentence.replaceAll(givenChar3, english_x[p]);
            String japaneseTempX = japSentence.replaceAll(givenChar3, japanese_x[p]);
            String romajiTempX = romaSentence.replaceAll(givenChar3, romaji_x[p]);

            //iterate through the second values
            for (int i = 0; i <= english_y.length - 1; i++) {

                //insert the value into the sentence
                String englishTempY = englishTempX.replaceAll(givenChar1, english_y[i]);
                String japaneseTempY = japaneseTempX.replaceAll(givenChar1, japanese_y[i]);
                String romajiTempY = romajiTempX.replaceAll(givenChar1, romaji_y[i]);

                //iterate through the third values
                for (int v = 0; v <= english_z.length - 1; v++) {

                    //insert the value into the sentence
                    String englishTempZ = englishTempY.replaceAll(givenChar2, english_z[v]);
                    String japaneseTempZ = japaneseTempY.replaceAll(givenChar2, japanese_z[v]);
                    String romajiTempZ = romajiTempY.replaceAll(givenChar2, romaji_z[v]);

                    //format each sentence to CVS
                    output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");

                }
            }
        }
        return output.toString();
    }



    //Order 4 variables
    //W1|W2 + X1|X2 + Y + Z
    //WX != YZ
    //Skips when W and X values matches Y and Z values.
    public String runQuadOrder() {

        String [] english_w = {},japanese_w = {},romaji_w = {};
        String wChar = "";

        String [] english_x = {},japanese_x = {},romaji_x = {};
        String xChar = "";

        //determine W1 (Y) or W2 (Z)
        if (engSentence.contains("W1")){
            //separate variables by comma
            english_w = engValues2.split(",");
            japanese_w = japValues2.split(",");
            romaji_w = romaValues2.split(",");

            wChar = "W1";
        } else if (engSentence.contains("W2")) {
            english_w = engValues1.split(",");
            japanese_w = japValues1.split(",");
            romaji_w = romaValues1.split(",");
            wChar = "W2";
        }

        //determine X1 (Y) or X2 (Z)
        if (engSentence.contains("X1")){
            //separate variables by comma
            english_x = engValues2.split(",");
            japanese_x = japValues2.split(",");
            romaji_x = romaValues2.split(",");

            xChar = "X1";
        } else if (engSentence.contains("X2")) {
            english_x = engValues1.split(",");
            japanese_x = japValues1.split(",");
            romaji_x = romaValues1.split(",");
            xChar = "X2";
        }


        //separate variables by comma
        String[] english_y = engValues1.split(",");
        String[] japanese_y = japValues1.split(",");
        String[] romaji_y = romaValues1.split(",");


        //separate variables by comma
        String[] english_z = engValues2.split(",");
        String[] japanese_z = japValues2.split(",");
        String[] romaji_z = romaValues2.split(",");

        StringBuilder output = new StringBuilder();


        //iterate through the w values
        for(int t = 0; t<=english_w.length-1;t++) {

            //insert the value into the sentence
            String englishTempW = engSentence.replaceAll(wChar, english_w[t]);
            String japaneseTempW = japSentence.replaceAll(wChar, japanese_w[t]);
            String romajiTempW = romaSentence.replaceAll(wChar, romaji_w[t]);

            //iterate through the x values
            for (int p = 0; p <= english_x.length - 1; p++) {

                //insert the value into the sentence
                String englishTempX = englishTempW.replaceAll(xChar, english_x[p]);
                String japaneseTempX = japaneseTempW.replaceAll(xChar, japanese_x[p]);
                String romajiTempX = romajiTempW.replaceAll(xChar, romaji_x[p]);


                //iterate through the third values
                for (int i = 0; i <= english_y.length - 1; i++) {

                    //insert the value into the sentence
                    String englishTempY = englishTempX.replaceAll(givenChar1, english_y[i]);
                    String japaneseTempY = japaneseTempX.replaceAll(givenChar1, japanese_y[i]);
                    String romajiTempY = romajiTempX.replaceAll(givenChar1, romaji_y[i]);

                    //iterate through the fourth values
                    for (int v = 0; v <= english_z.length - 1; v++) {

                        //insert the value into the sentence
                        String englishTempZ = englishTempY.replaceAll(givenChar2, english_z[v]);
                        String japaneseTempZ = japaneseTempY.replaceAll(givenChar2, japanese_z[v]);
                        String romajiTempZ = romajiTempY.replaceAll(givenChar2, romaji_z[v]);

                        //check wx don't equal yz
                        String wx = english_w[t].concat(english_x[p]);
                        String yz = english_z[v].concat(english_y[i]);

                        if (!wx.equals(yz)){
                            //format each sentence to CVS
                            output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");
                        }

                    }
                }
            }
        }
        return output.toString();
    }

}


