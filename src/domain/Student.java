package domain;

public class Student {
    private int username;
    private int password;
    private String name ;
    private String gender;
    private String classD;
    private String major;

    public Student(){

    }

    public Student(int username, int password, String name, String gender, String classD, String major) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.classD = classD;
        this.major = major;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassD() {
        return classD;
    }

    public void setClassD(String classD) {
        this.classD = classD;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
