package com.gramzin.hackathon.java.bits;

import static java.util.BitSet.valueOf;

import java.util.BitSet;

/**
 * Класс предназначен для побитного чтения.
 */
public class BitReader {

    private final BitSet bs;
    private int pos = -1;

    /**
     * @param n набор блоков для чтения по 64 бита каждый
     */
    public BitReader(long... n) {
        bs = valueOf(n);
    }

    public BitReader(BitWriter w) {
        bs = w.toBitSet();
    }

    /**
     * @return метод читает следующий по порядку бит
     */
    public boolean readBit() {
        return bs.get(++pos);
    }

    /**
     * @return запрошенное количество бит (может быть меньше, если достаточно битов не осталось)
     */
    public BitWriter readBits(int size) {
        BitWriter w = new BitWriter(size);
        for (int i = 0; (i < size) && hasUnreadBits(); ++i) {
            w.writeBit(readBit());
        }
        return w;
    }

    /**
     * Прочитать следующий примитив int (следующие 32 бита целиком)
     */
    public int readInt() {
        return readBits(32).toInt();
    }

    /**
     * Прочитать следующий примитив byte (следующие 8 бит целиком)
     */
    public byte readByte() {
        return readBits(8).toByte();
    }

    /**
     * @return количество еще не прочитанных бит
     */
    public int getUnreadSize() {
        return bs.size() - pos - 1;
    }

    /**
     * @return есть ли еще непрочитанные биты?
     */
    public boolean hasUnreadBits() {
        return getUnreadSize() > 0;
    }
}
