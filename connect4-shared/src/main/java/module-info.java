module connect.shared {
    requires java.sql;

    exports com.main.connect4shared.domain;
    exports com.main.connect4shared.request;
    exports com.main.connect4shared.response;
}