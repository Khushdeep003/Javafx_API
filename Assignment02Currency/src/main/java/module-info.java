module com.example.assignment02currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.google.gson;


    opens com.example.assignment02currency to javafx.fxml, com.google.gson;
    exports com.example.assignment02currency;
}