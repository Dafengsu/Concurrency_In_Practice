package _05building_blocks;

import java.util.Vector;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class UnsafeVectorHelpers {
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
