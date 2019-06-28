package com.hongdu.yuanmayuedu.encapsulationhttp.utils;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName GenerateSqlUtil
 * @Description
 *  目前支持: 传入参数表名 + 字段名 + 字段类型  + 数据库类型(生成日期函数用)
 *  然后就是生成对应的值
 *  字段名需带有长度
 *  已提供:
 *      1:  提供随机h生成数字类型
 *  insert into hlt_card_pool(cardNo,cardBin,status,createtime,createuser)
 * @Author dudu
 * @Date 2019/6/26 9:43
 * @Version 1.0
 */
public class GenerateSqlUtil {

    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static String PACKAGE_PATH = GenerateSqlUtil.class.getPackage().getName();
    public static final String SPACE = " ";
    public static final String VALUES = "VALUES";
    /**
     * 常量定义
     */
    public static final String INSERT_INO = "insert into";
    /**
     * 左括号
     */
    public static final String ZUO_KUO_HAO = "(";
    /**
     * 右括号
     */
    public static final String YOU_KUO_HAO = ")";
    /**
     * 日期格式
     */
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy--MM-dd hh:mm:ss");

    /**
     * 定义当前日期字符串格式
     */
    public static String CURRENT_DATE = SIMPLE_DATE_FORMAT.format(new Date());

    /*
    随机数生成方式:
    1: new Random()
    2: Math.random()
    3: currentTimeMills()
     */

    /**
     * 生成批量新增sql
     * @param tableName 表名
     * @param params 参数名字
     * @return
     */
    public static List<String> generateBatchInsertSql(String tableName, List<String> params) {
        List<String> batchSqlList = new ArrayList<>();
        //新增的sql变量
        StringBuffer insertSql = new StringBuffer(INSERT_INO);
        /**
         *          2、StringBuffer的删除功能
         * 　　　　public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身，只删除指定位置的这个字符
         * 　　　　public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身
         */

        return null;
    }


    /**
     * @param tableName
     * @param params 字段名 + 字段类型 不够 还需要一个字段长度
     *                  或者解决方式 : 字段类型+字段长度 中间使用一个符号作为拼接 -->解析出字段类型及字段长度
     * @return
     */
    public static List<String> generateBatchInsertSql(String tableName, Map<String,String> params) {
        List<String> batchSqlList = new ArrayList<>();
        //新增的sql变量
        StringBuffer insertSql = new StringBuffer(INSERT_INO);
        /**
         *          2、StringBuffer的删除功能
         * 　　　　public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身，只删除指定位置的这个字符
         * 　　　　public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身
         */

        return null;
    }

    /**
     *
     * @param tableName
     * @param params 字段名 为key  + 字段类型 对字段长度为value
     * @return
     */
    public static List<String> generateBatchInsertSqlMapMap(String tableName, Map<String,Map<String, Integer>> params) {
        List<String> batchSqlList = new ArrayList<>();
        //新增的sql变量
        StringBuffer insertSql = new StringBuffer(INSERT_INO);
        /**
         *          2、StringBuffer的删除功能
         * 　　　　public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身，只删除指定位置的这个字符
         * 　　　　public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身
         */
        /**
         * 拼接前半部分的: insert into table () --> 再进行拆解 : 字段名直接拆解出来
         */

        return null;
    }


