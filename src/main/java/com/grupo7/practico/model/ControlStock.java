package com.grupo7.practico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControlStock implements Serializable {

    @EmbeddedId
    ControlStockId controlStockId;

    Integer cantidadStock;
}
