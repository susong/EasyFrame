package com.dream.data;

import android.test.ApplicationTestCase;

import com.dream.library.AbApplication;
import com.orhanobut.logger.AbLog;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<AbApplication> {
    public ApplicationTest() {
        super(AbApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        AbLog.d("setUp");
//        Api.getImagesList("美女", 1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        AbLog.d("tearDown");
    }
}