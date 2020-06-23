import java.util.List;
import java.util.Map;

/*
 *
 * Create a listMapCompare() method of the MyUtils class to compare the contents
 * of a list of strings and the values of a map.
 * For example, for a given list [aa, bb, aa, cc] and map {1=cc, 2=bb, 3=cc, 4=aa, 5=cc}
 * you should get true.
 */

/**
 * @author Bogdan Kurchak
 */
class MyUtils {
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        return map.values().containsAll(list) && list.containsAll(map.values());
    }
}
