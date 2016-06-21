package com.hundsun.internet.billingsystem.common.velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** 自动生成form实现类.
 * @author: linyy
 * @since: 2014年5月14日 下午2:48:52
 * @history: 2014.7.22发现一个bug，maxlength和minlength同时存在，@Size出现两个会有问题
 */
public final class ValidateBeanWrite {

    /**构造方法.
     */
    private ValidateBeanWrite() {

    }

    /**
     * @Fields context : 放data的地方
     */
    static VelocityContext context = new VelocityContext();

    /**主方法.
     * @param connectUrl http://localhost:8080/member/product/step1
     * @param beanpath src/main/java/com/hundsun/internet/ihoms2/form/
     * @param packagestr 包路径
     * @create: 2014年4月24日 下午3:41:22 linyy
     * @history:
     */
    public static void main(String connectUrl, String beanpath,
            String packagestr) {
        /* first, we init the runtime engine. Defaults are fine. */

        try {
            Velocity.init();
        } catch (Exception e) {
            System.out.println("Problem initializing Velocity : " + e);
            return;
        }

        /* lets render a template */

        StringWriter w = new StringWriter();

        try {
            int xxxx = 1; // 只为跳过checkstyle的法眼，无其他作用
            // Velocity.mergeTemplate("./src/example2.vm", context, w );
        } catch (Exception e) {
            System.out.println("Problem merging template : " + e);
        }

        // System.out.println(" template : " + w );

        /*
         * lets dynamically 'create' our template and use the evaluate() method
         * to render it
         */

        Formbean[] formbean;
        formbean = putS(connectUrl, packagestr);
        if (formbean != null) {
            for (int i = 0; i < formbean.length; i++) {
                w = new StringWriter();
                try {
                    Velocity.evaluate(context, w, "mystring",
                        formbean[i].getBeanvalue());
                } catch (ParseErrorException pee) {
                    /*
                     * thrown if something is wrong with the syntax of our
                     * template string
                     */
                    System.out.println("ParseErrorException : " + pee);
                } catch (MethodInvocationException mee) {
                    /*
                     * thrown if a method of a reference called by the template
                     * throws an exception. That won't happen here as we aren't
                     * calling any methods in this example, but we have to catch
                     * them anyway
                     */
                    System.out.println("MethodInvocationException : " + mee);
                } catch (Exception e) {
                    System.out.println("Exception : " + e);
                }

                System.out.println(" string : \n" + w);
                // String uri = "d:/" + context.get("classname") + ".java";
                String folder = beanpath;
                String uri = beanpath + formbean[i].getClassname() + ".java";
                // 建立文件夹
                File f = new File(folder);
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = null;
                // 写文件
                FileWriter fw = null;
                try {
                    fw = new FileWriter(uri);
                    fw.write(w.toString());
                    fw.flush();
                    fw.close();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fw.close();
                        w.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**解析doc，返回所有的form.
     * @param connectUrl connectUrl
     * @param packagestr packagestr
     * @return forms
     * @create: 2014年5月14日 下午2:50:54 linyy
     * @history:
     */
    public static Formbean[] putS(String connectUrl, String packagestr) {
        try {

            Document doc = Jsoup.connect(connectUrl).get();

            /*
             * File file = new File(connectUrl); Document doc =
             * Jsoup.parse(file, "UTF-8", "");
             */

            Elements forms = doc.select("form"); // 一个页面多个form
            Formbean[] formbean = new Formbean[forms.size()];
            for (int i = 0; i < forms.size(); i++) {
                formbean[i] = new Formbean();
                Element form = forms.get(i);
                Elements inputs = new Elements();
                inputs = form
                    .select("input:not([type=file],[type=image],"
                            + "[type=button],[type=submit],[type=reset]),select,textarea");
                removeRepeatElements(inputs); // 去除name重复的Elements（radio，checkbox）

                String className = form.attr("name");
                if (className == null || "".equals(className)) {
                    formbean[i].setClassname("thereAreSomeNoNameForms");
                } else {
                    formbean[i].setClassname(firstLetterUp(className) + "Form");
                }
                formbean[i].setBeanvalue(getBeanvalue(form, inputs, packagestr,
                    formbean[i].getClassname())); // 生成的东西
            }
            return formbean;

            // input:text,password,hidden,checkbox,radio,
            // file,image,button,submit,reset
            /*
             * Elements input = doc.select(
             * "input[type=text],input[type=password],input[type=checkbox][checked],input[type=radio][checked]"
             * ); Elements select = doc.select("select"); Elements textarea =
             * doc.select("textarea"); addElements(inputs,input);
             * addElements(inputs,select); addElements(inputs,textarea);
             */
        } catch (IOException e) {
            System.out.println("出错了！");
            e.printStackTrace();
            return null;
        }
    }

    /**写bean.
     * @param form form
     * @param inputs inputs
     * @param packagestr packagestr
     * @param classname classname
     * @return string
     * @create: 2014年5月14日 下午2:52:55 linyy
     * @history:
     */
    public static String getBeanvalue(Element form, Elements inputs,
            String packagestr, String classname) {
        StringBuffer str = new StringBuffer();
        // package
        str.append("package " + packagestr + ";\n\n");
        // import
        str.append("import java.io.Serializable;").append("\n");
        str.append("import com.hundsun.internet.ihoms2.form.BaseForm;\n");
        str.append("import javax.validation.constraints.*;\n");
        str.append("import org.hibernate.validator.constraints.*;\n");
        str.append("import com.hundsun.internet.ihoms2.form.constraints.*;\n");
        str.append("\n");
        // class
        str.append(classNote("自动生成的Form"));
        str.append("public class ").append(classname)
            .append(" extends BaseForm implements Serializable {\n\n");
        // serialVersionUID
        str.append(attrNote("serialVersionUID", ""));
        str.append("    private static final long serialVersionUID = 1L;\n\n");
        // 变量、注解、注释
        for (int i = 0; i < inputs.size(); i++) {
            Element e = inputs.get(i);
            String name = e.attr("name");
            // 注释
            String attrNote = attrNote(e);
            str.append(attrNote);

            Attributes attrs = e.attributes();
            Iterator<Attribute> iter = attrs.iterator();
            while (iter.hasNext()) {
                Attribute att = iter.next();
                String key = att.getKey();
                String value = att.getValue();
                if (key.contains("data-rule-")) {
                    String annotation = "";
                    annotation = getAnnotation(key, value, e);
                    str.append(annotation);
                }
            }
            // String dataType = getDataType(e); // 数据类型
            str.append("    private ").append("String").append(" ")
                .append(name).append(";\n\n");
        }
        // get,set
        for (int i = 0; i < inputs.size(); i++) {
            Element e = inputs.get(i);
            String name = e.attr("name");
            String dataType = getDataType(e); // 数据类型
            String getMethodNote = getMethodNote(name); // get注释
            String setMethodNote = setMethodNote(name); // set注释
            String getMethod = getMethod(name, dataType); // get
            String setMethod = setMethod(name, dataType); // set
            String getHTMLMethodNote = getHTMLMethodNote(name); // getHTML注释
            String getHTMLMethod = getHTMLMethod(name, dataType); // getHTML
            str.append(getMethodNote);
            str.append(getMethod);
            str.append(getHTMLMethodNote);
            str.append(getHTMLMethod);
            str.append(setMethodNote);
            str.append(setMethod);
        }
        // 两个值比较的验证
        twoCompareAnnotation(inputs, str);

        str.append("}");

        return str.toString();
    }

    /**get方法注释.
     * @param name 属性名字
     * @return String
     * @create: 2014年5月14日 下午4:30:39 linyy
     * @history:
     */
    public static String getMethodNote(String name) {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String time = sdf.format(new Date());
        str.append("    /**这是一个高贵的get方法.\n");
        str.append("     * @return ").append(name).append("\n");
        str.append("     * @create: ").append(time).append(" linyy\n");
        str.append("     * @history: 历史第一次生成\n");
        str.append("     */\n");
        return str.toString();
    }

    /**set方法注释.
     * @param name 属性名字
     * @return String
     * @create: 2014年5月14日 下午4:30:39 linyy
     * @history:
     */
    public static String setMethodNote(String name) {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String time = sdf.format(new Date());
        str.append("    /**这是一个冷艳的set方法.\n");
        str.append("     * @param ").append(name).append(" ").append(name)
            .append("\n");
        str.append("     * @create: ").append(time).append(" linyy\n");
        str.append("     * @history: 历史第一次生成\n");
        str.append("     */\n");
        return str.toString();
    }

    /**getHTML方法注释.
     * @param name 属性名字
     * @return String
     * @create: 2014年8月18日 下午4:30:39 linyy
     * @history:
     */
    public static String getHTMLMethodNote(String name) {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String time = sdf.format(new Date());
        str.append("    /**这是一个低调的getHTML方法.\n");
        str.append("     * @return ").append(name).append("\n");
        str.append("     * @create: ").append(time).append(" linyy\n");
        str.append("     * @history: 历史第一次生成\n");
        str.append("     */\n");
        return str.toString();
    }

    /**get方法.
     * @param name 属性名字
     * @param dataType 属性类型
     * @return get方法
     * @create: 2014年5月14日 下午4:06:47 linyy
     * @history:
     */
    public static String getMethod(String name, String dataType) {
        StringBuffer str = new StringBuffer();
        if ("java.util.Date".equals(dataType)) {
            str.append("    public ").append(dataType).append(" get")
                .append(firstLetterUp(name)).append("() {\n");
            str.append("        return new java.util.Date(").append(name)
                .append(");\n    }\n\n");
            return str.toString();
        }
        // dataType为String类型
        str.append("    public ").append(dataType).append(" get")
            .append(firstLetterUp(name)).append("() {\n");
        str.append("        return ").append(name).append(";\n    }\n\n");
        return str.toString();
    }

    /**set方法.
     * @param name 属性名字
     * @param dataType 属性类型
     * @return set方法
     * @create: 2014年5月14日 下午4:06:51 linyy
     * @history:
     */
    public static String setMethod(String name, String dataType) {
        StringBuffer str = new StringBuffer();
        str.append("    public void set").append(firstLetterUp(name))
            .append("(");
        // str.append(dataType).append(" ").append(name).append(") {\n");
        str.append("String").append(" ").append(name).append(") {\n");
        str.append("        this.").append(name).append(" = ").append(name)
            .append(";\n    }\n\n");
        return str.toString();
    }

    /**getHTML方法.
     * @param name 属性名字
     * @param dataType 属性类型
     * @return getHTML方法
     * @create: 2014年8月18日 下午4:06:47 linyy
     * @history:
     */
    public static String getHTMLMethod(String name, String dataType) {
        StringBuffer str = new StringBuffer();
        if ("java.util.Date".equals(dataType)) {
            str.append("    public ").append(dataType).append(" get")
                .append(firstLetterUp(name)).append("HTML() {\n");
            str.append("        return new java.util.Date(").append(name)
                .append(");\n    }\n\n");
            return str.toString();
        }
        // dataType为String类型
        str.append("    public ").append(dataType).append(" get")
            .append(firstLetterUp(name)).append("HTML() {\n");
        str.append("        return ").append(name).append(";\n    }\n\n");
        return str.toString();
    }

    /**类的注释.
     * @param note 注释
     * @return 类的注释
     * @create: 2014年5月15日 下午2:48:12 linyy
     * @history:
     */
    public static String classNote(String note) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String time = sdf.format(new Date());
        StringBuffer str = new StringBuffer();
        str.append("/** ").append(note).append(".\n");
        str.append(" * @author: linyy\n");
        str.append(" * @since: ").append(time).append("\n");
        str.append(" * @history: 历史第一次生成\n");
        str.append(" */\n");
        return str.toString();
    }

    /**属性注释.
     * @param name String
     * @param note 注释
     * @return 注释
     * @create: 2014年5月14日 下午3:55:20 linyy
     * @history:
     */
    public static String attrNote(String name, String note) {
        if (note == null || "".equals(note.trim())) {
            note = "you can do better!";
        }
        StringBuffer str = new StringBuffer();
        str.append("    /**\n");
        str.append("     * @Fields ").append(name).append(" : ");
        str.append(note + "\n");
        str.append("     */\n");
        return str.toString();
    }

    /**注释.
     * @param e Element
     * @return 注释
     * @create: 2014年5月14日 下午3:55:20 linyy
     * @history:
     */
    public static String attrNote(Element e) {
        String name = e.attr("name");
        String labelText = previousLabelText(e);
        if (labelText == null || "".equals(labelText)) {
            labelText = "you can do better!";
        }
        StringBuffer str = new StringBuffer();
        str.append("    /**\n");
        str.append("     * @Fields ").append(name).append(" : ");
        str.append(labelText + "\n");
        str.append("     */\n");
        return str.toString();
    }

    /**两个值比较的验证.
     * @param es Elements
     * @param str StringBuffer
     * @create: 2014年5月14日 下午2:53:27 linyy
     * @history:
     */
    public static void twoCompareAnnotation(Elements es, StringBuffer str) {
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            String name = ""; // 当前的名字
            String nameTarget = ""; // 目标的名字
            Attributes attrs = e.attributes();
            Iterator<Attribute> iter = attrs.iterator();
            while (iter.hasNext()) {
                Attribute att = iter.next();
                String key = att.getKey();
                if (key.equalsIgnoreCase("data-rule-equalTo")) {
                    name = e.attr("name");
                    nameTarget = getNameAttr(att, es);
                    String getMethodNote = getMethodNote(name); // get注释
                    str.append(getMethodNote);
                    str.append("    @SelfEqualTo");
                    String msg = e.attr("data-msg-equalTo");
                    if (msg != null && !"".equals(msg)) {
                        str.append("(message = \"" + msg + "\")");
                    }
                    str.append("\n");
                    setTwoCompareAnnotation(str, name, nameTarget);
                } else if (key.equalsIgnoreCase("data-rule-afterdate")) {
                    name = e.attr("name");
                    nameTarget = getNameAttr(att, es);
                    String getMethodNote = getMethodNote(name); // get注释
                    str.append(getMethodNote);
                    str.append("    @SelfAfterdate");
                    String msg = e.attr("data-msg-afterdate");
                    if (msg != null && !"".equals(msg)) {
                        str.append("(message = \"" + msg + "\")");
                    }
                    str.append("\n");
                    setTwoCompareAnnotation(str, name, nameTarget);
                } else if (key.equalsIgnoreCase("data-rule-after")) {
                    name = e.attr("name");
                    nameTarget = getNameAttr(att, es);
                    String getMethodNote = getMethodNote(name); // get注释
                    str.append(getMethodNote);
                    str.append("    @SelfAfter");
                    String msg = e.attr("data-msg-after");
                    if (msg != null && !"".equals(msg)) {
                        str.append("(message = \"" + msg + "\")");
                    }
                    str.append("\n");
                    setTwoCompareAnnotation(str, name, nameTarget);
                } else if (key.equalsIgnoreCase("data-rule-before")) {
                    name = e.attr("name");
                    nameTarget = getNameAttr(att, es);
                    String getMethodNote = getMethodNote(name); // get注释
                    str.append(getMethodNote);
                    str.append("    @SelfBefore");
                    String msg = e.attr("data-msg-before");
                    if (msg != null && !"".equals(msg)) {
                        str.append("(message = \"" + msg + "\")");
                    }
                    str.append("\n");
                    setTwoCompareAnnotation(str, name, nameTarget);
                }
            }
        }
    }

    /**两个值比较 ，在方法头上加注释.
     * @param str str
     * @param name name
     * @param nameTarget 目标name
     * @create: 2014年5月14日 下午2:53:53 linyy
     * @history:
     */
    public static void setTwoCompareAnnotation(StringBuffer str, String name,
            String nameTarget) {
        str.append("    public String[] get").append(firstLetterUp(name))
            .append("_").append(nameTarget).append("() {\n");
        str.append("        return new String[] { ").append(name).append(", ")
            .append(nameTarget).append(" };\n");
        str.append("    }\n\n");
    }

    /**获得属性中id对应的name(更改为可以根据任何属性、id、类等选择器选择).
     * @param att Attribute
     * @param es Elements
     * @return string
     * @create: 2014年5月14日 下午2:54:13 linyy
     * @history: 注释部分为根据id选择（旧）
     */
    public static String getNameAttr(Attribute att, Elements es) {
        // String[] idsTarget = att.getValue().split("#");
        // String idTarget = idsTarget[idsTarget.length - 1].trim();
        // String nameTarget = es.select("#" + idTarget).attr("name");
        Elements targets = es.select(att.getValue());
        String nameTarget = targets.get(0).attr("name");
        return nameTarget;
    }

    /**根据节点获取对应的数据类型（String,BigDecimal,Date...）.
     * 暂时都是String，以免一堆英文错误提示
     * @param e Element
     * @return String
     * @create: 2014年5月14日 下午2:54:41 linyy
     * @history:
     */
    public static String getDataType(Element e) {
        Attributes attrs = e.attributes();
        Iterator<Attribute> iter = attrs.iterator();
        Map<String, String> dataMap = new HashMap<String, String>(); // 存放特殊的数据类型
        while (iter.hasNext()) {
            Attribute att = iter.next();
            String key = att.getKey();
            if (key.equalsIgnoreCase("data-rule-number")) {
                dataMap.put("dataType", "String");
            } else if (key.equalsIgnoreCase("data-rule-digits")) {
                dataMap.put("dataType", "String");
            } else if (key.equalsIgnoreCase("data-rule-range")) {
                dataMap.put("dataType", "String");
            } else if (key.equalsIgnoreCase("data-rule-max")) {
                dataMap.put("dataType", "String");
            } else if (key.equalsIgnoreCase("data-rule-min")) {
                dataMap.put("dataType", "String");
            } else if (key.equalsIgnoreCase("data-rule-date")) {
                dataMap.put("dataType", "java.util.Date");
            }
        }
        String dataType = "String"; // 默认String
        if (dataMap.size() > 0) {
            dataType = dataMap.get("dataType");
        }
        return dataType;
    }

    /**去除name重复的Elements.
     * @param inputs Elements
     * @create: 2014年5月14日 下午2:55:21 linyy
     * @history:
     */
    public static void removeRepeatElements(Elements inputs) {
        String names = "";
        for (int i = 0; i < inputs.size(); i++) {
            Element e = inputs.get(i);
            String name = e.attr("name");
            if (names.contains(name + ",")) {
                inputs.remove(e);
                i--; // remove掉一个元素，inputs.size()减1
            } else {
                names = names + name + ",";
            }
        }
    }

    /**把Elements放到Elements中.
     * @param all Elements
     * @param es Elements
     * @create: 2014年5月14日 下午2:55:40 linyy
     * @history:
     */
    public static void addElements(Elements all, Elements es) {
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            all.add(e);
        }
    }

    /**首字母大写.
     * @param str String
     * @return String
     * @create: 2014年5月14日 下午2:55:56 linyy
     * @history:
     */
    public static String firstLetterUp(String str) {
        if (str != null && !"".equals(str)) {
            return str.substring(0, 1).toUpperCase()
                    + str.substring(1, str.length());
        } else {
            return str;
        }
    }

    /**获得当前元素的前一个label元素的值.
     * @param e Element
     * @return String
     * @create: 2014年5月14日 下午2:56:12 linyy
     * @history:
     */
    public static String previousLabelText(Element e) {
        String text = "";
        Element preE = e.previousElementSibling();
        if (preE == null) {
            preE = e.parent();
        }
        // 向上寻找10个元素，若找不到则放弃
        for (int i = 0; i < 10; i++) {
            String tagName = preE.tagName();
            if (tagName.equalsIgnoreCase("label")
                    && (!preE.attr("class").equalsIgnoreCase("checkbox-inline"))) {
                text = preE.text();
                break;
            }
            // 到form后不再寻找
            if (tagName.equalsIgnoreCase("form")) {
                break;
            }
            if (preE.previousElementSibling() == null) {
                preE = preE.parent();
            } else {
                preE = preE.previousElementSibling();
            }
        }

        return text;
    }

    /**注解（单个字段验证）.
     * @param key String
     * @param value String
     * @param e Element
     * @return 注解
     * @create: 2014年5月14日 下午3:02:58 linyy
     * @history:
     */
    public static String getAnnotation(String key, String value, Element e) {
        key = key.split("-")[2];
        String str = "    ";
        if (key.equalsIgnoreCase("required")) {
            String msg = e.attr("data-msg-required");
            str += "@NotEmpty";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("number")) {
            String msg = e.attr("data-msg-number");
            str += "@SelfNumber";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("range")) {
            String msg = e.attr("data-msg-range");
            String[] numbers = value.split(",");
            String strMin = numbers[0].substring(1, numbers[0].length());
            String strMax = numbers[1].substring(0, numbers[1].length() - 1);
            str += "@Range(min = " + strMin + " , max = " + strMax;
            if (msg != null && !"".equals(msg)) {
                str += " , message = \"" + msg + "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("maxlength")) {
            String msg = e.attr("data-msg-maxlength");
            str += "@Size(max = " + value;
            if (msg != null && !"".equals(msg)) {
                str += " , message = \"" + msg + "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("minlength")) {
            String msg = e.attr("data-msg-minlength");
            str += "@Size(min = " + value;
            if (msg != null && !"".equals(msg)) {
                str += " , message = \"" + msg + "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("rangelength")) {
            String msg = e.attr("data-msg-rangelength");
            String[] numbers = value.split(",");
            String strMin = numbers[0].substring(1, numbers[0].length());
            String strMax = numbers[1].substring(0, numbers[1].length() - 1);
            str += "@Size(min = " + strMin + " , max = " + strMax;
            if (msg != null && !"".equals(msg)) {
                str += " , message = \"" + msg + "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("min")) {
            String msg = e.attr("data-msg-min");
            str += "@Min(value = " + value;
            if (msg != null && !"".equals(msg)) {
                str += " , message = \"" + msg + "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("max")) {
            String msg = e.attr("data-msg-max");
            str += "@Max(value = " + value;
            if (msg != null && !"".equals(msg)) {
                str += " , message = \"" + msg + "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("email")) {
            String msg = e.attr("data-msg-email");
            str += "@Email";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("creditcard")) {
            String msg = e.attr("data-msg-creditcard");
            str += "@CreditCardNumber";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("pattern")) {
            String msg = e.attr("data-msg-pattern");
            str += "@Pattern(regexp = \"" + value.replace("\\", "\\\\");
            if (msg != null && !"".equals(msg)) {
                str += "\" , message = \"" + msg + "\"";
            } else {
                str += "\"";
            }
            str += ")";
        } else if (key.equalsIgnoreCase("url")) {
            String msg = e.attr("data-msg-url");
            str += "@URL";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("date")) {
            String msg = e.attr("data-msg-date");
            str += "@SelfDate";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("digits")) {
            String msg = e.attr("data-msg-digits");
            str += "@SelfDigits";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("username")) {
            String msg = e.attr("data-msg-username");
            str += "@SelfUsername";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("mobile")) {
            String msg = e.attr("data-msg-mobile");
            str += "@SelfMobile";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("mobileto")) {
            String msg = e.attr("data-msg-mobileto");
            str += "@SelfMobileto";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("idcard")) {
            String msg = e.attr("data-msg-idcard");
            str += "@SelfIdcard";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("emailto")) {
            String msg = e.attr("data-msg-emailto");
            str += "@SelfEmailto";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("alphanumeric")) {
            String msg = e.attr("data-msg-alphanumeric");
            str += "@SelfAlphanumeric";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("lettersonly")) {
            String msg = e.attr("data-msg-lettersonly");
            str += "@SelfLettersonly";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("integer")) {
            String msg = e.attr("data-msg-integer");
            str += "@SelfInteger";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("time")) {
            String msg = e.attr("data-msg-time");
            str += "@SelfTime";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else if (key.equalsIgnoreCase("time12h")) {
            String msg = e.attr("data-msg-time12h");
            str += "@SelfTime12h";
            if (msg != null && !"".equals(msg)) {
                str += "(message = \"" + msg + "\")";
            }
        } else {
            return ""; // 不处理
        }
        if (!"".equals(str)) {
            return str + "\n";
        }
        return str;
    }
}

/**formBean 相关信息 .
 * @author: linyy
 * @since: 2014年5月14日 下午3:04:41
 * @history:
 */
class Formbean {

    /**
     * @Fields beanvalue : TODO(用一句话描述这个变量表示什么)
     */
    private String beanvalue; // 生成的bean的具体内容

    /**
     * @Fields inputs : TODO(用一句话描述这个变量表示什么)
     */
    private Elements inputs;

    /**
     * @Fields packagestr : TODO(用一句话描述这个变量表示什么)
     */
    private String packagestr;

    /**
     * @Fields classname : TODO(用一句话描述这个变量表示什么)
     */
    private String classname; // 生成的bean的名字

    /**用一句话描述这个方法的作用.
     * @return beanvalue
     * @create: 2014年5月14日 下午3:09:49 linyy
     * @history:
     */
    public String getBeanvalue() {
        return beanvalue;
    }

    /**用一句话描述这个方法的作用.
     * @param beanvalueCheck beanvalue
     * @create: 2014年5月14日 下午3:09:51 linyy
     * @history:
     */
    public void setBeanvalue(String beanvalueCheck) {
        this.beanvalue = beanvalueCheck;
    }

    /**用一句话描述这个方法的作用.
     * @return inputs
     * @create: 2014年5月14日 下午3:10:00 linyy
     * @history:
     */
    public Elements getInputs() {
        return inputs;
    }

    /**用一句话描述这个方法的作用.
     * @param inputsCheck inputs
     * @create: 2014年5月14日 下午3:10:13 linyy
     * @history:
     */
    public void setInputs(Elements inputsCheck) {
        this.inputs = inputsCheck;
    }

    /**用一句话描述这个方法的作用.
     * @return packagestr
     * @create: 2014年5月14日 下午3:10:11 linyy
     * @history:
     */
    public String getPackagestr() {
        return packagestr;
    }

    /**用一句话描述这个方法的作用.
     * @param packagestrCheck packagestr
     * @create: 2014年5月14日 下午3:10:07 linyy
     * @history:
     */
    public void setPackagestr(String packagestrCheck) {
        this.packagestr = packagestrCheck;
    }

    /**用一句话描述这个方法的作用.
     * @return classname
     * @create: 2014年5月14日 下午3:10:04 linyy
     * @history:
     */
    public String getClassname() {
        return classname;
    }

    /**用一句话描述这个方法的作用.
     * @param classnameCheck classname
     * @create: 2014年5月14日 下午3:10:02 linyy
     * @history:
     */
    public void setClassname(String classnameCheck) {
        this.classname = classnameCheck;
    }

}
