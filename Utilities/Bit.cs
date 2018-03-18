using System;
using System.Threading;

namespace Resources {
    public static class Bit {
        public static bool GetBit(this byte number, int bitIndex) {
            if (bitIndex > 7 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-7");
            return (number & (1 << bitIndex)) != 0;
        }
        public static bool GetBit(this int number, int bitIndex) {
            if (bitIndex > 31 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-31");
            return (number & (1 << bitIndex)) != 0;
        }
        public static bool GetBit(this long number, int bitIndex) {
            if (bitIndex > 63 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-64");
            return (number & (1L << bitIndex)) != 0;
        }
        
        public static void Set(ref byte number, int bitIndex, bool bitValue) {
            if (bitIndex > 7 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-7");
            else if (bitValue)
                number |= (byte)(1 << bitIndex);
            else
                number &= (byte)~(1 << bitIndex);
        }
        public static void Set(ref int number, int bitIndex, bool bitValue) {
            if (bitIndex > 31 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-31");
            else if (bitValue)
                number |= (1 << bitIndex);
            else
                number &= ~(1 << bitIndex);
        }
        public static void Set(ref long number, int bitIndex, bool bitValue) {
            if (bitIndex > 63 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-63");
            else if (bitValue)
                number |= (1L << bitIndex);
            else
                number &= ~(1L << bitIndex);
        }
    }
}