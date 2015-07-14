package services;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultUploadedFile;

@FacesValidator(value = "fileValidator")
public class UploadValidator implements Validator {

	private static final String[] ALLowed_Extensions = { "jpg", "jpeg", "gif",
			"png" };
	private static final long MAX_FILE_SIZE = 10485760L; // 10MB.

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		DefaultUploadedFile file = (DefaultUploadedFile) value;
		if (file == null) {
			return;
		}

		if (file.getSize() > MAX_FILE_SIZE) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"File exceeds maximum permitted size of 10MB", null));

		}
		Boolean AllowedExtension = isExtensionAllowed(FilenameUtils
				.getExtension(file.getFileName()));
		/** ------ [ If extesion is not Allowed ] ----- */
		if (!AllowedExtension) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Image Extension not Allowed",
					null));

		}
	}

	private static Boolean isExtensionAllowed(String ImageExt) {

		for (int i = 0; i < ALLowed_Extensions.length; i++) {

			if (ImageExt.toLowerCase().equals(ALLowed_Extensions[i])) {

				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}

}

