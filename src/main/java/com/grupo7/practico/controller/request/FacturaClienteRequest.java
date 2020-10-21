package com.grupo7.practico.controller.request;

import com.grupo7.practico.model.Factura;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacturaClienteRequest {
  LocalDate fecha;
  Double monto;
  Integer cliente;
}
