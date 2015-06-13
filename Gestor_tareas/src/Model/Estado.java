package Model;

import java.io.Serializable;

public enum Estado implements Serializable {activo, pausado, terminado, expirado}
/** activo
 *  pasuado
 *  terminado == completado
 *  expirado => fecha actual > fecha final
 */
