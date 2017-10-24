package io.github.shahrivari.kipmap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class BytesBuffTest {
    @Test
    public void testBytesAppend() throws IOException {
        BytesBuff buffer = new BytesBuff(0, 1.5f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            byte[] bytes = new byte[random.nextInt(4096)];
            random.nextBytes(bytes);
            buffer.append(bytes);
            baos.write(bytes);
            if (random.nextBoolean())
                Assertions.assertThat(buffer.toBytes()).isEqualTo(baos.toByteArray());
        }
        Assertions.assertThat(buffer.toBytes()).isEqualTo(baos.toByteArray());
    }

    @Test
    public void testStringAppend() throws IOException {
        BytesBuff buffer = new BytesBuff();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String s = Long.toBinaryString(random.nextLong());
            buffer.append(s);
            baos.write(s.getBytes(Charset.forName("UTF8")));
            if (random.nextBoolean())
                Assertions.assertThat(buffer.toBytes()).isEqualTo(baos.toByteArray());
        }
        Assertions.assertThat(buffer.toBytes()).isEqualTo(baos.toByteArray());
    }


}
