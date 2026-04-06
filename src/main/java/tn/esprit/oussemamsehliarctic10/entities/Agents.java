package tn.esprit.oussemamsehliarctic10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
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
    Set<CallSkills> skills = new HashSet<>();

    @OneToMany(mappedBy = "assignedAgent")
    @JsonIgnore
    Set<Calls> assignedCalls = new HashSet<>();

    @ManyToMany(mappedBy = "agents")
    @JsonIgnore
    Set<Projects> projects = new HashSet<>();

    //  Relation ManyToOne vers AISystems
    // Un agent peut être supervisé par un AI System
    @ManyToOne
    @JsonIgnore
    AISystems supervisingAiSystem;
}