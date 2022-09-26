package com.superhero.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


@Entity
@Table
public class Location {
	@Id
	@SequenceGenerator(
			name = "location_sequence",
			sequenceName = "location_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "location_sequence"
			)
	private Long id;
	private String name;
	private double latitude;
	private double longitude;
	@OneToMany(mappedBy = "location")
	private Set<Sighting> sightings;
}
