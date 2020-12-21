package com.plumblum.callback;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/12/16 17:38
 */
public class TestCallBack implements Callback{
    public CallBack mCallback;

    public TestCallBack() {
        mCallback = new CallBack(this);
    }

    @Override
    public void before() {
        System.out.println("hello before");
    }

    @Override
    public void after() {
        System.out.println("hello before");
    }

    public void doThing() {
        mCallback.doThing();
    }

    public static void main(String[] args) {
        new TestCallBack().doThing();
    }

}
