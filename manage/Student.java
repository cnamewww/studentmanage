package manage;

public class Student {
    private int id;
    private String studentId;
    private String name;
    private String gender;
    private int age;
    private String major;
    private String phone;

    public Student() {}

    public Student(int id, String studentId, String name, String gender, int age, String major, String phone) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.major = major;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

