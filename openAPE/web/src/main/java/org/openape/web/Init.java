package org.openape.web;

import org.openape.server.database.mongoDB.DatabaseConnection;
import org.openape.server.database.resources.ResourceList;
import org.openape.server.requestHandler.EnvironmentContextRequestHandler;
import org.openape.server.requestHandler.EquipmentContextRequestHandler;
import org.openape.server.requestHandler.ListingRequestHandler;
import org.openape.server.requestHandler.ResourceDescriptionRequestHandler;
import org.openape.server.requestHandler.ResourceRequestHandler;
import org.openape.server.requestHandler.TaskContextRequestHandler;
import org.openape.server.requestHandler.UserContextRequestHandler;
import org.openape.server.rest.EnvironmentContextRESTInterface;
import org.openape.server.rest.EquipmentContextRESTInterface;
import org.openape.server.rest.ListingRESTInterface;
import org.openape.server.rest.ResourceDescriptionRESTInterface;
import org.openape.server.rest.ResourceRESTInterface;
import org.openape.server.rest.TaskContextRESTInterface;
import org.openape.server.rest.UserContextRESTInterface;

import spark.servlet.SparkApplication;

public class Init implements SparkApplication {

    @Override
    public void init() {
        // Start rest api and database connection.
        DatabaseConnection.getInstance();
        ResourceList.getInstance();
        new UserContextRESTInterface(new UserContextRequestHandler());
        new EnvironmentContextRESTInterface(new EnvironmentContextRequestHandler());
        new EquipmentContextRESTInterface(new EquipmentContextRequestHandler());
        new TaskContextRESTInterface(new TaskContextRequestHandler());
        new ResourceRESTInterface(new ResourceRequestHandler());
        new ResourceDescriptionRESTInterface(new ResourceDescriptionRequestHandler());
        new ListingRESTInterface(new ListingRequestHandler());

    }

}
