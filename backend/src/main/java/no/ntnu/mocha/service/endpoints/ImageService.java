package no.ntnu.mocha.service.endpoints;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import no.ntnu.mocha.domain.entities.Image;
import no.ntnu.mocha.domain.repository.ImageRepository;

/**
 * <h1>Business Logic Service class for Image</h1>
 * 
 * <p> Representing an Service class for the Image and implements the
 * Image Service interface with the additional methods. </p>
 * 
 * @version 22.04.2023
 * @since   22.04.2023
 */
@Service public class ImageService {

    @Autowired private ImageRepository imageRepository;

    /* Arrays defining valid extensions and content types that are allowed to be uploaded. */
    private final String[] FILE_EXTENSIONS = { "jpeg", "jpg", "png", "svg", "webp" };
    private final String[] CONTENT_TYPES = { "image/jpeg", "image/jpg", "image/svg+xml", "image/webp" };



    /**
     * Adds a new image. {@code isImageValid} checks if the Image File
     * is valid, and if not throws an {@code IOException}.
     * 
     * @param imageFile the uploaded file.
     * @throws Exception if the Image is not valid.
     */
    public Image addImage(MultipartFile imageFile) {
        try {
            if (isImageValid(imageFile)) {
                byte[] imageData = imageFile.getBytes();
                String fileExtension = getExtension(imageFile);
                String contentType = imageFile.getContentType();
                String alt = "";

                return imageRepository.save(
                    new Image(imageData, fileExtension, contentType, alt)
                );
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }


    /**
     * Returns the Image by it's given id.
     * 
     * @param id    the id of the Image.
     */
    public Image getImageById(long id) {
        return imageRepository.findById(id).orElse(null);
    }


    /**
     * Updates already existing Image.
     * 
     * @param imageFile the image file to be updatet.
     * @param id        the id of the Image to be updatet.
     * @throws  Exception if the Image is not valid.
     */
    public Image updateImage(long id, MultipartFile imageFile) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            try {
                if (isImageValid(imageFile)) {
                    image.setImageData(imageFile.getBytes());
                    image.setExtension(getExtension(imageFile));
                    image.setContentType(imageFile.getContentType());
                    imageRepository.save(image);
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
        return image;
    }


    /**
     * Retuns the Extension and extracts the extension
     * from the original filename of an image file.
     * 
     * @param imageFile the image file to be extracted.
     * @return  the extension of the Image File.
     */
    private String getExtension(MultipartFile imageFile) {
        String fileName = imageFile.getOriginalFilename();
        String extension = "";
        if (fileName != null) {
            extension = fileName.split("\\.", 2)[1];
        }
        return extension;
    }


    /**
     * Checks if the extension is valid or not.
     * 
     * @param extension the extension of the image file.
     * @return  true or false, depending on if the extension
     *          is valid or not.
     */
    private boolean isExtenstionValid(String extension) {
        return extension != null && Arrays.asList(FILE_EXTENSIONS).contains(extension);
    }


    /**
     * Checks if the content type is valid or not.
     * 
     * @param contentType   the content type of the image file.
     * @return  true of false, depending on if the content type
     *          is valid or not.
     */
    private boolean isContentTypeValid(String contentType) {
        return contentType != null && Arrays.asList(CONTENT_TYPES).contains(contentType);
    }

    
    /**
     * Checks if the image is valid or not.
     * 
     * @param imageFile the image file to get checked.
     * @return  true or false, depending on if the Image
     *          File is valid or not.
     */
    private boolean isImageValid(MultipartFile imageFile) {
        String extension = getExtension(imageFile);
        String contentType = imageFile.getContentType();
        return isExtenstionValid(extension) && isContentTypeValid(contentType);
    }
}
