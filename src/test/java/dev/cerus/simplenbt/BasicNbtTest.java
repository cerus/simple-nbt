package dev.cerus.simplenbt;

import dev.cerus.simplenbt.tag.SimpleNbtUtil;
import dev.cerus.simplenbt.tag.TagByte;
import dev.cerus.simplenbt.tag.TagByteArray;
import dev.cerus.simplenbt.tag.TagCompound;
import dev.cerus.simplenbt.tag.TagDouble;
import dev.cerus.simplenbt.tag.TagFloat;
import dev.cerus.simplenbt.tag.TagInt;
import dev.cerus.simplenbt.tag.TagIntArray;
import dev.cerus.simplenbt.tag.TagList;
import dev.cerus.simplenbt.tag.TagLong;
import dev.cerus.simplenbt.tag.TagLongArray;
import dev.cerus.simplenbt.tag.TagShort;
import dev.cerus.simplenbt.tag.TagString;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class BasicNbtTest {

    @Test
    public void testBasicNbt() throws IOException {
        final TagCompound rootTag = TagCompound.createRootTag();
        rootTag.set(new TagByte("SomeByte", (byte) 1));
        rootTag.set(new TagByteArray("SomeByteArray", new byte[] {0, 1, 2, 3, 45, 36, 12}));
        rootTag.set(TagCompound.createTag("EmptyCompound"));

        final TagCompound firstNested = TagCompound.createTag("FirstNested");
        firstNested.set(new TagString("SomeString", "Wassup"));
        rootTag.set(firstNested);

        rootTag.set(new TagDouble("SomeDouble", 123.45d));
        rootTag.set(new TagFloat("SomeFloat", -123.45f));
        rootTag.set(new TagInt("SomeInt", 69));
        rootTag.set(new TagIntArray("SomeIntArray", new int[] {0, 4235, 435123, 465, 12, 7, 645, 23, -123}));
        rootTag.set(new TagList("SomeStringList", Arrays.asList(
                new TagString("", "Never gonna give you up"),
                new TagString("", "Never gonna let you down"),
                new TagString("", "Never gonna run around and desert you")
        ), 8));
        rootTag.set(new TagLong("SomeLong", 1234567890L));
        rootTag.set(new TagLongArray("SomeLongArray", new long[] {34573423, 456766435, 677568756, 7687}));
        rootTag.set(new TagShort("SomeShort", (short) 187));
        final String stringifiedNbt = rootTag.stringify();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        SimpleNbtUtil.writeTag(rootTag, out, false);
        final byte[] bytes = out.toByteArray();

        final TagCompound readCompound = SimpleNbtUtil.readCompound(new ByteArrayInputStream(bytes));
        Assert.assertEquals(stringifiedNbt, readCompound.stringify());
    }

}
