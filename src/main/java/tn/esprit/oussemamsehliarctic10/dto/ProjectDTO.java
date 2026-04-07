package tn.esprit.oussemamsehliarctic10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectDTO {
    private Long id;
    private String libelle;
    private String client;
}