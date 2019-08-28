package javacode;

public class Kata {


    public boolean solution(String startString, String endString) {
        String check = startString.substring(endString.length()-1);

        System.out.println(check);
        return check.equals(endString);
    }
}