    /**
     *
     * @param tableName 表名
     * @param tableFields 字段名
     * @param params 字段名 + 字段长度
     * @param  n 生成insert into 语句条数
     * @return
     */
    public static List<String> generateBatchInsertSqlMapMap(String tableName,LinkedList<String> tableFields ,LinkedList<Map<String, Integer>> params, int n) {
        List<String> batchSqlList = new ArrayList<>();
        //新增的sql变量
        StringBuffer insertSql = new StringBuffer(INSERT_INO + SPACE);
        /**
         * 拼接表名 + 括号
         */
        insertSql.append(tableName).append(SPACE).append(ZUO_KUO_HAO);
        /**
         *          2、StringBuffer的删除功能
         * 　　　　public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身，只删除指定位置的这个字符
         * 　　　　public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身
         */
        /**
         * 拼接前半部分的: insert into table () --> 再进行拆解 : 字段名直接拆解出来
         */
        if(tableFields != null && tableFields.size() > 0) {
            for (String appendFiled : tableFields) {
                insertSql.append(appendFiled).append(",");
            }
            //删除最后一个逗号:
            insertSql.deleteCharAt(insertSql.length() -1 );
        }
        insertSql.append(YOU_KUO_HAO).append(SPACE).append(VALUES).append(ZUO_KUO_HAO);
        //insert into user_info (cardNo,cardBin,status,createtime,createuser) VALUES(
        //开始拼接后半部分 : 其实就是生成 : 字符串 数字 日期字符串(中文暂时不添加)
        for (int i = 0; i < n; i ++) {
            StringBuffer lastPart = buildLastPart(tableFields,params);
            String temp = insertSql.append(lastPart).toString();
            batchSqlList.add(temp);
            //剔除insertSql的后半部分
            insertSql.delete(insertSql.indexOf(")") + 9, insertSql.length());
        }
        return batchSqlList;
    }

    @Test
    public void generateBatchInsertSqlMapMapTest() {
        LinkedList tableFields = new LinkedList();
        tableFields.add("cardNo");
        tableFields.add("cardBin");
        tableFields.add("status");
        tableFields.add("createtime");
        tableFields.add("createuser");

        LinkedList<Map<String, Integer>> params = new LinkedList();
        Map<String, Integer> t1 = new HashMap<>(16);
        t1.put("varchar",19);
        Map<String, Integer> t2 = new HashMap<>(16);
        t2.put("varchar",10);
        Map<String, Integer> t3 = new HashMap<>(16);
        t3.put("int",1);
        Map<String, Integer> t4 = new HashMap<>(16);
        t4.put("datetime",0);
        Map<String, Integer> t5 = new HashMap<>(16);
        t5.put("createUser",50);
        params.add(t1);
        params.add(t2);
        params.add(t3);
        params.add(t4);
        params.add(t5);
        List<String> stringList = generateBatchInsertSqlMapMap("hlt_card_pool", tableFields , params, 10);
        Iterator<String> lstIterator = stringList.iterator();
        while (lstIterator.hasNext()) {
            System.out.println(lstIterator.next());
        }
        String path = "./src/main/java/" + PACKAGE_PATH.replace(".","/") + "/x.sql";
        try {
            writeFileContext(stringList,path);
            System.out.println("输出sql脚本成功!");
        } catch (IOException e) {
            System.out.println("输出sql脚本失败!");
        }
    }

    @Test
    public void buildLastPartTest() {
        LinkedList tableFields = new LinkedList();
        tableFields.add("cardNo");
        tableFields.add("cardBin");
        tableFields.add("status");
        tableFields.add("createtime");
        tableFields.add("createuser");

        LinkedList<Map<String, Integer>> params = new LinkedList();
        Map<String, Integer> t1 = new HashMap<>(16);
        t1.put("varchar",19);
        Map<String, Integer> t2 = new HashMap<>(16);
        t2.put("varchar",10);
        Map<String, Integer> t3 = new HashMap<>(16);
        t3.put("int",1);
        Map<String, Integer> t4 = new HashMap<>(16);
        t4.put("datetime",0);
        Map<String, Integer> t5 = new HashMap<>(16);
        t5.put("createUser",50);
        params.add(t1);
        params.add(t2);
        params.add(t3);
        params.add(t4);
        params.add(t5);

        System.out.println(buildLastPart(tableFields, params));
    }

