package jdbc.model.dao;


import java.sql.Date;
import java.util.Objects;

public class ProjectDao {
    Integer id;
    String projectName;
    String country;
    Integer cost;
    Integer companyId;
    Integer customerId;
    Date dateOfCreation;

    public ProjectDao(Integer id, String projectName, String country, Integer cost, Integer companyId,
                      Integer customerId, Date dateOfCreation) {
        this.id = id;
        this.projectName = projectName;
        this.country = country;
        this.cost = cost;
        this.companyId = companyId;
        this.customerId = customerId;
        this.dateOfCreation = dateOfCreation;
    }

    public ProjectDao(String projectName, String country, Integer cost, Integer companyId,
                      Integer customerId, Date dateOfCreation) {
        this.projectName = projectName;
        this.country = country;
        this.cost = cost;
        this.companyId = companyId;
        this.customerId = customerId;
        this.dateOfCreation = dateOfCreation;
    }

    public ProjectDao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDao that = (ProjectDao) o;
        return Objects.equals(projectName, that.projectName) && Objects.equals(country, that.country) && Objects.equals(cost, that.cost) && Objects.equals(companyId, that.companyId) && Objects.equals(customerId, that.customerId) && Objects.equals(dateOfCreation, that.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, country, cost, companyId, customerId, dateOfCreation);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName = '" + projectName + '\'' +
                ", country = '" + country + '\'' +
                ", cost = " + cost +
                ", companyId = " + companyId +
                ", customerId = " + customerId +
                ", dateOfCreation = " + dateOfCreation +
                '}';
    }
}
