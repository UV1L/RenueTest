package net.noname.testapp.parser;

import java.util.List;
import java.util.stream.Stream;

public interface MyParser {

   <T extends List<String>> Stream<T> parse(String input, String pathToFile, int column);
}
