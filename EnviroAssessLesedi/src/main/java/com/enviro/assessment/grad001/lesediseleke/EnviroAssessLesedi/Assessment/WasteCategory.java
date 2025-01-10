package com.enviro.assessment.grad001.lesediseleke.EnviroAssessLesedi.Assessment;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

//Waste Category entity class, used for creating/representing the table in the database
@Entity
@Data
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id is being auto-generated
    private int id;

    //Category of waste must not be empty
    @NotBlank(message = "Required category name.")
    @Size(max = 100, message = "Name cannot be more than 100 characters.")
    private String name;

    //Optional waste description
    @Size(max = 450, message = "Description cannot be more than 450 characters.")
    private String description;

    //Waste disposal guidelines
    @Size(max = 1500, message = "Guidelines cannot be more than 1500 characters.")
    private String disposalGuideline;

    //Tips on recycling
    @Size(max = 1500, message = "Tips cannot be longer than 1500 characters.")
    private String tips;
}
