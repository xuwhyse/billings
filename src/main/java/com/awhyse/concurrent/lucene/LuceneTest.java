package com.awhyse.concurrent.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.LogDocMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.FileSwitchDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

/**
 * 
 * @author hzxumin15 2015-4-28下午4:20:35
 */
public class LuceneTest {

	private static File fileDir;
	private static File indexDir;
	private static String[] idArr = { "1", "2", "3", "4", "5", "6" };
	private static String[] emailArr = { "abc@us.ibm.com", "ert@cn.ibm.com",
			"lucy@us.ibm.com", "rock@cn.ibm.com", "test@126.com",
			"deploy@163.com" };
	private static String[] contentArr = { "welcome to Lucene,I am abc",
			"This is ert,I am from China", "I'm Lucy,I am english",
			"I work in IBM", "I am a tester", "I like Lucene in action" };

	private static String[] nameArr = { "abc", "ert", "lucy", "rock", "test",
			"deploy" };
	/**
	 * 操作索引文件的对象
	 */
	private static Directory directory = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		initFile();
//		initIndex();
		//=================
//		searchByTerm("name", "abc", 2);//必须是全名搜索
//		searchByPrefix("name", "e", 2);//只能某个字段开头搜，不能中间
//		searchByTermRange("name", "bcc", "g", 10);//后面那个是限制的数量,范围可以字母字符窜
		searchByWildcard("name", "*e*", 8);//名字里包含某个字符窜搜索！！！！！！！！！！！
	}

	private static void initIndex() {
		IndexWriter writer = null;
		try {
			directory = FSDirectory.open(indexDir);// 使用硬盘保存索引文件
													// RAMDirectory(内存)
			Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_47,
					analyzer);// 索引配置类，使用什么分解策略等
			conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
			// ------------
			LogMergePolicy mergePolicy = new LogDocMergePolicy();// 要根据物理机器的配置来进行次测试,,
			mergePolicy.setMergeFactor(10);// 合并因子控制 10个segment-》更大的segment索引块
			mergePolicy.setMaxMergeDocs(10);// 10个DOC变为一个segment索引块，
			conf.setMaxBufferedDocs(10);// 写入一个segment时，内存中可以放最多的doc数

			writer = new IndexWriter(directory, conf);// 最终的写类，具备1文档位置和 存放方式
														// 2：使用什么工具进行分析
			Document doc = null;// lucene的文档文件
			int date = 1;
			for (int i = 0; i < idArr.length; i++) {
				doc = new Document();// 创建doc
				IndexableField field = new StringField("id", idArr[i],
						Store.YES);// StoreField 至存储不索引,TextField 索引并分词
				doc.add(field);// 添加列
				doc.add(new StringField("email", emailArr[i], Store.YES));
				doc.add(new StringField("content", contentArr[i], Store.YES));
				doc.add(new StringField("name", nameArr[i], Store.YES));
				doc.add(new StringField("date", "2014120" + date + "11111111",
						Store.YES));

				writer.addDocument(doc);// 写入一个doc
				date++;
			}
//			writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();// 关闭写入流或者通道等操作
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private static void initFile() {
		String docsPath = "e:/lucene/temp/docs";// 存放文件
		String indexPath = "e:/lucene/temp/index";// 存放索引文件
		fileDir = new File(docsPath);
		indexDir = new File(indexPath);
		if (!fileDir.exists())
			fileDir.mkdirs();
		if (!indexDir.exists())
			indexDir.mkdirs();
	}

	/**
	 * 传入读取对象，获取索引搜索对象
	 * 
	 * @return
	 */
	public static IndexSearcher getSearcher() {
		IndexReader reader = null;
		try {
			if(directory==null){
				String indexPath = "e:/lucene/temp/index";// 存放索引文件
				indexDir = new File(indexPath);
				directory = FSDirectory.open(indexDir);// 使用硬盘保存索引文件
			}
			reader = DirectoryReader.open(directory);// 传入目标文件夹对象，获取文件读取操作对象
			IndexSearcher searcher = new IndexSearcher(reader);// 传入读取对象，获取索引搜索对象
			return searcher;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void searchByTerm(String field, String name, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			// TermQuery 是 Lucene 查询中最基本的一种查询，它只能针对一个字段进行查询
			Query query = new TermQuery(new Term(field, name));// 根据列和名字字段组装查询条件对象
			TopDocs tds = searcher.search(query, num);// IndexSearcher对象根据条件查询出相关对应的docs
			System.out.println("count:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println("docId:" + doc.get("id"));
				System.out.println("name:" + doc.get("name"));
				System.out.println("content:" + doc.get("content"));
				System.out.println("email:" + doc.get("email"));
				System.out.println("date:" + doc.get("date"));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 范围查询 RangeQuery (搜索指定范围的数据）
	 * 
	 * @param field
	 * @param start
	 * @param end
	 * @param num
	 */
	public static void searchByTermRange(String field, String start, String end,
			int num) {
		try {
			IndexSearcher searcher = getSearcher();
			BytesRef lowerTerm = new BytesRef(start);
			BytesRef upperTerm = new BytesRef(end);
			// TermRangeQuery query=new TermRangeQuery(字段名, 起始值, 终止值, 起始值是否包含边界,
			// 终止值是否包含边界)。
			Query query = new TermRangeQuery(field, lowerTerm, upperTerm, true,
					true);
			TopDocs tds = searcher.search(query, num);// 可以接受多种查询对象
			System.out.println("count:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println("docId:" + doc.get("id"));
				System.out.println("name:" + doc.get("name"));
				System.out.println("content:" + doc.get("content"));
				System.out.println("email:" + doc.get("email"));
				System.out.println("date:" + doc.get("date"));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 前缀查询,百度的搜索框
	 * 
	 * @param field
	 * @param value
	 * @param num
	 */
	public static void searchByPrefix(String field, String value, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			// 这个是前缀查询对象
			Query query = new PrefixQuery(new Term(field, value));
			TopDocs tds = searcher.search(query, num);
			System.out.println("count:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println("docId:" + doc.get("id"));
				System.out.println("name:" + doc.get("name"));
				System.out.println("content:" + doc.get("content"));
				System.out.println("email:" + doc.get("email"));
				System.out.println("date:" + doc.get("date"));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通配符查询
	 * “*”表示任何字符，“？”表示任意一个字符。
	 * @param field
	 * @param value
	 * @param num
	 */
	public static void searchByWildcard(String field, String value, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			// 通配符分为两种，“*”和“？”，“*”表示任何字符，“？”表示任意一个字符。
			// Term term=new Term(字段名, 搜索关键字+通配符)。
			Query query = new WildcardQuery(new Term(field, value));
			TopDocs tds = searcher.search(query, num);
			System.out.println("count" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println("docId:" + doc.get("id"));
				System.out.println("name:" + doc.get("name"));
				System.out.println("content:" + doc.get("content"));
				System.out.println("email:" + doc.get("email"));
				System.out.println("date:" + doc.get("date"));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * BooleanQuery，也就是组合查询，允许进行逻辑 AND、OR 或 NOT 的组合，通过 BooleanQuery 的 add 方法将一个查
	 * 询子句增加到某个 BooleanQuery 对象中。 BooleanClause.Occur.MUST：必须包含，相当于逻辑运算的与
	 * BooleanClause.Occur.MUST_NOT：必须不包含，相当于逻辑运算的非
	 * BooleanClause.Occur.SHOULD：可以包含，相当于逻辑运算的或
	 * 
	 * @param num
	 */
	public static void searchByBoolean(int num) {
		try {
			IndexSearcher searcher = getSearcher();
			BooleanQuery query = new BooleanQuery();
			query.add(new TermQuery(new Term("name", "abc")), Occur.SHOULD);
			query.add(new TermQuery(new Term("email", "lucy@us.ibm.com")),
					Occur.SHOULD);
			TopDocs tds = searcher.search(query, num);
			System.out.println("count" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println("docId:" + doc.get("id"));
				System.out.println("name:" + doc.get("name"));
				System.out.println("content:" + doc.get("content"));
				System.out.println("email:" + doc.get("email"));
				System.out.println("date:" + doc.get("date"));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
