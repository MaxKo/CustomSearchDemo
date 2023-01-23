package ua.kerberos.search.specification.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.persistence.*;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Entity
@Table(name = "POSITIONS")
@Data
@Accessors(chain = true)
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
