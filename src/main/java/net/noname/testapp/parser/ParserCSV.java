package net.noname.testapp.parser;

import org.springframework.core.io.ClassPathResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParserCSV implements MyParser {

    private final String separator;

    public ParserCSV(String separator) {
        this.separator = separator;
    }

    public Stream<List<String>> parse(String input, String pathToFile, int column) {
        try {
            var inputStream = new ClassPathResource(pathToFile).getInputStream();
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .map(this::getPartsFromLine);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> getPartsFromLine(String line) {
        return Arrays.stream(line.split(separator))
                .map(this::getStringWithoutQuotes).toList();
    }

    private String getStringWithoutQuotes(String part) {
        return part.replaceAll("\"", "");
    }

//    private static class ColumnComparator implements Comparator<List<String>> {
//
//        private static int _column;
//
//        public ColumnComparator(int column) {
//            _column = column;
//        }
//
//        @Override
//        public int compare(List<String> o1, List<String> o2) {
//            var firstStr = o1.get(_column);
//            var secondStr = o2.get(_column);
//
//            var result = firstStr.compareTo(secondStr);
//            return result;
//        }
//    }
}
