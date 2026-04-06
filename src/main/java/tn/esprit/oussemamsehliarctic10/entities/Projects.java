package tn.esprit.oussemamsehliarctic10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
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

    //  Projects est maintenant le OWNER de la relation
    // La FK project_details_id sera dans la table projects
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "project_details_id")
    ProjectDetails projectDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_agents",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id")
    )
    @JsonIgnore
    Set<Agents> agents = new HashSet<>();
}