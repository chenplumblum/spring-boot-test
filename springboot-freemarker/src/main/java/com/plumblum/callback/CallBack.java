package com.plumblum.callback;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/12/16 17:37
 */
public class CallBack {
    private Callback mCallback;

    public CallBack(Callback callback) {
        this.mCallback = callback;
    }

    public void doThing() {
        mCallback.before();
        System.out.println("hello chenyu");
        mCallback.after();
    }


}

interface Callback {
    public void before();
    public void after();
}
