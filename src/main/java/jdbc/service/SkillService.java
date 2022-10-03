package jdbc.service;

import jdbc.model.dao.SkillDao;
import jdbc.model.dto.SkillDto;
import jdbc.repository.SkillRepository;
import jdbc.service.converter.SkillConverter;

import java.util.List;
import java.util.stream.Collectors;


public class SkillService {
    private SkillRepository repository;
    private SkillConverter skillConverter;

    public SkillService(SkillRepository repository, SkillConverter skillConverter) {
        this.repository = repository;
        this.skillConverter = skillConverter;
    }

    public SkillDto create(SkillDto skillDto) {
        SkillDao skillDao = repository.create(skillConverter.to(skillDto));
        return skillConverter.from(skillDao);
    }

    public List<SkillDto> findAll() {
        return repository.findAll().stream()
                .map(skillConverter::from)
                .collect(Collectors.toList());
    }

    public SkillDto getByID(Integer id) {
        SkillDao skillDao = repository.getByID(id);
        return skillConverter.from(skillDao);
    }

    public SkillDto update(SkillDto skillDto) {
        SkillDao skillDao = repository.update(skillConverter.to(skillDto));
        return skillConverter.from(skillDao);
    }

    public void delete(SkillDto skillDto) {
        repository.delete(skillConverter.to(skillDto));
    }

}
