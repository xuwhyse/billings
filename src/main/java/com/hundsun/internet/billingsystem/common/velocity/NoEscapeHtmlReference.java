package com.hundsun.internet.billingsystem.common.velocity;

import org.apache.oro.text.perl.MalformedPerl5PatternException;
import org.apache.oro.text.perl.Perl5Util;
import org.apache.velocity.app.event.implement.EscapeHtmlReference;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.StringUtils;

/**
 * 自定义关键字开头进行html转译.
 * @author: xunmin
 * @since: 2014-8-12 上午9:20:12
 * @history:
 */
public class NoEscapeHtmlReference extends EscapeHtmlReference {

    private Perl5Util perl = new Perl5Util();

    private RuntimeServices rs;

    private String matchRegExp = "HTML";
    public String myMatch = "HTML";

    @Override
    protected String escape(Object text) {
        return escapeHtml(text);
    }

    private static String escapeHtml(Object value) {
        if (value == null)
            return null;

        if (value instanceof String) {
            String result = value.toString();
            if (result.equals(""))
                return "";
            // "'<>&
            result = result.replaceAll("&", "&amp;").replaceAll(">", "&gt;")
                .replaceAll("<", "&lt;").replaceAll("\"", "&quot;");
            return result;
        } else {
            return value.toString();
        }
    }

    // ============================================
    @Override
    public Object referenceInsert(String reference, Object value) {
        if (value == null) {
            return value;
        }
//        if (matchRegExp == null) {
//            return escape(value);
//        }
        boolean flag = reference.contains(myMatch);//!perl.match(matchRegExp, reference);
        if (flag) {
            return escape(value);
        } else {
            return value;
        }
    }

    public void setRuntimeServices(RuntimeServices rs) {
        this.rs = rs;

        /**
         * Get the regular expression pattern.
         */
        matchRegExp = StringUtils.nullTrim(rs.getConfiguration().getString(
            getMatchAttribute()));
        if ((matchRegExp != null) && (matchRegExp.length() == 0)) {
            matchRegExp = null;
        }

        /**
         * Test the regular expression for a well formed pattern
         */
        if (matchRegExp != null) {
            try {
                perl.match(matchRegExp, "");
            } catch (MalformedPerl5PatternException E) {
                rs.getLog().error(
                    "Invalid regular expression '" + matchRegExp
                            + "'.  No escaping will be performed.", E);
                matchRegExp = null;
            }
        }

    }

    protected RuntimeServices getRuntimeServices() {
        return rs;
    }

}
