package examples;

import dev.cerus.simplenbt.tag.SimpleNbtUtil;
import dev.cerus.simplenbt.tag.Tag;
import dev.cerus.simplenbt.tag.TagCompound;
import dev.cerus.simplenbt.tag.TagList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Test {

    public static void main(final String[] args) throws IOException {
        final String data = "H4sIAAAAAAAAAN1WzW7TQBCe1ElI0paiqi09Wj6XquoxFyhtKZX4kSg9IRRN7LG98nrXrNcRoeqddwAJiSsSLwA33oIbTwLr/DqOKi7QSjk5u/k88803f24BtOD2Qy7d6FhopvsvMWhC7VRTnLYAYK0C1TMu9VILLI1BC255LE049htQfYYxwccLh95qhU771YXTldxz2lpltOMwjZy5TttHnppjJjxSnAnyJlepViwiHSqZBeHkVnb9LHVRF4Cu5FI5bcdDFXWSTCWcnB1HG7/m8oRSNyQRJZgwUs7l68k/ziVABWqHMhP6QQOWmAdrsWHgKvR12+BJwSg8a4yzh7jNKS7GgITGjtenMbq62GLUSmJsT3GcBaHudHlGRT3qi63HckmP9SnO7aMoKrGy2EqsDsOzoPWCEmTqUKbavPy7CesH2tDsZpqeSo/5xsVgfFTG8W+aJiLF3N1Y9mhvf885S4g8C5rPjXnUTAqDhmWonp+fHplf1fwIFfFz8Fx+/6UBqxMXA4tbZYudNDdZh/pBnIdyX7/7dfJ14wKasHIs3BCFjk0jD2hZwzA3pmGS7zOXkXD7S2DxHocazGFSxqOOlpkbjjCVeUwmuoowYiIYYeowXwyfrrEY8E2GV1XBvTM3FMS8cj00ofpEKmoYpWrQnyc7cvEP2U5cz5Tlt7+6Hsr235rmCFPbyIOc7BP0AtK2/+O7sj1zbcrWp0iTPdZQlMh/vmnyPSnsQr7zhO+WON5gbj9cYw8EufmJ8wOvl8+ByGQ1t23v58NjlpwF9SPMF38+fMyQemzy+4hjkA6O42FZGXb/ncKECAlVOp6Wtxd7GayV1mJhCg79FRfjxmJrsXn1JwJn8YwSW4utxN2rq0IqFMGMFtsLqgXAH+qLVGNYDQAA";
        final byte[] decode = Base64.getDecoder().decode(data);

        final TagCompound tagCompound = SimpleNbtUtil.readCompressedCompound(new ByteArrayInputStream(decode));
        final TagCompound entityTag = tagCompound.get("BlockEntityTag");
        final TagList items = entityTag.get("Items");
        final List<Tag<?>> value = items.getValue();
        for (final Tag<?> tag : new ArrayList<>(value)) {
            final TagCompound compound = (TagCompound) tag;
            if (compound.stringify().contains("Geschenkpapier-Schneider")) {
                System.out.println("REMOVED " + tag.stringify());
                value.remove(tag);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(tagCompound.stringify());
        System.out.println();
        System.out.println();
        System.out.println();

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        SimpleNbtUtil.writeAndCompressTag(tagCompound, byteArrayOutputStream, false);
        System.out.println(Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()
        ));
    }

}
