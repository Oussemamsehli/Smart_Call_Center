package tn.esprit.oussemamsehliarctic10.entities;

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
@Table(name = "ai_systems")
public class AISystems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long aiSystemId;

    String type;
    Boolean available;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<CallSkills> skills;

    @OneToMany(mappedBy = "assignedAiSystem")
    Set<Calls> assignedCalls;
}