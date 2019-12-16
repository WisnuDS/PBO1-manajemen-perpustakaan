package com.company;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Crud {
    void create();
    void update();
    void delete();
    ArrayList read() throws SQLException;
}
