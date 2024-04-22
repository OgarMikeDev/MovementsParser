import org.w3c.dom.ls.LSOutput;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Double> mapBankCalculations = new HashMap<>();
        ArrayList<String> listLines = new ArrayList<>();
        String pathToFile = "data/movementList.csv";

        String regex = "[^a-zA-Z0-9]([a-zA-Z0-9\s]+)[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\s[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}";
        String regexForTypeOperation = "[a-zA-Z]+\\s{0,1}[a-zA-Z]+\\s{0,1}([a-zA-Z]+\\s{0,1}){0,1}[0-9]*";
        try {
            listLines = (ArrayList<String>) Files.readAllLines(Paths.get(pathToFile));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        for (String line : listLines) {
            if (line.contains("Тип счёта")) {
                continue;
            }

            String[] componentsLine = line.split(",");
            double expense = Double.parseDouble(componentsLine[7]);

            if (expense > 0) {
                String descriptionOperation = componentsLine[5];

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(descriptionOperation);
                while (matcher.find()) {
                    //descriptionOperation = matcher.group();

                    int start = matcher.start();
                    start++;
                    int end = matcher.end();
                    //YANDEX TAXI            23.03.17 21.03.17
                    //ZOOMAGAZIN 4            27.03.17 24.03.17
                    descriptionOperation = componentsLine[5].substring(start, end);
                }

                Pattern patternTypeOperation = Pattern.compile(regexForTypeOperation);
                Matcher matcherTypeOperation = patternTypeOperation.matcher(descriptionOperation);
                while (matcherTypeOperation.find()) {
                    int startTypeOperation = matcherTypeOperation.start();
                    int endTypeOperation = matcherTypeOperation.end();
                    String typeOperation = descriptionOperation.substring(startTypeOperation, endTypeOperation);

                    mapBankCalculations.put(typeOperation, expense);
                }
            }
        }

        mapBankCalculations.forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
