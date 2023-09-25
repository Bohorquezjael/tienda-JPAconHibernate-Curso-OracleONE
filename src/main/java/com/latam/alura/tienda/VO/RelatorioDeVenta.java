package com.latam.alura.tienda.VO;

import java.time.LocalDate;

public class RelatorioDeVenta {

    private String nombreDeProducto;
    private Long cantidadProducto;
    private LocalDate fechaProducto;
    /**
     * @return the nombreDeProducto
     */
    public String getNombreDeProducto() {
        return nombreDeProducto;
    }
    /**
     * @param nombreDeProducto the nombreDeProducto to set
     */
    public void setNombreDeProducto(String nombreDeProducto) {
        this.nombreDeProducto = nombreDeProducto;
    }
    /**
     * @return the cantidadProducto
     */
    public Long getCantidadProducto() {
        return cantidadProducto;
    }
    /**
     * @param cantidadProducto the cantidadProducto to set
     */
    public void setCantidadProducto(Long CantidadProducto) {
        cantidadProducto = CantidadProducto ;
    }
    /**
     * @return the fechaProducto
     */
    public LocalDate getFechaProducto() {
        return fechaProducto;
    }
    /**
     * @param fechaProducto the fechaProducto to set
     */
    public void setFechaProducto(LocalDate fechaProducto) {
        this.fechaProducto = fechaProducto;
    }
    /**
     * @param nombreDeProducto
     * @param cantidadProducto
     * @param fechaProducto
     */
    public RelatorioDeVenta(String nombreDeProducto, Long CantidadProducto, LocalDate FechaProducto) {
        this.nombreDeProducto = nombreDeProducto;
        cantidadProducto = CantidadProducto;
        fechaProducto = FechaProducto;
    }

    @Override
	public String toString() {
		return "RelatorioDeVenta [nombreDeProducto=" + nombreDeProducto + ", cantidadProducto=" + cantidadProducto
				+ ", FechaDeUltimaVenta=" + fechaProducto + "]";
	}

}
