
import java.text.DecimalFormat;
import java.util.Random;

public class EnglishNumberToWords {

    private static final String[] tensNames = {
        "",
        " ten",
        " twenty",
        " thirty",
        " forty",
        " fifty",
        " sixty",
        " seventy",
        " eighty",
        " ninety"
    };

    private static final String[] numNames = {
        "",
        " one",
        " two",
        " three",
        " four",
        " five",
        " six",
        " seven",
        " eight",
        " nine",
        " ten",
        " eleven",
        " twelve",
        " thirteen",
        " fourteen",
        " fifteen",
        " sixteen",
        " seventeen",
        " eighteen",
        " nineteen"
    };

    private EnglishNumberToWords() {
    }

    private static String convertLessThanOneThousand(int number) {
        String soFar;
//        System.out.println("String.valueOf(number) " + String.valueOf(number));
        boolean isItEquals3 = false;

//        if (String.valueOf(number).length() == 3 && Integer.numberOfTrailingZeros(number) < 2) {
//        if (String.valueOf(number).length() == 3 && Integer.numberOfTrailingZeros(number) < 2) {
//        if (!(Integer.numberOfTrailingZeros(number) > 1)) {
        if (String.valueOf(number).length() == 3 && (!String.valueOf(number).endsWith("00"))) {
            isItEquals3 = true;
//            System.out.println("String.valueOf(number).length() " + String.valueOf(number).length());
        }

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
//            System.out.println("number Before " + number);
//            System.out.println("soFar " + soFar);
//            System.out.println("number After " + number);
        } else {
            soFar = numNames[number % 10];
//            System.out.println("Num " + number);

            number /= 10;

//            System.out.println("number Before [Tens] " + number);
//            System.out.println("soFar " + soFar);
            soFar = tensNames[number % 10] + soFar;
            number /= 10;
//            System.out.println("number Before " + number);
//            System.out.println("soFar " + soFar);
//            System.out.println("number After " + number);
        }
        if (number == 0) {
            return soFar;
        }
//        System.out.println(isItEquals3 == true ? numNames[number] + " hundred and " + soFar : numNames[number] + " hundred " + soFar);
        return isItEquals3 == true ? numNames[number] + " hundred and " + soFar : numNames[number] + " hundred " + soFar;
    }

    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "zero";
        }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(snumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "one thousand ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " thousand ";
        }
        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    /**
     * testing
     *
     * @param args
     */
    
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int val = random.nextInt(1000000000);
            System.out.println(val + " *** " + EnglishNumberToWords.convert(val));
        }

        System.out.println("98 *** " + EnglishNumberToWords.convert(98));
        System.out.println("1 *** " + EnglishNumberToWords.convert(1));
        System.out.println("16 *** " + EnglishNumberToWords.convert(16));
        System.out.println("100 *** " + EnglishNumberToWords.convert(100));
        System.out.println("118 *** " + EnglishNumberToWords.convert(118));
        System.out.println("200 *** " + EnglishNumberToWords.convert(200));
        System.out.println("219 *** " + EnglishNumberToWords.convert(219));
        System.out.println("800 *** " + EnglishNumberToWords.convert(800));
        System.out.println("801 *** " + EnglishNumberToWords.convert(801));
        System.out.println("245376 *** " + EnglishNumberToWords.convert(245376));
        System.out.println("1000000 *** " + EnglishNumberToWords.convert(1000000));
        System.out.println("2000000 *** " + EnglishNumberToWords.convert(2000000));
        System.out.println("3000200 *** " + EnglishNumberToWords.convert(3000200));
        System.out.println("700000 *** " + EnglishNumberToWords.convert(700000));
        System.out.println("9000000 *** " + EnglishNumberToWords.convert(9000000));
        System.out.println("9001000 *** " + EnglishNumberToWords.convert(9001000));
        System.out.println("123456789 *** " + EnglishNumberToWords.convert(123456789));
        System.out.println("2147483647 *** " + EnglishNumberToWords.convert(2147483647));
        System.out.println("3000000010L *** " + EnglishNumberToWords.convert(3000000010L));
        
    }
//        String phrase = "699.99";
//        Float num = new Float(phrase);
//        int dollars = (int) Math.floor(num);
//        int cent = (int) Math.floor((num - dollars) * 100.0f);
//
//        System.out.println("*** ttt" + cent);
//
//        String s = "$ " + EnglishNumberToWords.convert(dollars) + " and " + EnglishNumberToWords.convert(cent) + " cents";
//        System.out.println("Dollar & Cent " + s);

        /*
     *** zero
     *** one
     *** sixteen
     *** one hundred
     *** one hundred eighteen
     *** two hundred
     *** two hundred nineteen
     *** eight hundred
     *** eight hundred one
     *** one thousand three hundred sixteen
     *** one million
     *** two millions
     *** three millions two hundred
     *** seven hundred thousand
     *** nine millions
     *** nine millions one thousand
     *** one hundred twenty three millions four hundred
     **      fifty six thousand seven hundred eighty nine
     *** two billion one hundred forty seven millions
     **      four hundred eighty three thousand six hundred forty seven
     *** three billion ten
     **/
/*    }
*/
}