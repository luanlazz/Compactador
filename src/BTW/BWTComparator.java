package BTW;

public class BWTComparator implements java.util.Comparator<Integer> {
    private final String string;

    BWTComparator(String string) {
      this.string = string;
    }

    @Override
    public int compare(Integer i, Integer j) {
      return string.charAt(i) - string.charAt(j);
    }

    public boolean equals(Integer i, Integer j) {
      return string.charAt(i) == string.charAt(j);
    }
  }
