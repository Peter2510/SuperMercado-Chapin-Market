package com.peter.chapin_market.post_request;

import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Producto;

public class BodegaRequest {
    private Producto producto;
    private Bodega bodega;
	
    public Producto getProducto() {
		return producto;
	}
	
    public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
    public Bodega getBodega() {
		return bodega;
	}
	
    public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}
    
}
