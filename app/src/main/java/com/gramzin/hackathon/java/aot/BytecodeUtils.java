package com.gramzin.hackathon.java.aot;

final class BytecodeUtils {

    private static final byte endOfCompiledLine = 100;

    static char safeByteToChar(byte b) {
        char c = byteToChar(b);
        if (c == 0) {
            throw new IllegalArgumentException("Invalid byte character: " + b);
        }
        return c;
    }

    static char byteToChar(byte b) {
        return switch (b) {
            case (byte) 0x2d -> '-';
            case (byte) 0x96 -> '–';
            case (byte) 0x97 -> '—';
            case (byte) 0x30 -> '0';
            case (byte) 0x31 -> '1';
            case (byte) 0x32 -> '2';
            case (byte) 0x33 -> '3';
            case (byte) 0x34 -> '4';
            case (byte) 0x35 -> '5';
            case (byte) 0x36 -> '6';
            case (byte) 0x37 -> '7';
            case (byte) 0x38 -> '8';
            case (byte) 0x39 -> '9';
            case (byte) 0xa8 -> 'Ё';
            case (byte) 0xb8 -> 'ё';
            case (byte) 0xc0 -> 'А';
            case (byte) 0xc1 -> 'Б';
            case (byte) 0xc2 -> 'В';
            case (byte) 0xc3 -> 'Г';
            case (byte) 0xc4 -> 'Д';
            case (byte) 0xc5 -> 'Е';
            case (byte) 0xc6 -> 'Ж';
            case (byte) 0xc7 -> 'З';
            case (byte) 0xc8 -> 'И';
            case (byte) 0xc9 -> 'Й';
            case (byte) 0xca -> 'К';
            case (byte) 0xcb -> 'Л';
            case (byte) 0xcc -> 'М';
            case (byte) 0xcd -> 'Н';
            case (byte) 0xce -> 'О';
            case (byte) 0xcf -> 'П';
            case (byte) 0xd0 -> 'Р';
            case (byte) 0xd1 -> 'С';
            case (byte) 0xd2 -> 'Т';
            case (byte) 0xd3 -> 'У';
            case (byte) 0xd4 -> 'Ф';
            case (byte) 0xd5 -> 'Х';
            case (byte) 0xd6 -> 'Ц';
            case (byte) 0xd7 -> 'Ч';
            case (byte) 0xd8 -> 'Ш';
            case (byte) 0xd9 -> 'Щ';
            case (byte) 0xda -> 'Ъ';
            case (byte) 0xdb -> 'Ы';
            case (byte) 0xdc -> 'Ь';
            case (byte) 0xdd -> 'Э';
            case (byte) 0xde -> 'Ю';
            case (byte) 0xdf -> 'Я';
            case (byte) 0xe0 -> 'а';
            case (byte) 0xe1 -> 'б';
            case (byte) 0xe2 -> 'в';
            case (byte) 0xe3 -> 'г';
            case (byte) 0xe4 -> 'д';
            case (byte) 0xe5 -> 'е';
            case (byte) 0xe6 -> 'ж';
            case (byte) 0xe7 -> 'з';
            case (byte) 0xe8 -> 'и';
            case (byte) 0xe9 -> 'й';
            case (byte) 0xea -> 'к';
            case (byte) 0xeb -> 'л';
            case (byte) 0xec -> 'м';
            case (byte) 0xed -> 'н';
            case (byte) 0xee -> 'о';
            case (byte) 0xef -> 'п';
            case (byte) 0xf0 -> 'р';
            case (byte) 0xf1 -> 'с';
            case (byte) 0xf2 -> 'т';
            case (byte) 0xf3 -> 'у';
            case (byte) 0xf4 -> 'ф';
            case (byte) 0xf5 -> 'х';
            case (byte) 0xf6 -> 'ц';
            case (byte) 0xf7 -> 'ч';
            case (byte) 0xf8 -> 'ш';
            case (byte) 0xf9 -> 'щ';
            case (byte) 0xfa -> 'ъ';
            case (byte) 0xfb -> 'ы';
            case (byte) 0xfc -> 'ь';
            case (byte) 0xfd -> 'э';
            case (byte) 0xfe -> 'ю';
            case (byte) 0xff -> 'я';
            default -> 0;
        };
    }

    static boolean isContent(byte b) {
        return b != endOfCompiledLine;
    }
}
