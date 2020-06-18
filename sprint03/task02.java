/*
 * Create public inner class named Iterator inside NameList class that correspond the class diagram.
 */

/**
 * @author Bogdan Kurchak
 */
class NameList {
    private String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator {
        private int counter = 0;

        private Iterator() { }

        public boolean hasNext() {
            return counter != names.length;
        }

        public String next() {
            String result = names[counter];
            counter++;
            return result;
        }
    }
}
