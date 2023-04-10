package annotations;

import java.util.List;

/**
 * @Author pengwen
 * @DESCRIPTION
 * @Create 2023/3/2
 */
public class Result {
    private int count;
    private List<Integer> list;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public List<Integer> getList() {
        return list;
    }
    public void setList(List<Integer> list) {
        this.list = list;
    }
    @Override
    public String toString() {
        return "Result{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }
}
