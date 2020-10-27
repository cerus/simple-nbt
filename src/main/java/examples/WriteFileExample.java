package examples;

import de.cerus.simplenbt.tag.SimpleNbtUtil;
import de.cerus.simplenbt.tag.TagByte;
import de.cerus.simplenbt.tag.TagCompound;
import de.cerus.simplenbt.tag.TagInt;
import de.cerus.simplenbt.tag.TagString;
import java.io.File;
import java.io.IOException;

public class WriteFileExample {

    public static void main(final String[] args) throws IOException {
        final File file = new File(String.join(" ", args));
        file.createNewFile();

        final TagCompound tagCompound = TagCompound.createRootTag();
        tagCompound.set(new TagInt("IntTag", 123));
        tagCompound.set(new TagString("StringTag", "hello world! :D"));

        final TagCompound nestedCompound = TagCompound.createTag("NestedCompound");
        nestedCompound.set(new TagByte("ByteTag", (byte) 1));

        tagCompound.set(nestedCompound);

        SimpleNbtUtil.writeAndCompressTag(tagCompound, file);
    }

}
