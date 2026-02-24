package tn.esprit.oussemamsehliarctic10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "project_details")
public class ProjectDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long detailsId;

    Double budget;
    String client;



    @OneToOne
    @JoinColumn(name = "project_projects_id", unique = true)
    private Projects project;
}