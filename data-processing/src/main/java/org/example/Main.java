package org.example;
import Scraper.Scraper;
import db_writer.Writer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Writer writer = new Writer();
        writer.sortVehicles();



    }
}