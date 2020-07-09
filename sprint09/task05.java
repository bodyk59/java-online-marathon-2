import java.util.*;
import java.util.stream.Stream;

/*
 * Let the key of Map is project name and value contains list of participants.
 * Create a Stream<String> nameList(Map<String, Stream<String>> map) method of
 * the MyUtils class to build sorted stream of all participants without duplication.
 * Please ignore null or empty strings, extra spaces and case sensitivity.
 * Throw NullPointerException if map is null.
 * For example, for a given map
 * {"Desktop"=[" iVan", "PeTro ", " Ira "], "Web"=["STepan", "ira ", " Andriy ", "an na"], "Spring"=["Ivan", "Anna"]}
 * you should get
 * ["Andriy", "Anna", "Ira", "Ivan", "Petro ", "Stepan"]
 */

/**
 * @author Bogdan Kurchak
 */
public class MyUtils {
    public Stream<String> nameList(Map<String, Stream<String>> maps) {
        if (maps == null)
            throw new NullPointerException("Map is null");

        return maps.values()
                .stream()
                .flatMap(s -> s)
                .filter(Objects::nonNull)
                .map(string -> string.replace(" ", ""))
                .filter(string -> !"".equals(string))
                .map(String::toLowerCase)
                .map(string -> string.substring(0, 1).toUpperCase() + string.substring(1))
                .distinct()
                .sorted();
    }
}
