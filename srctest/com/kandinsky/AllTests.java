package com.kandinsky;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.kandinsky.conn.AllConnTests;
import com.kandinsky.objects.AllObjectTests;

@RunWith(Suite.class)
@SuiteClasses({AllObjectTests.class, AllConnTests.class})
public class AllTests {

}
