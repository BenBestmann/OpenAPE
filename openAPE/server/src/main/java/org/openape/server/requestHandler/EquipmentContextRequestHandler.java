package org.openape.server.requestHandler;

import java.io.IOException;

import org.openape.api.DatabaseObject;
import org.openape.api.equipmentcontext.EquipmentContext;
import org.openape.api.usercontext.UserContext;
import org.openape.server.database.DatabaseConnection;
import org.openape.server.database.MongoCollectionTypes;
import org.openape.server.rest.EquipmentContextRESTInterface;

/**
 * Class with methods to manage equipment context on the server. It is used by
 * the rest API {@link EquipmentContextRESTInterface} and uses the server
 * database {@link DatabaseConnection}.
 */
public class EquipmentContextRequestHandler {

    /**
     * Method to store a new equipment context into the server. It is used by
     * the rest API {@link EquipmentContextRESTInterface} and uses the server
     * database {@link DatabaseConnection}.
     *
     * @param equipmentContext
     *            to be stored.
     * @return the ID of the stored equipment context.
     * @throws IOException
     *             if a storage problem still occurs, after to many tries.
     * @throws IllegalArgumentException
     *             if the parameter is not a complete equipment context.
     */
    public String createEquipmentContext(Object equipmentContext) throws IOException,
            IllegalArgumentException {
        // get database connection.
        DatabaseConnection databaseconnection = DatabaseConnection.getInstance();
        // try to store data. Class cast exceptions will be thrown as illegal
        // argument exceptions. IO exceptions will just be thrown through.
        String id = null;
        try {
            id = databaseconnection.storeData(MongoCollectionTypes.EQUIPMENTCONTEXT,
                    (DatabaseObject) equipmentContext);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return id;
    }

    /**
     * Method to delete an existing equipment context from the server. It is
     * used by the rest API {@link EquipmentContextRESTInterface} and uses the
     * server database {@link DatabaseConnection}.
     *
     * @param id
     *            the ID of the equipment context to delete.
     * @return true if successful. Else an exception is thrown.
     * @throws IOException
     *             if a storage problem still occurs, after to many tries.
     * @throws IllegalArgumentException
     *             if the id is no valid id or not assigned.
     */
    public boolean deleteEquipmentContextById(String id) throws IOException,
            IllegalArgumentException {
        // get database connection.
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        boolean success = databaseConnection.deleteData(MongoCollectionTypes.EQUIPMENTCONTEXT, id);
        if (!success) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    /**
     * Method to get an existing equipment context from the server. It is used
     * by the rest API {@link EquipmentContextRESTInterface} and uses the server
     * database {@link DatabaseConnection}.
     *
     * @param id
     *            the ID of the requested equipment context.
     * @return requested equipment context.
     * @throws IOException
     *             if a storage problem still occurs, after to many tries.
     * @throws IllegalArgumentException
     *             if the id is no valid id or not assigned.
     */
    public EquipmentContext getEquipmentContextById(String id) throws IOException,
            IllegalArgumentException {
        // get database connection.
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        // Get the requested data.
        DatabaseObject result = databaseConnection.getData(MongoCollectionTypes.EQUIPMENTCONTEXT,
                id);

        // If the result is null the id is not found.
        if (result == null) {
            throw new IllegalArgumentException();
        }

        // convert into correct type.
        EquipmentContext returnObject;
        try {
            returnObject = (EquipmentContext) result;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
        return returnObject;
    }

    /**
     * Method to update an existing equipment context on the server. It is used
     * by the rest API {@link EquipmentContextRESTInterface} and uses the server
     * database {@link DatabaseConnection}.
     *
     * @param id
     *            the ID of the equipment context to update.
     * @param equipmentContext
     *            new version of the equipment context.
     * @return true if successful. Else an exception is thrown.
     * @throws IOException
     *             if a storage problem still occurs, after to many tries.
     * @throws IllegalArgumentException
     *             if the id is no valid id, not assigned or the equipment
     *             context is not valid.
     */
    public boolean updateEquipmentContextById(String id, Object equipmentContext)
            throws IOException, IllegalArgumentException {
        // get database connection.
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        // Update data. If a class cast exception occurs or the return value is
        // false the parameters is not valid and an illegal argument exception
        // is thrown. IO exceptions are thrown through.
        boolean success;
        try {
            success = databaseConnection.updateData(MongoCollectionTypes.EQUIPMENTCONTEXT,
                    (DatabaseObject) equipmentContext, id);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        if (!success) {
            throw new IllegalArgumentException();
        }
        return true;
    }
}