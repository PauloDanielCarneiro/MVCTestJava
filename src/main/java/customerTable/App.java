package customerTable;

import java.sql.Connection;

import customerTable.Controller.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        customerController controller = new customerController();
        controller.IniciaPrograma();
    }
}
