package examples;

import de.cerus.simplenbt.tag.SimpleNbtUtil;
import de.cerus.simplenbt.tag.TagCompound;
import java.io.File;
import java.io.IOException;

public class ReadFileExample {

    public static void main(final String[] args) throws IOException {
        final TagCompound tagCompound = SimpleNbtUtil.readCompressedFile(new File(String.join(" ", args)));
        System.out.println("Read tag compound with " + tagCompound.getValue().size() + " items");
    }

}
