package com.grupo7.practico.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/** Request para agregar stock */
@Data
@NoArgsConstructor
public class StockRequest {
  Integer producto;
  Integer cantidad;
}
