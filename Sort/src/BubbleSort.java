import tools.Integers;
import tools.Times;

import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;

/**
 * url:
 * Author:Savannah
 * Description:
 * AlgorithmLearningProject 12/29/20
 */
public class BubbleSort {


    //升序，每次结束的时候实际上是把最大的移动到最末尾
    public static void main(String[] args) {
        final Integer[] array1 = Integers.random(10000, 0, 100000);
        final Integer[] array2 = Integers.copy(array1);
        Times.test("array1", () -> {
            bubbleSort1(array1);
        });

        Times.test("array2", () -> {
            bubbleSort3(array2);
        });

    }

    public static void bubbleSort1(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
    }

    public static void bubbleSort2(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSort = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    public static void bubbleSort3(Integer[] array) {

    }
}
