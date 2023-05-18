module com.example.combogui {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.combogui to javafx.fxml;
    exports com.example.combogui;
}