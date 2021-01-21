package service.impl;

import dao.UserDao;
import dao.impl.UsersDaoImpl;
import domain.*;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UsersServiceImpl implements UserService {
    private UsersDaoImpl dao = new UsersDaoImpl();
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<Users> findAll1() {
        return dao.findAll1();
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }
    public void addCourse(String username,String number,String r_id){
        dao.addCourse(username,number,r_id);
    }
    public void addCourse(UsersCourse usersCourse){
        dao.addCourse(usersCourse);
    }
    @Override
    public void deleteUser(String id) {

    }
    //学生删除自己的选课
    public void deleteCourse(String username,String number){
        dao.delete(username,number);
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void delSelectedUser(String[] ids) {

    }
    //查找教室
    public List<Room> findRoom(){
        return dao.findRoom();
    }
    public int findRoomCount(){
        return dao.findRoomCount();
    }
    //某一时间使用的教室
    public List<UsersCourse> findCourseAndRoom(String s){
        return dao.findCourseAndRoom(s);
    }
    //int 类型 查找所有的教室和课程数目
    public int courseAndRoomCount(String s){
        return dao.courseAndRoomCount(s);
    }
    //将学期课表存入到List集合中
    public List<UsersCourse> findTermCourse(String username,String r_id, int term){
        List<UsersCourse> list = dao.findTermCourse(username,r_id,term);
        return list;
    }
    @Override
    public PageBean<Users> findUsersByPage(String _currentPage, String _rows, Map<String, String[]> condition,String r_id) {
        //转换为字符串
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建空的PageBean的空对象
        PageBean<Users> pb = new PageBean<Users>();
        //调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition,r_id);//首先查询总的数目
        pb.setTotalCount(totalCount);
        //计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        //设置当前页面和行数的参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
       //处理查询零条记录的异常
        try{
            List<Users> list = dao.findByPage1(start,rows,condition,r_id);//查询详细信息
            pb.setList(list);
        }catch (Exception e){
            pb.setCurrentPage(1);
            pb.setTotalCount(0);
        }

        return pb;
    }

    /**
     * 管理员查看所有学生所选的课程
     * @param _currentPage
     * @param _rows
     * @param condition
     * @param r_id
     * @return
     */
    public PageBean<SandTCourse> findSandTByPage (String _currentPage, String _rows, Map<String, String[]> condition, String r_id) throws SQLException {
        int currentPage = Integer.parseInt(_currentPage);//当前页面
        int rows = Integer.parseInt(_rows);
        PageBean<SandTCourse> pb = new PageBean<SandTCourse>();
        int totalCount = dao.findSandTCount(condition,r_id);
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;//每一页的起始点
        List<SandTCourse> list = dao.findByPageSandTCourse(start, rows,condition,r_id);//复杂查询
        pb.setList(list);
        return pb;
    }
    //某一学期
    public int findTerm(String username){
        return dao.findTerm(username);
    }

    //学生查看自己已选的课程 某一学期
    public PageBean<SandTCourse> findSandTByPage(String _currentPage, String _rows, Map<String, String[]> condition, String r_id,String username,int term){
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<SandTCourse> pb = new PageBean<SandTCourse>();
        int totalCount = dao.findSandTCount(condition,r_id,username,term);//数目
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        List<SandTCourse> list = null;
        try{
            list = dao.findByPageSandTCourse(start, rows,condition,r_id,username,term);//复杂查询  有异常该用户没有记录 却默认有一条记录
            pb.setList(list);
        }catch (Exception e){
            pb.setCurrentPage(1);
            pb.setTotalCount(0);
        }
        return pb;
    }
    //学生查看自己的课程无复杂条件的
    public PageBean<SandTCourse> findSandTByPage(String _currentPage, String _rows, String r_id,String username){
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<SandTCourse> pb = new PageBean<SandTCourse>();
        int totalCount = dao.findSandTCount(r_id,username);//数目
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        List<SandTCourse> list = null;
        try {
            list = dao.findByPageSandTCourse(start, rows,r_id,username);//复杂查询
            pb.setList(list);
        }catch (Exception e){
            pb.setList(list);
            pb.setCurrentPage(1);
            pb.setTotalCount(0);
        }
        return pb;
    }
//查看所有课程有教室关联好的
    public List<UsersCourse> findCourseByPage(){
        return dao.findByPageCourse();
    }
    public PageBean<UsersCourse> findCourseByPage(String _currentPage,String _rows){
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<UsersCourse> pb = new PageBean<UsersCourse>();
        int totalCount = dao.findCourseCount();
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        List<UsersCourse> list = dao.findByPageCourse(start, rows);//复杂查询
        pb.setList(list);
        return pb;
    }
    //查询该课程学生的信息
    public PageBean<SandTCourse> findCourseByPage(String _currentPage,String _rows,String r_id,String number){
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<SandTCourse> pb = new PageBean<SandTCourse>();
        int totalCount = dao.findStudentNumber(r_id,number);
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        try {
            List<SandTCourse> list = dao.findByPageCourse(start, rows,r_id,number);//复杂查询
            pb.setList(list);
        }catch (Exception e){
            pb.setTotalCount(0);
            pb.setCurrentPage(1);
        }

        return pb;
    }
    public PageBean<UsersCourse> findCourseByPage(Map<String, String[]> condition,String _currentPage,String _rows){
        int currentPage = Integer.parseInt(_currentPage);//当前页
        int rows = Integer.parseInt(_rows);

        PageBean<UsersCourse> pb = new PageBean<UsersCourse>();
        int totalCount = dao.findCourseCount1(condition);//复杂查询
        pb.setTotalCount(totalCount);

        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        List<UsersCourse> list = dao.findByPageCourse(start, rows, condition);//复杂查询
        pb.setList(list);
        return pb;
    }
    public PageBean<UsersCourse> findCourseByPage1(Map<String, String[]> condition,String _currentPage,String _rows){
        int currentPage = Integer.parseInt(_currentPage);//当前页
        int rows = Integer.parseInt(_rows);

        PageBean<UsersCourse> pb = new PageBean<UsersCourse>();
        int totalCount = dao.findCourseCount(condition);//复杂查询
        pb.setTotalCount(totalCount);

        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        List<UsersCourse> list = dao.findCourse(start, rows, condition);//复杂查询
        pb.setList(list);
        return pb;
    }

    //通过代号查找课程，来修改此课程
    public UsersCourse findCourseByNu(String number){
        UsersCourse course = dao.findCourseByNum(number);
        return course;
    }
    //修改课程
    public void updateCourse(UsersCourse userscourse){
       dao.updateCourse(userscourse);
    }
    //查看某个课表与教室的关联情况
    public UsersCourse findOneCourseAndRoon(String number) {
        return dao.findOneCourseAndRoon(number);
    }

    public PageBean<UsersCourse> findCourseHavePlace(Map<String, String[]> condition, String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<UsersCourse> pb = new PageBean<UsersCourse>();

        int totalCount = dao.findCourseHavePlaceCount(condition);
        pb.setTotalCount(totalCount);

        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage <= 0) {//不能点击第0个页面
            currentPage = 1;
        }
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int start = (currentPage - 1) * rows;
        List<UsersCourse> list = dao.findCourseHavePlace(start, rows, condition);//复杂查询
        pb.setList(list);
        return pb;
    }
    //课表和地点关联  已有关联的不执行
    public void conNumAndPla(String number, String place) {
        dao.conNumAndPla(number,place);
    }

    public void addCourseStudent(String username, String number) {
        dao.addCourseStudent(username,number);
    }
}
