/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Armando
 */
public class Producto {
    
    private String codigo;
    private String detalle;
    private Double cantidadSolicitada;
    private Double cantidadRecibida;
    private String unidad;
    private Double precioUnitario;
    private Double subTotal;

    public Producto() {
    }

    public Producto(String codigo, String detalle, Double cantidadSolicitada, String cantidadRecibida, String unidad, Double precioUnitario) {
        this.codigo = codigo;
        this.detalle = detalle;
        this.cantidadSolicitada = cantidadSolicitada;
        if(cantidadRecibida.equals("-")){
            this.cantidadRecibida = this.cantidadSolicitada;
        }else{
            this.cantidadRecibida = Double.parseDouble(cantidadRecibida.replace(',', '.'));
        }
        
        this.unidad = unidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = resultadoRedondeadoDosDecimales();
    }
        
    private Double resultadoRedondeadoDosDecimales(){        
        BigDecimal resultado = new BigDecimal(this.cantidadRecibida*this.precioUnitario).setScale(2, RoundingMode.HALF_DOWN);
        return resultado.doubleValue();
    }
    
    public Object[] convertirProductoObjetoEnArreglo(){
        Object[] arreglo = new Object[7];
        arreglo[0] = this.codigo;
        arreglo[1] = this.detalle;
        arreglo[2] = this.cantidadSolicitada;
        arreglo[3] = this.cantidadRecibida;
        arreglo[4] = this.unidad;
        arreglo[5] = obtenerPrecioFormatoMoneda(this.precioUnitario);
        arreglo[6] = obtenerPrecioFormatoMoneda(this.subTotal);
        return arreglo;
    }
    
    public String obtenerPrecioFormatoMoneda(Double valor){
        DecimalFormat formato = new DecimalFormat("₡ #,###,###.00");
        return formato.format(valor);
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Double cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
    
}
