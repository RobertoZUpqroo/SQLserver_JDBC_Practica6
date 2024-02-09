package com.mycompany.sqlserver_jdbc_practica6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class ConnectDb {
    private static final String hostname = "localhost";
    private static final String sqlInstanceName = "DESKTOP-6RF8J0P\\MSSQLSERVER01";
    private static final String sqlDatabase = "Libreria";
    private static final String sqlUser = "sa";
    private static final String sqlPassword = "123";

    private static String getConnectURL() {
        return "jdbc:sqlserver://" + hostname + ":1433" 
            + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
            + ";trustServerCertificate=true";
    }

    private Connection AbrirConexion() throws SQLException {
        return DriverManager.getConnection(getConnectURL(), sqlUser, sqlPassword);
    }

    private void CerrarConexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-------------------LIBROS PRESTADOS POR VENCER---------------------------
        public List<Libros> mostrarLibrosPorVencer() {
        List<Libros> librosPorVencer = new ArrayList<>();
        try (Connection connection = AbrirConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Libros l JOIN Prestamos p ON l.Codigo_libro = p.Codigo_libro WHERE DATEDIFF(day, GETDATE(), p.Fecha_devolucion) <= 7");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Libros libro = new Libros();
                libro.setCodigoLibro(resultSet.getInt("Codigo_libro"));
                libro.setNombreLibro(resultSet.getString("Nombre_libro"));
                libro.setEditorial(resultSet.getString("Editorial"));
                libro.setAutor(resultSet.getString("Autor"));
                libro.setGenero(resultSet.getString("Genero"));
                libro.setPaisAutor(resultSet.getString("Pais_autor"));
                libro.setNumeroPaginas(resultSet.getInt("Numero_paginas"));
                libro.setAñoEdicion(resultSet.getDate("Año_edicion"));
                libro.setPrecioLibro(resultSet.getDouble("Precio_libro"));

                librosPorVencer.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librosPorVencer;
    }
    
        //-------------------LIBROS DEVUELTOS POR DÍA -----------
        
         public List<Libros> mostrarLibrosDevueltosPorDia(Date dia) {
    List<Libros> librosDevueltosPorDia = new ArrayList<>();
    try (Connection connection = AbrirConexion();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT * FROM Libros l JOIN Prestamos p ON l.Codigo_libro = p.Codigo_libro WHERE CONVERT(DATE, p.Fecha_devolucion) = ?")) {

        preparedStatement.setDate(1, dia);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Libros libro = new Libros(
                        resultSet.getInt("Codigo_libro"),
                        resultSet.getString("Nombre_libro"),
                        resultSet.getString("Editorial"),
                        resultSet.getString("Autor"),
                        resultSet.getString("Genero"),
                        resultSet.getString("Pais_autor"),
                        resultSet.getInt("Numero_paginas"),
                        resultSet.getDate("Año_edicion"),
                        resultSet.getDouble("Precio_libro")
                );

                librosDevueltosPorDia.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return librosDevueltosPorDia;
}

        //-------------------LIBROS PRESTADOS ------------------------
        
        public List<Libros> mostrarLibrosPrestados() {
    List<Libros> librosPrestados = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT l.* FROM Libros l " +
                       "JOIN Prestamos p ON l.Codigo_libro = p.Codigo_libro";

        connection = AbrirConexion();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Libros libro = new Libros(
                resultSet.getInt("Codigo_libro"),
                resultSet.getString("Nombre_libro"),
                resultSet.getString("Editorial"),
                resultSet.getString("Autor"),
                resultSet.getString("Genero"),
                resultSet.getString("Pais_autor"),
                resultSet.getInt("Numero_paginas"),
                resultSet.getDate("Año_edicion"),
                resultSet.getDouble("Precio_libro")
            );

            librosPrestados.add(libro);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return librosPrestados;
}
        
        //-------------------LIBROS PRESTADOS POR DÍA -------------------
        
    public List<Libros> mostrarLibroPrestadosPorDia(Date dia) {
    List<Libros> librosPrestadosPorDia = new ArrayList<>();

    try (Connection connection = AbrirConexion();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT l.* FROM Libros l JOIN Prestamos p ON l.Codigo_libro = p.Codigo_libro WHERE CONVERT(DATE, p.Fecha_salida) = ?");
         ) {

        preparedStatement.setDate(1, dia);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Libros libro = new Libros(
                        resultSet.getInt("Codigo_libro"),
                        resultSet.getString("Nombre_libro"),
                        resultSet.getString("Editorial"),
                        resultSet.getString("Autor"),
                        resultSet.getString("Genero"),
                        resultSet.getString("Pais_autor"),
                        resultSet.getInt("Numero_paginas"),
                        resultSet.getDate("Año_edicion"),
                        resultSet.getDouble("Precio_libro")
                );

                librosPrestadosPorDia.add(libro);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
    }

    return librosPrestadosPorDia;
}
        //-------------------------------INSERTAR USUARIO----------------------------------------
        
        public void insertarUsuario(String nombre, String apellido, String dni, String domicilio, String poblacion, String provincia, Date fechaNacimiento) {
        try (Connection connection = AbrirConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Usuarios (Nombre_usuario, Apellidos_usuario, DNI, Domicilio, Poblacion, Provincia, Fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, dni);
            preparedStatement.setString(4, domicilio);
            preparedStatement.setString(5, poblacion);
            preparedStatement.setString(6, provincia);
            preparedStatement.setDate(7, fechaNacimiento);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario insertado correctamente!!");
            } else {
                System.out.println("Error al insertar usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }
        
        //-------------------------------INSERTAR LIBRO----------------------------------------
        public void insertarLibro(int codigoLibro, String nombreLibro, String editorial, String autor, String genero, String paisAutor, int numeroPaginas, Date anoEdicion, double precioLibro) {
        try (Connection connection = AbrirConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Libros (Codigo_libro, Nombre_libro, Editorial, Autor, Genero, Pais_autor, Numero_paginas, Año_edicion, Precio_libro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, codigoLibro);
            preparedStatement.setString(2, nombreLibro);
            preparedStatement.setString(3, editorial);
            preparedStatement.setString(4, autor);
            preparedStatement.setString(5, genero);
            preparedStatement.setString(6, paisAutor);
            preparedStatement.setInt(7, numeroPaginas);
            preparedStatement.setDate(8, anoEdicion);
            preparedStatement.setDouble(9, precioLibro);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Libro insertado correctamente!!");
            } else {
                System.out.println("Error al insertar libro.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting book: " + e.getMessage());
        }
    }

               public void actualizarUsuario(int idUsuario, String nuevoNombre, String nuevoApellido, String nuevoDni, String nuevoDomicilio, String nuevaPoblacion, String nuevaProvincia, Date nuevaFechaNacimiento) {
    try (Connection connection = AbrirConexion();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE Usuarios SET Nombre_usuario = ?, Apellidos_usuario = ?, DNI = ?, Domicilio = ?, Poblacion = ?, Provincia = ?, Fecha_nacimiento = ? WHERE Codigo_usuario = ?"
         )) {

        preparedStatement.setString(1, nuevoNombre);
        preparedStatement.setString(2, nuevoApellido);
        preparedStatement.setString(3, nuevoDni);
        preparedStatement.setString(4, nuevoDomicilio);
        preparedStatement.setString(5, nuevaPoblacion);
        preparedStatement.setString(6, nuevaProvincia);
        preparedStatement.setDate(7, nuevaFechaNacimiento);
        preparedStatement.setInt(8, idUsuario);

        preparedStatement.executeUpdate();
        System.out.println("Usuario actualizado correctamente!!");
    } catch (SQLException e) {
        System.out.println("Error updating user: " + e.getMessage());
    }
}

// ------------------------------- ACTUALIZAR LIBROS ------------------------------
public void actualizarLibros(int codigoLibro, String nuevoNombreLibro, String nuevaEditorial, String nuevoAutor, String nuevoGenero, String nuevoPaisAutor, int nuevoNumeroPaginas, Date nuevoAnoEdicion, double nuevoPrecioLibro) {
    try (Connection connection = AbrirConexion();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE Libros SET Nombre_libro = ?, Editorial = ?, Autor = ?, Genero = ?, Pais_autor = ?, Numero_paginas = ?, Año_edicion = ?, Precio_libro = ? WHERE Codigo_libro = ?"
         )) {

        preparedStatement.setString(1, nuevoNombreLibro);
        preparedStatement.setString(2, nuevaEditorial);
        preparedStatement.setString(3, nuevoAutor);
        preparedStatement.setString(4, nuevoGenero);
        preparedStatement.setString(5, nuevoPaisAutor);
        preparedStatement.setInt(6, nuevoNumeroPaginas);
        preparedStatement.setDate(7, nuevoAnoEdicion);
        preparedStatement.setDouble(8, nuevoPrecioLibro);
        preparedStatement.setInt(9, codigoLibro);

        preparedStatement.executeUpdate();
        System.out.println("Libro actualizado correctamente!!");
    } catch (SQLException e) {
        System.out.println("Error updating book: " + e.getMessage());
    }
}

            //---------------CONTAR TOTAL DE LIBROS---------------------
        public List<Libros> obtenerLibrosPorPrecio(double precioMinimo) {
        List<Libros> librosPorPrecio = new ArrayList<>();
        try (Connection connection = AbrirConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Libros WHERE Precio_libro > ?");
        ) {
            preparedStatement.setDouble(1, precioMinimo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Libros libro = new Libros(
                            resultSet.getInt("Codigo_libro"),
                            resultSet.getString("Nombre_libro"),
                            resultSet.getString("Editorial"),
                            resultSet.getString("Autor"),
                            resultSet.getString("Genero"),
                            resultSet.getString("Pais_autor"),
                            resultSet.getInt("Numero_paginas"),
                            resultSet.getDate("Año_edicion"),
                            resultSet.getDouble("Precio_libro")
                    );
                    librosPorPrecio.add(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return librosPorPrecio;
    }
        
        //-----------------------LIBROS PRESTADOS EN NOVIEMBRE------------------------------------
        public List<Libros> librosPrestadosEnNoviembre() {
    List<Libros> librosEnNoviembre = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT * FROM Libros l " +
                       "JOIN Prestamos p ON l.Codigo_libro = p.Codigo_libro " +
                       "WHERE MONTH(p.Fecha_salida) = 11";

        connection = AbrirConexion();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Libros libro = new Libros(
                resultSet.getInt("Codigo_libro"),
                resultSet.getString("Nombre_libro"),
                resultSet.getString("Editorial"),
                resultSet.getString("Autor"),
                resultSet.getString("Genero"),
                resultSet.getString("Pais_autor"),
                resultSet.getInt("Numero_paginas"),
                resultSet.getDate("Año_edicion"),
                resultSet.getDouble("Precio_libro")
            );

            librosEnNoviembre.add(libro);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return librosEnNoviembre;
}
     
    //--------------------------NUMERO DE VECES QUE LOS LIBROS HAN SIDO PRESTADOS----------------------------------------
        
        public List<LibrosPrestados> obtenerVecesPrestadoPorLibro() {
    List<LibrosPrestados> librosPrestadosPorLibro = new ArrayList<>();
    try (Connection connection = AbrirConexion();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(
                 "SELECT l.Codigo_libro, l.Nombre_libro, COUNT(p.Codigo_libro) as Veces_prestado " +
                 "FROM Libros l " +
                 "LEFT JOIN Prestamos p ON l.Codigo_libro = p.Codigo_libro " +
                 "GROUP BY l.Codigo_libro, l.Nombre_libro")) {

        while (resultSet.next()) {
            LibrosPrestados librosPrestados = new LibrosPrestados(
                resultSet.getInt("Codigo_libro"),
                resultSet.getString("Nombre_libro"),
                resultSet.getInt("Veces_prestado")
            );

            librosPrestadosPorLibro.add(librosPrestados);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return librosPrestadosPorLibro;
}
        
        //-------------------PEDIDOS DE LIBROS CUYOS AUTORES NO SEAN ESPAÑOLES---------------------------
    public List<PedidoLibro> obtenerPedidosAutoresNoEspanoles() {
        List<PedidoLibro> pedidos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = AbrirConexion();
            String query = "SELECT p.*, l.Nombre_libro, l.Autor " +
                           "FROM Pedidos p " +
                           "JOIN Libros l ON p.Codigo_libro = l.Codigo_libro " +
                           "WHERE NOT l.Pais_autor = 'España'";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int codigoPedido = resultSet.getInt("Codigo_pedido");
                int codigoLibro = resultSet.getInt("Codigo_libro");
                String nombreLibro = resultSet.getString("Nombre_libro");
                String autorLibro = resultSet.getString("Autor");
                Date fechaPedido = resultSet.getDate("Fecha_pedido");
                int cantidad = resultSet.getInt("Cantidad");

                PedidoLibro pedido = new PedidoLibro(codigoPedido, codigoLibro, nombreLibro, autorLibro, fechaPedido, cantidad);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                CerrarConexion(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pedidos;
    }
    
        //--------------------OBTENER LIBROS POR USUARIO----------------------------
    
        public List<UsuarioLibros> obtenerLibrosPorUsuario() {
        List<UsuarioLibros> librosPorUsuario = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT u.Nombre_usuario, u.Apellidos_usuario, l.Nombre_libro " +
                           "FROM Usuarios u " +
                           "JOIN Prestamos p ON u.Codigo_usuario = p.Codigo_usuario " +
                           "JOIN Libros l ON p.Codigo_libro = l.Codigo_libro";

            connection = AbrirConexion();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombreUsuario = resultSet.getString("Nombre_usuario");
                String apellidosUsuario = resultSet.getString("Apellidos_usuario");
                String nombreLibro = resultSet.getString("Nombre_libro");

                UsuarioLibros usuarioLibros = new UsuarioLibros(nombreUsuario, apellidosUsuario, nombreLibro);
                librosPorUsuario.add(usuarioLibros);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null && !connection.isClosed()) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return librosPorUsuario;
    }
        
        //--------------------OBTENER LIBROS POR MES----------------------------
        
            public List<LibrosPorMes> obtenerLibrosPorMes() {
        List<LibrosPorMes> librosPorMesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = AbrirConexion();
            String query = "SELECT MONTH(p.Fecha_salida) AS Mes, l.Nombre_libro, COUNT(*) AS Cantidad " +
                           "FROM Prestamos p " +
                           "JOIN Libros l ON p.Codigo_libro = l.Codigo_libro " +
                           "GROUP BY MONTH(p.Fecha_salida), l.Nombre_libro " +
                           "ORDER BY Mes, Cantidad DESC";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int mes = resultSet.getInt("Mes");
                String nombreLibro = resultSet.getString("Nombre_libro");
                int cantidad = resultSet.getInt("Cantidad");

                LibrosPorMes librosPorMes = new LibrosPorMes(mes, nombreLibro, cantidad);
                librosPorMesList.add(librosPorMes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                CerrarConexion(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return librosPorMesList;
    }
        
        //--------------------OBTENER RESUMEN----------------------------    
            
            public List<String> obtenerResumen() {
        List<String> resumenList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = AbrirConexion();
            String query = "SELECT l.Nombre_libro, l.Autor, l.Editorial, u.Nombre_usuario " +
                           "FROM Prestamos p " +
                           "JOIN Libros l ON p.Codigo_libro = l.Codigo_libro " +
                           "JOIN Usuarios u ON p.Codigo_usuario = u.Codigo_usuario";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombreLibro = resultSet.getString("Nombre_libro");
                String autor = resultSet.getString("Autor");
                String editorial = resultSet.getString("Editorial");
                String nombreUsuario = resultSet.getString("Nombre_usuario");

                String resumen = nombreLibro + "; " + autor + "; " + editorial + "; " + nombreUsuario;
                resumenList.add(resumen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                CerrarConexion(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resumenList;
}
        
        //-------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
            ConnectDb connectDb = new ConnectDb();
            connectDb.testConnection();
        }


        // Método de prueba de conexión
        private void testConnection() {
            Connection connection = null;
            try {
                connection = AbrirConexion();
                System.out.println("Connected to database successfully!!");
            } catch (SQLException e) {
                System.out.println("Error connecting to database: " + e.getMessage());
            } finally {
                CerrarConexion(connection);
            }
        }
        
}





