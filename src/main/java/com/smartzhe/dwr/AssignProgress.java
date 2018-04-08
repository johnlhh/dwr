package com.smartzhe.dwr;

/**
 * Created by luohuahua on 18/4/8.
 */
public class AssignProgress extends Progress {

    public static int count = 0;

    @Override
    public int doGetCount() {
        count++;
        if(count  == 101){
            count = 0;
        }

        return count;
    }

    @Override
    public int doGetTotal() {
        return 100;
    }
}
