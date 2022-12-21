package com.formacionbdi.springboot.app.covinic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	private Long identificacion;

	@NotBlank
	@Size(max = 15)
	private String telefono;

	@NotBlank
	@Size(max = 40)

	private String nombre;

}
