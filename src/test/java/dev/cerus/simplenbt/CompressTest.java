package dev.cerus.simplenbt;

import dev.cerus.simplenbt.util.GzipUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Assert;
import org.junit.Test;

public class CompressTest {

    @Test
    public void testCompression() throws IOException {
        final String testString = "This is the test string.";
        System.out.println("Test string: " + testString);

        final byte[] compressed = GzipUtil.compress(testString.getBytes(StandardCharsets.UTF_8));
        System.out.println("Compressed: " + new String(compressed, StandardCharsets.UTF_8));

        final byte[] decompressed = GzipUtil.decompress(compressed);
        final String decompressedString = new String(decompressed, StandardCharsets.UTF_8);
        System.out.println("Decompressed: " + decompressedString);

        Assert.assertEquals(testString, decompressedString);
    }

}
