package tn.esprit.oussemamsehliarctic10.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.oussemamsehliarctic10.dto.ProjectDTO;
import tn.esprit.oussemamsehliarctic10.entities.Projects;


@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "projectsId", target = "id")
    @Mapping(source = "libelle", target = "libelle")
    @Mapping(source = "projectDetails.client", target = "client")
    ProjectDTO toDTO(Projects project);
}