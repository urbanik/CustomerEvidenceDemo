package com.inloopx.customerevidence.resource;

import com.inloopx.customerevidence.entity.Photo;
import com.inloopx.customerevidence.exception.ViolatedBusinessRule;
import com.inloopx.customerevidence.repository.PhotoRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

@Path("/images")
@Stateless
//@RolesAllowed({"admin", "user"})
public class PhotoResource {

    @EJB
    private PhotoRepository repository;

    @EJB
    private AuthResource authResource;

    @ConfigProperty(name = "images-path")
    @Inject
    String imagesPath;

    HashSet<String> acceptableMimeTypes = new HashSet<String>(){{
        add("image/jpg");
        add("image/png");
        add("image/jpeg");
    }};

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetails) throws IOException {

        byte[] bytes = IOUtils.toByteArray(uploadedInputStream);

        if(!isFilenameValid(fileDetails.getName())){
            throw new ViolatedBusinessRule("You used illegal characters!");
        }

        Tika tika = new Tika();
        String mimeType = tika.detect(bytes);;

        if(!acceptableMimeTypes.contains(mimeType)){
            throw new ViolatedBusinessRule("You can upload only photos!");
        }

        String randomFolderName = authResource.getRandomString(5) + "/";
        String path = imagesPath + randomFolderName;

        File newFolder = new File(path);
        newFolder.mkdir();

        String uploadedFileLocation = path  + fileDetails.getFileName();

        FileUtils.writeByteArrayToFile(new File(uploadedFileLocation), bytes, false);

        repository.saveModel(new Photo(fileDetails.getFileName(), randomFolderName + fileDetails.getFileName()));

        return Response
                .status(Response.Status.OK)
                .entity(fileDetails.getFileName())
                .build();
    }

    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
