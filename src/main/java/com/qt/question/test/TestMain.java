package com.qt.question.test;

import com.qt.question.config.QuestionConfig;
import com.qt.question.dao.mapper.AcademyMapper;
import com.qt.question.dao.mapper.JudgeTopicMapper;
import com.qt.question.dao.mapper.StudentClassMapper;
import com.qt.question.exception.BaseException;
import com.qt.question.pojo.*;
import com.qt.question.service.*;
import com.qt.question.util.JsonResult;
import com.qt.question.util.poi.POIUtil;
import com.qt.question.util.string.StringUtil;
import com.qt.question.util.token.TokenUtil;
import com.qt.question.util.token.impl.TokenUtilImpl;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author vector
 * @since 2019/10/9
 * <p>
 * git上传的时候不需要上传测试函数
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/bean.xml")
public class TestMain {


    @Autowired
    private TokenUtil tokenUtil;


    @Autowired
    private POIUtil poiUtil;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RadioTopicService radioTopicService;

    @Autowired
    private JudgeTopicService judgeTopicService;


    @Autowired
    private StringUtil stringUtil;

    @Autowired
    private AcademyMapper academyMapper;

    /**
     * 获取token的方法  类似
     */
    @Test
    public void test() {
        Map<String, String> params = new HashMap<>();
        params.put("studentId", "20171905");
        try {
            String token = tokenUtil.getToken(params);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tokenUtil.isValid(token)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private StudentClassMapper studentClassMapper;

    /**
     * 插入学生数据到数据库
     */
    @Test
    public void insertStudent() throws IOException, InvalidFormatException {
        int count = 0;
        File excelFile = new File("C:\\Users\\26332\\Desktop\\students.xls");
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> academyMap = new HashMap<>();
        Map<String, Integer> classMap = new HashMap<>();
        List<List<Object>> data = poiUtil.getExcelLinePartData(excelFile, "student", 1, 7031);
        System.out.println(data.size());
        /*
        政法学院	社会工作	19级社会工作班	201925903040	陈晓云	女	1611451	汉族
        政法学院	社会工作	19级社会工作班	201925903041	王珠娟	女	010048	汉族n
         */
//        //学院  专业  班级  学号  名字  性别 密码  名族
//        //0     1     2    3     4     5    6     7
//        StudentClass studentClass = new StudentClass();
//        Academy academy = new Academy();
//        Student student = new Student();
//        for (List<Object> list : data) {
//            if (list != null) {
//                if (list.size() != 8) {
//                    System.err.println(list);
//                    continue;
//                }
//                //学院
//                if (list.get(0) != null) {
//                    String s = (String) list.get(0);
//                    if (!map.containsKey(s)) {
//                        map.put(s, new ArrayList<>());
//                        academy.setAcademyName(s);
//                        int id = academyService.insertAcademy(academy);
//                        academyMap.put(s, id);
//                    }
//                    //插入数据库 班级的信息
//                    List<String> li = map.get(s);
//                    String className = (String) list.get(2);
//                    if (!li.contains(className)) {
//                        li.add(className);
//                        //插入数据
//                        studentClass.setClassName(className);
//                        studentClass.setClassAcademyId(academyMap.get(s));
//                        int classId = studentClassService.insertStudentClass(studentClass);
//                        classMap.put(className, classId);
//                    }
//                    //插入学生信息
//                    student.setStudentClass(classMap.get(className));
//                    student.setStudentAcademy(academyMap.get(s));
//                    student.setStudentGender((String) list.get(5));
//                    student.setStudentId(Long.parseLong((String) list.get(3)));
//                    student.setStudentNation((String) list.get(7));
//                    student.setStudentName((String) list.get(4));
//                    student.setStudentMajor((String) list.get(1));
//                    //设置密码
//                    student.setStudentPassword(DigestUtils.md5Hex((String) list.get(6)));
//                    studentService.insertStudent(student);
//                }
//            }
//            count++;
//            if (count % 100 == 0) {
//                System.out.println(count);
//            }
//        }

        //新闻与传播学院	新闻传播学类（新闻学、广播电视学、广告学）	19级新闻传播学类3班	201926402121	林千禧	女

        //财政金融学院	会计学	19级会计学1班	201926803025	黄渝闳	男

        //化学化工学院	化学类（化学、应用化学）	19级化学类2班	201926101113	洪瑜	男
    }

    @Test
    public void test2() {
        Academy academy = new Academy();
        academy.setAcademyId(5);
        academyMapper.updateAcademy(academy);
    }


    @Test
    public void test3() {
        String[] answers = stringUtil.splitAnswer("3A7B8C13B19B20A25B29B34B37B42B44B50B55B58C62A65A69A74A77A82A84A90A92A96A101A104A109A114A117A122A124A129A133A136A140A147A150A152B157B2A,D,B");
        int[] numbers = stringUtil.splitTopicNumber("3A7B8C13B19B20A25B29B34B37B42B44B50B55B58C62A65A69A74A77A82A84A90A92A96A101A104A109A114A117A122A124A129A133A136A140A147A150A152B157B2A,D,B");
        for (int i = 0; i < answers.length; i++) {
            System.out.println(numbers[i] + ":" + answers[i]);
        }
    }


    @Autowired
    private JudgeTopicMapper judgeTopicMapper;

    @Test
    public void testTime() throws BaseException {
        System.out.println(judgeTopicMapper.getJudgeTopic(1));
    }


    @Autowired
    private MultipartTopicService multipartTopicService;

    @Test
    public void testAnswer() throws BaseException {

//        String radioAnswer = "1A5A9A13A16A22A22A27ANaN33A42A45A51A52A58A60A66A69A72A77A81A87A91A93A96A101A104A110A115A119A121A125A129A134A137A140A147A149A154A157A";
        String radioAnswer = "1A4A11A12A19A20A27A30A32A38A40A45A51A55A59A61A64A70A73A76A83A85A89A93A97A101A104A110A115A119A120A124A129A134A139A141A145A151A153A156A";
        String multipartAnswer = "1A6A11A14A17A24A28A29A34A37A43A45A49A54A57A61A68A69A73A77A";
        String judgeAnswer = "2×5×7×10×13×16×19×22×25×30×32×35×37×41×43×48×50×54×57×59×";


        String[] radio = stringUtil.splitAnswer(radioAnswer);
        int[] radioNumbers =  stringUtil.splitTopicNumber(radioAnswer);
        System.out.println(Arrays.toString(radio) + "--" + Arrays.toString(radioNumbers));

        int[]  multipartNumbers =  stringUtil.splitTopicNumber(multipartAnswer);
        String[] multipart = stringUtil.splitAnswer(multipartAnswer);
        System.out.println(Arrays.toString(multipartNumbers) + "--" + Arrays.toString(multipart));

        int[] judgeNumbers =  stringUtil.splitTopicNumber(judgeAnswer);
        String[] judge = stringUtil.splitAnswer(judgeAnswer);
        System.out.println(Arrays.toString(judgeNumbers) + "--" + Arrays.toString(judge));


        Student student = new Student();
        student.setStudentId(201926803001L);
        JsonResult jsonResult = studentService.calStudentScore(radio,radioNumbers,multipart,multipartNumbers,judge,judgeNumbers,student,new Date().getTime());
        System.out.println(jsonResult);
    }


    @Test
    public void testToken(){
        boolean res = tokenUtil.isValid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhbnN3ZXJJbmRleCI6IjEiLCJsb2dpbklkIjoiMjAxOTI2ODAzMDAxIiwibG9naW5UaW1lIjoiMTU3NDgzMTU5MTkzOCIsInR5cCI6IkpXVCIsImV4cCI6MTU3NDgzNTI1MSwiYWxnIjoiSFMyNTYifQ.RlnOUbx1ByrEapSI5L48o4r4Qw_uht5X-bFBhxak4kI");
        System.out.println(res);
    }


    @Test
    public void getStudent(){
        Student student = studentService.getStudent(201926803001L).getData();
        System.out.println(student.getStudentAnswerCount() > QuestionConfig.MAX_ANSWER_COUNT);
    }



    @Test
    public void testCount(){
        Student student = new Student();
        student.setStudentId(201926803001L);
        Student student1 = studentService.getStudent(student.getStudentId()).getData();
        System.out.println(student1.getGradeList());
    }

    @Test
    public void testAnswerIndex(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhbnN3ZXJJbmRleCI6IjEiLCJsb2dpblRpbWUiOiIxNTc0OTE2MzExMzc2IiwibG9naW5JZCI6IjIwMTkyNjgwMzAwMSIsInR5cCI6IkpXVCIsImV4cCI6MTU3NDkxOTk3MSwiYWxnIjoiSFMyNTYifQ.HEdv_KeDO4hWkNOevaZ_rTFiYKOUYHVNYrJVnUXXj-A";
        Map<String,String> map = tokenUtil.parseToken(token);
        System.out.println(map);
    }


    @Autowired
    private GradeService gradeService;

    static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        String [] strs = read.readLine().split(" ");
        int m = Integer.parseInt(strs[0]);
        int n = Integer.parseInt(strs[1]);
        int[][] arr = new int[m][n];
        for(int i = 0;i < m;i++){
            strs = read.readLine().split(" ");
            for(int j = 0;j < strs.length ; j++){
                arr[i][j]  = Integer.parseInt(strs[j]);
            }
        }


        int target = Integer.parseInt(read.readLine());
        int i = m - 1,j = 0;
        while(i >= 0 && j < n){
            if(arr[i][j] == target){
                System.out.println(true);
                break;
            }else if(arr[i][j] > target){
                i--;
            }else{
                j++;
            }
        }
    }
}