package dev.lowdad.libs.redisclient;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 二进制set get设置参数
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class BitOpParams {

    private static final String u1 = "u1";
    private static final String X1 = "1";
    private static final String X0 = "0";

    static enum Op {
        set, get
    }

    static class Builder {
        private final Op op;
        private List<String> args = new ArrayList<>();
        public Builder(Op op) {
            this.op = op;
        }

        public Builder offset(int offset) {
            this.args.add(op.name());
            this.args.add(u1);
            this.args.add(String.valueOf(offset));
            return this;
        }

        public Builder value(boolean value) {
            if (value) {
                this.args.add(X1);
            } else {
                this.args.add(X0);
            }
            return this;
        }

        public String[] build() {
            return this.args.toArray(new String[]{});
        }
    }
}
