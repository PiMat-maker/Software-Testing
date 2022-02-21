package task2;

import org.junit.jupiter.params.ParameterizedTest;
import task2.annotations.ArraySource;
import task2.annotations.ArraySources;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortTest {

    private MergeSortAlgorithm mergeSortAlgorithm;

    private void checker(int[] arr){
        mergeSortAlgorithm = new MergeSortAlgorithm(arr);
        int[] expectedArr = arr.clone();
        Arrays.sort(expectedArr);
        assertTrue(Arrays.equals(expectedArr, mergeSortAlgorithm.mergeSort()));
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {4, 7, 11, 15, 20, 22, 23, 26})
    })
    public void checkSortedEvenLengthArray(int[] arr){
        checker(arr);
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {4, 7, 11, 15, 20, 22, 23})
    })
    public void checkSortedOddLengthArray(int[] arr){
        checker(arr);
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {14, 7, 1, -2, 20, 232, 123, 216}),
            @ArraySource(array = {15, -5, -2, 51, 35, 14, 18, 13, 16, 21})
    })
    public void checkEvenLengthArray(int[] arr){
        checker(arr);
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {14, 7, 121, 20, 232, 123, 216}),
            @ArraySource(array = {15, -5, -2, 51, 35, 14, 13, 16, 21})
    })
    public void checkOddLengthArray(int[] arr){
        checker(arr);
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {})
    })
    public void checkEmptyArray(int[] arr){
        checker(arr);
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {26, 23, 22, 20, 15, 11, 7, 4})
    })
    public void checkReverseSortedEvenLengthArray(int[] arr){
        checker(arr);
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {23, 22, 20, 15, 11, 7, 4})
    })
    public void checkReverseSortedOddLengthArray(int[] arr){
        checker(arr);
    }
}
