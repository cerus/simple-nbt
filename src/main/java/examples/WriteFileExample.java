package examples;

import de.cerus.simplenbt.tag.SimpleNbtUtil;
import de.cerus.simplenbt.tag.TagByte;
import de.cerus.simplenbt.tag.TagCompound;
import de.cerus.simplenbt.tag.TagInt;
import de.cerus.simplenbt.tag.TagList;
import de.cerus.simplenbt.tag.TagString;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class WriteFileExample {

    public static void main(final String[] args) throws IOException {
        final File file = new File(String.join(" ", args));
        file.createNewFile();

        final TagCompound tagCompound = TagCompound.createRootTag();
        tagCompound.set(new TagInt("IntTag", 123));
        tagCompound.set(new TagString("StringTag", "hello world! :D"));

        final TagCompound nestedCompound = TagCompound.createTag("NestedCompound");
        nestedCompound.set(new TagByte("ByteTag", (byte) 1));
        final TagCompound nestedCompound2 = TagCompound.createTag("NestedCompound2");
        nestedCompound2.set(new TagByte("ByteTag2", (byte) 1));
        final TagCompound nestedCompound3 = TagCompound.createTag("NestedCompound3");
        nestedCompound3.set(new TagByte("ByteTag3", (byte) 1));
        nestedCompound3.set(new TagList("SomeList", Arrays.asList(
                new TagInt("", 1),
                new TagInt("", 44),
                new TagInt("", -456)
        ), 3));

        nestedCompound2.set(nestedCompound3);
        tagCompound.set(nestedCompound);
        tagCompound.set(nestedCompound2);

        SimpleNbtUtil.writeAndCompressTag(tagCompound, file);
    }

}
