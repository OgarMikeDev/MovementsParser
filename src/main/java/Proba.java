import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Proba {
    public static void main(String[] args) {
        String textOne = "YANDEX TAXI            23.03.17 21.03.17";
        String textTwo = "ZOOMAGAZIN 4            27.03.17 24.03.17";
        String textThree = "Alfa Iss                      03.04.17 02.04.17";

        String regex = "[a-zA-Z]+\\s{0,1}[a-zA-Z]+\\s{0,1}[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textTwo);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
