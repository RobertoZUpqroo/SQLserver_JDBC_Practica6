package com.mycompany.sqlserver_jdbc_practica6;

import java.sql.Date;
import java.util.List;


public class SQLserver_JDBC_Practica6 {

    public static void main(String[] args) {
        try {
            ConnectDb connectDb = new ConnectDb();

        //-------------------------MOSTRAR LIBROS POR VENCER------------------------------
            /*List<Libros> librosPorVencer = connectDb.mostrarLibrosPorVencer();
            System.out.println("\n-------------Esta es la lista de los libros por vencer---------------");
            System.out.println("\n");
            for (Libros libro : librosPorVencer) {
                System.out.println(libro.getNombreLibro() + " - Fecha de devolución: " + libro.getAñoEdicion());
            }
            System.out.println("\n");*/

        //---------------------MOSTRAR LIBROS DEVUELTOS POR DÍA EN ESPECIFICO------------------------------------
            /*String fechaEjemplo = "1999-11-13";
            List<Libros> librosDevueltosPorDia = connectDb.mostrarLibrosDevueltosPorDia(Date.valueOf(fechaEjemplo));
            System.out.println("\n-------------Esta es la lista de los libros devueltos por día---------------");
            System.out.println("\n");
            for (Libros libro : librosDevueltosPorDia) {
                System.out.println(libro.getNombreLibro() + " - Fecha de devolución: " + libro.getAñoEdicion());
            }
            System.out.println("\n");*/
            
        //-------------------------MOSTRAR LIBROS PRESTADOS------------------------------------------
            /*List<Libros> librosPrestados = connectDb.mostrarLibrosPrestados();
            System.out.println("\n-------------Lista de libros prestados---------------");
            for (Libros libro : librosPrestados) {
                System.out.println(libro.getNombreLibro());
            }
            System.out.println("\n");*/
            
        //-------------------------MOSTRAR LIBROS PRESTADOS POR DÍA EN ESPECIFICO------------------------------------------
            /*List<Libros> librosPrestadosPorDia = connectDb.mostrarLibroPrestadosPorDia(Date.valueOf("1999-11-18"));
            System.out.println("\n-------------Lista de libros prestados por día---------------");
            for (Libros libro : librosPrestadosPorDia) {
                System.out.println(libro.getNombreLibro());
            }
            System.out.println("\n");

        // Verificar si la lista está vacía
            if (librosPrestadosPorDia.isEmpty()) {
                System.out.println("No se encontraron libros prestados para la fecha proporcionada.");
            }*/

        //-------------------------INSERTAR USUARIOS------------------------------------------
            /*connectDb.insertarUsuario("Benito", "Juarez", "13.234.823-A", "Av Carro", "Quintana Roo", "Cancun", Date.valueOf("2000-07-06"));*/
            
            
        //-------------------------INSERTAR LIBRO------------------------------------------
            //connectDb.insertarLibro(10,"Ikigai", "El naranjo", "Ezequiel Dellutri", "Infantil", "España", 200, Date.valueOf("2023-01-01"), 179);
            
            

        //-------------------------ACTUALIZAR USUARIO------------------------------------------
            //connectDb.actualizarUsuario(7, "Darius", "Martinez", "13.234.823-D", "Av Monarca", "Runaterra", "Freljord", Date.valueOf("2002-01-01"))
            
            ;

        //-------------------------ACTUALIZAR LIBROS------------------------------------------
            //connectDb.actualizarLibros(5, "El principe", "S.M.", "Maquiavelo", "Politico", "Italia", 210, Date.valueOf("1995-01-01"), 10300);
            
            
       
        //-----------------OBTENER LIBROS POR UN RANGO DE PRECIO MAYOR A ?------------------------
        /*List<Libros> librosPorPrecio = connectDb.obtenerLibrosPorPrecio(700);
            System.out.println("\n-------------Lista de libros mayores a la cantidad dada---------------");
            for (Libros libro : librosPorPrecio) {
                System.out.println(libro.getNombreLibro() + " - Precio: " + libro.getPrecioLibro());
            }
            System.out.println("\n");*/

        
        //-------------------------MOSTRAR LIBROS PRESTADOS EN NOVIEMBRE--------------------------------------
            /*List<Libros> librosEnNoviembre = connectDb.librosPrestadosEnNoviembre();
            System.out.println("\n-------------Lista de libros prestados en el mes de noviembre---------------");
            for (Libros libro : librosEnNoviembre) {
                System.out.println(libro.getNombreLibro());
            }
            System.out.println("\n");*/
            
            
         //-------------------------VECES QUE SE HA PRESTADO CADA LIBRO----------------------------------------
        /*List<LibrosPrestados> librosPrestadosPorLibro = connectDb.obtenerVecesPrestadoPorLibro();
        System.out.println("\n-------------Veces que se ha prestado cada libro---------------");
        for (LibrosPrestados librosPrestados : librosPrestadosPorLibro) {
            System.out.println(librosPrestados.getNombreLibro() + " - Veces prestado: " + librosPrestados.getVecesPrestado());
        }
        System.out.println("\n");*/
        
        
        //----------------------------------OBTENER PEDIDOS AUTORES NO ESPAÑOLES---------------------------------
            /*List<PedidoLibro> pedidosAutoresNoEspanoles = connectDb.obtenerPedidosAutoresNoEspanoles();
            
            System.out.println("\n-------------Pedidos de libros cuyos autores no son españoles---------------\n");
            for (PedidoLibro pedido : pedidosAutoresNoEspanoles) {
                System.out.println("Código Pedido: " + pedido.getCodigoPedido());
                System.out.println("Código Libro: " + pedido.getCodigoLibro());
                System.out.println("Nombre Libro: " + pedido.getNombreLibro());
                System.out.println("Autor Libro: " + pedido.getAutorLibro());
                System.out.println("Fecha Pedido: " + pedido.getFechaPedido());
                System.out.println("Cantidad: " + pedido.getCantidad());
                System.out.println("\n");
            }*/
            
        // ----------------------------------------MOSTRAR LIBROS POR USUARIO-------------------------------------------
            /*List<UsuarioLibros> librosPorUsuario = connectDb.obtenerLibrosPorUsuario();
            System.out.println("\n-------------Lista de libros por usuario---------------");
            for (UsuarioLibros usuarioLibros : librosPorUsuario) {
                System.out.println("Usuario: " + usuarioLibros.getNombreUsuario() + " " + usuarioLibros.getApellidosUsuario() +
                                   " - Libro: " + usuarioLibros.getNombreLibro());
            }
            System.out.println("\n");*/

        // ---------------------MOSTRAR LIBROS POR MES------------------------------
            /*List<LibrosPorMes> librosPorMesList = connectDb.obtenerLibrosPorMes();
            System.out.println("\n-------------Libros sacados por mes---------------");
            for (LibrosPorMes librosPorMes : librosPorMesList) {
                System.out.println("Mes: " + librosPorMes.getMes() + " - Libro: " + librosPorMes.getNombreLibro() + " - Cantidad: " + librosPorMes.getCantidad());
            }
            System.out.println("\n");*/
            
            
            
        // ---------------------MOSTRAR RESUMEN DE LIBROS Y USUARIOS------------------------------
        /*List<String> resumenList = connectDb.obtenerResumen();
        System.out.println("\n-------------Resumen de libros y usuarios---------------");
        for (String resumen : resumenList) {
            System.out.println(resumen);
        }
        System.out.println("\n");  */  
            
        //--------------------------------------------------------------------------------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}