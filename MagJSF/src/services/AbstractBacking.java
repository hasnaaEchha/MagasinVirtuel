package services;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class AbstractBacking {
	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	protected void displayInfoMessageToUser(String message) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO,
				message);
		addMessageToJsfContext(facesMessage);
	}

	protected void displayErrorMessageToUser(String message) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR,
				message);
		addMessageToJsfContext(facesMessage);
	}

	private FacesMessage createMessage(Severity severity, String mensagemErro) {
		return new FacesMessage(severity, mensagemErro, mensagemErro);
	}

	private void addMessageToJsfContext(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
