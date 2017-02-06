package org.openape.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.openape.api.Messages;
import org.openape.api.environmentcontext.EnvironmentContext;
import org.openape.api.equipmentcontext.EquipmentContext;
import org.openape.api.listing.Listing;
import org.openape.api.rest.RESTPaths;
import org.openape.api.taskcontext.TaskContext;
import org.openape.api.usercontext.UserContext;
/** @author Lukas Smirek
*/

public class OpenAPEClient {
private Client client;
private WebTarget webResource;

public OpenAPEClient(String uri) {
	ClientConfig config = new ClientConfig();
	 client = ClientBuilder.newClient(config);
webResource = client.target(uri);
}
	
public URI createUserContext(UserContext userContext) throws URISyntaxException{
	Messages.
	return createContext(MessagesUserContextRESTInterface.UserContextURLWithoutID   , userContext);
}

public URI createEquipmentContext(EquipmentContext equipmentContext) throws URISyntaxException{
	return createContext(, equipmentContext);
}

public URI createEnvironmentContext(EnvironmentContext envrionmentContext) throws URISyntaxException{
	return createContext(, EnvironmentContext.class);
}

public URI createTaskContext(TaskContext taskContext) throws URISyntaxException{
	return createContext(, taskContext);
}

public Listing createListing(URI userContextUri, URI equipmentContextUri, URI environMentUri, URI taskContextUri){
	Response response = webResource.path(path).request(MediaType.APPLICATION_JSON_TYPE)
		    .post(Entity.entity(listingQuery,MediaType.APPLICATION_JSON));
		    		
			if (response.getStatus() != 201){
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
		    Listing output = response.readEntity(Listing.class);
		 	return output;
}





private URI createContext(String path,Object uploadContext ) throws URISyntaxException{
	
	Response response = webResource.path(path).request(MediaType.APPLICATION_JSON_TYPE)
    .post(Entity.entity(uploadContext,MediaType.APPLICATION_JSON));
    		
	if (response.getStatus() != 201){
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}
	
    String output = response.readEntity(String.class);
 
return new URI(response.getHeaderString("Location"));
}


	public void getListing(String url) {
		Invocation.Builder invocationBuilder = webResource.request();


			 
				

	}
	
	
	public File getResource(URI uri, String targetFile){

        Invocation.Builder invocationBuilder = webResource.path(uri.getPath()).request();

Response response = invocationBuilder.get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			InputStream in = (InputStream) response.readEntity(InputStream.class);
			
			File tf = new File(targetFile);
					try {
						Files.copy(in, tf.toPath());
		                 in.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}


		
		return tf;
	}

	public File getResource(String url, String targetFile) throws URISyntaxException {
		URI uri = new URI(url);
return 		getResource(uri, targetFile);
		
	}
	
}
