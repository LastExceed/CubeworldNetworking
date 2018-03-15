using System;
using System.Threading;

namespace Resources {
    public static class Tools {
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

        /// <param name="bitIndex">0 based Position</param>
        public static void SetBit(ref byte number, int bitIndex, bool bitValue) {
            if (bitIndex > 7 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-7");
            else if (bitValue)
                number |= (byte)(1 << bitIndex);
            else
                number &= (byte)~(1 << bitIndex);
        }
        /// <param name="bitIndex">0 based Position</param>
        public static void SetBit(ref int number, int bitIndex, bool bitValue) {
            if (bitIndex > 31 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-31");
            else if (bitValue)
                number |= (1 << bitIndex);
            else
                number &= ~(1 << bitIndex);
        }
        /// <param name="bitIndex">0 based Position</param>
        public static void SetBit(ref long number, int bitIndex, bool bitValue) {
            if (bitIndex > 63 || bitIndex < 0) throw new IndexOutOfRangeException("bitIndex must be in range 0-63");
            else if (bitValue)
                number |= (1L << bitIndex);
            else
                number &= ~(1L << bitIndex);
        }

        /// <summary>
        /// if you specify an amount of iterations > 1 the initial delay will be 0
        /// </summary>
        /// <param name="todo"></param>
        /// <param name="initialDelay"></param>
        /// <param name="iterationDelay"></param>
        /// <param name="iterations"></param>
        public static void DoLater(Func<bool> todo, int delay, int iterations = 1) {
            Timer t = null;
            t = new Timer((obj) => {
                if (todo() || --iterations == 0) {
                    t.Dispose();
                }
            }, null, iterations == 1 ? delay : 0, delay);
        }
    }
}