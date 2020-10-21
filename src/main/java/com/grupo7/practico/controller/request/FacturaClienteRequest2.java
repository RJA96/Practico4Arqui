package com.grupo7.practico.controller.request;

import com.grupo7.practico.model.Factura;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request para agregar facturas */
@Data
@NoArgsConstructor
public class FacturaClienteRequest2 {
  Factura factura;
  Integer idCliente;
}
