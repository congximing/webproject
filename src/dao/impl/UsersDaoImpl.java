package dao.impl;

import dao.UserDao;
import domain.*;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;


import java.sql.SQLException;
import java.util.*;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class UsersDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());//数据库封装技术
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<Users> findAll1() {
        String sql = "select * from users";
        List<Users> users = template.query(sql, new BeanPropertyRowMapper<Users>(Users.class));
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public void add(User user) {

    }
//    查找教室
    public List<Room> findRoom(){
        String sql = "SELECT*FROM classroom";
        return template.query(sql,new BeanPropertyRowMapper<Room>(Room.class));
    }
    public int findRoomCount(){
        String sql = "SELECT COUNT(*)FROM classroom";
        return template.queryForObject(sql,Integer.class );
    }

    //学生添加课程
    public void addCourseStudent(String username, String number) {
        String sql="insert into use_cur values('"+username+"','"+number+"')";
        try{
            template.update(sql);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * 添加课程不能大于3 且时间地点不能重复
     * @param username
     * @param number
     * @param r_id
     */
    public void addCourse(String username,String number,String r_id){
        String sql1="INSERT INTO use_cur VALUES(?,?)";//如何避免重课（是主键 抓异常）、重时间、重地点
        String sql2="SELECT curriculum.*,classroom.* FROM users,curriculum,cur_cla,classroom,use_cur WHERE t_id=number AND p_id=place AND username=u_id AND number=c_id AND  username= "+username+" ";//已选课
        String sql3="SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place and number="+number+" ";//将要添加的课程信息
        String sql4="SELECT COUNT(*)FROM users,curriculum,cur_cla,classroom,use_cur WHERE t_id=number AND p_id=place AND username=u_id AND number=c_id AND username="+username+" ";//选课数目
        String sql5="select count(*)from use_cur,users where c_id="+number+" and r_id='2' and username = u_id";
        Integer count0 = 0;
        try{
            count0=template.queryForObject(sql5, Integer.class);//一个课程不能被加2次
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(count0);
        if (count0>=2){
            return;
        }
        Integer count = template.queryForObject(sql4, Integer.class);//如果用户课程为零

        List<UsersCourse> course = template.query(sql3, new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
        UsersCourse courses = course.get(0);//得到集合中的一个对象
        String time = courses.getTime();
        String number1 = courses.getNumber();

        List<UsersCourse> result = template.query(sql2, new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
        int length = result.size();
        int i = 1;
        if (count == 0){
            template.update(sql1,username,number);//如果用户课程为零 直接添加
        }else{
            //对集合进行遍历
            for (UsersCourse user: result) {
                if (user.getTime().equals(time) || user.getNumber().equals(number1)  ){//时间不同
                    //如何在前端做出标记?????
                    break;
                }else if (length == i){
                    try {
                        template.update(sql1,username,number);
                    }catch (Exception e) {
                        printStackTrace();
                    }
                }
                i++;
                if (length >= 3 && r_id.equals("2")){//教师的选课不能大于三
                    break;
                }
            }
        }
    }
    //在管理员界面上的课程管理 增加课程
    public void addCourse(UsersCourse usersCourseion){
        String sql="INSERT INTO curriculum VALUES(?,?,?,?,?,?)";
        String sql1="select *from subject where name='"+ usersCourseion.getCoursename()+"'";
        List<Subject> subject =  template.query(sql1, new BeanPropertyRowMapper<Subject>(Subject.class));
        try {
            template.update(sql,subject.get(0).getNumber(),usersCourseion.getCoursename(),usersCourseion.getTime(),usersCourseion.getWeeks(),usersCourseion.getType(),usersCourseion.getCredit());
        }catch (Exception e){
            System.out.println(e);
        }

//        List<UsersCourse> users = template.query(sql1,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
//        int length = users.size();//获取list集合的长度
//        int i = 1;
//        //上课时间和地点不能重复和课号不同  必须全部遍历完
//        if ( usersCourseion.getTime()!="" && usersCourseion.getPlace() !="" && usersCourseion.getNumber()!="" && usersCourseion.getType() != "" && usersCourseion.getWeeks() != "" && usersCourseion.getCredit() != ""){
//            for ( UsersCourse user: users) {
//                if (user.getTime().equals(usersCourseion.getTime()) || user.getPlace().equals(usersCourseion.getPlace()) || user.getNumber().equals(usersCourseion.getNumber())){
//                    break;
//                }
//                else if(i == length){
//                    template.update(sql,usersCourseion.getNumber(),usersCourseion.getCoursename(),usersCourseion.getTime(),usersCourseion.getWeeks(),usersCourseion.getType(),usersCourseion.getCredit());
//                }
//                i++;
//            }
//        }
    }
    @Override
    public void delete(int id) {

    }
    public void delete(String username ,String number) {
        String sql = "DELETE FROM use_cur WHERE u_id="+username+" AND c_id="+number+" ";
        template.update(sql);
    }

    @Override
    public User findById(int i) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    public List<UsersCourse> findTermCourse(String username,String r_id,int term){
        String sql = "SELECT curriculum.*,classroom.place FROM users,use_cur,curriculum,cur_cla,classroom WHERE u_id=username AND c_id=number AND t_id=number AND p_id=place AND username="+username+" AND r_id="+r_id+" AND term="+term+" ";
        List<UsersCourse> list = template.query(sql, new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
        return list;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition,String r_id) {
        String sql = "select count(*) from users where r_id="+r_id+ " ";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
//        return template.queryForObject(sql,Integer.class);
    }
    //查询学生的选课数目
    public int findSandTCount(String r_id,String username){
        String sql = "select count(*) from users,use_cur,curriculum,cur_cla,classroom,identity where u_id=username and p_id=place and c_id=number and r_id=s_id and  t_id=number and r_id="+r_id+ " and username="+username+" ";
        return template.queryForObject(sql,Integer.class);
    }
    //查询学生和老师的选课信息
    public int findSandTCount(Map<String, String[]> condition,String r_id){
        String sql= " SELECT COUNT(*) FROM users,use_cur,curriculum,cur_cla,classroom,identity WHERE t_id=number AND p_id=place AND username=u_id AND c_id=number AND r_id=s_id AND r_id= "+r_id+"";//40min
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }
    //查询学生自己已选的课表
    public int findSandTCount(Map<String, String[]> condition,String r_id,String username,int term){

        String sql= " SELECT count(*) FROM users,use_cur,curriculum,cur_cla,classroom WHERE u_id=username AND c_id=number AND t_id=number AND p_id=place AND username="+username+" and r_id="+r_id+" and term="+term+" ";//40min
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());

    }
    public int findCourseCount(){//查询课表的总数
        String sql = "SELECT COUNT(*) FROM timetable where 1=1 ";
        return  template.queryForObject(sql,Integer.class);
    }
    //查询课表的总数 含复杂查询（包括没有安排好地点的课程）
    public int findCourseCount(Map<String, String[]> condition){
//        String sql = "SELECT COUNT(*) FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        String sql = "SELECT COUNT(*) FROM curriculum";
        StringBuilder sb = new StringBuilder(sql);

        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        return  template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }
    //安排好的课程
    public int findCourseCount1(Map<String, String[]> condition){
        String sql = "SELECT COUNT(*) FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        return  template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    //复杂查询
    public void find(Map<String, String[]> condition,StringBuilder sb,List<Object> params){
        //2.遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {//*************复杂查询**********

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];//数组转化为字符串****
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值 传入params.toArray()；
            }
        }
    }
//查看所有课程有教室关联好的
    public List<UsersCourse> findByPageCourse(){
        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        return template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
    }
    //将每页的信息存储共5页
    public List<Users> findByPage1(int start, int rows, Map<String, String[]> condition,String r_id) {
        String sql = "select * from users where r_id= "+r_id+  " ";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<Users>(Users.class),params.toArray());
    }
    //查询所有用户已选课表
    public List<UsersCourse> findByPageCourse(int start, int rows){
        String sql = "SELECT * FROM timetable where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();
        sb.append(" limit ?,? ");//限制每页
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class),params.toArray());
    }
    //查询该课程学生的信息
    public List<SandTCourse> findByPageCourse(int start, int rows,String r_id,String number){
       // String sql = "SELECT users.*,timetable.* FROM users,timetable,middle WHERE r_id='1' AND username=u AND number=n AND number="+number+" and r_id="+r_id+" ";
        String sql = "SELECT users.* , curriculum.* FROM users,curriculum,use_cur WHERE r_id=1 AND username=u_id AND c_id=number AND number="+number+" ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();
        sb.append(" limit ?,? ");//限制每页
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<SandTCourse>(SandTCourse.class),params.toArray());
    }

    //查看所有的课程
    public List<UsersCourse> findCourse(int start, int rows, Map<String, String[]> condition){
//        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        String sql = "SELECT curriculum.* from curriculum";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();//参数
        find(condition,sb,params);
        //添加分页查询
        sb.append(" order by number  limit ?,? ");//限制每页
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class),params.toArray());
    }
    //安排好的
    public List<UsersCourse> findByPageCourse(int start, int rows, Map<String, String[]> condition){
        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        //String sql = "SELECT curriculum.* from curriculum";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();//参数
        find(condition,sb,params);
        //添加分页查询
        sb.append(" order by number  limit ?,? ");//限制每页
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        List<UsersCourse> list=new ArrayList<UsersCourse>();
        try{
            return template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class),params.toArray());
        }catch (Exception e){
            return list;
        }
    }
    //将学生和老师课表封装为5条记录的页
    public List<SandTCourse>  findByPageSandTCourse(int start, int rows, Map<String, String[]> condition,String r_id) throws SQLException {
        String sql = "SELECT users.*,identity.page,curriculum.*,classroom.* FROM users,use_cur,curriculum,cur_cla,classroom,identity WHERE t_id=number AND p_id=place AND username=u_id AND c_id=number AND r_id=s_id AND r_id="+r_id+" ";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        //添加分页查询
        sb.append(" order by username limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        List<SandTCourse> student = new ArrayList<SandTCourse>();
        try{
            return template.query(sql,new BeanPropertyRowMapper<SandTCourse>(SandTCourse.class),params.toArray());
        }catch (Exception e){
            return student;
        }
    }
        //插询学生自己的课表 并封装成10条记录每页
    public List<SandTCourse> findByPageSandTCourse(int start, int rows, Map<String, String[]> condition,String r_id,String username,int term ) {
        String sql = "SELECT users.*,curriculum.*,classroom.place FROM users,use_cur,curriculum,cur_cla,classroom WHERE u_id=username AND c_id=number AND t_id=number AND p_id=place AND r_id= "+r_id+" and username="+username+" and term="+term+" ";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        List<SandTCourse> users = template.query(sql, new BeanPropertyRowMapper<SandTCourse>(SandTCourse.class), params.toArray());
        return users;
    }
    public List<SandTCourse> findByPageSandTCourse(int start, int rows,String r_id,String username) {
        String sql = "SELECT users.*,identity.page,curriculum.*,classroom.* FROM users,use_cur,curriculum,cur_cla,classroom,identity WHERE t_id=number AND p_id=place AND username=u_id AND c_id=number AND r_id=s_id AND r_id= "+r_id+" and username="+username+" ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();
        sb.append(" order by username limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        List<SandTCourse> users = template.query(sql, new BeanPropertyRowMapper<SandTCourse>(SandTCourse.class), params.toArray());
        return users;
    }

    @Override
    public Users loginM(Users loginUsers){
        String sql = " SELECT users.*,identity.page FROM users,identity WHERE  r_id=s_id and username=? AND password=?";
        Users users = template.queryForObject(sql,new BeanPropertyRowMapper<Users>(Users.class),loginUsers.getUsername(),loginUsers.getPassword());
        return users;
    }
//    public Users loginM(Users loginUsers) throws SQLException {
//        Connection connection = JDBCUtils.getConnection();
//        String sql = " SELECT*FROM users,identity,function WHERE users.r_id=identity.s_id=function.f_id/10 AND username=? AND password=?";
////        Users users = template.queryForObject(sql,new BeanPropertyRowMapper<Users>(Users.class),loginUsers.getUsername(),loginUsers.getPassword());//查询并封装
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1,loginUsers.getUsername());
//        pstm.setString(2,loginUsers.getPassword());
//        ResultSet rs = pstm.executeQuery();
//        int u_id = rs.getInt("u_id");
//        String username = rs.getString("username");
//        String password = rs.getString("password");
//        String name = rs.getString("name");
//        String gender = rs.getString("gender");
//        String grade = rs.getString("grade");
//        String major = rs.getString("major");
//        int r_id = rs.getInt("r_id");
//        int s_id = rs.getInt("s_id");
//        String ident = rs.getString("ident");
//        int f_id = rs.getInt("f_id");
//        String func = rs.getString("func");
//
//        loginUsers.setU_id(u_id);
//        loginUsers.setUsername(username);
//        loginUsers.setPassword(password);
//        loginUsers.setName(name);
//        loginUsers.setGender(gender);
//        loginUsers.setGrade(grade);
//        loginUsers.setMajor(major);
//        loginUsers.setR_id(r_id);
//        loginUsers.setS_id(s_id);
//        loginUsers.setIdent(ident);
//        loginUsers.setF_id(f_id);
//        loginUsers.setFunc(func);
//        return loginUsers;
//    }
    public boolean findCourse(String coursename) {
        String sql="select*from curriculum where coursename='"+coursename+"'";
        boolean flage=false;
        try{
            template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
            flage=true;
        }catch (Exception e){
            flage=false;
        }
        return flage;
    }

    //通过代号查找课程，来修改此课程
    public UsersCourse findCourseByNum(String number){
        String sql="SELECT curriculum.*,place FROM curriculum,cur_cla,classroom WHERE 1=1 AND number=t_id AND p_id=place  AND number = "+number+" ";
        return  template.queryForObject(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
    }
    //修改课程
    public void updateCourse(UsersCourse userscourse){
        String sql="update timetable set time = ?, place = ?, credit =? where number = ? ";
        //添加的课程和时间不能冲突
        String sql2="select*from timetable";
        List<UsersCourse> course = template.query(sql2, new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
        int length = course.size();
        int i = 1;
//        for (UsersCourse c: course) {
//            String place = c.getPlace();
//            String time = c.getTime();
//            if ( userscourse.getTime().equals(time)  || userscourse.getPlace().equals(place)){
//                break;
//            }else if (i == length ){
//                template.update(sql,userscourse.getTime(),userscourse.getPlace(),userscourse.getCredit(),userscourse.getNumber());
//            }
//            i++;
//        }

    }
    //该课程的学生总数
    public int findStudentNumber(String r_id,String number){
//        String sql="SELECT COUNT(*)FROM users,timetable,middle WHERE r_id='1' AND username=u AND number=n AND number="+number+" and r_id="+r_id+" ";
        String sql="SELECT COUNT(*) FROM users,curriculum,use_cur WHERE r_id=1 AND username=u_id AND c_id=number AND number="+number+" ";
        return template.queryForObject(sql,Integer.class);
    }
    //某一时间使用的教室
    public List<UsersCourse> findCourseAndRoom(String s) {
        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place AND TIME='"+s+"'order by number";
        return template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
    }
    public int courseAndRoomCount(String s) {
        String sql = "SELECT count(*) FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place and time='"+s+"' order by number";
        return template.queryForObject(sql,Integer.class);
    }
    //查看某个课表与教室的关联情况
    public UsersCourse findOneCourseAndRoon(String number) {
        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place AND number="+number+" ";
        String sql1="SELECT*FROM curriculum WHERE number="+number+" ";
        String sql2 ="SELECT count(*) FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place AND number="+number+" ";
        int count = template.queryForObject(sql2, Integer.class);
        return count == 1? template.queryForObject(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class)) : template.queryForObject(sql1,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
    }

    public int findCourseHavePlaceCount(Map<String, String[]> condition) {
        String sql = "SELECT COUNT(*) FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    public List<UsersCourse> findCourseHavePlace(int start, int rows, Map<String, String[]> condition) {
        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom WHERE t_id=number AND p_id=place ";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        find(condition,sb,params);
        //添加分页查询
        sb.append(" order by number limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class), params.toArray());

    }
    //课表和地点关联  已有关联的不执行
    public void conNumAndPla(String number, String place) {
        String sql = "INSERT INTO cur_cla VALUES(?,?)";
        String sql1 = "SELECT COUNT(*)FROM cur_cla WHERE t_id="+number+" ";//是否已关联
        Integer count = template.queryForObject(sql1, Integer.class);
        if (count != 1){
            template.update(sql,number,place);
        }
    }
    //判断当前学期
    public int findTerm(String username) {
        String sql = "select*from users where username="+username+" ";
        Users users = template.queryForObject(sql, new BeanPropertyRowMapper<Users>(Users.class));
        String grade = users.getGrade();
        Calendar calendar = Calendar.getInstance();
        int mouth = calendar.get(Calendar.MONTH);//当前月份
        int term = 0;
        if ("大四".equals(grade)){
            term  = 7;
            if (mouth <= 6){
                term += 1;
            }
        }
        if ("大三".equals(grade)){
            term  = 5;
            if (mouth <= 6){
                term += 1;
            }
        }
        if ("大二".equals(grade)){
            term  = 3;
            if (mouth <= 6){
                term += 1;
            }
        }
        if ("大一".equals(grade)){
            term  = 1;
            if (mouth <= 6){
                term += 1;
            }
        }
        return term;
    }


    /**
     * 前端的Ajax请求 学科分类
     */
    public List<Type> findType() {
        String sql ="select*from subject_type";
        return template.query(sql,new BeanPropertyRowMapper<Type>(Type.class));
    }

    public List<Subject> findSubject(int type) {
        String sql = "select s.*from subject s,subject_type t where  s.type=t.type and t.type="+type;
        return template.query(sql,new BeanPropertyRowMapper<Subject>(Subject.class));
    }

    public List<UsersCourse> arrCourse(int type) {
        String sql = "SELECT curriculum.*,classroom.* FROM curriculum,cur_cla,classroom,SUBJECT WHERE t_id=curriculum.number AND p_id=place AND curriculum.number=subject.number AND subject.type="+type;
        return template.query(sql,new BeanPropertyRowMapper<UsersCourse>(UsersCourse.class));
    }


}
