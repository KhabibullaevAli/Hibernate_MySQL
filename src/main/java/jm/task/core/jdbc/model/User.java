package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return (sb.append("Users ID: ").append(this.id)
                .append(" name: ").append(this.name)
                .append(" lastName: ").append(this.lastName)
                .append(" age: ").append(this.age)).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (!name.equals(user.name)) return false;
        if (!lastName.equals(user.lastName)) return false;
        return age.equals(user.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }
}
