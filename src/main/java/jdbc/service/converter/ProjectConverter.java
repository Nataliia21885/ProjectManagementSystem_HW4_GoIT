package jdbc.service.converter;

import jdbc.model.dao.ProjectDao;
import jdbc.model.dto.ProjectDto;


public class ProjectConverter implements Converter<ProjectDto, ProjectDao> {
    @Override
    public ProjectDto from(ProjectDao entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(entity.getId());
        projectDto.setProjectName(entity.getProjectName());
        projectDto.setCountry(entity.getCountry());
        projectDto.setCompanyId(entity.getCompanyId());
        projectDto.setCustomerId(entity.getCustomerId());
        projectDto.setCost(entity.getCost());
        projectDto.setDateOfCreation(entity.getDateOfCreation());
        return projectDto;
    }

    @Override
    public ProjectDao to(ProjectDto entity) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setId(entity.getId());
        projectDao.setProjectName(entity.getProjectName());
        projectDao.setCountry(entity.getCountry());
        projectDao.setCompanyId(entity.getCompanyId());
        projectDao.setCustomerId(entity.getCustomerId());
        projectDao.setCost(entity.getCost());
        projectDao.setDateOfCreation(entity.getDateOfCreation());
        return projectDao;
    }
}
