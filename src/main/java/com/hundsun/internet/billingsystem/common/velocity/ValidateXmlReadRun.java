package com.hundsun.internet.billingsystem.common.velocity;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/** 代码自动生成类.
 * @author: linyy
 * @since: 2014年5月14日 下午2:38:58
 * @history:
 */
public final class ValidateXmlReadRun {
    /**构造函数.
     */
    private ValidateXmlReadRun() {

    }

    /**mian方法.
     * @param args 参数
     * @create: 2014年5月14日 下午2:38:45 linyy
     * @history:
     */
    public static void main(String[] args) {
        try {
            Document doc = read("src/main/resources/validateForm.xml");
            // 根节点
            Element root = doc.getRootElement();
            // 上下文
            Element eContext = root.element("context");
            String context = eContext.getTextTrim();
            // 生成的bean根路径(/)
            Element eBeanrootpath = root.element("beanrootpath");
            String beanrootpath = eBeanrootpath.getTextTrim();
            // 生成的包根路径(.)
            Element ePackagerootpath = root.element("packagerootpath");
            String packagerootpath = ePackagerootpath.getTextTrim();
            // 页面的urls
            Element eUrls = root.element("urls");
            for (Iterator i = eUrls.elementIterator(); i.hasNext();) {
                Element e = (Element) i.next();
                String url = e.getTextTrim();
                String connectUrl = context + url;
                // 包路径
                String urlpathPoint = urlToPointUrlPath(url);
                String packagestr = packagerootpath + urlpathPoint;
                // bean路径
                String beanpathLine = urlToLineUrlPath(url);
                String beanpath = beanrootpath + beanpathLine;
                // 抓取xml上的name生成formbean
                ValidateBeanWrite.main(connectUrl, beanpath, packagestr);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**"/"的路径变成"/"的路径（去掉末尾的html）.
     * @param url url
     * @return /路径
     * @create: 2014年5月14日 下午2:38:08 linyy
     * @history:
     */
    public static String urlToLineUrlPath(String url) {
        String retValue = "";
        String[] urls = url.split("/");
        if (urls.length == 1) {
            return "";
        } else {
            for (int i = 0; i < urls.length - 1; i++) {
                retValue = retValue + urls[i] + "/";
            }
            return retValue;
        }
    }

    /**"/"的路径变成"."的路径（去掉末尾的html）.
     * @param url url
     * @return .路径
     * @create: 2014年5月14日 下午2:37:03 linyy
     * @history:
     */
    public static String urlToPointUrlPath(String url) {
        String retValue = "";
        String[] urls = url.split("/");
        if (urls.length == 1) {
            return "";
        } else {
            for (int i = 0; i < urls.length - 1; i++) {
                retValue = retValue + "." + urls[i];
            }
            return retValue;
        }
    }

    /**读xml.
     * @param fileName 文件名
     * @return doc
     * @throws MalformedURLException 异常
     * @throws DocumentException 异常
     * @create: 2014年5月14日 下午2:37:16 linyy
     * @history:
     */
    public static Document read(String fileName) throws MalformedURLException,
            DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(fileName));
        return document;
    }
}
