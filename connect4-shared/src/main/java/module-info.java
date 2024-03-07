module connect.shared {
    requires java.sql;

    exports com.main.connect4shared.domain;
    exports com.main.connect4shared.request;
    exports com.main.connect4shared.response;
    exports com.main.connect4shared.utils;

    opens com.main.connect4shared.domain to com.main.connect4client;
    exports com.main.connect4shared.enums;
    exports com.main.connect4shared.domain.generic;
    opens com.main.connect4shared.domain.generic to com.main.connect4client;
}