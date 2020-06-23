import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * Create a createNotebook() method of the MyUtils class to create a new map with name as key and phone list as value.
 * The method receives a map  with phone as key and name as value.
 * For example, for a given map
 * {0967654321=Petro, 0677654321=Petro, 0501234567=Ivan, 0970011223=Stepan, 0631234567=Ivan}
 * you should get
 * {Ivan=[0501234567, 0631234567], Petro=[0967654321, 0677654321], Stepan=[0970011223]}.
 */

/**
 * @author Bogdan Kurchak
 */
class MyUtils {
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, String> entry : phones.entrySet()) {
            result.computeIfAbsent(entry.getValue(), value -> new ArrayList<>()).add(entry.getKey());
        }
        return result;
    }
}
