package examples;

import de.cerus.simplenbt.tag.SimpleNbtUtil;
import de.cerus.simplenbt.tag.Tag;
import de.cerus.simplenbt.tag.TagCompound;
import de.cerus.simplenbt.tag.TagRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CustomTagExample {

    public static void main(final String[] args) throws IOException {
        TagRegistry.registerTag(CustomTag.ID, CustomTag.class);

        final boolean read = args[0].equalsIgnoreCase("read");
        final File file = new File(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));

        if (read) {
            final TagCompound compound = SimpleNbtUtil.readFile(file);
            final CustomTag tag = compound.get("MyCoolTag");
            System.out.println("Read custom tag: " + tag.getName() + " - " + tag.getValue().name());
        } else {
            // Generate pseudo random enum constant
            final int id = ThreadLocalRandom.current().nextInt(1, 4);
            SomeEnum someEnum = null;

            for (final SomeEnum value : SomeEnum.values()) {
                if (value.id == id) {
                    someEnum = value;
                    break;
                }
            }

            System.out.println("Generated " + someEnum.name());

            final TagCompound compound = TagCompound.createRootTag();
            compound.set(new CustomTag("MyCoolTag", someEnum));

            SimpleNbtUtil.writeTag(compound, file);
        }
    }

    public enum SomeEnum {
        CONST_1(1), CONST_2(2), CONST_3(3);

        private final int id;

        SomeEnum(final int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    public static class CustomTag extends Tag<SomeEnum> {

        public static final int ID = TagRegistry.getNextFreeId();

        CustomTag(final InputStream inputStream, final boolean parseName) throws IOException {
            super(inputStream, parseName);
        }

        public CustomTag(final String name, final SomeEnum value) {
            super(name, value);
        }

        @Override
        protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
            if (parseName) {
                this.name = this.readName(inputStream);
            }

            final byte[] bytes = new byte[4];
            inputStream.read(bytes, 0, 4);
            final int id = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getInt();

            for (final SomeEnum someEnum : SomeEnum.values()) {
                if (someEnum.id == id) {
                    this.value = someEnum;
                    break;
                }
            }
        }

        @Override
        protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
            super.write(outputStream, withName, writeId);

            outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(this.value.id).array());
        }

        @Override
        public int getId() {
            return ID;
        }
    }

}
