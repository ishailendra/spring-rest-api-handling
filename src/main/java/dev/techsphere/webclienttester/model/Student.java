package dev.techsphere.webclienttester.model;

public class Student {

    private Integer id;
    private String name;
    private Double cgpa;
    private String address;

    public Student() {
        super();
    }

    public Student(Integer id, String name, Double cgpa, String address) {
        super();
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
        this.address = address;
    }

    public Student(String name, Double cgpa, String address) {
        super();
        this.name = name;
        this.cgpa = cgpa;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getCgpa() {
        return cgpa;
    }
    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                ", address='" + address + '\'' +
                '}';
    }
}
