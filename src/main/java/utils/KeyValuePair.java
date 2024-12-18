package utils;

import net.dv8tion.jda.api.entities.Member;

public class KeyValuePair {
    private Member key;
    private Object value;

    public KeyValuePair(Member key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Member getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
