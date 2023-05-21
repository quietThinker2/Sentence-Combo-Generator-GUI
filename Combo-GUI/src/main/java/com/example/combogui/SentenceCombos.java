package com.example.combogui;

public class SentenceCombos {
    private final String japSentence;
    private final String engSentence;
    private final String romaSentence;
    private final String japValuesY;
    private final String engValuesY;
    private final String romaValuesY;
    private final String japValuesZ;
    private final String engValuesZ;
    private final String romaValuesZ;
    private final String sideCharOne;
    private final String sideCharTwo;


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
        engValuesY = builder.engValuesY;
        japValuesY = builder.japValuesY;
        romaValuesY = builder.romaValuesY;
        sideCharOne = builder.sideCharOne;
        japValuesZ = builder.japValuesZ;
        engValuesZ = builder.engValuesZ;
        romaValuesZ = builder.romaValuesZ;
        sideCharTwo = builder.sideCharTwo;
    }

    //Single - One value --> i am Y - where y = a,b,c,d...
    public String runSingle() {
        String[] english_y = engValuesY.split(",");
        String[] japanese_y = japValuesY.split(",");
        String[] romaji_y = romaValuesY.split(",");

        StringBuilder output = new StringBuilder();

        //iterate through the values
        for (int i = 0; i <= english_y.length - 1; i++) {

            String englishTempY = engSentence.replaceAll(sideCharOne, english_y[i]);
            String japaneseTempY = japSentence.replaceAll(sideCharOne, japanese_y[i]);
            String romajiTempY = romaSentence.replaceAll(sideCharOne, romaji_y[i]);

            output.append(japaneseTempY).append(",").append(englishTempY).append(",").append(romajiTempY).append("\n");
        }

        return output.toString();

    }

    //Double Swap - Takes one of the same values and swaps them -> it is a A and B - it is a B and A
    public String runDoubleSwap() {
        //separate variables by comma
        String[] english_y = engValuesY.split(",");
        String[] japanese_y = japValuesY.split(",");
        String[] romaji_y = romaValuesY.split(",");

        StringBuilder output = new StringBuilder();

        for (int i = 0; i <= english_y.length - 1; i++) {

            String englishTempY = engSentence.replaceAll(sideCharOne, english_y[i]);
            String japaneseTempY = japSentence.replaceAll(sideCharOne, japanese_y[i]);
            String romajiTempY = romaSentence.replaceAll(sideCharOne, romaji_y[i]);

            for (int v = 0; v <= english_y.length - 1; v++) {
                if (!english_y[i].equals(english_y[v])) {
                    String englishTempZ = englishTempY.replaceAll(sideCharTwo, english_y[v]);
                    String japaneseTempZ = japaneseTempY.replaceAll(sideCharTwo, japanese_y[v]);
                    String romajiTempZ = romajiTempY.replaceAll(sideCharTwo, romaji_y[v]);

                    output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");


                }

            }
        }
        return output.toString();
    }
    //Triple Swap - Takes one of the same values and swaps them -> it is a A B and C - it is a A C and B
    public String runTripleSwap() {

        String[] english_y,japanese_y,romaji_y;
        String side1 ="";
        String side2="";
        String side3="";
        //if sentence contains X1 or X2
        if (engSentence.contains("X1")){
            side1 = "Y";
            side2 = "Z";
            side3="X1";
            //separate variables by comma
            english_y = engValuesY.split(",");
            japanese_y = japValuesY.split(",");
            romaji_y = romaValuesY.split(",");
        } else {
            side1 = "Z";
            side2 = "Y";
            side3 = "X2";
            //separate variables by comma
            english_y = engValuesZ.split(",");
            japanese_y = japValuesZ.split(",");
            romaji_y = romaValuesZ.split(",");
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i <= english_y.length - 1; i++) {

            String englishTempY = engSentence.replaceAll(side1, english_y[i]);
            String japaneseTempY = japSentence.replaceAll(side1, japanese_y[i]);
            String romajiTempY = romaSentence.replaceAll(side1, romaji_y[i]);

            for (int v = 0; v <= english_y.length - 1; v++) {
                if (!english_y[i].equals(english_y[v])) {
                    String englishTempZ = englishTempY.replaceAll(side2, english_y[v]);
                    String japaneseTempZ = japaneseTempY.replaceAll(side2, japanese_y[v]);
                    String romajiTempZ = romajiTempY.replaceAll(side2, romaji_y[v]);

                    for (int x = 0; x <= english_y.length - 1; x++) {
                        if (!english_y[v].equals(english_y[x]) & !english_y[i].equals(english_y[x]) ) {
                            String englishTempX = englishTempZ.replaceAll(side3, english_y[x]);
                            String japaneseTempX = japaneseTempZ.replaceAll(side3, japanese_y[x]);
                            String romajiTempX = romajiTempZ.replaceAll(side3, romaji_y[x]);
                            output.append(japaneseTempX).append(",").append(englishTempX).append(",").append(romajiTempX).append("\n");
                        }
                    }
                }
            }
        }
        return output.toString();
    }


    //Double Order - Swaps from an order a -> 1,2,3  b -> 1,2,3
    public String runDoubleOrder() {

        //separate variables by comma
        String[] english_y = engValuesY.split(",");
        String[] japanese_y = japValuesY.split(",");
        String[] romaji_y = romaValuesY.split(",");

        //separate variables by comma
        String[] english_z = engValuesZ.split(",");
        String[] japanese_z = japValuesZ.split(",");
        String[] romaji_z = romaValuesZ.split(",");

        StringBuilder output = new StringBuilder();

        //iterate through the first values
        for(int i = 0; i<=english_y.length-1;i++){
            String englishTempY =engSentence.replaceAll(sideCharOne,english_y[i]) ;
            String japaneseTempY =japSentence.replaceAll(sideCharOne,japanese_y[i]) ;
            String romajiTempY =romaSentence.replaceAll(sideCharOne, romaji_y[i]);

            //iterate through the second values
            for(int v = 0; v<=english_z.length-1;v++){
                if(!english_y[i].equals(english_z[v])){
                    String englishTempZ = englishTempY.replaceAll(sideCharTwo,english_z[v]) ;
                    String japaneseTempZ = japaneseTempY.replaceAll(sideCharTwo,japanese_z[v]) ;
                    String romajiTempZ = romajiTempY.replaceAll(sideCharTwo, romaji_z[v]);

                    output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");
                }
            }
        }
        return output.toString();
    }

    //X1 X2 Y Z
    public String runTripleOrder() {

        String [] english_x = new String[0],japanese_x = new String[0],romaji_x = new String[0];
        String xValue = "";
        if (engSentence.contains("X1")){
            //separate variables by comma
            english_x = engValuesZ.split(",");
            japanese_x = japValuesZ.split(",");
            romaji_x = romaValuesZ.split(",");

            xValue = "X1";
        } else if (engSentence.contains("X2")) {
            english_x = engValuesY.split(",");
            japanese_x = japValuesY.split(",");
            romaji_x = romaValuesY.split(",");
            xValue = "X2";
        }


            //separate variables by comma
        String[] english_y = engValuesY.split(",");
        String[] japanese_y = japValuesY.split(",");
        String[] romaji_y = romaValuesY.split(",");

        StringBuilder output = new StringBuilder();


        //separate variables by comma
        String[] english_z = engValuesZ.split(",");
        String[] japanese_z = japValuesZ.split(",");
        String[] romaji_z = romaValuesZ.split(",");


        //iterate through the first values
        for(int p = 0; p<=english_x.length-1;p++) {
            String englishTempX = engSentence.replaceAll(xValue, english_x[p]);
            String japaneseTempX = japSentence.replaceAll(xValue, japanese_x[p]);
            String romajiTempX = romaSentence.replaceAll(xValue, romaji_x[p]);


            //iterate through the first values
            for (int i = 0; i <= english_y.length - 1; i++) {
                String englishTempY = englishTempX.replaceAll(sideCharOne, english_y[i]);
                String japaneseTempY = japaneseTempX.replaceAll(sideCharOne, japanese_y[i]);
                String romajiTempY = romajiTempX.replaceAll(sideCharOne, romaji_y[i]);

                //iterate through the second values
                for (int v = 0; v <= english_z.length - 1; v++) {

                    String englishTempZ = englishTempY.replaceAll(sideCharTwo, english_z[v]);
                    String japaneseTempZ = japaneseTempY.replaceAll(sideCharTwo, japanese_z[v]);
                    String romajiTempZ = romajiTempY.replaceAll(sideCharTwo, romaji_z[v]);

                    output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");

                }
            }
        }
        return output.toString();
    }



    //W1 W2 X1 X2 Y Z
    public String runQuadOrder() {

        String [] english_w = new String[0],japanese_w = new String[0],romaji_w = new String[0];
        String wValue = "";

        String [] english_x = new String[0],japanese_x = new String[0],romaji_x = new String[0];
        String xValue = "";

        if (engSentence.contains("W1")){
            //separate variables by comma
            english_w = engValuesZ.split(",");
            japanese_w = japValuesZ.split(",");
            romaji_w = romaValuesZ.split(",");

            wValue = "W1";
        } else if (engSentence.contains("W2")) {
            english_w = engValuesY.split(",");
            japanese_w = japValuesY.split(",");
            romaji_w = romaValuesY.split(",");
            wValue = "W2";
        }

        if (engSentence.contains("X1")){
            //separate variables by comma
            english_x = engValuesZ.split(",");
            japanese_x = japValuesZ.split(",");
            romaji_x = romaValuesZ.split(",");

            xValue = "X1";
        } else if (engSentence.contains("X2")) {
            english_x = engValuesY.split(",");
            japanese_x = japValuesY.split(",");
            romaji_x = romaValuesY.split(",");
            xValue = "X2";
        }


        //separate variables by comma
        String[] english_y = engValuesY.split(",");
        String[] japanese_y = japValuesY.split(",");
        String[] romaji_y = romaValuesY.split(",");


        //separate variables by comma
        String[] english_z = engValuesZ.split(",");
        String[] japanese_z = japValuesZ.split(",");
        String[] romaji_z = romaValuesZ.split(",");

        StringBuilder output = new StringBuilder();


        //iterate through the w values
        for(int t = 0; t<=english_w.length-1;t++) {
            String englishTempW = engSentence.replaceAll(wValue, english_w[t]);
            String japaneseTempW = japSentence.replaceAll(wValue, japanese_w[t]);
            String romajiTempW = romaSentence.replaceAll(wValue, romaji_w[t]);

            //iterate through the x values
            for (int p = 0; p <= english_x.length - 1; p++) {
                String englishTempX = englishTempW.replaceAll(xValue, english_x[p]);
                String japaneseTempX = japaneseTempW.replaceAll(xValue, japanese_x[p]);
                String romajiTempX = romajiTempW.replaceAll(xValue, romaji_x[p]);


                //iterate through the y values
                for (int i = 0; i <= english_y.length - 1; i++) {
                    String englishTempY = englishTempX.replaceAll(sideCharOne, english_y[i]);
                    String japaneseTempY = japaneseTempX.replaceAll(sideCharOne, japanese_y[i]);
                    String romajiTempY = romajiTempX.replaceAll(sideCharOne, romaji_y[i]);

                    //iterate through the z values
                    for (int v = 0; v <= english_z.length - 1; v++) {

                            String englishTempZ = englishTempY.replaceAll(sideCharTwo, english_z[v]);
                            String japaneseTempZ = japaneseTempY.replaceAll(sideCharTwo, japanese_z[v]);
                            String romajiTempZ = romajiTempY.replaceAll(sideCharTwo, romaji_z[v]);

                            //check wx dont equal yz
                            String wx = english_w[t].concat(english_x[p]);
                            String yz = english_z[v].concat(english_y[i]);

                            if (!wx.equals(yz)){
                                output.append(japaneseTempZ).append(",").append(englishTempZ).append(",").append(romajiTempZ).append("\n");
                            }

                    }
                }
            }
        }
        return output.toString();
    }

}


