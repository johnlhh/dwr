package com.smartzhe.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luohuahua on 18/4/4.
 */
public abstract class Progress implements Runnable {

    private static Map<String, Integer> cache = new ConcurrentHashMap();

    public Progress() {

    }

    //进度条的ID
    private String id;

    //js 方法名称
    private String funName;

    //总的个数
    private int total;

    //最大尝试次数  当查询数 最终小于总数时，即count < total,那么尝试查询5次，如果5次中的count值一样，那么结束
    private static final int DEFAULT_TRY_TIMES = 5;

    //默认间隔时间查询count 200毫秒
    private static final int INTERVAL_INVOKE_DO_GETCOUNT=200;

    private int time = DEFAULT_TRY_TIMES;

    private boolean isEnd = false;


    @Override
    public void run() {
        Integer num = cache.get(id);
        if (num != null) {
            return;
        }
        int count = getCount();
        final int total = getTotal();

        while (doCheck(count,total)) {
            final int len = count;

            if(doCheckDone(count,total)){
                isEnd = true;
            }

            final boolean tresult = isEnd;
            Browser.withAllSessions(new Runnable() {
                public void run() {
                    ScriptSessions.addFunctionCall(funName, id, len, new ProgressResult(tresult, len, total));
                }
            });
            try {
                Thread.sleep(INTERVAL_INVOKE_DO_GETCOUNT);
                int countTemp = getCount();
                if (countTemp == count) {
                    time--;
                    if (time < 0) {
                        isEnd = true;
                    }
                } else {
                    time = DEFAULT_TRY_TIMES;
                    count = countTemp;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("InterruptedException");
            }
        }
        cache.remove(id);
    }

    private boolean doCheck(int count,int total){
        if(isEnd){
            return false;
        }
        return count <= total;

    }

    private boolean doCheckDone(int count,int total){
        return count == total;
    }

    public void doWork(String id, String funName) {
        this.id = id;
        this.funName = funName;
        //进度条耗时一般较长，不采用线程池
        new Thread(this).start();
    }

    public int getCount() {
        cache.put(id, doGetCount());
        return cache.get(id);
    }

    public int getTotal(){
        if(total == 0){
            total = doGetTotal();
        }
        return total;
    }

    public abstract int doGetCount();

    public abstract int doGetTotal();


}
