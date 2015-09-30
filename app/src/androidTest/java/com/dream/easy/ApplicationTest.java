package com.dream.easy;

import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<App> {
    public ApplicationTest() {
        super(App.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @LargeTest
    public void testVolley() {
//        Api.getImagesList("美女", 1);
    }
}