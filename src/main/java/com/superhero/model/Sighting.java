package com.superhero.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Sighting {
	@Id
	@SequenceGenerator(
			name = "sighting_sequence",
			sequenceName = "sighting_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sighting_sequence"
			)
	private Long id;
	private String name;
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="id",insertable=false, updatable=false)
	private Location location;
}
