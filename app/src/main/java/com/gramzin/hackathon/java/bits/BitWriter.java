package com.gramzin.hackathon.java.bits;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

/**
 * Класс предназначен для побитной записи.
 */
public class BitWriter {

    private final BitSet bs;
    private int pos = -1;

    /**
     * @param size Количество бит которые будут доступны для записи.
     */
    public BitWriter(int size) {
        bs = new BitSet(size);
    }

    /**
     * @return 64 бита, результат записи в long (не вместившиеся биты будут отброшены)
     */
    public long toLong() {
        long[] arr = bs.toLongArray();
        return arr.length == 0 ? 0 : arr[0];
    }

    /**
     * @return набор бит (safe copy)
     */
    public BitSet toBitSet() {
        return (BitSet) bs.clone();
    }

    /**
     * @return 32 бита, результат записи в int (не вместившиеся биты будут отброшены)
     */
    public int toInt() {
        return (int) toLong();
    }

    /**
     * @return биты сохраненные в виде UTF-8 строки
     */
    @Override
    public String toString() {
        BitReader bytesReader = new BitReader(this);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (bytesReader.hasUnreadBits()) {
            outputStream.write(bytesReader.readByte());
        }
        return outputStream.toString();
    }

    /**
     * @return 8 бит, результат записи в byte (не вместившиеся биты будут отброшены)
     */
    public byte toByte() {
        byte[] arr = bs.toByteArray();
        return arr.length == 0 ? 0 : arr[0];
    }

    /**
     * Метод записывает следующий по порядку бит
     */
    public void writeBit(boolean bit) {
        bs.set(++pos, bit);
    }

}
