package com.masterkpkit.anhtam.kpkit.utils;

import android.content.Context;

import java.util.Random;

/**
 * @author : IBN-5100
 * @since : April
 * Copyright (c) 2017 NTEX JSC. All rights reserved.
 */

public class Numbers {

    public static final int INVALID = -1;

    public static boolean isValid(int i) {
        return i != INVALID;
    }

    public static int rand(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private static final String[] arrNumber = {"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};

    private static String tenth(int number, boolean flag) {
        String string = "";
        int tenth = (int) Math.floor(number / 10);
        int unit = number % 10;
        if (tenth > 1) {
            string = " " + arrNumber[tenth] + " mươi";
            if (unit == 1) {
                string += " mốt";
            }
        } else if (tenth == 1) {
            string = " mười";
            if (unit == 1) {
                string += " một";
            }
        } else if (flag && unit > 0) {
            string = " lẻ";
        }

        if (unit == 5 && tenth >= 1) {
            string += " lăm";
        } else if (unit > 1 || (unit == 1 && tenth == 0)) {
            string += " " + arrNumber[unit];
        }

        return string;
    }

    private static String hundreds(int number, boolean flag) {
        String string = "";
        int hundreds = (int) Math.floor(number / 100);
        number %= 100;

        if (flag || hundreds > 0) {
            string = " " + arrNumber[hundreds] + " trăm";
            string += tenth(number, true);
        } else {
            string = tenth(number, false);
        }
        return string;
    }

    private static String million(int number, boolean flag) {
        String string = "";
        int million = (int) Math.floor(number / 1000000);
        number %= 1000000;

        if (million > 0) {
            string = hundreds(million, flag) + " triệu";
            flag = true;
        }

        int thousand = (int) Math.floor(number / 1000);
        number %= 1000;
        if (thousand > 0) {
            string += hundreds(thousand, flag) + " nghìn";
            flag = true;
        }
        if (number > 0) {
            string += hundreds(number, flag);
        }
        return string;
    }

    public static String moneyToString(int number) {
        if (number == 0) return "Không";
        String string = "";
        String suffixes = "";

        do {
            int billion = number % 1000000000;
            number = (int) Math.floor(number / 1000000000);
            if (number > 0) {
                string = million(billion, true) + suffixes + string;
            } else {
                string = million(billion, false) + suffixes + string;
            }
            suffixes = " tỷ";
        } while (number > 0);
        return string.substring(1, 2).toUpperCase() + string.substring(2);
    }

    private static final String[] specialNames = {
            "",
            " thousand",
            " million",
            " billion",
            " trillion",
            " quadrillion",
            " quintillion"
    };

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

    private static String convertLessThanOneThousand(int number) {
        String current;

        if (number % 100 < 20) {
            current = numNames[number % 100];
            number /= 100;
        } else {
            current = numNames[number % 10];
            number /= 10;

            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) return current;
        return numNames[number] + " hundred" + current;
    }

    public static String convert(int number) {

        if (number == 0) {
            return "zero";
        }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "negative";
        }

        String current = "";
        int place = 0;

        do {
            int n = number % 1000;
            if (n != 0) {
                String s = convertLessThanOneThousand(n);
                current = s + specialNames[place] + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);

        String string = (prefix + current).trim();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String moneyFormat(String str) {
        return new StringBuilder(Strings.separate(new StringBuilder(str).reverse().toString(), 3, ",")).reverse().toString();
    }

    public static String moneyFormat(String str, Context context) {
        if (LocaleHelper.getLanguage(context).equals("vi")) {
            return new StringBuilder(Strings.separate(new StringBuilder(str).reverse().toString(), 3, ",")).reverse().toString();
        } else {
            return new StringBuilder(Strings.separate(new StringBuilder(str).reverse().toString(), 3, ".")).reverse().toString();
        }
    }

    public static String moneyFormat(int money) {
        return moneyFormat(String.valueOf(money));
    }

    public static String moneyFormat(long money) {
        return moneyFormat(String.valueOf(money));
    }

    public static String moneyToNumber(String money) {
        return money.replace(",", "");
    }

    public static String moneyFormat(long l, Context context) {
        return moneyFormat(String.valueOf(l), context);
    }

    public static String moneyToNumber(String money, Context context) {
        if (LocaleHelper.getLanguage(context).equals("vi")) {
            return money.replace(",", "");
        } else {
            return money.replace(".", "");
        }
    }
}
