module com.ftutic.itoprema {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.ftutic.itoprema to javafx.fxml;
    exports com.ftutic.itoprema;
}