    /**
     * 根据字段名和字段长度 随机生成对应的 字符串 数字 或者日期字符串
     *
     * @param tableFields
     * @param params
     * @return
     */
    private static StringBuffer buildLastPart(LinkedList<String> tableFields, LinkedList<Map<String, Integer>> params) {
        Set<String> uniqueCollection = new HashSet<>();
        StringBuffer lastPartSql = new StringBuffer();
        for (int i = 0, len = tableFields.size(); i < len; i ++) {
            String fieldString = tableFields.get(i);
            Map<String, Integer> typeAndLength = params.get(i);
            Set<Map.Entry<String, Integer>> typeOrLenMaps = typeAndLength.entrySet();
            for (Map.Entry<String, Integer> typeOrLenMap : typeOrLenMaps ) {
//                System.out.println("字段名 : " + fieldString + ", 字段类型及长度 : " + typeOrLenMap.getKey() + "," + typeOrLenMap.getValue() );
                //随机生成值并拼接
                String key = typeOrLenMap.getKey();
                Integer value = typeOrLenMap.getValue();
                //如果是varchar类型 : 字符串拼接
                if("varchar".equalsIgnoreCase(key)) {
                    lastPartSql.append("\'");
                    String temp = randomGenerateVarchar(value);
                    //生成唯一19位卡号
                    while (true) {
                        if(!uniqueCollection.contains(temp)) {
                            uniqueCollection.add(temp);
                            lastPartSql.append(temp);
                            break;
                        }
                    }
                    lastPartSql.append("\'").append(",");
                    continue;
                }
                if("int".equalsIgnoreCase(key)) {
                    lastPartSql.append(9).append(",");
                    continue;
                }
                if("datetime".equalsIgnoreCase(key)) {
                    //暂时是 : sqlserver
                    lastPartSql.append("GETDATE").append(ZUO_KUO_HAO).append(YOU_KUO_HAO).append(",");
                    continue;
                }
                if("createUser".equalsIgnoreCase(key)) {
                    lastPartSql.append("\'").append("dbo").append("\',");
                    continue;
                }
                lastPartSql.append("\'").append("\'").append(",");
                continue;
            }
        }
        return lastPartSql.replace(lastPartSql.length() - 1, lastPartSql.length(), YOU_KUO_HAO);//替换起始位置 替换结束为止 -->其实是一个区间
    }

    /**
     * 生成纯数字字符串
     * @param value
     * @return
     */
    private static String randomGenerateVarchar(Integer value) {
        //char charr[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~;<@#:>%^".toCharArray();
        //生成纯数字字符串
        char charr[] = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int x = 0; x < value; ++x) {
            sb.append(charr[r.nextInt(charr.length)]);
        }
        return sb.toString();
    }

    @Test
    public void randomGenerateVarcharTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println(randomGenerateVarchar(19));
        }
    }

    /**
     *
     * @param params
     * @return
     */
    private static StringBuffer buildLastPartByClass(LinkedList<Map<Class<?>, Integer>> params) {

        return null;
    }

    @Test
    public void stringBufferAppendTest() {
        LinkedList<String> tableFields = new LinkedList<>();
        tableFields.add("cardNo");
        tableFields.add("cardBin");
        tableFields.add("status");
        tableFields.add("createtime");
        tableFields.add("createuser");
        StringBuffer insertSql = new StringBuffer(INSERT_INO + SPACE);
        /**
         * 拼接表名 + 括号
         */
        insertSql.append("user_info").append(SPACE).append(ZUO_KUO_HAO);
        /**
         *          2、StringBuffer的删除功能
         * 　　　　public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身，只删除指定位置的这个字符
         * 　　　　public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身
         */
        /**
         * 拼接前半部分的: insert into table () --> 再进行拆解 : 字段名直接拆解出来
         */
        if(tableFields != null && tableFields.size() > 0) {
            for (String appendFiled : tableFields) {
                insertSql.append(appendFiled).append(",");
            }
            //删除最后一个逗号: 长度减去 1 才是最后一个逗号所在的位置
            insertSql.deleteCharAt(insertSql.length() -1 );
        }
        insertSql.append(YOU_KUO_HAO).append(SPACE).append(VALUES).append(ZUO_KUO_HAO).append(SPACE);
        System.out.println(insertSql);
    }

    @Test
    public void stringBufferDeleteTest() {
        StringBuffer insertSql = new StringBuffer(INSERT_INO).append(SPACE).append(",");
        insertSql.replace(insertSql.length() - 1, insertSql.length(), YOU_KUO_HAO);
        System.out.println(insertSql);
//        System.out.println(insertSql.length());
//        insertSql.append("hello");
//        System.out.println(insertSql);
//        insertSql.delete(insertSql.lastIndexOf("into") + 5, insertSql.length());
//        System.out.println(insertSql + "; " +insertSql.length());
    }

    /**
     *
     * @param n
     * @return
     */
    //提供一个生成根据位数 生成相应 位数的 数字方法 返回一个数字
    private static Integer getStatusAndOthers(int n) {

        return 0;
    }

    @Test
    public void test() {
        Random r1 = new Random(1);
        for(int i=0 ; i<5 ;  i++){
            int ran1 = r1.nextInt(100);
            System.out.print(ran1 + " ");//85 88 47 13 54 : 使用种子 1 一直是这个结果 --> 不太随机
            //第一次 : 85 88 47 13 54
            //第2次 : 85 88 47 13 54
            //第3次 : 85 88 47 13 54
            //第4次 : 85 88 47 13 54
            //第5次 : 85 88 47 13 54
            //第6次 : 85 88 47 13 54
        }
        System.out.println();
        Random r2 = new Random();
        for(int i=0 ; i<5 ;  i++){
            int ran1 = r2.nextInt(100);
            System.out.print(ran1 + " ");//
            //第一次 :92 5 61 68 97
            //第2次 : 57 5 63 26 95
            //第3次 : 81 81 38 68 57
            //第4次 : 93 49 76 61 14
            //第5次 : 20 62 97 62 2
            //第6次 : 73 36 7 34 5
        }
    }

    /**
     * 将集合写到文件当中
     * @param strings 集合
     * @param path 文件路径 : 全文件路径 : 包含文件名
     */
    private static void writeFileContext(List<String> strings, String path) throws IOException {
        File file = new File(path);
        //如果没有文件就创建
        if(!file.isFile()) {
            file.createNewFile();
        }
        //集合 写到文件中 -->从内存输出 -->  内存写道外面 (读史读到内存)
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
        for (String ls : strings) {
            fileWriter.write(ls + "\r\n");
        }
        fileWriter.close();
    }

