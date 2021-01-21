package dao;


import domain.User;
import domain.Users;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {


    public List<User> findAll();
    public List<Users> findAll1();

    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int id);

    User findById(int i);

    void update(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition,String r_id);
    public int findCourseCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */

    List<Users> findByPage1(int start, int rows, Map<String, String[]> condition,String r_id);

    Users loginM(Users loginUsers) throws SQLException;
}
