package io.github.shahrivari.kipmap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrListTest {

    private List<String> randomList(int size) {
        List<String> strs = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++)
            strs.add(Long.toString(random.nextLong()));
        return strs;
    }

    @Test
    public void testAdd() {
        List<String> strs = randomList(1000);
        StrList strList = new StrList();
        for (String str : strs)
            strList.add(str);
        for (int i = 0; i < strs.size(); i++)
            Assertions.assertThat(strs.get(i)).as("testing: %d", i).isEqualTo(strList.get(i));
        Assertions.assertThat(strList).hasSameSizeAs(strs);
    }

    @Test
    public void testClear() {
        List<String> strs = randomList(1000);
        StrList strList = new StrList(strs);
        strList.clear();
        Assertions.assertThat(strList.size()).isZero();
    }

    @Test
    public void testIndexOf() {
        List<String> strs = randomList(1000);
        StrList strList = new StrList(strs);
        Random random = new Random();
        for (int i = 0; i < strs.size(); i++) {
            String randStr = strs.get(random.nextInt(strs.size()));
            Assertions.assertThat(strList.indexOf(randStr)).isEqualTo(strs.indexOf(randStr));
            Assertions.assertThat(strList.lastIndexOf(randStr)).isEqualTo(strs.lastIndexOf(randStr));
        }
    }

}
