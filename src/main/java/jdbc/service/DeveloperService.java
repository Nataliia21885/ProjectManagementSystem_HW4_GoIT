package jdbc.service;


import jdbc.model.dao.DeveloperDao;
import jdbc.model.dto.DeveloperDto;
import jdbc.repository.DeveloperRepository;
import jdbc.service.converter.DeveloperConverter;

import java.util.List;
import java.util.stream.Collectors;

public class DeveloperService {

    private DeveloperRepository repository;
    private DeveloperConverter developerConverter;

    public DeveloperService(DeveloperRepository repository, DeveloperConverter developerConverter) {
        this.repository = repository;
        this.developerConverter = developerConverter;
    }

    public DeveloperDto create(DeveloperDto developerDto) {
        DeveloperDao developerDao = repository.create(developerConverter.to(developerDto));
        return developerConverter.from(developerDao);
    }

    public List<DeveloperDto> findAll() {
        return repository.findAll().stream()
                .map(developerConverter::from)
                .collect(Collectors.toList());
    }

    public DeveloperDto getByID(Integer id) {
        DeveloperDao developerDao = repository.getByID(id);
        return developerConverter.from(developerDao);
    }

    public DeveloperDto update(DeveloperDto developerDto) {
        DeveloperDao developerDao = repository.update(developerConverter.to(developerDto));
        return developerConverter.from(developerDao);
    }

    public void delete(DeveloperDto developerDto) {
        repository.delete(developerConverter.to(developerDto));
    }

}
