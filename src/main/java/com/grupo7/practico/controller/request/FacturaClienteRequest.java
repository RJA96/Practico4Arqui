package com.grupo7.practico.controller.request;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request de factura para adaptarlo al front sin productos */
@Data
@NoArgsConstructor
public class FacturaClienteRequest {
  LocalDate fecha;
  Double monto;
  Integer cliente;
}
