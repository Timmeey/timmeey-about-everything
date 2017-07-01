package de.timmeey.blog.singleStatementUnitTests;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.junit.*;

public class IterableAsListTest {

    List<Integer> list;
    final int num = 345;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(num);
    }

    @Test
    public void elementAtIndexTest() throws Exception {
        MatcherAssert.assertThat(
            "Can't convert an iterable to a list",
            new IterableAsList<>(list
            ).get(3).equals(num)
        );
    }

    @Test
    public void changedUnderlying() {
        int newNum = num + 1;
        IterableAsList<Integer> underTest = new IterableAsList<>(list);
        //change something in the underlying list before IterableAsList prefetched anything
        list.add(3, newNum);
        //Assertion will suceed since prefetching is only done on the first get() call
        //therefore it will reflect the change in the underlying list before this point
        MatcherAssert.assertThat("Returned wrong element",
                                 underTest.get(3).equals(newNum)
        );
    }

    @Test
    public void changedUnderlyingAfterCallingTheList() {
        //Initialize the IterableAsList as always
        IterableAsList<Integer> underTest = new IterableAsList<>(list);
        //Call get on it, so it prefetches everything
        MatcherAssert.assertThat("Returned wrong element",
                                 underTest.get(3).equals(num)
        );

        //now change something in the underlying list
        int newNum = num + 1;
        //assert will fail since IterableAsList prefetched everything before we replaced the value
        list.add(3, newNum);
        MatcherAssert.assertThat("Returned wrong element",
                                 underTest.get(3).equals(newNum)
        );
    }
}