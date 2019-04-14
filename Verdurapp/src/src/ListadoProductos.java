package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite el manejo de las principales funciones de la aplicacion
 *
 * @author: Armando Ayala C
 * @fecha: 07/05/2018
 */
public class ListadoProductos {

    //<editor-fold desc="atributos" defaultstate="collapsed">
    private List<Producto> listadoProductos_OC;
    private List<Producto> listadoProductos;
    private Double cantidadTotalListado;
    //</editor-fold>

    //<editor-fold desc="metodos" defaultstate="collapsed">
    
    /**
     * Constructor por defecto de la clase
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public ListadoProductos() {
        this.listadoProductos = new ArrayList<>();
        this.listadoProductos_OC = new ArrayList<>();
        this.cantidadTotalListado = new Double("0");
    }

    /**
     * Metodo que limpia los registros de la tabla
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public void limpiarListado() {
        this.listadoProductos.clear();
        this.cantidadTotalListado = new Double("0");
    }
    
    /**
     * Metodo que permite limpiar el listado de los productos de OC
     * @autor: Armando Ayala C
     * @fecha: 16/05/2018
     */
    public void limpiarListado_OC() {
        this.listadoProductos_OC.clear();
    }

    /**
     * Metodo que verifica que los productos para las OC no sean repetidos
     * @autor: Armando Ayala C
     * @fecha: 15/05/2018
     * @param codProducto
     * @return 
     */
    public boolean verificaProductoEnListado_OC(String codProducto) {

        boolean salida = false;
        for (Producto productoAux : this.listadoProductos_OC) {
            if (productoAux.getCodigo().equals(codProducto)) {
                salida = true;
                break;
            }
        }
        return salida;
    }
    
    /**
     * Metodo que permite verificar si el produto ya existe en el listado
     * general
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param codProducto
     * @return
     */
    public boolean verificaProductoEnListado(String codProducto) {

        boolean salida = false;
        for (Producto productoAux : this.listadoProductos) {
            if (productoAux.getCodigo().equals(codProducto)) {
                salida = true;
                break;
            }
        }
        return salida;
    }

    /**
     * Metodo que permite agregar un Producto en el listado
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param producto
     */
    public void agregarProductoListado_OC(Producto producto) {
        this.listadoProductos_OC.add(producto);
    }
    
    /**
     * Metodo que permite agregar el producto en el listado de productos de OC
     * @autor: Armando Ayala C
     * @fecha: 16/05/2018
     * @param producto 
     */
    public void agregarProductoListado(Producto producto) {
        this.listadoProductos.add(producto);       
    }

    /**
     * Metodo que modifica el producto ya ingresado en el listado
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param codProducto
     * @param cantidadSolicitada
     * @param cantidadRecibida
     */
    public void modificarCantidadesProductos(String codProducto, Float cantidadSolicitada, Float cantidadRecibida) {
        for (Producto productoAux : this.listadoProductos) {
            if (productoAux.getCodigo().equals(codProducto)) {
                productoAux.setCantidadSolicitada(productoAux.getCantidadSolicitada() + cantidadSolicitada);
                productoAux.setCantidadRecibida(productoAux.getCantidadRecibida() + cantidadRecibida);
                productoAux.setSubTotal(resultadoRedondeadoDosDecimales(Double.parseDouble(productoAux.getCantidadRecibida().toString()), productoAux.getPrecioUnitario()));

                break;
            }
        }
    }

    /**
     * Metodo que permite redondear el resultado de los datos
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param cantidadRecibida
     * @param precioUnitario
     * @return
     */
    public Double resultadoRedondeadoDosDecimales(Double cantidadRecibida, Double precioUnitario) {
        BigDecimal resultado = new BigDecimal(cantidadRecibida * precioUnitario).setScale(2, RoundingMode.HALF_DOWN);
        return resultado.doubleValue();
    }

    /**
     * Metodo que ordena el listado de forma alfabetica por detalle del producto
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public void ordenarAlfabeticamenteLista() {
        Collections.sort(this.listadoProductos, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                return o1.getDetalle().compareTo(o2.getDetalle());
            }
        });
        this.cantidadTotalListado = new Double("0");
        for(Producto productoAux : this.listadoProductos) {
            this.cantidadTotalListado += productoAux.getSubTotal();
        }
    }

    /**
     * Metodo que devuelve una matriz para mostrarse en el jtable
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @return
     */
    public Object[][] obtieneListadoProductosParaJTable() {
        Object[][] vector = new Object[this.listadoProductos.size()][7];
        Producto productoAux;

        for (int x = 0; x < this.getListadoProductos().size(); x++) {
            productoAux = this.getListadoProductos().get(x);
            vector[x] = productoAux.convertirProductoObjetoEnArreglo();
        }
        return vector;
    }