//    2.在类中取得路径：
//            (1)类的绝对路径：Class.class.getClass().getResource("/").getPath()
//    结果：/D:/TEST/WebRoot/WEB-INF/classes/pack/
//            (2)得到工程的路径：System.getProperty("user.dir")
//    结果：D:\TEST
    private String getPathForNeed() {
        System.out.println(this.getClass().getResource("/").getPath());
        ///G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/test-classes/
        /**
         * 获取工程路径
         * //G:\my_maven_workspace_git\javaSpaceForGupao\homework\proxy
         */
        System.out.println(PROJECT_PATH);
        /**
         * 获取包路径 :
         * com.hongdu.yuanmayuedu.encapsulationhttp.utils
         */
        System.out.println(PACKAGE_PATH);//com.hongdu.yuanmayuedu.encapsulationhttp.utils
        System.out.println(PACKAGE_PATH.replace(".","/").concat("/"));//com.hongdu.yuanmayuedu.encapsulationhttp.utils
        /**
         * 设定为当前文件夹
         */
        File directory = new File("");
        try{
            /**
             * 获取标准的路径
             */
            System.out.println(directory.getCanonicalPath());
            System.out.println(directory.getAbsolutePath());//获取绝对路径
            /**
             file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu/yuanmayuedu/encapsulationhttp/utils/
             */
            System.out.println( GenerateSqlUtil.class.getResource(""));
            System.out.println( GenerateSqlUtil.class.getResource("/"));
            System.out.println( GenerateSqlUtil.class.getClassLoader().getResource(""));
            System.out.println( GenerateSqlUtil.class.getResource("").toURI());
            File currentPath = new File(".");
            /**
             * G:\my_maven_workspace_git\javaSpaceForGupao\homework\proxy
             */
            System.out.println(currentPath.getCanonicalPath());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    @Test
    public void getPathForNeedTest() {
        System.out.println(getPathForNeed());
    }


}
