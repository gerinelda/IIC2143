package View;

import java.awt.event.ActionEvent;

public interface EmailListener {

    void EnviarEmail(String contenido, String destinatario, String asunto);
}
