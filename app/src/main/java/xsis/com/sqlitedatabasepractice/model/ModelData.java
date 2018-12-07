package xsis.com.sqlitedatabasepractice.model;

/**
 * Created by Irfan Naufal Ridi on 07/11/2018.
 */

public class ModelData {
    String name,gender;
    int height,age,id;

    public ModelData() {
    }

    public ModelData(String name, String gender, int height, int age) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
    }

    public ModelData(String name, String gender, int height, int age, int id) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
