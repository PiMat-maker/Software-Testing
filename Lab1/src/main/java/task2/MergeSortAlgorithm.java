package task2;

public class MergeSortAlgorithm {

    private final int[] arr;
    private final int[] additionalArr;
    private final int startPointer;
    private final int endPointer;

    public MergeSortAlgorithm(int[] inputArr) {
        this.arr = inputArr;
        this.additionalArr = new int[inputArr.length];
        this.startPointer = 0;
        this.endPointer = inputArr.length - 1;
    }

    private void merge(int startPosFirstArr, int startPosSecondArr, int lengthSecondArr) {
        int pos1 = startPosFirstArr, pos2 = startPosSecondArr, iAdditionalArr = 0;

        while (pos1 < startPosSecondArr && pos2 - startPosSecondArr < lengthSecondArr) {
            if (arr[pos1] <= arr[pos2]) {
                additionalArr[iAdditionalArr++] = arr[pos1++];
            } else {
                additionalArr[iAdditionalArr++] = arr[pos2++];
            }
        }

        while (pos1 < startPosSecondArr) {
            additionalArr[iAdditionalArr++] = arr[pos1++];
        }

        while (pos2 - startPosSecondArr < lengthSecondArr) {
            additionalArr[iAdditionalArr++] = arr[pos2++];
        }

        for (int i = 0; i < iAdditionalArr; ++i) {
            arr[startPosFirstArr + i] = additionalArr[i];
        }
    }

    private void mSort(int startPointer, int endPointer) {
        int mid = (endPointer + startPointer) / 2;

        if (startPointer < endPointer) {
            mSort(startPointer, mid);
            mSort(mid + 1, endPointer);
            merge(startPointer, mid + 1, endPointer - mid);
        }
    }

    public int[] mergeSort() {
        mSort(startPointer, endPointer);
        return arr;
    }
}
