package tn.esprit.oussemamsehliarctic10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "agents")
public class Agents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long agentId;

    String name;
    Boolean available;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<CallSkills> skills;

    @OneToMany(mappedBy = "assignedAgent")
    @JsonIgnore
    Set<Calls> assignedCalls;

    @ManyToMany(mappedBy = "agents")   //  lié à Projects.agents
    @JsonIgnore
    Set<Projects> projects;
}