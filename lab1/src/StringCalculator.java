import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {

        int result = 0;

        ArrayList<Integer> arrayInteger = new ArrayList<>();

        Matcher mt = Pattern.compile("(-?[0-9]+)").matcher(numbers);
        while (mt.find()) {arrayInteger.add(Integer.valueOf(mt.group()));}

        for (int i = 0; i < arrayInteger.size(); i++) if (arrayInteger.get(i) > 1000) arrayInteger.set(i, 0);
        for (Integer integer : arrayInteger) {
            if (integer < 0) {
                System.out.println("Недозволенe від’ємнe числo: " + integer);
                result = -1;
            }
        }

        if (!numbers.contains("//") & result != -1) {
            if (numbers.length() == 1 & "123456789".contains(numbers)) {
                result = Integer.parseInt(numbers);
            } else if (numbers.length() > 1) {
                result = sumWithoutSample(numbers, arrayInteger);
            }
        } else if (result != -1) result = sumWithSample(numbers, arrayInteger);

        return result;
    }

    private int sumWithoutSample(String num, ArrayList<Integer> arrInteger) {

        int sum = 0;

        num = num.replace("\n", ",");

        if (verification(num, arrInteger.size(), num.replaceAll("[^,]", "").length())) {
            for (Integer integer : arrInteger) sum += integer;
            return sum;
        } else return -1;

    }

    private boolean verification(String num, int lenInteger, int lenSeparator) {

        boolean test1 = Pattern.compile("(^(\\d)(\\S)+(\\d)$)").matcher(num).find();

        return test1 & lenInteger - 1 == lenSeparator;
    }

    private int sumWithSample(String num, ArrayList<Integer> arrInteger) {

        int sum = 0;
        boolean bool = true;


        String[] arrNumbers = num.replace("[", "]").split("\n",2);
        System.out.println(Arrays.toString(arrNumbers));
        arrNumbers[1] = arrNumbers[1].replace("\n", ",");

        ArrayList<String> separatorInExpression = new ArrayList<>();
        Matcher mtExpression = Pattern.compile("([^0-9]+)").matcher(arrNumbers[1]);
        while (mtExpression.find()) separatorInExpression.add(mtExpression.group());

        ArrayList<String> separatorInSample = new ArrayList<>(List.of(","));
        Matcher mtSample = Pattern.compile("[^]/]+").matcher(arrNumbers[0]);
        while (mtSample.find()) separatorInSample.add(mtSample.group());

        for (String s : separatorInExpression) {
            if (!separatorInSample.contains(s)) {
                bool = false;
                break;
            }
            break;
        }

        if (bool & verification(arrNumbers[1], arrInteger.size(), separatorInExpression.size())) {
            for (Integer integer : arrInteger) sum += integer;
            return sum;
        } else return -1;
    }
}