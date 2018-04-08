package com.smartzhe.dwr;

/**
 * Created by luohuahua on 18/4/5.
 */
public  class ProgressResult{
    private boolean isEnd;
    private int count;
    private int total;
    public ProgressResult(boolean isEnd,int count,int total){
        this.isEnd = isEnd;
        this.count = count;
        this.total = total;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
