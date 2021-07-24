package net.catstack.testapp;

import net.catstack.testapp.logic.AirportsDataAccessObject;
import net.catstack.testapp.parser.ParserCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class RenueTestApp implements CommandLineRunner {

    @Autowired
    private AirportsDataAccessObject airportsDAO;

    public static void main(String[] args) {
        SpringApplication.run(RenueTestApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.print("Введите желаемую строку для фильтрации: ");
        var input = getInput();

        airportsDAO.getAirports(input, new ParserCSV(","));
    }

    private String getInput() {
        var scanner = new Scanner(System.in);
        if (scanner.hasNext())
            return scanner.next();
        return null;
    }
}
