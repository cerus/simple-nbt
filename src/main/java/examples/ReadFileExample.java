package examples;

import de.cerus.simplenbt.tag.SimpleNbtUtil;
import de.cerus.simplenbt.tag.Tag;
import de.cerus.simplenbt.tag.TagCompound;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ReadFileExample {

    public static void main(final String[] args) throws IOException {
        final boolean compressed = args[0].equalsIgnoreCase("yes");

        final File file = new File(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
        final TagCompound tagCompound = compressed ? SimpleNbtUtil.readCompressedFile(file) : SimpleNbtUtil.readFile(file);

        System.out.println("Read tag compound with " + tagCompound.getValue().size() + " items");
        System.out.println(tagCompound.stringify());

        print("", tagCompound);
    }

    private static void print(final String before, final TagCompound tagCompound) {
        System.out.println(before + "=== " + (tagCompound.getName() == null || tagCompound.getName().length() == 0
                ? "<ROOT>" : tagCompound.getName()) + " ===");
        for (final Tag<?> tag : tagCompound.getValue()) {
            if (tag instanceof TagCompound) {
                print(before + "    ", (TagCompound) tag);
                continue;
            }

            System.out.println(before + " " + tag.getName() + ": " + tag.stringify());
        }
    }

}
