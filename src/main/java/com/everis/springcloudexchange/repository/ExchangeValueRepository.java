package com.everis.springcloudexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.springcloudexchange.model.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
	//Consulta personalizada findBy + nombre de la columna a filtrar
	//Filtra la consulta con dos atributos (columnas) en este caso por From y To
	//Compara Si son iguales las cadenas
	ExchangeValue findByFromAndTo(String from, String to);

}
