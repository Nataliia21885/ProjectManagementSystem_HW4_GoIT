package jdbc.model.dao;

import java.util.Objects;

public class CompanyDao {
    Integer id;
    String name;
    String hrm;

    public CompanyDao(Integer id, String name, String hrm) {
        this.id = id;
        this.name = name;
        this.hrm = hrm;
    }

    public CompanyDao(String name, String hrm) {
        this.name = name;
        this.hrm = hrm;
    }

    public CompanyDao() {
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

    public String getHrm() {
        return hrm;
    }

    public void setHrm(String hrm) {
        this.hrm = hrm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDao that = (CompanyDao) o;
        return Objects.equals(name, that.name) && Objects.equals(hrm, that.hrm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hrm);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name = '" + name + '\'' +
                ", hrm = '" + hrm + '\'' +
                '}';
    }
}
