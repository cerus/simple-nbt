package dev.cerus.simplenbt;

import dev.cerus.simplenbt.tag.SimpleNbtUtil;
import dev.cerus.simplenbt.tag.Tag;
import dev.cerus.simplenbt.tag.TagCompound;
import dev.cerus.simplenbt.tag.TagInt;
import dev.cerus.simplenbt.tag.TagList;
import dev.cerus.simplenbt.tag.TagReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Test {

    public static void main(final String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(new File("/home/max/Pictures/message.txt").toPath());
        final String str = new String(bytes).replace("\n", "").trim();

        bytes = Files.readAllBytes(new File("/home/max/Downloads/message.txt").toPath());
        final String str2 = new String(bytes).replace("\n", "").trim();

        final byte[] decode1 = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        final byte[] decode2 = Base64.getDecoder().decode(str2.getBytes(StandardCharsets.UTF_8));

        ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8)));
        final TagList t = (TagList) TagReader.readNextTagExceptionally(in, true);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        SimpleNbtUtil.writeTag(t, byteArrayOutputStream, true);
        final byte[] out = byteArrayOutputStream.toByteArray();

        System.out.println(Arrays.equals(decode1, out));

        System.out.println("char:");
        System.out.println((char) (byte) 83);

        in = new ByteArrayInputStream(out);
        final Tag<?> tt = TagReader.readNextTagExceptionally(in, true);

        System.out.println(tt.stringify().equals(t.stringify()));

        if (true) {
            return;
        }

        System.out.println(Arrays.toString(decode1));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Arrays.toString(decode2));

        System.out.println(str.equals(str2));
        System.out.println(Arrays.equals(decode1, decode2));

        System.out.println("LEN " + decode1.length + " " + decode2.length);
        int i = 0;
        for (int i1 = 0; i1 < decode1.length; i1++) {
            if (decode1[i1] != decode2[i1]) {
                System.out.println(">> " + i + " " + decode1[i1] + " " + decode2[i1]);
            }
            i++;
        }

        System.out.println(Arrays.toString(Arrays.copyOfRange(decode1, i - 10, i + 10)));
        System.out.println();
        System.out.println(Arrays.toString(Arrays.copyOfRange(decode2, i - 10, i + 10)));

        if (true) {
            return;
        }

        final List<Tag<?>> list = new ArrayList<>();
        Tag<?> tag;
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8)));
        final TagList tagList = (TagList) TagReader.readNextTagExceptionally(inputStream, true);

        final TagCompound compound = (TagCompound) tagList.getValue().get(0);
        compound.set(new TagInt("tag", 0));
        System.out.println(compound.stringify());
    }

}
