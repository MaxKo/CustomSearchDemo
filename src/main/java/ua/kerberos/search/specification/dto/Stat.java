package ua.kerberos.search.specification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Stat {
	@Id
	Long regionId;
	Long amount;
}
