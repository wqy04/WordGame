package GameMenu.util;


import java.util.Arrays;
import java.util.Stack;

public class UndoManager {
    //保存当前关卡的实时位置，用于
    private Stack<int[][]> mapStored;
    public UndoManager() {
        //初始化
        mapStored = new Stack<>();

    }
    //执行记录未移动时的map数组
    public void saveHistoryToStorage(int[][] originalMap) {
        int[][] copiedMap = Arrays.stream(originalMap)
                .map(int[]::clone)
                .toArray(int[][]::new);
        mapStored.push(copiedMap);

    }

    //获取栈顶元素，用于undo操作
    public int[][] getPreviousMap() {
        if (!mapStored.isEmpty()) {
            return mapStored.peek();
        }
        return null;
    }

    public Stack<int[][]> getMapStored() {
        return mapStored;
    }

}
