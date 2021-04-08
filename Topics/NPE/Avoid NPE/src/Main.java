import java.util.*;

class FixingNullPointerException {

    public static void main(String[] args) {
        String[] strings = { "abc", "hello", "army of robots", null }; // 0

        String s1 = strings[0]; // 1
        String s2 = strings[3]; // 2
        String s3 = s2.concat("abc"); // 3
        String s4 = strings[strings.length]; // 4
    }
}