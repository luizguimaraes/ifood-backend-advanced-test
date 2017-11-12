package br.com.ifood.backend.advanced.test.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

/**
 * Entidade que representa uma descrição climática. É identificado por um nome e
 * contém um genero musical associado e uma faixa de temperatura.
 */
@Entity
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String musicGenre;
	private double temperatureIntervalStart;
	private double temperatureIntervalEnd;

	// JPA use only
	private Weather() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMusicGenre() {
		return musicGenre;
	}

	public double getTemperatureIntervalStart() {
		return temperatureIntervalStart;
	}

	public double getTemperatureIntervalEnd() {
		return temperatureIntervalEnd;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Weather)) {
			return false;
		}
		return Objects.equals(name, ((Weather) obj).getName());
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", id).append("name", name).append("musicGenre", musicGenre)
				.append("temperatureIntervalStart", temperatureIntervalStart)
				.append("temperatureIntervalEnd", temperatureIntervalEnd).toString();
	}
}