    /**
     * Metodo que devuelve el formato de moneda para un valor Float
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param valor
     * @return
     */
    public String obtenerPrecioFormatoMoneda(Double valor) {
        DecimalFormat formato = new DecimalFormat("₡ #,###,###.00");
        return formato.format(valor);
    }

    /**
     * Metodo que permite verificar que el archivo que se selecciona cuente con los campos suficientes
     * @autor: Armando Ayala C
     * @fecha: 21/05/2018
     * @param pArchivo
     * @param pTipo
     * @return 
     */
    public Boolean verificadorArchivoTab(File pArchivo, String pTipo){
        Boolean resultado = false;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(pArchivo),"ISO-8859-1");
            BufferedReader br = new BufferedReader(isr);
            String linea = br.readLine();
            String[] cantidadColumas = linea.split(";");
            if(pTipo.equals("OC") && cantidadColumas.length == 6){
                resultado = true;
            }else if(pTipo.equals("OEM") && cantidadColumas.length == 7){
                resultado = true;
            }
                        
        } catch (IOException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    /**
     * Metodo que permite agregar los productos de las OC para obtener los precios en el 
     * listado por producto
     * @autor: Armando Ayala C
     * @fecha: 16/05/2018
     * @param pArchivoCSV_OC 
     */
    public void agregarProductos_OC(File pArchivoCSV_OC) { 
        InputStreamReader isr = null;
        try {
            String linea;
            isr = new InputStreamReader(new FileInputStream(pArchivoCSV_OC), "ISO-8859-1");
            BufferedReader br = new BufferedReader(isr);
            limpiarListado_OC();
            Producto productoAux;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (!verificaProductoEnListado_OC(campos[0])) {
                    productoAux = new Producto(
                            campos[2],
                            campos[3],
                            new Double((campos[0].replace('.', ' ')).replaceAll(" ", "").replace(',', '.')),
                            "-",
                            campos[1],
                            Double.parseDouble((campos[4].replace('.', ' ')).replaceAll(" ", "").replace(',', '.'))
                    );
                    agregarProductoListado_OC(productoAux);
                }
            }               
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                isr.close();
            } catch (IOException ex) {
                Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Metodo que permite obtener el precio unitario de un producto del listado de OC
     * @autor: Armando Ayala C
     * @fecha: 16/05/2018
     * @param pCodProducto
     * @return 
     */
    public Double obtenerPrecioUnitarioPorCodProducto_OC(String pCodProducto){
        Double precio = new Double("0");
        for(Producto productoAux : this.listadoProductos_OC){
            if(productoAux.getCodigo().equals(pCodProducto)){
                precio = productoAux.getPrecioUnitario();
                break;
            }
        }
        return precio;
    }
    
    /**
     * Metodo que permite agregar los productos de las OEM para obtener los precios en el 
     * listado por producto
     * @autor: Armando Ayala C
     * @fecha: 16/05/2018
     * @param pArchivoCSV_OEM 
     */
    public void agregarProductos_OEM(File pArchivoCSV_OEM) {   
        InputStreamReader isr = null;
        try {
            String linea;
            isr = new InputStreamReader(new FileInputStream(pArchivoCSV_OEM), "ISO-8859-1");
            BufferedReader br = new BufferedReader(isr);
            limpiarListado();
            Producto productoAux;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (!verificaProductoEnListado(campos[3])) {                    
                    productoAux = new Producto(
                            campos[3],
                            campos[4],
                            Double.parseDouble(campos[0].replace(',', '.')),
                            campos[1],
                            campos[2],
                            obtenerPrecioUnitarioPorCodProducto_OC(campos[3])
                            //Float.parseFloat((campos[5].replace('.', ' ')).replaceAll(" ", "").replace(',', '.'))
                    );
                    agregarProductoListado(productoAux);
                } else {
                    if (campos[1].equals("-")) {
                        modificarCantidadesProductos(campos[3], Float.parseFloat(campos[0].replace(',', '.')), Float.parseFloat(campos[0].replace(',', '.')));
                    } else {
                        modificarCantidadesProductos(campos[3], Float.parseFloat(campos[0].replace(',', '.')), Float.parseFloat(campos[1].replace(',', '.')));
                    }
                }
            }
            ordenarAlfabeticamenteLista();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                isr.close();
            } catch (IOException ex) {
                Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    //</editor-fold>

    //<editor-fold desc="set's y get's" defaultstate="collapsed">
    public List<Producto> getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(List<Producto> listadoProductos) {
        this.listadoProductos = listadoProductos;
    }

    public Double getCantidadTotalListado() {
        return cantidadTotalListado;
    }

    public void setCantidadTotalListado(Double cantidadTotalListado) {
        this.cantidadTotalListado = cantidadTotalListado;
    }

    public List<Producto> getListadoProductos_OC() {
        return listadoProductos_OC;
    }

    public void setListadoProductos_OC(List<Producto> listadoProductos_OC) {
        this.listadoProductos_OC = listadoProductos_OC;
    }

    //</editor-fold>
    
}
