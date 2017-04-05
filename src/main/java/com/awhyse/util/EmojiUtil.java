package com.awhyse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by whyse
 * on 2017/3/17 18:40
 */
public class EmojiUtil {
    static Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;

    public static String filterEmoji(String source) {
        if(source != null)
        {
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("***");
                return source ;
            }
            return source;
        }
        return source;
    }

    public static void main(String[] args) {
        String name = "\uD83D\uDE04";
        String str = filterEmoji(name);
        System.err.print(str);
    }
}
