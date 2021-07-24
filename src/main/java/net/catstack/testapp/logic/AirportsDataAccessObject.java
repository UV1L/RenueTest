package net.catstack.testapp.logic;

import net.catstack.testapp.model.AirportModel;
import net.catstack.testapp.parser.MyParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class AirportsDataAccessObject {

    private static final StopWatch timer = new StopWatch();
    private String input;
    private int count = 0;

    @Value("${column}")
    private int columnToSearch;

    @Value("${csv_path}")
    private String csvPath;

    public Stream<String> getAirports(String input, MyParser parser) {
        this.input = input;

        Function<List<String>, AirportModel> func = AirportModel::new;

        timer.start();
        var parsedStream = parser.parse(input, csvPath, columnToSearch);
        var airportModelStream = parsedStream.map(func);
        var allDataStringStream = airportModelStream.map(AirportModel::toString);
        var filteredResultData = allDataStringStream.filter(s -> s.split(", ")[columnToSearch].startsWith(input));
        timer.stop();

        filteredResultData.forEach( s -> {
            ++count;
            System.out.println(s);
        });

        System.out.println("Количество найденных строк: " + count);
        System.out.println("Время, затраченное на поиск: " + timer.getLastTaskTimeMillis() + "мс");

        return filteredResultData;
    }
}
