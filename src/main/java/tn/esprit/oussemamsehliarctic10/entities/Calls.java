package tn.esprit.oussemamsehliarctic10.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "calls")
public class Calls implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long callsId;

    LocalDateTime callsDateTime;
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    CallStatus status;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<CallSkills> requiredSkills;

    @ManyToOne
    Agents assignedAgent;

    @ManyToOne
    AISystems assignedAiSystem;
}