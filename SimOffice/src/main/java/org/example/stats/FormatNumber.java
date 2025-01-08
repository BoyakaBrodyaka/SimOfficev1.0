package org.example.stats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormatNumber {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.0");

    public static String formatNumber(double number) {
        BigDecimal num = BigDecimal.valueOf(number);
        BigDecimal notgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal octgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal sptgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal sxtgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal qitgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal qatgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal ttgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal dtgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal utgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal tgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal novgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal ocvgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal spvgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal sxvgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal qivgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal qavgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal tvgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal dvgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal uvgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal vgillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal nodillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000"));
        BigDecimal ocdillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000"));
        BigDecimal spdillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000"));
        BigDecimal sxdillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000"));
        BigDecimal qidillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000"));
        BigDecimal qadillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000"));
        BigDecimal dedillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000"));
        BigDecimal tedillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000000"));
        BigDecimal udillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000000"));
        BigDecimal decillion = new BigDecimal(new BigInteger("1000000000000000000000000000000000"));
        BigDecimal nonillion = new BigDecimal(new BigInteger("1000000000000000000000000000000"));
        BigDecimal octillion = new BigDecimal(new BigInteger("1000000000000000000000000000"));
        BigDecimal septillion = new BigDecimal(new BigInteger("1000000000000000000000000"));
        BigDecimal sextillion = new BigDecimal(new BigInteger("1000000000000000000000"));
        BigDecimal quintillion = BigDecimal.valueOf(1_000_000_000_000_000_000L);
        BigDecimal quadrillion = BigDecimal.valueOf(1_000_000_000_000_000L);
        BigDecimal trillion = BigDecimal.valueOf(1_000_000_000_000L);

        if (num.compareTo(notgillion) >= 0) {
            return num.divide(notgillion, 2, RoundingMode.HALF_UP).toString() + "Notg";
        } else if (num.compareTo(octgillion) >= 0) {
            return num.divide(octgillion, 1, RoundingMode.HALF_UP).toString() + "Octg";
        } else if (num.compareTo(sptgillion) >= 0) {
            return num.divide(sptgillion, 1, RoundingMode.HALF_UP).toString() + "Sptg";
        } else if (num.compareTo(sxtgillion) >= 0) {
            return num.divide(sxtgillion, 1, RoundingMode.HALF_UP).toString() + "Sxtg";
        } else if (num.compareTo(qitgillion) >= 0) {
            return num.divide(qitgillion, 1, RoundingMode.HALF_UP).toString() + "Qitg";
        } else if (num.compareTo(qatgillion) >= 0) {
            return num.divide(qatgillion, 1, RoundingMode.HALF_UP).toString() + "Qatg";
        } else if (num.compareTo(ttgillion) >= 0) {
            return num.divide(ttgillion, 1, RoundingMode.HALF_UP).toString() + "Ttg";
        } else if (num.compareTo(dtgillion) >= 0) {
            return num.divide(dtgillion, 1, RoundingMode.HALF_UP).toString() + "Dtg";
        } else if (num.compareTo(utgillion) >= 0) {
            return num.divide(utgillion, 1, RoundingMode.HALF_UP).toString() + "Utg";
        } else if (num.compareTo(tgillion) >= 0) {
            return num.divide(tgillion, 1, RoundingMode.HALF_UP).toString() + "Tg";
        } else if (num.compareTo(novgillion) >= 0) {
            return num.divide(novgillion, 1, RoundingMode.HALF_UP).toString() + "Novg";
        } else if (num.compareTo(ocvgillion) >= 0) {
            return num.divide(ocvgillion, 1, RoundingMode.HALF_UP).toString() + "Ocvg";
        } else if (num.compareTo(spvgillion) >= 0) {
            return num.divide(spvgillion, 1, RoundingMode.HALF_UP).toString() + "Spvg";
        } else if (num.compareTo(sxvgillion) >= 0) {
            return num.divide(sxvgillion, 1, RoundingMode.HALF_UP).toString() + "Sxvg";
        } else if (num.compareTo(qivgillion) >= 0) {
            return num.divide(qivgillion, 1, RoundingMode.HALF_UP).toString() + "Qivg";
        } else if (num.compareTo(qavgillion) >= 0) {
            return num.divide(qavgillion, 1, RoundingMode.HALF_UP).toString() + "Qavg";
        } else if (num.compareTo(tvgillion) >= 0) {
            return num.divide(tvgillion, 1, RoundingMode.HALF_UP).toString() + "Tvg";
        } else if (num.compareTo(dvgillion) >= 0) {
            return num.divide(dvgillion, 1, RoundingMode.HALF_UP).toString() + "Dvg";
        } else if (num.compareTo(uvgillion) >= 0) {
            return num.divide(uvgillion, 1, RoundingMode.HALF_UP).toString() + "Uvg";
        } else if (num.compareTo(vgillion) >= 0) {
            return num.divide(vgillion, 1, RoundingMode.HALF_UP).toString() + "Vg";
        } else if (num.compareTo(nodillion) >= 0) {
            return num.divide(nodillion, 1, RoundingMode.HALF_UP).toString() + "Nod";
        } else if (num.compareTo(ocdillion) >= 0) {
            return num.divide(ocdillion, 1, RoundingMode.HALF_UP).toString() + "Ocd";
        } else if (num.compareTo(spdillion) >= 0) {
            return num.divide(spdillion, 1, RoundingMode.HALF_UP).toString() + "Spd";
        } else if (num.compareTo(sxdillion) >= 0) {
            return num.divide(sxdillion, 1, RoundingMode.HALF_UP).toString() + "Sxd";
        } else if (num.compareTo(qidillion) >= 0) {
            return num.divide(qidillion, 1, RoundingMode.HALF_UP).toString() + "Qid";
        } else if (num.compareTo(qadillion) >= 0) {
            return num.divide(qadillion, 1, RoundingMode.HALF_UP).toString() + "Qad";
        } else if (num.compareTo(tedillion) >= 0) {
            return num.divide(tedillion, 1, RoundingMode.HALF_UP).toString() + "Td";
        } else if (num.compareTo(dedillion) >= 0) {
            return num.divide(dedillion, 1, RoundingMode.HALF_UP).toString() + "Dd";
        } else if (num.compareTo(udillion) >= 0) {
            return num.divide(udillion, 1, RoundingMode.HALF_UP).toString() + "Ud";
        } else if (num.compareTo(decillion) >= 0) {
            return num.divide(decillion, 1, RoundingMode.HALF_UP).toString() + "Dc";
        } else if (num.compareTo(nonillion) >= 0) {
            return num.divide(nonillion, 1, RoundingMode.HALF_UP).toString() + "No";
        } else if (num.compareTo(octillion) >= 0) {
            return num.divide(octillion, 1, RoundingMode.HALF_UP).toString() + "Oc";
        } else if (num.compareTo(septillion) >= 0) {
            return num.divide(septillion, 1, RoundingMode.HALF_UP).toString() + "Sp";
        } else if (num.compareTo(sextillion) >= 0) {
            return num.divide(sextillion, 1, RoundingMode.HALF_UP).toString() + "Sx";
        } else if (num.compareTo(quintillion) >= 0) {
            return num.divide(quintillion, 1, RoundingMode.HALF_UP).toString() + "Qi";
        } else if (num.compareTo(quadrillion) >= 0) {
            return num.divide(quadrillion, 1, RoundingMode.HALF_UP).toString() + "Qa";
        } else if (num.compareTo(trillion) >= 0) {
            return num.divide(trillion, 1, RoundingMode.HALF_UP).toString() + "T";
        } else if (num.compareTo(BigDecimal.valueOf(1_000_000_000)) >= 0) {
            return num.divide(BigDecimal.valueOf(1_000_000_000), 1, RoundingMode.HALF_UP).toString() + "B";
        } else if (num.compareTo(BigDecimal.valueOf(1_000_000)) >= 0) {
            return num.divide(BigDecimal.valueOf(1_000_000), 1, RoundingMode.HALF_UP).toString() + "M";
        } else if (num.compareTo(BigDecimal.valueOf(1_000)) >= 0) {
            return num.divide(BigDecimal.valueOf(1_000), 1, RoundingMode.HALF_UP).toString() + "K";
        } else {
            return decimalFormat.format(number);
        }
    }

    public static double parseValue(String value) throws NumberFormatException {
        String[] suffixes = {"Notg", "Octg", "Sptg", "Sxtg", "Qitg", "Qatg", "Ttg", "Dtg", "Utg", "Tg", "Novg", "Ocvg", "Spvg", "Sxvg", "Qivg", "Qavg", "Tvg", "Dvg", "Uvg", "Vg", "Nod", "Ocd", "Spd", "Sxd", "Qid", "Qad", "Td", "Dd", "Ud", "Dc", "No", "Oc", "Sp", "Sx", "Qi", "Qa", "T", "B", "M", "K"};
        BigDecimal[] values = {
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000000")),
                new BigDecimal(new BigInteger("1000000000000000")),
                new BigDecimal(new BigInteger("1000000000000")),
                new BigDecimal(new BigInteger("1000000000")),
                new BigDecimal(new BigInteger("1000000")),
                new BigDecimal(new BigInteger("1000")),
                new BigDecimal(new BigInteger("1"))
        };
        for (int i = 0; i < suffixes.length; i++) {
            if (value.endsWith(suffixes[i])) {
                return new BigDecimal(value.replace(suffixes[i], "")).multiply(values[i]).doubleValue();
            }
        }
        return Double.parseDouble(value);
    }
}
