package tn.esprit.oussemamsehliarctic10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "projects")
public class Projects implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long projectsId;

    String libelle;
    LocalDate startDate;
    LocalDate endDate;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProjectDetails projectDetails;

    @ManyToMany
    @JoinTable(
            name = "project_agents",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id")
    )
    @JsonIgnore
    Set<Agents> agents;
}