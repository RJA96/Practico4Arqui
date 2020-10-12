package com.grupo7.practico.controller.request;

import com.grupo7.practico.model.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockRequest {
  Producto producto;
  Integer cantidad;
}
